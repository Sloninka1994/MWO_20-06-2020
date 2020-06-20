package pl.edu.agh.mwo.java.Reports;

import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Report1 {
    private ArrayList<RecordEntry> recordEntries;
    public Report1(ArrayList<RecordEntry> recordEntries, int Year){
        RecordFilter recordFilter = new RecordFilter(recordEntries);
        this.recordEntries =  recordFilter.byYear(Year);
    }

    public TreeMap<String, Double> getReport(){
        TreeMap<String, Double> retVal = new TreeMap();
        for(int i=0; i < recordEntries.size(); i++){
            if (retVal.containsKey(recordEntries.get(i).getWorkerName())) {
                retVal.put(recordEntries.get(i).getWorkerName(), retVal.get(recordEntries.get(i).getWorkerName()) + recordEntries.get(i).getWorkingHours());
            }else{
                retVal.put(recordEntries.get(i).getWorkerName(),recordEntries.get(i).getWorkingHours());
            }
        }
        return retVal;
    }

    public void printOnConsole(){
        TreeMap<String, Double> a = getReport();
        if(recordEntries.size() > 0) {
            for (Map.Entry<String, Double> entry : a.entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();
                System.out.println(key + " => " + value + " h");
            }
        }else{
            System.out.println("Brak danych za ten rok :(");
        }
    }

}
