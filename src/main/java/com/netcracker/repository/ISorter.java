package com.netcracker.repository;

import com.netcracker.contract.Contract;
/**
 * The class buble sorter
 *
 * @author Alex Lozovoy
 */

import java.util.Comparator;

public interface ISorter {
    <T> Repository sorted(Repository sorter, int startIndex, int count, Comparator<? super Contract> comparator);

    default <T> void swap(Repository sorter, int firstIndex, int secondIndex) {
        sorter.swap(firstIndex, secondIndex);
    }
}


