package DatabaseInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseUsage {


	/**
	 * The least stores all database related function info
	 */
	public ArrayList <DatabaseMethod> methodUsageList =  new ArrayList<DatabaseMethod> ();

	
	
	/**
	 * parse the given database CSV file 
	 * @param csvFile
	 */
	public void parseCSVfile(String csvFile){

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\\|";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				//System.out.println("line   : " + line);
				// use comma as separator
				String[] MethodDBInfo = line.split(cvsSplitBy);
				DatabaseMethod dm = new DatabaseMethod(MethodDBInfo);
				//build DatabaseMethod list then
				methodUsageList.add(dm);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * Print all database related function info
	 */
	public void printList(){
		for(DatabaseMethod dm : this.methodUsageList){
			System.out.println(dm);
		}
		
	}



	public static void main(String [] args){
		DatabaseUsage du = new DatabaseUsage ();
		//du.parseCSVfile("DatabaseUsageInfo\\UMAS.csv");
		du.parseCSVfile("DatabaseUsageInfo\\Test.csv");
		du.printList();

	}

}
