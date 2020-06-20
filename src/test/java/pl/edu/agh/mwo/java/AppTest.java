package pl.edu.agh.mwo.java;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;

import java.time.LocalDate;

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
        LocalDate date =  LocalDate.now();
        RecordEntry  recordEntry = new RecordEntry();
        recordEntry.setWorkerName("Janek");
        recordEntry.setDate(date);
        recordEntry.setDescription("some important stuff");
        recordEntry.setProjectName("Project Manhattan");
        recordEntry.setWorkingHours(8);
        assertTrue( recordEntry.toString().equals("Janek spent 8.0h on project Project Manhattan doing some important stuff (2020-06-20)"));
    }
}
