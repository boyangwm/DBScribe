package edu.semeru.wm.qextractor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.internal.core.search.matching.SecondaryTypeDeclarationPattern;

public class TableConstraintsVO {

	private String tableName;
	private List<String> primaryKeys;
	private List<KeyVO> foreignKeys;
	private List<String> uniqueFields;
	private List<String> autonumericFields;
	private List<String> noNullFields;
	private HashMap<Long, List<String>> varcharLengths;
	
	
	public TableConstraintsVO(){
		primaryKeys = new ArrayList<String>();
		foreignKeys = new ArrayList<KeyVO>();
		uniqueFields = new ArrayList<String>();
		autonumericFields = new ArrayList<String>();
		noNullFields = new ArrayList<String>();
		varcharLengths = new HashMap<Long, List<String>>();
	}
	
	
	
	public String getTableName() {
		return tableName;
	}



	public void setTableName(String tableName) {
		this.tableName = tableName;
	}



	public void addPrimayKey(String column){
		primaryKeys.add(column);
	}
	
	public void addForeignKey(KeyVO key){
		foreignKeys.add(key);
	}
	
	public void adUniqueField(String column){
		uniqueFields.add(column);
	}
	
	public void addAutonumericField(String column){
		autonumericFields.add(column);
	}
	
	public void addNoNullField(String column){
		noNullFields.add(column);
	}
	
	public void addVarcharConstraint(long length, String column){
		List<String> temp = null;
		if(!varcharLengths.containsKey(length)){
			temp = new ArrayList<String>();
		}else{
			temp = varcharLengths.get(length);
		}
		temp.add(column);
		varcharLengths.put(length, temp);
		
	}

}
