package XMLVersionBK.SourceCodeAnalysis;

import java.util.ArrayList;

/**
 * store and manage all classes information
 * @author Boyang
 *
 */
public class ClassManager {
	
	/**
	 * store all all class entities.
	 */
	private ArrayList<Class> allClass = new  ArrayList<Class>();

	
	
	/**
	 * add class c into the list which stores all methods
	 * @param c
	 */
	public void addClass(Class c){
		allClass.add(c);
	}
	
	
	
	
	/**
	 * for testing
	 */
	public void printClassesName(){
		for(Class c : this.allClass) {
			System.out.println(c.getClassName());
		}
		
	}
	
	
}
