package SourceCodeAnalysis;

import java.util.ArrayList;

/**
 * Name entity to represent a name
 * It can be a function name or package name, or name space name.
 * For example, <name><name>java</name><operator>.</operator><name>sql</name><operator>.</operator><name>Connection</name></name>
 * @author Boyang
 *
 */
public class Name {

	/**
	 * Store the name 
	 */
	ArrayList <String> strNameList = new ArrayList<String> ();

	
	
	public Name(){
		
	}
	
	
	/*
	 * Create a Name list
	 * @param nameList
	 */
	public Name(ArrayList <String> nameList){
		this.strNameList = nameList;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * toString in Namespace format
	 */
	public String toString(){
		String ret_Str = "";
		Boolean first = true;
		for(String str : this.strNameList){
			if(first){
				ret_Str += str;
				first = false;
			}else{
				ret_Str += "." + str;
			}
		}
		return ret_Str;
	}


}
