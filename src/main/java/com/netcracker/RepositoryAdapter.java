package com.netcracker;

import com.netcracker.reflection.Injector;
import com.netcracker.contract.Contract;
import com.netcracker.repository.ISorter;
import com.netcracker.repository.Repository;

import java.util.Comparator;
import java.util.function.Predicate;

import javax.inject.Inject;

/*
@author Lozovoy
@version 1.0
class repository adapter

 */

public class RepositoryAdapter extends Repository<Contract> {
    @Inject
    public ISorter sorter;

    public RepositoryAdapter() {
        super();
        Injector.inject(this);
    }

    public RepositoryAdapter(RepositoryAdapter anotherAdapter) {
        super(anotherAdapter);
        this.sorter = anotherAdapter.sorter;
    }

    private RepositoryAdapter(Repository<Contract> superAdapter) {
        super(superAdapter);
    }

    /**
     * Adds a new contract to the end of the list
     *
     * @param element new contract
     */
    @Override
    public void add(Contract element) {
        super.add(element);
    }

    /**
     * @param id id of the required contract
     * @return required contract
     */
    public Contract getById(Integer id) {
        int index = indexById(id);

        if (index >= 0)
            return (Contract) data[index];
        else
            return null;
    }

    /**
     * @param id id of the required contract
     * @return index of the required contract in the list
     */
    protected int indexById(Integer id) {
        for (int i = 0; i < size; i++)
            if (getByIndex(i).getId().equals(id))
                return i;

        return -1;
    }

    /**
     * Deletes the contract for the specified id
     *
     * @param id id of the required contract
     * @return deleted element
     */
    public Contract removeById(Integer id) {
        int index = indexById(id);

        if (index >= 0)
            return removeAt(index);
        else
            return null;
    }

    /**
     * Returns a {@link RepositoryAdapter} consisting of the elements of this adapter, sorted according to the provided {@link Comparator}
     *
     * @param sorter     implementation of the {@link ISorter} class that defines the sorting algorithm
     * @param comparator a comparator to be used to compare adapter elements
     * @return the new ContractAdapter
     */
    @Override
    public RepositoryAdapter sorted(ISorter sorter, Comparator<? super Contract> comparator) {
        return new RepositoryAdapter(super.sorted(sorter, comparator));
    }

    public RepositoryAdapter sorted(Comparator<? super Contract> comparator) {
        return sorted(sorter, comparator);
    }

    /**
     * Returns a {@link RepositoryAdapter} consisting of the elements of this adapter that match the given {@link Predicate}.
     *
     * @param predicate a predicate to apply to each element to determine if it should be included
     * @return the new ContractAdapter
     */
    @Override
    public RepositoryAdapter filter(Predicate<? super Contract> predicate) {
        return new RepositoryAdapter(super.filter(predicate));
    }

    @Override
    public RepositoryAdapter clone() {
        try {
            super.clone();
        } catch (Exception ignored) {
        }

        return new RepositoryAdapter(this);
    }
}
