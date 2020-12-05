package com.netcracker.contract;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Client creation class
 *
 * @author Alex Lozovoy
 */
public class Client {
    public int getId() {
        return id;
    }

    private int id;
    private String fullName;
    private String birthday;

    public String getSex() {
        return sex;
    }

    private String sex;
    private int seriesNumber;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

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
