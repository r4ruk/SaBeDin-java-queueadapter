package service_test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;
import com.tngtech.archunit.library.Architectures;

public class ArchitectureTest {

    public static void main(String[] args) {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("service_example");
        evaluateAndPrintViolations(layeredArchitectureRule, importedClasses);
    }



    @ArchTest
    public static final ArchRule layeredArchitectureRule = Architectures.layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage("service_example..")
            .layer("Adapters").definedBy("service_example.adapters..")
            .layer("UseCase").definedBy("service_example.use_case..")
            .layer("Domain").definedBy("service_example.domain..")

            // Define allowed access rules
            .whereLayer("Adapters").mayOnlyBeAccessedByLayers("Adapters")
            .whereLayer("UseCase").mayOnlyBeAccessedByLayers("UseCase", "Adapters")
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Domain", "UseCase", "Adapters");


    private static void evaluateAndPrintViolations(ArchRule rule, JavaClasses classes) {
        EvaluationResult result = rule.evaluate(classes);
        if (result.hasViolation()) {
            result.getFailureReport().getDetails().forEach(System.out::println);
            layeredArchitectureRule.check(classes);
        }
    }
}


