package SourceCodeAnalysis;

import java.util.ArrayList;

/**
 * Class entity to represent a class
 * @author Boyang
 */
public class Class {
	
	private Name packageName = new Name();  			// the package name
	private ArrayList <Name> imports = new ArrayList <Name> (); //shows all import information
	private String className = "";					//the class name
	
	
	/**
	 * all methods of this class
	 */
	private ArrayList <Method> allMethods = new ArrayList <Method>();
	
	public Class(){
		
	}
	
	/**
	 * Set package name
	 * @param strlistPackageName
	 */
	public void setPackageName(ArrayList<String> strlistPackageName) {
		this.packageName = new Name(strlistPackageName);
		
	}
	
	
	/**
	 * Set class name
	 * @param strClassName
	 */
	public void setClassName(String strClassName) {
		this.className = strClassName;	
	}
	
	
	/**
	 * return class name
	 * @return
	 */
	public String getClassName() {
		return this.className;	
	}
	
	
	/**
	 * return package name 
	 * @return
	 */
	public String getPackageName(){
		return this.packageName.toString();
	}
	
	
	/**
	 * add an import information to the class
	 * @param strImportName
	 */
	public void addImport(ArrayList<String> strlistImportName){
		Name nameNewImport = new Name(strlistImportName);
		imports.add(nameNewImport);
	}
	
	
	/**
	 * Add a method m to the method list
	 * @param m
	 */
	public void addMethod(Method m){
		allMethods.add(m);
		
	}
	
	
	public String toString(){
		String str_ret = "";
		str_ret += "=====  package   ======  \n";
		str_ret += packageName.toString() + "\n";
		
		str_ret += "=====  name   ======  \n";
		str_ret += className + "\n";
		
		str_ret += "=====  imports   ======  \n";
		for(Name nameImport:imports){
			str_ret += nameImport.toString() + "\n";
		}
		
		return str_ret;
	}

}
