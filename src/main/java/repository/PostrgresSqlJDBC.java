package repository;

import contract.*;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Class postgresSQL jdbc
* */

public class PostrgresSqlJDBC {

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PostrgresSqlJDBC.logger = logger;
    }

    Connection connection;

    private static Logger logger = Logger.getLogger(PostrgresSqlJDBC.class.getName());


    /* add connection
       @return connection
    * */
    public Connection newConnection() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            logger.info("Driver type postgres");

        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING,"PostgreSQL JDBC Driver is not found.");
            e.printStackTrace();
            return null;
        }

        String url = "jdbc:postgresql://localhost:15432/netcracker";
        String user = "postgres";
        String pass = "28y75w";
        this.connection = DriverManager.getConnection(url, user, pass);
        return connection;

    }
    /* receiving data
    * */
    public ResultSet getAllData () throws SQLException{
        Statement statement=this.connection.createStatement();
        String query="select * from client_contract";
        ResultSet resultSet=statement.executeQuery(query);
        return resultSet;
    }

    /* add contract
    * */
    public void getContract (Contract contract,Client client) throws SQLException{
        Statement statement=this.connection.createStatement();
        if (contract instanceof DigitalTv){
            statement.executeUpdate("INSERT INTO client_contract (id,start_date,end_date,number_contract,client_id,full_name,birthday,sex,serial_number,channel_package) " +
                    "VALUES("+contract.getId()+","+contract.getStartOfContract()+","+contract.getEndOfContract()+","+contract.getNumberContract()+","+client.getId()+","+client.getFullName()+
                    ","+client.getBirthday()+","+client.getSex()+","+client.getSeriesNumber()+","+((DigitalTv) contract).getChannelPackage()+")");
        }
        if (contract instanceof MobileContract){
            statement.executeUpdate("INSERT INTO client_contract (id,start_date,end_date,number_contract,client_id,full_name,birthday,sex,serial_number,minutes,messages,trafic) " +
                    "VALUES("+contract.getId()+","+contract.getStartOfContract()+","+contract.getEndOfContract()+","+contract.getNumberContract()+","+client.getId()+","+client.getFullName()+
                    ","+client.getBirthday()+","+client.getSex()+","+client.getSeriesNumber()+","+((MobileContract) contract).getMinutes()+","+((MobileContract) contract).getMessages()+","+((MobileContract) contract).getMessages()+")");
        }

        if (contract instanceof WiredInternetContract){
            statement.executeUpdate("INSERT INTO client_contract (id,start_date,end_date,number_contract,client_id,full_name,birthday,sex,serial_number,speed) " +
                    "VALUES("+contract.getId()+","+contract.getStartOfContract()+","+contract.getEndOfContract()+","+contract.getNumberContract()+","+client.getId()+","+client.getFullName()+
                    ","+client.getBirthday()+","+client.getSex()+","+client.getSeriesNumber()+","+((WiredInternetContract) contract).getSpeed()+")");
        }
    }
    /*
    * adding a contract from the repository
    * */
    public void createRepositoryContract(RepositoryContract repositoryContract) throws SQLException {
        newConnection();
        for (int i=0; i<repositoryContract.size();i++){
            Contract contract=repositoryContract.getById(i+1);
            if (contract instanceof WiredInternetContract){
                PreparedStatement preparedStatementRepository=connection.prepareStatement("INSERT INTO client_contract " +
                        "(id,start_date,end_date,number_contract,client_id,full_name,birthday,sex,serial_number,speed) VALUES (?,?,?,?,?,?,?,?,?,?);");
               Client client=contract.getClient();
               preparedStatementRepository.setInt(1,contract.getId());
               preparedStatementRepository.setLong(2,contract.getStartOfContract());
               preparedStatementRepository.setLong(3,contract.getEndOfContract());
               preparedStatementRepository.setInt(4,contract.getNumberContract());
               preparedStatementRepository.setInt(5,client.getId());
               preparedStatementRepository.setString(6,client.getFullName());
               preparedStatementRepository.setString(7,client.getBirthday());
               preparedStatementRepository.setString(8,client.getSex());
               preparedStatementRepository.setInt(9,client.getSeriesNumber());
               preparedStatementRepository.setFloat(10,((WiredInternetContract) contract).getSpeed());
               preparedStatementRepository.executeUpdate();
            }
            if (contract instanceof DigitalTv){
                PreparedStatement preparedStatementRepository=connection.prepareStatement("INSERT INTO client_contract " +
                        "(id,start_date,end_date,number_contract,client_id,full_name,birthday,sex,serial_number,channel_package) VALUES (?,?,?,?,?,?,?,?,?,?);");
                Client client=contract.getClient();
                preparedStatementRepository.setInt(1,contract.getId());
                preparedStatementRepository.setLong(2,contract.getStartOfContract());
                preparedStatementRepository.setLong(3,contract.getEndOfContract());
                preparedStatementRepository.setInt(4,contract.getNumberContract());
                preparedStatementRepository.setInt(5,client.getId());
                preparedStatementRepository.setString(6,client.getFullName());
                preparedStatementRepository.setString(7,client.getBirthday());
                preparedStatementRepository.setString(8,client.getSex());
                preparedStatementRepository.setInt(9,client.getSeriesNumber());
                preparedStatementRepository.setFloat(10,((DigitalTv) contract).getChannelPackage());
                preparedStatementRepository.executeUpdate();
            }
            if (contract instanceof MobileContract){
                PreparedStatement preparedStatementRepository=connection.prepareStatement("INSERT INTO client_contract " +
                        "(INSERT INTO client_contract (id,start_date,end_date,number_contract,client_id,full_name,birthday,sex,serial_number,minutes,messages,trafic) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
                Client client=contract.getClient();
                preparedStatementRepository.setInt(1,contract.getId());
                preparedStatementRepository.setLong(2,contract.getStartOfContract());
                preparedStatementRepository.setLong(3,contract.getEndOfContract());
                preparedStatementRepository.setInt(4,contract.getNumberContract());
                preparedStatementRepository.setInt(5,client.getId());
                preparedStatementRepository.setString(6,client.getFullName());
                preparedStatementRepository.setString(7,client.getBirthday());
                preparedStatementRepository.setString(8,client.getSex());
                preparedStatementRepository.setInt(9,client.getSeriesNumber());
                preparedStatementRepository.setInt(10,((MobileContract) contract).getMinutes());
                preparedStatementRepository.setInt(11,((MobileContract) contract).getMessages());
                preparedStatementRepository.setInt(12,((MobileContract) contract).getMessages());
                preparedStatementRepository.executeUpdate();
            }
        }
    }



    public void closeConnection () throws SQLException{
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
