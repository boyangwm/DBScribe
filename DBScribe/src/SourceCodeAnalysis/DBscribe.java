package SourceCodeAnalysis;

import java.util.ArrayList;

import DatabaseInfo.DatabaseUsage;
import LevelGraph.Analyzer;

public class DBscribe {
	public static ClassManager cm = new ClassManager();
	public static MethodManager mm = new MethodManager();
	public static final int LEVELTHRESHOLD = 15;


	/**
	 * save method m into the method manager
	 * @param m
	 */
	public static void storeMethod(Method m){
		//add method first
		mm.addMethod(m);

		//build an index for finding
		String name = m.getMethodName();
		int num = m.getMethodNumArgs();
		MethodKey mk = new MethodKey(name, num);
		mm.putKeyAndMethod(mk, m);
	}


	/**
	 * parseXMLFolder to get the information
	 * the class and method info will be store in cm and mm. (Class and Method manager)
	 * @param folder
	 */
	public static void parseXMLFolderAndGetInfo(String folder){
		Xmlparser xp = new Xmlparser();
		xp.parseXMLFolder(folder);
	}


	/**
	 * save a class into cm (a class manager)
	 * @param newCL
	 */
	public static void storeClass(Class newCL){
		cm.addClass(newCL);
	}



	/**
	 * run DBScribe
	 */
	public void run(String sourceLoc, String xmlFileOutputLoc, String DBusageInfoLoc){


		//source to xml files
		Src2XML sx = new Src2XML();
		sx.sourceFolderToXML(sourceLoc, xmlFileOutputLoc);



		//parse xml file and store SC info
		parseXMLFolderAndGetInfo(xmlFileOutputLoc);
		//DBscribe.mm.myTest();
		//DBscribe.cm.printClassesName();

		//parse file and store DB usage info
		DatabaseUsage du = new DatabaseUsage ();
		du.parseCSVfile(DBusageInfoLoc);
		DBscribe.mm.ImportDBUsage(du);	
		//DBscribe.mm.printAllMethod();

		//build the level graph
		Analyzer az = new Analyzer();
		az.buildLevelGraph();
		az.printLevelMap();

		//testing
		ArrayList<Method> al =  mm.getAllMethods();
		System.out.println("num of all Method  : "  + al.size());
		for(Method m : al){
			az.findCalleeListToDB(m);
		}


		for(Method m : al){
			az.findCallerList(m);
		}

		System.out.println("Done");

	}



	/**
	 * run DBScribe
	 */
	public void run2(String sourceLoc, String xmlFileOutputLoc){


		//source to xml files
		Src2XML sx = new Src2XML();
		sx.sourceFolderToXML(sourceLoc, xmlFileOutputLoc);



		//parse xml file and store SC info
		parseXMLFolderAndGetInfo(xmlFileOutputLoc);
		DBscribe.mm.myTest();

	}
	

	public static void main(String [] args){

		//String sourceLoc = "D:\\workspace_test\\CallGraphSubject";
		String sourceLoc = "C:\\Users\\Boyang\\Desktop\\ASE15\\subjects\\IBFb-Tags-master\\IBFbUI";
		String xmlOutputLoc = "output3\\";

		//String DBusageInfoLoc = "DatabaseUsageInfo\\UMAS.csv";
		String DBusageInfoLoc = "DatabaseUsageInfo\\Test.csv";

		DBscribe dbscribe = new DBscribe();
		//dbscribe.run(sourceLoc, xmlOutputLoc, DBusageInfoLoc);
		dbscribe.run2(sourceLoc, xmlOutputLoc);
	}



}
