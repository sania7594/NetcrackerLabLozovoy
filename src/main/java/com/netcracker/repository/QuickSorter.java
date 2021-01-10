package com.netcracker.repository;

import java.util.Comparator;

/*
@author Lozovoy
@version 1.0
class quick sorter

 */

public class QuickSorter implements ISorter {
    @Override
    public <T> Repository<T> sorted(
            Repository<T> adapter, int startIndex, int count, Comparator<? super T> comparator
    ) {
        Repository<T> sorted = adapter.clone();

        quickSort(sorted, startIndex, count - 1, comparator);

        return sorted;
    }

    private <T> void quickSort(
            Repository<T> adapter, int startIndex, int endIndex, Comparator<? super T> comparator
    ) {
        if (startIndex < endIndex) {
            int partitionIndex = partition(adapter, startIndex, endIndex, comparator);

            quickSort(adapter, startIndex, partitionIndex - 1, comparator);
            quickSort(adapter, partitionIndex + 1, endIndex, comparator);
        }
    }

    private <T> int partition(
            Repository<T> adapter, int startIndex, int endIndex, Comparator<? super T> comparator
    ) {
        int i = startIndex - 1;

        for (int j = startIndex; j < endIndex; j++)
            if (comparator.compare(adapter.getByIndex(j), adapter.getByIndex(endIndex)) < 0)
                swap(adapter, ++i, j);

        swap(adapter, ++i, endIndex);

        return i;
    }
}