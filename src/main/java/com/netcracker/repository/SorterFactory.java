package com.netcracker.repository;

/**
 * The class sorter factory
 *
 * @author Alex Lozovoy
 */

public class SorterFactory {
    protected static ISorter sorter, bubleSorter;

    private SorterFactory(){}

    public static ISorter getSorter() {
        if (sorter == null)
            sorter = new QuickSorted();

        return sorter;
    }

    public static ISorter getBubleSorter(){
        if (bubleSorter==null)
            bubleSorter=new BubleSorter();

        return bubleSorter;
    }
}

