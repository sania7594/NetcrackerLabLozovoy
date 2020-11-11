package com.netcracker.repository;

import java.util.Comparator;

/**
 *Interface for sorting
 */
public interface RepositorySort {
    <T> Repository<T> sorted(Repository<T> adapter, int startIndex, int count, Comparator<? super T> comparator);

    default <T> void swap(Repository<T> adapter, int firstIndex, int secondIndex) {
        adapter.swap(firstIndex, secondIndex);
    }
}
