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
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	
	/**
	 * @return the methodSpecifier
	 */
	public String getMethodSpecifier() {
		return methodSpecifier;
	}
	
	
	
	
	/**
	 * @param methodSpecifier the methodSpecifier to set
	 */
	public void setMethodSpecifier(String methodSpecifier) {
		this.methodSpecifier = methodSpecifier;
	}
	
	
	
	public void addfuncCallStmt(CallStmt cs){
		funcCallStmts.add(cs);
		
	}
	
	
	public void setMethodNumArgs(int numMethodArgs){
		this.methodNumArgs = numMethodArgs;
	}
	
	
	public String toString(){
		String str_ret = "";
		str_ret += "Method :" + this.methodSpecifier +  "  " + this.methodName +"(" +methodNumArgs+")"+"\n";
		for(CallStmt cs: funcCallStmts){
			str_ret += cs.toString() + "\n";		
		}
		return str_ret;
		
	}
	
	

}
