package com.netcracker.repository;
import com.netcracker.contract.Contract;

import java.util.Comparator;

public class Repository<T> {
    /**
     *declaring an array of objects
     *The purpose of the size
     * Setting the zoom step(EXTENSION_SIZE)
     */
    protected static final int EXTENSION_SIZE = 10;
    protected int size;
    protected Object[] data;

    /**
     *the constructor declaration
     *specifying the size
     *creating an array of objects of the specified size
     */
    public Repository(){
        this.size=0;
        this.data=new Object[size];
    }

    /**
     * creating another repository
     * @param anotherReposytory repository
     */
    public Repository(Repository<T> anotherReposytory){
        this.size=anotherReposytory.size;
        this.data=anotherReposytory.data.clone();
    }


    public void add(T element){
        if (isFull()){
            expand();
        }
        data[size++]=element;

    }

    private boolean isFull()
    {
        return size>=data.length;
    }

    /**
     * intermediate extension method
     */
    private void expand(){
        expand(EXTENSION_SIZE);
    }

    /**
     * Extending an array
     * @param extensionSize extension size
     */
    private void expand(int extensionSize){
        Object[] tmp=data.clone();
        data = new Object[tmp.length + extensionSize];
        System.arraycopy(tmp, 0, data, 0, size);
    }

    /**
     * Inserting an element
     * @param element element
     * @param index index
     */
    public void insert(T element, int index){
        if (index==size){
            add(element);
            return;
        }
        size++;
        incSizeBetween(index);
        data[index] = element;
    }

    /**
     * @param index index
     */
    protected void incSizeBetween(int index) {
        expand(1);
        System.arraycopy(data, index, data, index + 1, size - index);
    }

    /**
     * Delete an object by index
     * @param index the index of the element
     * @return element
     */
    public T removeAt(int index) {
        size--;
        Object element = data[index];
        decSizeBetween(index);
        data[size] = null;

        return (T) element;
    }

    /**
     * @param index the index of the element
     */
    protected void decSizeBetween(int index) {
        System.arraycopy(data, index + 1, data, index, size - index);
    }

    /**
     * @param element element
     * @return Location
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++)
            if (data[i].equals(element))
                return i;

        return -1;
    }

    /**
     * @param index index
     * @return object
     */
    public T getByIndex(int index) {
        return (T) data[index];
    }

}










