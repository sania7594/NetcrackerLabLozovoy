package com.netcracker.repository;
import com.netcracker.contract.Contract;

public class Repository {
    /**
     * Сreating an array of contracts
     * The purpose of the size
     * Setting the zoom step(EXTENSION_SIZE)
     */
    private Contract[] data = new Contract[0];
    private int size = 0;
    private static final int EXTENSION_SIZE = 10;

    /**
     * Adding a contract
     * @param contract Contract
     */
    public void add(Contract contract) {
        if (isNotFull()) {
            data[size] = contract;
            size++;
        } else {
            expand();
            add(contract);
        }
    }

    /**
     * Getting an object
     * @param id ID
     * @return null
     */
    public Contract getById(int id){
        int index=indexById(id);
        if (index!=-1){
            return data[index];
        }
        return null;
    }

    /**
     * remove by id
     * @param id ID
     */
    public void removeById(int id ){
        int index=indexById(id);

        if (index!=-1){
            size--;
            trim(index);
        }
    }

    /**
     * @param id ID contract
     * @return  Location in the array
     */
    private int indexById(int id){
        for (int i=0; i<size; i++) {
            if (data[i].getId() == id) {
                return i;
            }
        }
        return -1;

    }

    /**
     * @param index array's
     */
    private void trim(int index) {
        int count = size - index;
        Contract[] tmp = new Contract[size];
        System.arraycopy(data, 0, tmp, 0, index);
        System.arraycopy(data, index + 1, tmp, index, count);
        data=tmp;
    }


    /**
     * Array extension
     */
    private void expand() {
        Contract[] newData = new Contract[size + EXTENSION_SIZE];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /**
     * Comparing the size with the length of an array
     * @return True and False
     */
    private boolean isNotFull() {
        return size < data.length;
    }
}
