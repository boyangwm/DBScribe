package XMLVersionBK.SourceCodeAnalysis;

/**
 * Entity of call statement
 * @author Boyang
 *
 */
import java.util.ArrayList;



public class CallStmt {
	//record the function name. Last "."
	public String  functionName = null;
	
	//number of args in the call stmt.
	public int numParas = 0;
	
	//might not use 
	public Name fullName = new Name();  			// fullName
	

	
	
	public CallStmt(String name, int numberPara){
		this.functionName = name;
		this.numParas = numberPara;
	}
	
	
	public CallStmt(String name, int numberPara, ArrayList <String> fullName){
		this.functionName = name;
		this.numParas = numberPara;
		this.fullName = new Name(fullName);
	}
	
	
	public String toString(){
		String retStr = "";
		retStr += "Call : " +  functionName + "(" + numParas +")";
		return retStr;
	}
	

}
