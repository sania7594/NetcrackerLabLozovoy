package com.netcracker.contract;

/**
 * A contract for digital TV
 * @author Alex Lozovoy
 */

public class DigitalTv extends  Contract{
    private int channelPackage;
    /**
     * @param id ID
     * @param startDate Contract start date
     * @param endDate The end date of the contract
     * @param numberContract Number contract
     * @param client Client
     * @param channelPackage Channel package
     */
    public DigitalTv(int id, long startDate, long endDate, int numberContract, Client client, int channelPackage) {
        super(id, startDate, endDate, numberContract, client);
        this.channelPackage = channelPackage;
    }
}
