package contract;

import injector.InjectionException;
import injector.Injector;
import parse.CSVParser;
import org.junit.Assert;
import org.junit.Test;
import repository.RepositoryContract;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class TestContract {
    /**
     * Adding a contract
     */
    @Test
    public void TestAdd() {
        RepositoryContract repositorys = new RepositoryContract();
        repositorys.add(new DigitalTv(0, 0L, 9L, 43,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));
        repositorys.add(new MobileContract(1, 1L, 9L, 33,
                new Client(1, "fsdfsd", "11.12.1986", "m", 1271), 43, 12, 12));
        repositorys.add(new WiredInternetContract(2, 0L, 9L, 43,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));

    }

    /**
     * The removal of the contract
     */
    @Test
    public void testRemoveById() {
        RepositoryContract repositorys = new RepositoryContract();
        repositorys.add(new DigitalTv(4, 0L, 9L, 43,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));
        repositorys.add(new MobileContract(5, 1L, 9L, 33,
                new Client(1, "fsdfsd", "11.12.1986", "m", 1271), 43, 12, 12));
        repositorys.add(new WiredInternetContract(6, 0L, 9L, 43,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));
        repositorys.removeById(4);
        assertNull(repositorys.getById(4));
        repositorys.removeById(5);
        assertNull(repositorys.getById(5));
        repositorys.removeById(6);
        assertNull(repositorys.getById(6));
    }

    @Test
    public void testFilter() {
        RepositoryContract repositoryContract = new RepositoryContract();
        fill(repositoryContract);
        RepositoryContract result = repositoryContract.filter(contract -> contract.getId() == 4);
        RepositoryContract result2 = repositoryContract.filter(contract -> contract.getStartOfContract() == 0L);
        assertNotNull(result.getById(4));
        assertEquals(1, result.size());
        assertNotNull(result2.getById(4));
        assertEquals(2, result2.size());
        assertNotNull(result2.getById(6));
    }

    /**
     * test quick sort
     */
    /*@Test
    public void testQuickSort(){
        RepositoryContract repositoryContract = new RepositoryContract();
        fill(repositoryContract);
        RepositoryContract sorted= repositoryContract.sorted(SorterFactory.getBubleSorter(),Comparator.comparingLong(Contract::getStartOfContract));

        assertEquals(6, (int) sorted.getByIndex(1).getId());
        assertEquals(4, (int) sorted.getByIndex(0).getId());
    }*/

    /**
     * test bubble sort
     */
    /*@Test
    public void testBubbleSort(){
        RepositoryContract repositoryContract =new RepositoryContract();
        fill(repositoryContract);

        RepositoryContract sorted= repositoryContract.sorted(SorterFactory.getBubleSorter(),Comparator.comparingInt(Contract::getId));

        assertEquals(5, (int) sorted.getByIndex(1).getId());
        assertEquals(4, (int) sorted.getByIndex(0).getId());
    }*/
    @Test
    /**
     * Parsing and adding data
     */
    public void testParserCSV() throws IOException {
        RepositoryContract repositoryContract =new RepositoryContract();
        CSVParser.readArr(repositoryContract);
        assertNotNull(repositoryContract.getById(2));
        assertNull(repositoryContract.getById(0));
        assertEquals(1, repositoryContract.size());

    }


    /**
     * @param repositoryContract repository
     * adding data to the repository
     */
    public void fill(RepositoryContract repositoryContract) {
        repositoryContract.add(new DigitalTv(4, 0L, 9L, 4,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));
        repositoryContract.add(new MobileContract(5, 1L, 9L, 33,
                new Client(1, "fsdfsd", "11.12.1986", "m", 1271), 43, 12, 12));
        repositoryContract.add(new WiredInternetContract(6, 0L, 9L, 43,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));
    }

    @Test
    public void testChet(){
        RepositoryContract repositoryContract =new RepositoryContract();
        fill(repositoryContract);
        assertEquals(0,(repositoryContract.getByIndex(0).getId() % 2));
        assertNotEquals(0,(repositoryContract.getByIndex(1).getId() % 2));
    }

    @Test
    public void inject() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, InjectionException {
        RepositoryContract repositoryContract =new RepositoryContract();
        fill(repositoryContract);
        Injector.inject(repositoryContract);
        Assert.assertNotNull(repositoryContract.getSorter());


        CSVParser parser=new CSVParser();
        Injector.inject(parser);
        Assert.assertNotNull(parser.getValidators());
    }


}

