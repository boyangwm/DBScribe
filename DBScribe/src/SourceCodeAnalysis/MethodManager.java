package SourceCodeAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import DatabaseInfo.DatabaseMethod;
import DatabaseInfo.DatabaseUsage;

/**
 * store and manage all methods information
 * @author Boyang
 *
 */
public class MethodManager {
	
	/**
	 * Key is a method key <method name, number of paras>
	 * Value is an arraylist of method
	 * This is an index
	 */
	private HashMap <MethodKey, ArrayList<Method>> map   =  new HashMap <MethodKey, ArrayList<Method>>();
	
	private ArrayList<Method> allMethod = new  ArrayList<Method>();
	
	/**
	 * return a method list based on the given key
	 * @param mkey
	 * @return
	 */
	public ArrayList<Method> getMethodList(MethodKey mkey){
		
		 ArrayList<Method> returnList =  map.get(mkey);
		 if(returnList!= null){
			 return returnList;
		 }else{
			 return new ArrayList<Method>();
		 }
	}
	
	
	/**
	 * put method into the list of key mk
	 * @param mk
	 * @param m
	 */
	public void putKeyAndMethod(MethodKey mk, Method m){
		ArrayList<Method> methodList = map.get(mk);
		if(methodList == null){
			ArrayList<Method> newList = new ArrayList<Method>();
			newList.add(m);
			map.put(mk, newList);
		}else{
			methodList.add(m);
		}
	}
	
	
	
	/**
	 * return number of methods with key mk 
	 * @param mk
	 * @return
	 */
	public int sizeOfList(MethodKey mk){
		ArrayList<Method> methodList = map.get(mk);
		return methodList.size();
	}
	
	
	
	
	/**
	 * add method m into the list which stores all methods
	 * @param m
	 */
	public void addMethod(Method m){
		allMethod.add(m);
	}
	
	
	
	/**
	 * import databases usage info to the methods
	 * @param du
	 */
	public void ImportDBUsage(DatabaseUsage du){

		for(DatabaseMethod dm : du.methodUsageList){
			MethodKey mk = new MethodKey (dm.getMethodName(), dm.getNumPara());
			ArrayList<Method> al = DBscribe.mm.getMethodList(mk);
			for(Method m: al){
				Class curClass = m.getClassBelong();
				if(curClass.getClassName().equals(dm.getClassName()) && 
						curClass.getPackageName().equals(dm.getPackageName())){
					m.setHasDBusage(true);
					m.setDBusageInfo(dm.getDbInfo());
				}
			}
		}
	}
	
	
	public ArrayList<Method> getAllMethods(){
		return this.allMethod;
	}
	
	public void printAllMethod(){
		for(Method m : this.allMethod){
			System.out.println(m);
		}
		
	}
	
	public void myTest(){
		
		int counter = 0;
		HashMap <Integer, Integer> hm = new HashMap<Integer, Integer> ();
		for(Method m : this.allMethod){
			String name = m.getMethodName();
			int num = m.getMethodNumArgs();
			MethodKey mk = new MethodKey(name, num);
			int listSize = sizeOfList(mk);
			System.out.println(name +"(" +num+")" +  "   " + listSize);
			Integer val =  hm.get(listSize);
			if(val == null){
				hm.put(listSize, 0);
			}else{
				val++;
				hm.put(listSize, val);
			}
			
			
			counter++;
			
		}
		System.out.println("number of all method  : "  + counter);
		
		Iterator<Entry<Integer, Integer>> it = hm.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)it.next();
	        System.out.println(  pair.getKey() + "  ---  " + "Number of keys (name + numOfParas) " + ((pair.getValue()+1)/pair.getKey()));
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	

}
