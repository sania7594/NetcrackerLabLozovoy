package com.netcracker.repository;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/*
@author Lozovoy
@version 2.0
class repository

 */

public class Repository<T> {
    protected static final int EXTENSION_SIZE = 10;

    protected int size;
    protected Object[] data;

    public Repository() {
        this.size = 0;
        this.data = new Object[size];
    }

    public Repository(Repository<T> anotherAdapter) {
        this.size = anotherAdapter.size;
        this.data = anotherAdapter.data.clone();
    }

    /**
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * @param element element
     */
    public void add(T element) {
        if (isFull())
            expand();

        data[size++] = element;
    }

    /**
     * @return result checks
     */
    protected boolean isFull() {
        return size >= data.length;
    }

    protected void expand() {
        expand(EXTENSION_SIZE);
    }

    /**
     * @param extensionSize extension size
     */
    protected void expand(int extensionSize) {
        Object[] tmp = data.clone();
        data = new Object[tmp.length + extensionSize];
        System.arraycopy(tmp, 0, data, 0, size);
    }

    /**
     * @param element element
     * @param index index
     */
    public void insert(T element, int index) {
        if (index == size) {
            add(element);
            return;
        }

        checkBounds(index);

        size++;
        incSizeBetween(index);
        data[index] = element;
    }

    /**
     * @param index index
     */
    protected void checkBounds(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * @param index index
     */
    protected void incSizeBetween(int index) {
        expand(1);
        System.arraycopy(data, index, data, index + 1, size - index);
    }

    /**
     * @param index index
     * @return
     */
    @SuppressWarnings("unchecked")
    public T removeAt(int index) {
        checkBounds(index);

        size--;
        Object element = data[index];
        decSizeBetween(index);
        data[size] = null;

        return (T) element;
    }

    /**
     * @param index index
     */
    protected void decSizeBetween(int index) {
        System.arraycopy(data, index + 1, data, index, size - index);
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++)
            if (data[i].equals(element))
                return i;

        return -1;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @SuppressWarnings("unchecked")
    public T getByIndex(int index) {
        checkBounds(index);

        return (T) data[index];
    }

    /**
     * @param predicate predicate
     * @return result search
     */
    public Repository<T> filter(Predicate<? super T> predicate) {
        Repository<T> filtered = new Repository<>();

        for (int i = 0; i < size; i++) {
            T element = getByIndex(i);
            if (predicate.test(element))
                filtered.add(element);
        }

        return filtered;
    }

    /**
     * @param sorter sorter
     * @param comparator comparator
     * @return result sorter
     */
    public Repository<T> sorted(ISorter sorter, Comparator<? super T> comparator) {
        return sorter.sorted(this, 0, size, comparator);
    }

    public <E> Repository<E> map(Function<? super T, E> mapper) {
        Repository<E> mapped = new Repository<>();

        for (int i = 0; i < size; i++)
            mapped.add(mapper.apply(getByIndex(i)));

        return mapped;
    }

    /**
     * @param firstIndex first index
     * @param secondIndex second index
     */
    public void swap(int firstIndex, int secondIndex) {
        checkBounds(firstIndex);
        checkBounds(secondIndex);

        Object temp = data[firstIndex];
        data[firstIndex] = data[secondIndex];
        data[secondIndex] = temp;
    }

    @Override
    public Repository<T> clone() {
        try {
            super.clone();
        } catch (Exception ignored) {
        }

        return new Repository<>(this);
    }
}