package DatabaseInfo;

/**
 * 
 * Entity of method database usage info
 * @author Boyang
 *
 */
public class DatabaseMethod {
	private final String packageName;
	private final String className;
	private final String MethodName;
	private final int numPara;
	private final String dbInfo;


	public DatabaseMethod(){
		this.packageName = "";
		this.className = "";
		this.MethodName = "";
		this.numPara = 0;
		this.dbInfo = "";
	}

	
	
	/**
	 * initialized a database method information
	 * @param strList
	 * @throws Exception
	 */
	public DatabaseMethod(String [] strList) throws Exception{
		if(strList.length != 5) {
			System.out.println("ERROR: the database information file is not in the correct format");
			throw new Exception();
		}
		this.packageName = strList[0];
		this.className = strList[1];
		this.MethodName = strList[2];
		this.numPara = Integer.parseInt(strList[3]);
		this.dbInfo = strList[4];

	}



	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return MethodName;
	}

	/**
	 * @return the numPara
	 */
	public int getNumPara() {
		return numPara;
	}

	/**
	 * @return the dbInfo
	 */
	public String getDbInfo() {
		return dbInfo;
	}

	
	public String toString(){
		String str_Ret = "";
		
		str_Ret += " Package name : " + this.packageName;
		str_Ret += "  Class name : " + this.className;
		str_Ret += "  Method Name : " + this.MethodName;
		str_Ret += "  NumOfPara  :  "   +  this.numPara;
		str_Ret += "  DbInfo  :  "   +  this.dbInfo;
		return str_Ret;
		
	}






}
