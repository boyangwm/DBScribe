package SourceCodeAnalysis;

/**
 * Method entity to represent a method
 * @author Boyang
 *
 */
public class Method {
	
	private String methodName = "";
	private String methodSpecifier = "";
	
	
	
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
	
	
	
	public String toString(){
		String str_ret = "";
		str_ret += "Method :" + this.methodSpecifier +  "  " + this.methodName +"\n";
		return str_ret;
		
	}
	
	

}
