package pl.edu.agh.mwo.java.ReadData;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;

import pl.edu.agh.mwo.java.DataModel.RecordEntry;

public class ReadData {
	
	public static ArrayList<String> folderExplorer(String myDirectoryPath) {
		ArrayList<String> pathList = new ArrayList<String>();
		
		File dir = new File(myDirectoryPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
		    for (File subDir1 : directoryListing) {
		    	if (subDir1.isDirectory()) {
		            for (File subDir2 : subDir1.listFiles()) {
		            	if (subDir2.isDirectory()) {
		            	//System.out.println(file1.toString());
		            		for (File file1 : subDir2.listFiles()) {
		            				pathList.add(file1.toString());
		            		}
		            	}
		            }
		    	}
		    }
		} 
		else {
		    // Handle the case where dir is not really a directory.
		}
		
		return pathList;
	}
	
	public static ArrayList<RecordEntry> readXls(String path) {
		ArrayList<RecordEntry> recordEntryList = new ArrayList<RecordEntry>();
		try {
			Workbook wb = WorkbookFactory.create(new File(path));
			String fullPath = path;
			

			int endOfname = fullPath.lastIndexOf(".");
			int startOfSurname = fullPath.lastIndexOf("\\")+1;		
			String name = new String(fullPath.substring(startOfSurname, endOfname));

			System.out.println(name);

			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheetAt(i);
				String project = sheet.getSheetName();
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {
					Row r = sheet.getRow(j);
					
					Date date = r.getCell(0).getDateCellValue();
					LocalDate localDate= date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					String description = r.getCell(1).getStringCellValue();;
					double hours = r.getCell(2).getNumericCellValue();
					
					RecordEntry toAdd = new RecordEntry();
					toAdd.setDate(localDate);
					toAdd.setDescription(description);
					toAdd.setWorkingHours(hours);
					toAdd.setProjectName(project);
					toAdd.setWorkerName(name);
					
					recordEntryList.add(toAdd);
					
			}
		}
		
			
        } catch (EncryptedDocumentException | IOException e) {
        	e.printStackTrace();
            
        }
		
		return recordEntryList;
	}
	
	public static void main(String[] args) {
        System.out.println("Hello World!");
        String path = "C:\\Users\\krzuc\\Desktop\\pracownia projektowa\\MWO_20-06-2020\\dane";
        ArrayList<String> pathList = folderExplorer(path); 
        for(String path1: pathList) {
//        	System.out.println(path1);
        	 ArrayList<RecordEntry> recordList = readXls(path1);
        	 for(RecordEntry r: recordList) {
//        		 System.out.println(r.getWorkerName());
        		 System.out.println(r.getDescription());
        	 }
        }
    }
}