package repository;

import contract.Contract;

import java.util.Comparator;

public interface ISorter {
    <T> RepositoryContract sorted(RepositoryContract sorter, int startIndex, int count, Comparator<? super Contract> comparator);

    default <T> void swap(RepositoryContract sorter, int firstIndex, int secondIndex) {
        sorter.swap(firstIndex, secondIndex);
    }
}


