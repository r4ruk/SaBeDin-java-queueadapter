package service_example.adapters;

import sabedin.ICommandHandler;
import sabedin.RequestPostBody;
import service_example.use_case.IPatientService;

public class CommandHandler implements ICommandHandler {
    final IPatientService patientService;
    public CommandHandler(IPatientService patientService){
        this.patientService = patientService;
    }
    @Override
    public void handle(RequestPostBody body) {
        switch (body.getMethod()) {
            case "create":
                patientService.create(body.getObject());
            case "delete":
                patientService.delete(body.getObject());
            default:
        }
    }
}
