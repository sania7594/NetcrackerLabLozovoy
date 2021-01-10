package com.netcracker.repository;
/*
@author Lozovoy
@version 1.0
class factory sorter

 */

public class SorterFactory {
    protected static ISorter sorter, bubbleSorter;

    private SorterFactory() {}

    /**
     * @return result quick sorter
     */
    public static ISorter getSorter() {
        if (sorter == null)
            sorter = new QuickSorter();

        return sorter;
    }

    /**
     * @return result bubble sorter
     */
    public static ISorter getBubbleSorter() {
        if (bubbleSorter == null)
            bubbleSorter = new BubbleSorter();

        return bubbleSorter;
    }
}
