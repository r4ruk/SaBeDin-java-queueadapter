import service_example.adapters.CommandHandler;
import service_example.adapters.PersistenceImpl;
import service_example.adapters.QueryHandler;

import sabedin.QueueManager;
import service_example.use_case.IPatientService;
import service_example.use_case.IRepository;
import service_example.use_case.PatientService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        IRepository repo = new PersistenceImpl();
        IPatientService patientService = new PatientService(repo);


        QueryHandler queryHandler = new QueryHandler(patientService);
        CommandHandler commandHandler = new CommandHandler(patientService);


        var adapter = new QueueManager("patient", queryHandler, commandHandler);

        adapter.Listen();
    }
}