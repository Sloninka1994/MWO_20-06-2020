package pl.edu.agh.mwo.java;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void toStringFromRecordEntry(){
        LocalDate date =  LocalDate.of(2020,6,20);
        RecordEntry  recordEntry = new RecordEntry();
        recordEntry.setWorkerName("Janek");
        recordEntry.setDate(date);
        recordEntry.setDescription("some important stuff");
        recordEntry.setProjectName("Project Manhattan");
        recordEntry.setWorkingHours(8);
        assertTrue(recordEntry.toString().equals("Janek spent 8.0h on project Project Manhattan doing some important stuff (2020-06-20)"));
    }

    @Test
    public void filteringRecordEntryCollection() {
        ArrayList<RecordEntry> re = new ArrayList<RecordEntry>();
        LocalDate date = LocalDate.of(2020, 6, 20);
        RecordEntry recordEntry = new RecordEntry();
        recordEntry.setWorkerName("Janek");
        recordEntry.setDate(date);
        recordEntry.setDescription("some important stuff");
        recordEntry.setProjectName("Project Manhattan");
        recordEntry.setWorkingHours(8);
        re.add(recordEntry);

        recordEntry = new RecordEntry();
        date = LocalDate.of(2019, 6, 20);
        recordEntry.setWorkerName("Tomek");
        recordEntry.setDate(date);
        recordEntry.setDescription("not important stuff");
        recordEntry.setProjectName("Project Manhattan");
        recordEntry.setWorkingHours(4);
        re.add(recordEntry);
        RecordFilter rf = new RecordFilter(re);
        re = rf.byYear(2020);
        assertTrue(re.size() == 1);
    }
}
