package com.netcracker.contract;

/**
 * The class contract
 * @author Alex Lozovoy
 */
public class Contract {
    private int id;
    private long startOfContract;
    private long endOfContract;
    private int numberContract;
    private Client client;

    /**
     * Constructor for creating a contract
     * @param id ID contract
     * @param startOfContract Contract start date
     * @param endOfContract The end date of the contract
     * @param numberContract Number contract
     * @param client Client
     */
    public Contract(int id, long startOfContract, long endOfContract , int numberContract, Client client  ){
        this.id=id;
        this.startOfContract=startOfContract;
        this.endOfContract=endOfContract;
        this.numberContract=numberContract;
        this.client=client;
    }

    /**
     * Returning the contract Id
     * @return id contract
     */
    public int getId(){
        return id;
    }

}
