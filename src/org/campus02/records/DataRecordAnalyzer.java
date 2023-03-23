package org.campus02.records;

import java.util.ArrayList;
import java.util.HashMap;

public class DataRecordAnalyzer {
    private ArrayList<DataRecord> dataRecords;

    public DataRecordAnalyzer(ArrayList<DataRecord> dataRecords) {
        this.dataRecords = dataRecords;
    }

    public DataRecord getSector(String sector) {
        DataRecord dataRecord = null;
        for (DataRecord dr : dataRecords) {
            if (dr.getSector().equals(sector))
                dataRecord = dr;
        }
        return dataRecord;
    }

    public HashMap<Integer, Double> totalValuesPerYear() {
        HashMap<Integer, Double> map = new HashMap<>();
        for (int year = 1990; year < 2021; year++) {
            for (DataRecord dr : dataRecords) {
                double value = dr.getByYear(year);
                if (map.containsKey(year)) map.replace(year, map.get(year) + value);
                else map.put(year, value);
            }
        }
        return map;
    }

/*    public static void main(String[] args) throws DataFileException {
        DataRecordAnalyzer dataRecordAnalyzer = new DataRecordAnalyzer(DataRecordLoader.load("data/thg-emissionen_1990-2020.csv"));
        HashMap<Integer, Double> map = dataRecordAnalyzer.totalValuesPerYear();
        for (Integer key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }*/
}
