package edu.semeru.wm.CallGraph;

import java.util.ArrayList;
import java.util.HashSet;

import edu.semeru.wm.qextractor.model.MethodQueryVO;

/**
 * Method entity to represent a method
 * @author Boyang
 *
 */
public class Method {
	
	private String packageName = "";
	private String className = "";
	private String methodName = "";
	//private String methodSpecifier = "";
	private int methodNumArgs = 0;
	private boolean hasDBusage = false;  // has DBusage in Current method
	
	
	//we use MethodQueryVO record DBInfo 
	private MethodQueryVO dbInfo = new MethodQueryVO();
	
	
	
	
	
	private HashSet<String> funcCalls = new HashSet <String>(); 
	
	
	/**
	 * class constructor. create a method belong to classBelong
	 */
	public Method(String pName, String cName, String mName,  int numOfArgs){
		this.packageName = pName;
		this.className = cName;
		this.methodName = mName;
		this.methodNumArgs = numOfArgs;
	}
	
	
	
	
	public Method() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	/**
	 * return package name
	 * @return
	 */
	public String getPackageName(){
		return this.packageName;
	}
	
	
	/**
	 * return class name
	 * @return
	 */
	public String getClassName(){
		return this.className;
	}
	

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	

	
//	/**
//	 * @return the methodSpecifier
//	 */
//	public String getMethodSpecifier() {
//		return methodSpecifier;
//	}
//	

	/**
	 * return number of args
	 * @return
	 */
	public int getMethodNumArgs(){
		return this.methodNumArgs;
	}
	
	

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	
//	/**
//	 * @param methodSpecifier the methodSpecifier to set
//	 */
//	public void setMethodSpecifier(String methodSpecifier) {
//		this.methodSpecifier = methodSpecifier;
//	}
//	
	
	
	public void setMethodNumArgs(int numMethodArgs){
		this.methodNumArgs = numMethodArgs;
	}
	
	
	
	/**
	 * setter boolean DBusage 
	 * @param b
	 */
	public void setHasDBusage(boolean b) {
		this.hasDBusage = b;
	}
	
	
	
	/**
	 * getter boolean DBusage
	 * @return
	 */
	public boolean getHasDBusage() {
		return hasDBusage;
	}
	
	
	
	/**
	 * temporarily record the info
	 * @param MethodQueryVO mvo
	 */
	public void setDBusageInfo(MethodQueryVO mvo) {
		this.dbInfo = mvo; 
	}
	
	
	
	/**
	 * return the database info
	 */
	public MethodQueryVO getDBusageInfo() {
		return this.dbInfo; 
	}
	
	
	
	/**
	 * add function call cs in this method
	 * @param cs
	 */
	public void addfuncCall(String fc){
		this.funcCalls.add(fc);
	}
	
	
	
	public HashSet <String> getfuncCalls(){
		return this.funcCalls;
	}
	

	public String CreateKey(){
		//return key |||
		return "";
	}

	
	public String toString(){
		String str_ret = "";
		str_ret += "Class :" + this.packageName +"."+this.className;
		str_ret += "  Method :" +  "  " + this.methodName +"(" +methodNumArgs+")";
		str_ret += " hasDBusage  :  " + hasDBusage;
		str_ret += " DBInfo  :  " + this.dbInfo;
		str_ret += " Callees : ";
		for(String fc: funcCalls){
			str_ret += fc.toString() + ";    ";		
		}
		return str_ret;
		
	}
	
	

}
