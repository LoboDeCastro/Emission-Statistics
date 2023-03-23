package org.campus02.records;

import java.util.Comparator;

public class DataRecordComparator implements Comparator<DataRecord> {
    @Override
    public int compare(DataRecord o1, DataRecord o2) {
        int x = o1.getSector().compareTo(o2.getSector());
        if (x == 0) x = Double.compare(o2.getTotalValue(), o1.getTotalValue());
        return x;
    }
}
