package com.netcracker.contract;

import com.netcracker.parse.CSVParser;
import com.netcracker.repository.Repository;
import com.netcracker.repository.SorterFactory;
import com.netcracker.validators.Message;
import com.netcracker.validators.Status;
import org.junit.Test;

import java.io.IOException;
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
        Repository repositorys = new Repository();
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
        Repository repositorys = new Repository();
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
        Repository repository = new Repository();
        fill(repository);
        Repository result = repository.filter(contract -> contract.getId() == 4);
        Repository result2 = repository.filter(contract -> contract.getStartOfContract() == 0L);
        assertNotNull(result.getById(4));
        assertEquals(1, result.size());
        assertNotNull(result2.getById(4));
        assertEquals(2, result2.size());
        assertNotNull(result2.getById(6));
    }

    /**
     * test quick sort
     */
    @Test
    public void testQuickSort(){
        Repository repository = new Repository();
        fill(repository);
        Repository sorted=repository.sorted(SorterFactory.getBubleSorter(),Comparator.comparingLong(Contract::getStartOfContract));

        assertEquals(6, (int) sorted.getByIndex(1).getId());
        assertEquals(4, (int) sorted.getByIndex(0).getId());
    }

    /**
     * test bubble sort
     */
    @Test
    public void testBubbleSort(){
        Repository repository=new Repository();
        fill(repository);

        Repository sorted=repository.sorted(SorterFactory.getBubleSorter(),Comparator.comparingInt(Contract::getId));

        assertEquals(5, (int) sorted.getByIndex(1).getId());
        assertEquals(4, (int) sorted.getByIndex(0).getId());
    }
    @Test
    /**
     * Parsing and adding data
     */
    public void testParserCSV() throws IOException {
        Repository repository=new Repository();
        CSVParser.readArr(repository);
        assertNotNull(repository.getById(2));
        assertNull(repository.getById(0));
        assertEquals(1,repository.size());

    }


    /**
     * @param repository repository
     * adding data to the repository
     */
    public void fill(Repository repository) {
        repository.add(new DigitalTv(4, 0L, 9L, 4,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));
        repository.add(new MobileContract(5, 1L, 9L, 33,
                new Client(1, "fsdfsd", "11.12.1986", "m", 1271), 43, 12, 12));
        repository.add(new WiredInternetContract(6, 0L, 9L, 43,
                new Client(0, "adsdsd", "10.12.1984", "m", 1231), 43));
    }

    @Test
    public void testChet(){
        Repository repository=new Repository();
        fill(repository);
        assertEquals(0,(repository.getByIndex(0).getId() % 2));
        assertNotEquals(0,(repository.getByIndex(1).getId() % 2));
    }

}

