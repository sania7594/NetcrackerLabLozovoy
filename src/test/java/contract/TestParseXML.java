package contract;

import org.junit.Test;
import parse.CSVParser;
import parse.ParserXML;
import repository.RepositoryContract;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/*
* Test parser xml
* */

import static org.junit.Assert.assertNull;

public class TestParseXML {
    private ParserXML parserXML = new ParserXML();
    private static Logger logger = Logger.getLogger(ParserXML.class.getName());


    /*
    * test create xml
    * */
    @Test
    public void createXML() throws JAXBException {
        RepositoryContract repositorys = new RepositoryContract();
        repositorys.add(new DigitalTv(0, 0L, 9L, 43,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));
        repositorys.add(new MobileContract(1, 1L, 9L, 33,
                new Client(1, "fsdfsd", "11.12.1986", "m", 1271), 43, 12, 12));
        repositorys.add(new WiredInternetContract(2, 0L, 9L, 43,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));

        parserXML.createXML(repositorys);
    }

    /*
    * test read xml
    * */

    @Test
    public void readXML() throws FileNotFoundException, JAXBException {

        RepositoryContract repositoryContract = parserXML.readXML("repository.xml");
        assertNotNull(repositoryContract);
        logger.info("Parsing results: ");
        logger.info(repositoryContract.toString());
    }

}
