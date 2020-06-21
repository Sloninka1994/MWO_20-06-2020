package pl.edu.agh.mwo.java.Reports;

import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;
import pl.edu.agh.mwo.java.Helpers.ReportFunctions;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
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
        int maxLenKey;
        int maxLenVal;
        if(recordEntries.size() > 0) {
            maxLenKey = ReportFunctions.maxLengthOfMapTreeKey(a);
            maxLenVal = ReportFunctions.maxLengthOfMapTreeValue(a);

            for (Map.Entry<String, Double> entry : a.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                System.out.println(ReportFunctions.adjustTextToLength(key, maxLenKey)  + " => " + ReportFunctions.adjustTextToLength(value, maxLenVal) + " h");
            }
        }else{
            System.out.println("Brak danych za ten rok :(");
        }
    }
    public void exportToExcel(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj folder zapisu:");
        String folderPath = scan.nextLine();
        File dir = new File(folderPath);
        if (dir.exists()){
            TreeMap<String, Double> a = getReport();
            
        }



    }

}
