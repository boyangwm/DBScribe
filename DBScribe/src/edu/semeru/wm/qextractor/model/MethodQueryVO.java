package edu.semeru.wm.qextractor.model;

import java.util.ArrayList;
import java.util.List;

public class MethodQueryVO {

	private String name;
	private int nArgs;
	private String key;
	private String signature;
	private List<QueryVO> selectQueries;
	private List<QueryVO> insertQueries;
	private List<QueryVO> updateQueries;
	private List<QueryVO> deleteQueries;
	private List<QueryVO> dropQueries;
	private List<QueryVO> truncateQueries;
	private List<QueryVO> createQueries;
	private List<QueryVO> alterQueries;
	
	
	private List<QueryVO> errorQueries;
	private int nonErrorQueries;
	
	private List<String> calledMethods;
	
	public MethodQueryVO() {
		selectQueries = new ArrayList<QueryVO>();
		insertQueries = new ArrayList<QueryVO>();
		updateQueries = new ArrayList<QueryVO>();
		deleteQueries = new ArrayList<QueryVO>();
		dropQueries = new ArrayList<QueryVO>();
		truncateQueries = new ArrayList<QueryVO>();
		createQueries = new ArrayList<QueryVO>();
		alterQueries = new ArrayList<QueryVO>();
		
		errorQueries = new ArrayList<QueryVO>();
		calledMethods = new ArrayList<String>();
		
		nonErrorQueries = 0;
		
	}
	
	public int getNonErrorQueriesNumber(){
		return nonErrorQueries;
	}
	public void addSelect(QueryVO vo){
		selectQueries.add(vo);
		nonErrorQueries++;
	}
	
	public void addInsert(QueryVO vo){
		insertQueries.add(vo);
		nonErrorQueries++;
	}
	
	public void addUpdate(QueryVO vo){
		updateQueries.add(vo);
		nonErrorQueries++;
	}
	
	public void addDelete(QueryVO vo){
		deleteQueries.add(vo);
		nonErrorQueries++;
	}
	
	public void addDrop(QueryVO vo){
		dropQueries.add(vo);
		nonErrorQueries++;
	}
	
	public void addTruncate(QueryVO vo){
		truncateQueries.add(vo);
		nonErrorQueries++;
	}
	
	public void addCreate(QueryVO vo){
		updateQueries.add(vo);
		nonErrorQueries++;
	}
	
	public void addAlter(QueryVO vo){
		alterQueries.add(vo);
		nonErrorQueries++;
	}
	
	public void addError(QueryVO vo){
		errorQueries.add(vo);
	}

	
	public void addQuery (QueryVO vo){
		int type = vo.getType();
		if(type == QueryType.SELECT.getId()){
			addSelect(vo);
		} else if(type == QueryType.INSERT.getId()){
			addInsert(vo);
		} else if (type == QueryType.UPDATE.getId()){
			addUpdate(vo);
		} else if (type == QueryType.DELETE.getId()){
			addDelete(vo);
		} else if (type == QueryType.DROP.getId()){
			addDrop(vo);
		} else if(type == QueryType.TRUNCATE.getId()){
			addTruncate(vo);
		} else if(type == QueryType.CREATE.getId()){
			addCreate(vo);
		} else if(type == QueryType.ALTER.getId()){
			addAlter(vo);
		} else if(type == QueryType.ERROR.getId()){
			addError(vo);
		}
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getnArgs() {
		return nArgs;
	}

	public void setnArgs(int nArgs) {
		this.nArgs = nArgs;
	}

	public List<QueryVO> getSelectQueries() {
		return selectQueries;
	}

	public List<QueryVO> getInsertQueries() {
		return insertQueries;
	}

	public List<QueryVO> getUpdateQueries() {
		return updateQueries;
	}

	public List<QueryVO> getDeleteQueries() {
		return deleteQueries;
	}

	public List<QueryVO> getDropQueries() {
		return dropQueries;
	}

	public List<QueryVO> getTruncateQueries() {
		return truncateQueries;
	}

	public List<QueryVO> getCreateQueries() {
		return createQueries;
	}

	public List<QueryVO> getErrorQueries() {
		return errorQueries;
	}

	
	public List<QueryVO> getAlterQueries() {
		return alterQueries;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public void addCalledMethod(String method){
		this.calledMethods.add(method);
	}

	public List<String> getCalledMethods() {
		return calledMethods;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	

}
