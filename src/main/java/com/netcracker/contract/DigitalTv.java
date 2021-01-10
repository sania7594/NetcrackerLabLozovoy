package com.netcracker.contract;
/*
@author Lozovoy
@version 2.0
class Tv

 */

public class DigitalTv extends Contract {
    private final TVPackage tvPackage;

    /**
     * @param tvPackage tv package
     * @param id id
     * @param startDate start date
     * @param endDate end date
     * @param client client
     */
    public DigitalTv(TVPackage tvPackage, Integer id, Long startDate, Long endDate, Client client) {
        super(id, startDate, endDate, client);

        this.tvPackage = tvPackage;
    }

    public TVPackage getTvPackage() {
        return tvPackage;
    }
}