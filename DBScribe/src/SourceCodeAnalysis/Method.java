package SourceCodeAnalysis;

import java.util.ArrayList;

/**
 * Method entity to represent a method
 * @author Boyang
 *
 */
public class Method {
	
	private String methodName = "";
	private String methodSpecifier = "";
	private int methodNumArgs = 0;
	
	private ArrayList <CallStmt> funcCallStmts = new ArrayList <CallStmt>(); 
	
	
	
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
