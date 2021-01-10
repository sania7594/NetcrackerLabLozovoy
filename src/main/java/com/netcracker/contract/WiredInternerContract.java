package com.netcracker.contract;

/*
@author Lozovoy
@version 2.0
class internet contract

 */

public class WiredInternerContract extends Contract {
    private final Integer speedLimit;

    /**
     * @param speedLimit speed limit
     * @param id id
     * @param startDate start date
     * @param endDate end date
     * @param client client
     */
    public WiredInternerContract(Integer speedLimit, Integer id, Long startDate, Long endDate, Client client) {
        super(id, startDate, endDate, client);

        this.speedLimit = speedLimit;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }
}