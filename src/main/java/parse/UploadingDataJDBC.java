package parse;

import contract.*;
import injector.AutoInjectable;
import repository.PostrgresSqlJDBC;
import repository.RepositoryContract;
import validators.Message;
import validators.Status;
import validators.Validator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 Uploading data JDBC postgressSQL
 */


public class UploadingDataJDBC {

    private static Logger logger = Logger.getLogger(UploadingDataJDBC.class.getName());



    private RepositoryContract repositoryContract;

    /*
    * getting a repository from BD
    * @return repository
    * */

    public RepositoryContract readArr() throws SQLException {
        PostrgresSqlJDBC postrgresSqlJDBC=new PostrgresSqlJDBC();
        postrgresSqlJDBC.newConnection();
        ResultSet resultSet=postrgresSqlJDBC.getAllData();

        int lineNumber=1;

        logger.log(Level.INFO, "Data upload has started.");

        while (resultSet.next()){
            Client client=new Client(resultSet.getInt(5),resultSet.getString(6)
                    ,resultSet.getString(7),resultSet.getString(8),resultSet.getInt(9));
            if (resultSet.getFloat(10)!=0.0f & resultSet.getFloat(10)>0.0f){
                Contract contract=new WiredInternetContract(resultSet.getInt(1),
                        resultSet.getLong(2),resultSet.getLong(3),
                        resultSet.getInt(4),client,resultSet.getFloat(10));
                int errorCount = 0;
                for (Message dovalidation : doValidation(contract)) {
                    if (dovalidation.getStatus() == Status.ERROR) {
                        System.out.println(dovalidation.getMessage());
                        errorCount++;
                    } else {

                    }

                }
                if (errorCount == 0) {
                    repositoryContract.add(contract);
                    logger.log(Level.INFO, "Add contract.");
                }
                break;
            }

            if (resultSet.getInt(14)!=0 & resultSet.getInt(14)>0){
                Contract contract=new DigitalTv(resultSet.getInt(1),
                        resultSet.getLong(2),resultSet.getLong(3),
                        resultSet.getInt(4),client,resultSet.getInt(14));
                int errorCount = 0;
                for (Message dovalidation : doValidation(contract)) {
                    if (dovalidation.getStatus() == Status.ERROR) {
                        System.out.println(dovalidation.getMessage());
                        errorCount++;
                    } else {

                    }

                }
                if (errorCount == 0) {
                    repositoryContract.add(contract);
                    logger.log(Level.INFO, "Add contract.");
                }
                break;
            }

            if (resultSet.getInt(11)!=0 &resultSet.getInt(11)>0){
                Contract contract=new MobileContract(resultSet.getInt(1),
                        resultSet.getLong(2),resultSet.getLong(3),
                        resultSet.getInt(4),client,
                        resultSet.getInt(11),resultSet.getInt(12),
                        resultSet.getInt(13));
                int errorCount3 = 0;
                for (Message dovalidation : doValidation(contract)) {
                    if (dovalidation.getStatus() == Status.ERROR) {
                        System.out.println(dovalidation.getMessage());
                        errorCount3++;
                    } else {

                    }

                }
                if (errorCount3 == 0) {
                    repositoryContract.add(contract);
                    logger.log(Level.INFO, "Add contract.");
                }
                break;
            }

        }
        return repositoryContract;
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
