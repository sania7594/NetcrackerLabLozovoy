package com.netcracker;

import com.netcracker.reflection.MyInject;
import com.netcracker.reader.CSVReader;
import com.netcracker.reader.Reader;
import com.netcracker.contract.Contract;
import com.netcracker.parser.CSVParser;
import com.netcracker.repository.ISorter;
import com.netcracker.repository.SorterFactory;
import com.netcracker.repository.ConsoleLogger;
import com.netcracker.repository.Logger;
import com.netcracker.validator.Condition;
import com.netcracker.validator.Conditions;
import com.netcracker.validator.Validator;
import com.netcracker.validator.ValidatorBuilder;

import java.io.File;
import java.net.URISyntaxException;

import javax.inject.Singleton;

/*
@author Lozovoy
@version 1.0
class appmodule

 */

public class AppModule {
    @MyInject
    @Singleton
    public File file() {
        try {
            return Reader.getFileFromResource("tableCSV.csv");
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @MyInject
    @Singleton
    public Validator<Contract> validator() {
        ValidatorBuilder<Contract> builder = new ValidatorBuilder<>();
        builder.add(new Condition<>(2, Contract::getId, ((expected, actual) -> actual % expected == 0)));
        builder.add(new Condition<>("lera", contract -> contract.getClient().getFullName()));
        builder.add(new Condition<>(
                9,
                Conditions.GREATER_THAN_OR_EQUALS,
                Contract::getId,
                (expected, actual) -> expected >= actual
        ));
        return builder.build();
    }

    @MyInject
    @Singleton
    public Reader reader(File file) {
        return new CSVReader(file);
    }

    @MyInject
    @Singleton
    public ISorter sorter() {
        return SorterFactory.getSorter();
    }

    @MyInject
    @Singleton
    public CSVParser parser() {
        return new CSVParser();
    }

    @MyInject
    @Singleton
    public Logger logger() {
        return new ConsoleLogger();
    }
}
