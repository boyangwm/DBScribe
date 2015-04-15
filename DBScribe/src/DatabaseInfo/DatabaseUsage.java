package DatabaseInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseUsage {


	ArrayList <DatabaseMethod> methodUsageList =  new ArrayList<DatabaseMethod> ();

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

				System.out.println("0 " + MethodDBInfo[0] 
						+ " 1 " + MethodDBInfo[1]
								+ " 2 " + MethodDBInfo[2]
										+ " 3 " + MethodDBInfo[3]
												+ " 4 " + MethodDBInfo[4]);
				
				//build DatabaseMethod list then. 

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");

	}



	public static void main(String [] args){
		DatabaseUsage du = new DatabaseUsage ();
		du.parseCSVfile("DatabaseUsageInfo\\UMAS.csv");

	}

}
