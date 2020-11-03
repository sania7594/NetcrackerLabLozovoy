package com.netcracker.contract;
import com.netcracker.repository.Repository;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

/**
 * Unit test for simple App.
 */
public class TestContract
{
    /**
     * Adding a contract
     */
    @Test
    public void TestAdd()
    {
        Repository repositorys=new Repository();
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
}
