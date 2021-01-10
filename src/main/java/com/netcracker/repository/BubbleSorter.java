package com.netcracker.repository;

import java.util.Comparator;

/*
@author Lozovoy
@version 1.0
class bubble sorter

 */


public class BubbleSorter implements ISorter {
    /**
     * @param adapter adapter
     * @param startIndex start index
     * @param count count
     * @param comparator comparator
     * @param <T> T
     * @return result sorted
     */
    @Override
    public <T> Repository<T> sorted(
            Repository<T> adapter, int startIndex, int count, Comparator<? super T> comparator
    ) {
        Repository<T> sorted = adapter.clone();

        for (int i = startIndex; i < count; i++)
            for (int j = startIndex + 1; j < (count - i); j++)
                if (comparator.compare(sorted.getByIndex(j - 1), sorted.getByIndex(j)) > 0)
                    swap(sorted, j - 1, j);

        return sorted;
    }
}
