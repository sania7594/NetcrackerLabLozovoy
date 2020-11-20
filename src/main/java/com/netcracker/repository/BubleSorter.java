package com.netcracker.repository;

import com.netcracker.contract.Contract;

import java.util.Comparator;

/**
 * The class buble sorter
 *
 * @author Alex Lozovoy
 */

public class BubleSorter implements ISorter {
    /**
     * @param sorter sorter
     * @param startIndex startIndex
     * @param count count
     * @param comparator comparator
     * @return result sorter
     */
    //@Override
    public <T> Repository sorted(Repository sorter, int startIndex, int count, Comparator<? super Contract> comparator) {


        for (int i = startIndex; i < count; i++)
            for (int j = startIndex + 1; j < (count - i); j++)
                if (comparator.compare(sorter.getByIndex(j - 1), sorter.getByIndex(j)) > 0)
                    swap(sorter, j - 1, j);

        return sorter;
    }
}

