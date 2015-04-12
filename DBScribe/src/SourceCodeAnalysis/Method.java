package SourceCodeAnalysis;

import java.util.ArrayList;

/**
 * Method entity to represent a method
 * @author Boyang
 *
 */
public class Method {
	
	//The class this method belong to 
	private Class classBelong = null;
	
	private String methodName = "";
	private String methodSpecifier = "";
	private int methodNumArgs = 0;
	
	private ArrayList <CallStmt> funcCallStmts = new ArrayList <CallStmt>(); 
	
	
	/**
	 * class constructor. create a method belong to classBelong
	 * @param classBelong
	 */
	public Method(Class classBelong){
		this.classBelong = classBelong;
	}
	
	
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	

	
	/**
	 * @return the methodSpecifier
	 */
	public String getMethodSpecifier() {
		return methodSpecifier;
	}
	

	/**
	 * return number of args
	 * @return
	 */
	public int getMethodNumArgs(){
		return this.methodNumArgs;
	}
	
	
	/**
	 * return the class it belongs.
	 * @return
	 */
	public Class getClassBelong(){
		return this.classBelong;
	}
	
	
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	
	/**
	 * @param methodSpecifier the methodSpecifier to set
	 */
	public void setMethodSpecifier(String methodSpecifier) {
		this.methodSpecifier = methodSpecifier;
	}
	
	
	
	public void setMethodNumArgs(int numMethodArgs){
		this.methodNumArgs = numMethodArgs;
	}
	
	
	
	/**
	 * add function call cs in this method
	 * @param cs
	 */
	public void addfuncCallStmt(CallStmt cs){
		funcCallStmts.add(cs);
		
	}
	


	
	public String toString(){
		String str_ret = "";
		str_ret += "Class :" + this.classBelong.getClassName();
		str_ret += "  Method :" + this.methodSpecifier +  "  " + this.methodName +"(" +methodNumArgs+")"+"\n";
		System.out.println(this.methodName  + "   " + funcCallStmts.size());
		for(CallStmt cs: funcCallStmts){
			str_ret += cs.toString() + "\n";		
		}
		return str_ret;
		
	}
	
	

}
