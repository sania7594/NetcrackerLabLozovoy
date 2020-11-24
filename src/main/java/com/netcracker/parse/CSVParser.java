package com.netcracker.parse;

import com.netcracker.contract.Client;
import com.netcracker.contract.DigitalTv;
import com.netcracker.contract.MobileContract;
import com.netcracker.contract.WiredInternetContract;
import com.netcracker.repository.Repository;
import java.io.*;
/**
 * Class for parsing a csv file and transmitting data to the repositor.
 *
 * @author Alex Lozovoy
 */

public class CSVParser{

    /**
     * @param repository repository
     * @throws IOException exception
     */
    public static void readArr(Repository repository) throws IOException {
        File file = new File("src/main/java/com/netcracker/parse/loader.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        int j=0;
        String line="";
        while (true){
            line=reader.readLine();
            if (line==null){
                break;
            }
            if (j==0){
                j++;
                continue;
            }
            String[] sources=line.split(",");
            for (int i=0; i< sources.length; i++){
                sources[i]=sources[i].trim();
            }

        String[] extra=sources[sources.length-1].split(";");
        Client clientNew= new Client(Integer.parseInt(sources[4]), sources[5], sources[6], sources[7],
                Integer.parseInt(sources[8]));

        switch (sources[9]){
            case "tv":
                repository.add(new DigitalTv(Integer.parseInt(sources[0]),
                        Long.parseLong(sources[1]),Long.parseLong(sources[2]),Integer.parseInt(sources[3]),
                                clientNew, Integer.parseInt(extra[0]))
                        );
                break;
            case "internet":
                repository.add(new WiredInternetContract(Integer.parseInt(sources[0]),
                        Long.parseLong(sources[1]),Long.parseLong(sources[2]),Integer.parseInt(sources[3]),
                        clientNew, Float.parseFloat(extra[0]))
                );
                break;
            case "mobile":
                repository.add(new MobileContract(Integer.parseInt(sources[0]),
                        Long.parseLong(sources[1]),Long.parseLong(sources[2]),Integer.parseInt(sources[3]),
                        clientNew, Integer.parseInt(extra[0]),Integer.parseInt(extra[1]),
                        Integer.parseInt(extra[2])));
                break;
        }

    }
}
}
