package org.campus02.records;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class DataRecordLoader {
    public static void main(String[] args) throws DataFileException {
        ArrayList<DataRecord> list = load("data/thg-emissionen_1990-2020.csv");
        for (DataRecord dataRecord : list) {
            System.out.println(dataRecord.getSector());
            HashMap<Integer, Double> map = dataRecord.getValues();
            for (Integer key : map.keySet()) {
                System.out.println(key + " - " + map.get(key));
            }
        }
    }

    public static ArrayList<DataRecord> load(String path) throws DataFileException {
        ArrayList<DataRecord> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                DataRecord record = new DataRecord();
                record.setSector(data[0]);
                int counter = 1;
                while (1989 + counter != 2021) {
                    record.setValue(1989 + counter, Double.parseDouble(data[counter]));
                    counter++;
                }
                list.add(record);
            }
        } catch (FileNotFoundException e) {
            throw new DataFileException("Error! File not found!", e);
        } catch (IOException e) {
            throw new DataFileException("IO Fail", e);
        }
        return list;
    }

    public static ArrayList<DataRecord> load(String path, Comparator comparator) throws DataFileException {
        ArrayList<DataRecord> list = load(path);
        list.sort(comparator);
        return list;
    }
}
