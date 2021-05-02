package parse;

import contract.*;
import repository.RepositoryContract;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

/**
 * class for creating and parsing xml file
 */
public class ParserXML {

    private static Logger logger = Logger.getLogger(ParserXML.class.getName());

    /**
     * Creating an xml file for an existing repository
     * @param repositoryContract  converted to xml file
     */
    public void createXML (RepositoryContract repositoryContract){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RepositoryContract.class,
                    Contract.class, WiredInternetContract.class, MobileContract.class,
                    DigitalTv.class, Client.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            logger.info("Creating xml-file.");
            marshaller.marshal(repositoryContract, new File("repository.xml"));
            logger.info("Xml-file created.");
        } catch (JAXBException e) {
            logger.warning("Xml creation error");
        }

    }
    /**
     * parse existing xml-file
     * @param path to file in root directory
     * @return repository contract
     */

    public RepositoryContract readXML (String path) throws FileNotFoundException, JAXBException {
        RepositoryContract repositoryContract = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RepositoryContract.class,
                    Contract.class, WiredInternetContract.class, MobileContract.class,
                    DigitalTv.class, Client.class);
            FileReader xmlFile = new FileReader(path);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            logger.info("Parsing xml-file");
            repositoryContract = (RepositoryContract) unmarshaller.unmarshal(xmlFile);
            if (repositoryContract !=  null)
                logger.info("xml-file parsed successfully.");
            else
                logger.info("xml-file created nullable repository contract.");
        } catch (JAXBException e) {
            logger.warning("Throws   parsing xml");
        }
        return repositoryContract;
    }

}
