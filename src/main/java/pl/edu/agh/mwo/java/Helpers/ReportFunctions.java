package pl.edu.agh.mwo.java.Helpers;

import java.util.Map;
import java.util.TreeMap;

public class ReportFunctions {
    public static int maxLengthOfMapTreeKey(TreeMap<String, Double> m){
        int retVal = 0;
        int tmpVal;
        for (Map.Entry<String, Double> entry : m.entrySet()) {
            tmpVal = entry.getKey().length();
            if (tmpVal > retVal) {
                retVal = tmpVal;
            }
        }
        return retVal;
    }
    public static int maxLengthOfMapTreeValue(TreeMap<String, Double> m){
        int retVal = 0;
        int tmpVal;
        for (Map.Entry<String, Double> entry : m.entrySet()) {
            tmpVal = String.valueOf(entry.getValue()).length();
            if (tmpVal > retVal) {
                retVal = tmpVal;
            }
        }
        return retVal;
    }
    public static String adjustTextToLength(String text, int maxLength){
        int lengthDiff = maxLength - text.length();
        for (int i = 0; i < lengthDiff; i++) {
            text += " ";
        }
        return text;
    }
}
