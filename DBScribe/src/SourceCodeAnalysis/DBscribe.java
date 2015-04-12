package SourceCodeAnalysis;

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
		mm.put(mk, m);
	}
	
	
	
	public static void main(String [] args){
		//call parser first
//		Xmlparser xp = new Xmlparser();
//		xp.parseXMLFolder("output\\");
		

	}


}
