package org.campus02.records;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DataRecord {
    private String sector;
    private HashMap<Integer, Double> values = new HashMap<>();


    public String getSector() {
        return sector;
    }

    public void setSector(String sector) { this.sector = sector; }

    public void setValue(int year, double value){
        values.put(year, value);
    }

    public double getByYear(int year) {
        return values.get(year);
    }

    public double getTotalValue() {
        // TODO: Your implementation, point 4
        double sum = 0;
        for (Integer key : values.keySet()) {
            sum += values.get(key);
        }
        return sum;
    }

    public HashMap<Integer, Double> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "sector='" + sector + '\'' +
                ", values=" + values +
                '}';
    }
}
