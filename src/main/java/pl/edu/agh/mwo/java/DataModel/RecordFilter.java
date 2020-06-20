package pl.edu.agh.mwo.java.DataModel;

import java.util.ArrayList;

public class RecordFilter {
    private ArrayList<RecordEntry> recordEntries;

    public RecordFilter(ArrayList<RecordEntry> recordEntries){
        this.recordEntries = recordEntries;
    }

    public ArrayList<RecordEntry> byYear(int year){
        ArrayList<RecordEntry> retVal = new ArrayList<RecordEntry>();

        for(int i=0; i < recordEntries.size(); i++){
            if(year == recordEntries.get(i).getDate().getYear()){
                retVal.add(recordEntries.get(i));
            };
        }
        return retVal;
    }

    public ArrayList<RecordEntry> byProject(String projectName){
        ArrayList<RecordEntry> retVal = new ArrayList<RecordEntry>();
        for(int i=0; i < recordEntries.size(); i++){
            if(recordEntries.get(i).getProjectName().equals(projectName)){
                retVal.add(recordEntries.get(i));
            };
        }
        return retVal;
    }

    public ArrayList<RecordEntry> byWorkerName(String workerName){
        ArrayList<RecordEntry> retVal = new ArrayList<RecordEntry>();
        for(int i=0; i < recordEntries.size(); i++){
            if(recordEntries.get(i).getWorkerName().equals(workerName)){
                retVal.add(recordEntries.get(i));
            };
        }
        return retVal;
    }
}
