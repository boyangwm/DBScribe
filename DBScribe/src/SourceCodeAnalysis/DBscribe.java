package SourceCodeAnalysis;

import java.util.ArrayList;

import DatabaseInfo.DatabaseUsage;
import LevelGraph.Analyzer;

public class DBscribe {
	public static ClassManager cm = new ClassManager();
	public static MethodManager mm = new MethodManager();
	
	
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
	public void run(){
		//parse xml file and store SC info
		parseXMLFolderAndGetInfo("output2\\");
//		DBscribe.mm.myTest();
//		DBscribe.cm.printClassesName();
		
		//parse file and store DB usage info
		DatabaseUsage du = new DatabaseUsage ();
		du.parseCSVfile("DatabaseUsageInfo\\Test.csv");
		DBscribe.mm.ImportDBUsage(du);	
		//DBscribe.mm.printAllMethod();
		
		Analyzer az = new Analyzer();
		az.buildLevelGraph();
		
		
		
		
		System.out.println("Done");

	}
	
	
	
	public static void main(String [] args){
		DBscribe dbscribe = new DBscribe();
		dbscribe.run();
	}



}
