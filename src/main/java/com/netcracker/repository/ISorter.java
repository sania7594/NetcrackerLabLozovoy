package com.netcracker.repository;

import java.util.Comparator;

/*
@author Lozovoy
@version 1.0
interface sorted
 */

public interface ISorter {
    /**
     * @param adapter adapter
     * @param startIndex start index
     * @param count count
     * @param comparator comparator
     * @param <T> T
     * @return result sorted
     */
    <T> Repository<T> sorted(
            Repository<T> adapter, int startIndex, int count, Comparator<? super T> comparator
    );

    default <T> void swap(Repository<T> adapter, int firstIndex, int secondIndex) {
        adapter.swap(firstIndex, secondIndex);
    }
}
