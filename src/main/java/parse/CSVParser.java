package parse;

import contract.*;
import contract.Contract;
import contract.DigitalTv;
import injector.AutoInjectable;
import repository.RepositoryContract;
import validators.Message;
import validators.Status;
import validators.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for parsing a csv file and transmitting data to the repositor.
 *
 * @author Alex Lozovoy
 */

public class CSVParser {
    private final static HashMap<Integer, Client> clients = new HashMap<>();

    /**
     * @param repository repository
     * @throws IOException exception
     */
    public static void readArr(RepositoryContract repository) throws IOException {
        File file = new File("src/main/java/parse/loader.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        int j = 0;
        String line = "";
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            if (j == 0) {
                j++;
                continue;
            }
            String[] sources = line.split(",");
            for (int i = 0; i < sources.length; i++) {
                sources[i] = sources[i].trim();
            }

            String[] extra = sources[sources.length - 1].split(";");
            Integer idClient = Integer.parseInt(sources[4]);
            Client clientNew = checkIdClient(idClient, sources);
            switch (sources[9]) {
                case "tv":
                    contract.Contract contract = new DigitalTv((Integer.parseInt(sources[0])),
                            Long.parseLong(sources[1]), Long.parseLong(sources[2]), Integer.parseInt(sources[3]),
                            clientNew, Integer.parseInt(extra[0]));
                    int errorCount = 0;
                    for (Message dovalidation : doValidation(contract)) {
                        if (dovalidation.getStatus() == Status.ERROR) {
                            //new Message("Fatal error",Status.ERROR);
                            System.out.println(dovalidation.getMessage());
                            errorCount++;
                        } else {

                        }

                    }
                    if (errorCount == 0) {
                        repository.add(contract);
                    }
                    break;
                case "internet":
                    Contract contractInternet = new WiredInternetContract(Integer.parseInt(sources[0]),
                            Long.parseLong(sources[1]), Long.parseLong(sources[2]), Integer.parseInt(sources[3]),
                            clientNew, Float.parseFloat(extra[0]));
                    int errorCount2 = 0;
                    for (Message dovalidation : doValidation(contractInternet)) {
                        if (dovalidation.getStatus() == Status.ERROR) {
                            //new Message("Fatal error",Status.ERROR);
                            System.out.println(dovalidation.getMessage());
                            errorCount2++;
                        } else {

                        }

                    }
                    if (errorCount2 == 0) {
                        repository.add(contractInternet);
                    }
                    break;
                case "mobile":
                    Contract contractMobile = new MobileContract(Integer.parseInt(sources[0]),
                            Long.parseLong(sources[1]), Long.parseLong(sources[2]), Integer.parseInt(sources[3]),
                            clientNew, Integer.parseInt(extra[0]), Integer.parseInt(extra[1]),
                            Integer.parseInt(extra[2]));
                    int errorCount3 = 0;
                    for (Message dovalidation : doValidation(contractMobile)) {
                        if (dovalidation.getStatus() == Status.ERROR) {
                            System.out.println(dovalidation.getMessage());
                            errorCount3++;
                        } else {

                        }

                    }
                    if (errorCount3 == 0) {
                        repository.add(contractMobile);
                    }
                    break;
            }

        }
    }

    /**
     * @param idClient id client
     * @param sources  sorces
     * @return client new and 0
     */
    public static Client checkIdClient(Integer idClient, String[] sources) {
        Client client = clients.get(idClient);
        if (client != null) {
            return client;
        } else {
            Client clientNew = new Client(Integer.parseInt(sources[4]), sources[5], sources[6], sources[7],
                    Integer.parseInt(sources[8]));
            clients.put(clientNew.getId(), clientNew);
            return clientNew;
        }
    }

    public static List<Validator> getValidators() {
        return validators;
    }

    @AutoInjectable
    private static List<Validator> validators = new ArrayList<>();

    static {
        validators.add(new Validator<Contract>(0, Contract::getId, (e, a) -> e == a % 2));
        validators.add(new Validator<Contract>("w", contract -> contract.getClient().getSex(), String::equals));
        //validators.add(new ValidatorParity());
    }

    private static List<Message> doValidation(Contract contract) {
        return validators.stream().map(v -> v.validate(contract)).collect(Collectors.toList());
    }
}