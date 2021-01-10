package com.netcracker.contract;
/*
@author Lozovoy
@version 2.0
class mobile contract

 */
public class MobileContract extends Contract {
    private final Integer minutes, sms, gb;

    /**
     * @param minutes minutes
     * @param sms sms
     * @param gb gb
     * @param id id
     * @param startDate start date
     * @param endDate end date
     * @param client client
     */
    public MobileContract(
            Integer minutes, Integer sms, Integer gb, Integer id, Long startDate, Long endDate, Client client
    ) {
        super(id, startDate, endDate, client);

        this.minutes = minutes;
        this.sms = sms;
        this.gb = gb;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public Integer getSms() {
        return sms;
    }

    public Integer getGb() {
        return gb;
    }
}