package com.timbuchalka;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Csv {

    public static void createCSV(List<List<String>> rows) throws IOException {
        FileWriter csvWriter=new FileWriter("taylorSum.csv");
        csvWriter.append("sep=;\n");
        csvWriter.append("Value");
        csvWriter.append(";");
        csvWriter.append("FromStart");
        csvWriter.append(";");
        csvWriter.append("FromEnd");
        csvWriter.append(";");
        csvWriter.append("Math");
        csvWriter.append(";");
        csvWriter.append("NfPs");
        csvWriter.append(";");
        csvWriter.append("NfPe");
        csvWriter.append("\n");

        for(List<String> rowData: rows){
            csvWriter.append(String.join(";",rowData));
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}