package pl.edu.agh.mwo.java.Reports;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.Helpers.ReportFunctions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class ReportSimple {
    protected ArrayList<RecordEntry> recordEntries;
    protected String headerCol1;
    protected String headerCol2;
    protected String reportName;

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

    public boolean printOnConsole(){
        TreeMap<String, Double> a = getReport();
        int maxLenKey;
        int maxLenVal;
        if(this.recordEntries.size() > 0) {
            maxLenKey = ReportFunctions.maxLengthOfMapTreeKey(a);
            maxLenVal = ReportFunctions.maxLengthOfMapTreeValue(a);
            System.out.println(ReportFunctions.adjustTextToLength(headerCol1, maxLenKey)  + " => " + ReportFunctions.adjustTextToLength(headerCol2, maxLenVal));
            for (Map.Entry<String, Double> entry : a.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                System.out.println(ReportFunctions.adjustTextToLength(key, maxLenKey)  + " => " + ReportFunctions.adjustTextToLength(value, maxLenVal) + " h");
            }
            return true;
        }else{
            System.out.println("Brak danych za ten rok :(");
            return false;
        }
    }

    public void exportToExcel(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj folder zapisu:");
        String folderPath = scan.nextLine();
        File dir = new File(folderPath);
        if (dir.exists()){
            TreeMap<String, Double> a = getReport();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Report");
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue(headerCol1);
            rowhead.createCell(1).setCellValue(headerCol2);
            int i = 0;
            for (Map.Entry<String, Double> entry : a.entrySet()) {
                i++;
                HSSFRow row = sheet.createRow((short)i);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }
            try  (FileOutputStream fileOut = new FileOutputStream(folderPath + "\\"+ reportName+ ".xls")) {
                wb.write(fileOut);
                fileOut.close();
                wb.close();
                System.out.println("Raport został wygenerowany poprawnie!");
            }catch (Exception e){

            }
        }
    }
    public void generateBarChart(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj folder zapisu:");
        String folderPath = scan.nextLine();
        File dir = new File(folderPath);
        if (dir.exists()) {
            TreeMap<String, Double> a = getReport();
            String[] seriesLabel = ReportFunctions.extractLabels(a);
            Double[] valuesLabel = ReportFunctions.extractValues(a);
            CategoryChart chart = new CategoryChartBuilder().width(1200).height(800).title(reportName).xAxisTitle(headerCol1).yAxisTitle(headerCol2).build();
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            chart.getStyler().setHasAnnotations(true);

            chart.addSeries(reportName, Arrays.asList(seriesLabel), Arrays.asList(valuesLabel));
            try{
                BitmapEncoder.saveBitmap(chart, folderPath + "\\Report6_chart", BitmapEncoder.BitmapFormat.PNG );
                System.out.println("Wykres został wygenerowany poprawnie!");
            }catch (Exception e){

            }
        }
    }
}
