package contract;

import parse.LoadDataJDBC;
import parse.UploadingDataJDBC;
import repository.PostrgresSqlJDBC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryContract;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestPostgressql {

    public static PostrgresSqlJDBC postrgresSqlJDBC;
    /*
    * Checking the connection with BD
    * */

    @Test
    public void getJdbcConnection() throws SQLException{
        postrgresSqlJDBC=new PostrgresSqlJDBC();
        try(Connection connection=postrgresSqlJDBC.newConnection()){
            assertTrue(connection.isValid(1));
        }
    }
    /*
    * create contract
    * */
    @Test
    public void createContract() throws SQLException {
        postrgresSqlJDBC=new PostrgresSqlJDBC();
        Connection connection=postrgresSqlJDBC.newConnection();
        Client client=new Client(0, "4323", "12082021", "1", 1231);
        DigitalTv digitalTv=new DigitalTv(0, 0L, 9L, 43,
                client, 43);
        MobileContract mobileContract=new MobileContract(1, 1L, 9L, 33,
                client, 43, 12, 12);
        WiredInternetContract wiredInternetContract=new WiredInternetContract(2, 0L, 9L, 43,
                client, 43);
        postrgresSqlJDBC.getContract (digitalTv,client);
        postrgresSqlJDBC.getContract (mobileContract,client);
        postrgresSqlJDBC.getContract (wiredInternetContract,client);
        postrgresSqlJDBC.getConnection().close();
    }
    /*
    * create contract repository
    * */

    @Test
    public void createLoadData() throws IOException, SQLException {
        postrgresSqlJDBC=new PostrgresSqlJDBC();
        RepositoryContract repositoryContract=new RepositoryContract();
        LoadDataJDBC loadDataJDBC=new LoadDataJDBC();
        postrgresSqlJDBC.createRepositoryContract(loadDataJDBC.readArr(repositoryContract));
    }
    /*
    * select repository contract BD
    * */

    @Test
    public void receivingLoadData() throws SQLException {
        UploadingDataJDBC uploadingDataJDBC=new UploadingDataJDBC();
        RepositoryContract repositoryContract=uploadingDataJDBC.readArr();
    }

    @Before
    public void init() throws SQLException{
        postrgresSqlJDBC=new PostrgresSqlJDBC();
        Connection connection=postrgresSqlJDBC.newConnection();

    }


    @After
    public void close() throws SQLException{
        postrgresSqlJDBC.getConnection().close();
    }

}
