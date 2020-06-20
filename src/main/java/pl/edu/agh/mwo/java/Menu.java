package pl.edu.agh.mwo.java;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.ReadData.ReadData;
import pl.edu.agh.mwo.java.Reports.Report1;

import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
	static ArrayList<RecordEntry> re = new ArrayList<RecordEntry>();

	public static String getPath(){
		String path;
		while (true) {
			Scanner scan = new Scanner(System.in);
	        System.out.println();
	        System.out.println("Podaj ścieżkę do katalogu z arkuszami pracowników!: ");
	        path = scan.nextLine();
	        if (path.length() > 0) {
	        	return path;
	        }
	        else {
	        	System.out.println("Pusta sciezka !");
	        }
		}
    }
	
	public static void printMenu() {
		while (true) {

			Scanner scan = new Scanner(System.in);
			System.out.println();
			System.out.println("Wybierz, który raport chcesz wygenerować: ");
			System.out.println("1: Alfabetycznej zestawienie listy pracowników <-> liczby godzin");
			System.out.println("2: Alfabetycznie zestawienie projektów <-> liczba godzin ");
			System.out.println("3: Szczegółowy wykaz pracy kazdego pracownika");
			System.out.println("4: Procentowe zaangazowanie danego pracownika w projektach w danym roku");
			System.out.println("5: Szczegółowy wykaz pracy w danym projekcie");
			System.out.println("6: Pokaż wszystkie zaimportowane dane");
			System.out.println("7: Exit");
			
			String option = scan.nextLine();
			
			switch (option) {
			case "1": 
				System.out.println("Generating Report 1");
				System.out.println("Podaj rok dla którego am powstać raport:");
				String sYear = scan.nextLine();
				int Year;
				try {
					Year = Integer.parseInt(sYear);
				} catch (Exception e) {
					System.out.println("Wprowadzono błędną wartość");
					break;
				}
				Report1 r1 = new Report1(re,Year);
				r1.printOnConsole();
				// generate and print report
				break;
			case "2": 
				System.out.println("Generating Report 2");
				break;
			case "3": 
				System.out.println("Generating Report 3");
				break;
			case "4": 
				System.out.println("Generating Report 4");
				break;
			case "5": 
				System.out.println("Generating Report 5");
				break;
			case "6":
				for(RecordEntry itm : re){
					System.out.println(itm.toString());
				}
				break;
			case "7":
				System.out.println("Zapraszamy ponownie! ");
				return;
			default:
				System.out.println("Nie ma takiej opcji! Wybierz ponownie!");
				break;
			}
		}	
		
	}
	
    public static void main(String[] args) {
    	
    	String path;
    	String option;
    	System.out.println("Witaj w programie do generowania raportów - MWO2020!");
    	System.out.println("____________________________________________________");

    	path = getPath();
    	System.out.println(path);

    	re = ReadData.readAllFromFolder(path);

    	if (re.size() > 0) {
			printMenu();
		}else{
			System.out.println("Wczytywanie danych nie powiodło się.");
		}
        //Funkcje odpowiednie do raportu -> Wczytywanie Roku, miesiąca, Imienia, etc;
    	
    	
        

    }
}