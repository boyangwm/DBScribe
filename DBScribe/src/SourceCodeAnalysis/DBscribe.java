package SourceCodeAnalysis;

import java.util.ArrayList;

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
	public void parseXMLFolderAndGetInfo(String folder){
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
	
	
	public void run(){
		parseXMLFolderAndGetInfo("output\\");
		DBscribe.mm.myTest();
		DBscribe.cm.printClassesName();
	}
	
	
	
	public static void main(String [] args){
		DBscribe dbscribe = new DBscribe();
		dbscribe.run();
		

	}



}
