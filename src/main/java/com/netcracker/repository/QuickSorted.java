package com.netcracker.repository;

import com.netcracker.contract.Contract;

import java.util.Comparator;

/**
 * The class quick sorter
 *
 * @author Alex Lozovoy
 */

public class QuickSorted implements ISorter {
    /**
     * @param sorter repository sorter
     * @param startIndex start index
     * @param count count
     * @param comparator comparator
     * @param <T> parameter
     * @return result sorter
     */
    public <T> Repository sorted(Repository sorter, int startIndex, int count, Comparator<? super Contract> comparator){
        quickSort(sorter, startIndex, count-1, comparator);

        return sorter;
    }

    /**
     * @param sorter repository sorter
     * @param startIndex start index
     * @param endIndex end index
     * @param comparator comparator
     * @param <T> parameter
     */
    private <T> void quickSort(Repository sorter, int startIndex, int endIndex, Comparator<? super Contract> comparator) {
        if (startIndex < endIndex) {
            int partitionIndex = partition(sorter, startIndex, endIndex, comparator);

            quickSort(sorter, startIndex, partitionIndex - 1, comparator);
            quickSort(sorter, partitionIndex + 1, endIndex, comparator);
        }
    }

    /**
     * @param sorter repository sorter
     * @param startIndex start index
     * @param endIndex end index
     * @param comparator comparator
     * @param <T> parameter
     */
    private <T> int partition(Repository sorter, int startIndex, int endIndex, Comparator<? super Contract> comparator) {
        int i = startIndex - 1;

        for (int j = startIndex; j < endIndex; j++)
            if (comparator.compare(sorter.getByIndex(j), sorter.getByIndex(endIndex)) < 0)
                swap(sorter, ++i, j);

        swap(sorter, ++i, endIndex);

        return i;
    }

}
