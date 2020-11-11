package com.netcracker.contract;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Client creation class
 *
 * @author Alex Lozovoy
 */
public class Client {
    private int id;
    private String fullName;
    private String birthday;
    private String sex;
    private int seriesNumber;

    /**
     * Constructor for creating a client
     *
     * @param id           ID client
     * @param fullName     Full client
     * @param birthday     birthday client
     * @param sex          sex clinet
     * @param serialNumber Passport series and number
     */
    public Client(int id, String fullName, String birthday, String sex, int serialNumber) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.sex = sex;
        this.seriesNumber = serialNumber;
    }

    /**
     * Сalculating the age of a person
     *
     * @return age of the person
     */
    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.parse(birthday, formatter);
        Period period = Period.between(endDate, startDate);
        return period.getYears();
    }

}
