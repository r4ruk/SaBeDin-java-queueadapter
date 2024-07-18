package service_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceDefinitionTest {

    private static final String REGEX = "case\\s\"([^\"]+)\"";
    public static final String SRC_MAIN_JAVA = File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;

    @Test
    void TestQueryServiceDefinition() throws InterruptedException, IOException {
        String currentDirectory = System.getProperty("user.dir");
        ServiceDefinition serviceDefinition = getServiceDefinition(currentDirectory);
        String queryHandlerPath = currentDirectory + SRC_MAIN_JAVA + serviceDefinition.getName() + File.separator + "adapters" + File.separator + "QueryHandler.java";
        List<String> queryMethodsImplemented = new ArrayList<>();
        Matcher matcher = getMatcher(queryHandlerPath);
        while (matcher.find()) {
            queryMethodsImplemented.add(matcher.group(1));
        }

        assertTrue(queryMethodsImplemented.containsAll(
                serviceDefinition.getQueryMethods().stream()
                .map(MethodDefinition::getFunctionName)
                        .toList()));
    }

    @Test
    void TestCommandServiceDefinition() throws InterruptedException, IOException {
        String currentDirectory = System.getProperty("user.dir");
        ServiceDefinition serviceDefinition = getServiceDefinition(currentDirectory);
        String commandHandlerPath = currentDirectory + SRC_MAIN_JAVA + serviceDefinition.getName()  + File.separator + "adapters" + File.separator + "CommandHandler.java";
        List<String> commandMethodsImplemented = new ArrayList<>();

        Matcher matcher = getMatcher(commandHandlerPath);
        while (matcher.find()) {
            commandMethodsImplemented.add(matcher.group(1));
        }

        assertTrue(commandMethodsImplemented.containsAll(
                serviceDefinition.getCommandMethods().stream()
                        .map(MethodDefinition::getFunctionName)
                        .toList()));
    }

    private static @NotNull Matcher getMatcher(String handlerPath) throws IOException {
        String queryContent = Files.readString(Paths.get(handlerPath));
        Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
        return pattern.matcher(queryContent);
    }

    private static ServiceDefinition getServiceDefinition(String currentDirectory) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();
        String definitionInitializerPath = currentDirectory + File.separator + "src" + File.separator + "test" + File.separator + "helpers" + File.separator + "SaBeDin_definition_analyzer.exe";
        builder.command(definitionInitializerPath);
        builder.redirectErrorStream(true);
        boolean firstLine = true;
        Process process = builder.start();
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!firstLine){
                    result.append(line);
                } else {
                    firstLine = false;
                }
            }
        }
        process.waitFor();
        System.out.println(result);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result.toString(), ServiceDefinition.class);
    }
}
