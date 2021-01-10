package com.netcracker;

import com.netcracker.reflection.Injector;
import com.netcracker.reader.Reader;
import com.netcracker.contract.Contract;
import com.netcracker.contract.WiredInternerContract;
import com.netcracker.contract.MobileContract;
import com.netcracker.contract.DigitalTv;
import com.netcracker.parser.CSVParser;
import com.netcracker.repository.Array;
import com.netcracker.repository.Logger;
import com.netcracker.validator.ValidationResult;
import com.netcracker.validator.Validator;
import com.netcracker.repository.Ext;

import javax.inject.Inject;

/*
@author Lozovoy
@version 1.0
class contract parser

 */

public class ContractParser {
    @Inject
    public Logger logger;
    @Inject
    public Validator<Contract> validator;
    @Inject
    private Reader reader;
    @Inject
    private CSVParser parser;

    public ContractParser() {
        Injector.inject(this);
    }

    public RepositoryAdapter parse() {
        RepositoryAdapter adapter = new RepositoryAdapter();

        for (String line : reader.readLines()) {
            String[] sourceValues = line.split(",");
            for (int i = 0; i < sourceValues.length; i++)
                sourceValues[i] = sourceValues[i].trim();

            String[] extra = sourceValues[sourceValues.length - 1].split(";");
            String[] normalValues = new String[sourceValues.length + extra.length - 2];
            System.arraycopy(extra, 0, normalValues, 0, extra.length);
            System.arraycopy(sourceValues, 1, normalValues, extra.length, sourceValues.length - 2);

            String contractSource = Array.join(normalValues, ",");
            Contract contract = parser.from(contractSource, parseClass(sourceValues[0]));
            if (validator != null) {
                int errorCount = 0;
                for (ValidationResult result : validator.validate(contract)) {
                    if (!result.isValid()) {
                        errorCount++;
                        if (logger != null)
                            logger.error(result.getMessage());
                    } else if (logger != null)
                        logger.note(result.getMessage());
                }
                logResult(logger, errorCount, contract.toString());
                if (errorCount == 0)
                    adapter.add(contract);
            } else
                adapter.add(contract);
        }

        return adapter;
    }

    private void logResult(Logger logger, int errorCount, String currentContract) {
        if (logger == null)
            return;

        StringBuilder sb = new StringBuilder();
        Ext.append(sb, "Validation finished with ", errorCount, " error");
        if (errorCount != 1)
            Ext.append(sb, "s");
        if (errorCount > 0)
            Ext.append(sb, Ext.newLine(), "Current contract [", currentContract, "] will be ignored", Ext.newLine());
        else
            Ext.append(sb, Ext.newLine());
        logger.note(sb.toString());
    }

    private Class<? extends Contract> parseClass(String s) {
        switch (s.toLowerCase()) {
            case "internet":
                return WiredInternerContract.class;
            case "mobile":
                return MobileContract.class;
            case "tv":
                return DigitalTv.class;
        }
        return null;
    }
}
