package LevelGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DatabaseInfo.DatabaseUsage;
import SourceCodeAnalysis.CallStmt;
import SourceCodeAnalysis.DBscribe;
import SourceCodeAnalysis.Method;
import SourceCodeAnalysis.MethodKey;

public class Analyzer {

	//To Do 1: need a level tracker  <Method, depth> and use depth to bounded loop
	//keep track the highest one

	/**
	 * DB usage info
	 */
	LevelGraph lg = new LevelGraph(); 

	HashMap <Method, Integer> levelMap = new HashMap <Method, Integer>();



	/**
	 * constructor 
	 * @param du
	 */
	public Analyzer(){
	}



	/**
	 * build the level graph based on existing info
	 */
	public void buildLevelGraph(){
		//the leaf nodes
		ArrayList<Method> allMethods = DBscribe.mm.getAllMethods();
		for(Method m: allMethods){
			if(m.getHasDBusage() == true){
				lg.addNode(m);
				lg.addBotLevNode(m);
				levelMap.put(m, 0);
			}
		}

		boolean hasChange = true;
		while(hasChange){
			hasChange = false;
			for(Method m: allMethods){
				
				ArrayList<CallStmt> curCallStmts = m.getfuncCallStmt();
				for(CallStmt cs: curCallStmts){
					MethodKey mk = new MethodKey ( cs.functionName, cs.numParas);
					ArrayList<Method> alMethod = DBscribe.mm.getMethodList(mk);
					for(Method calleeM : alMethod){
						//means calleeM is not related with DB
						if(!lg.containsMethod(calleeM)){
							continue;
						}else{
							boolean addNew = lg.addNode(m);
							//if it's a new node, we set its level to 0
							if(addNew == true){
								levelMap.put(m, 0);
							}
							
							int calleeLvl = levelMap.get(calleeM);
							int curLvl = levelMap.get(m);
							curLvl = Math.max(curLvl, calleeLvl+1);
							if(curLvl > DBscribe.LEVELTHRESHOLD){
								return;
							}
							levelMap.put(m, curLvl);
							boolean localChange1 = lg.addCalleeEdge(m, calleeM);
							boolean localChange2 = lg.addCallerEdge(m, calleeM);
							if(localChange1 || localChange2){
								hasChange = true;
							}
						}
					}
				}
			}
			//lg.lgPrint();
			System.out.println("*************");
		}
		//lg.lgPrint();
		
	}


	ArrayList <ArrayList <Method>> calleeList;

	public ArrayList <ArrayList <Method>> findCalleeListToDB(Method m){
		calleeList = new ArrayList <ArrayList <Method>>();
		Method curM = m; 
		ArrayList <Method> path = new ArrayList <Method>();
		findCalleeListToDBHelper(m, new ArrayList <Method>(path));
		//print the list
		System.out.println("callee list of " + m.getMethodName());
		for(ArrayList <Method> al : calleeList){
			for(Method met : al){
				System.out.print(met.getMethodName() +  "----");
			}
			System.out.println("");
		}
		//end---
		return calleeList;
	}


	public void findCalleeListToDBHelper(Method m, ArrayList <Method> path){
		if(m == null){
			return;
		}
		path.add(m);

		if(m.getHasDBusage() == true)
		{
			calleeList.add(path);
			return;
		}
		else
		{
			ArrayList <Method> calleeList = lg.returnCallee(m);
			for(Method calleeM : calleeList){
				findCalleeListToDBHelper(calleeM, new ArrayList <Method>(path));
			}	    
		}   
	}




	public void printLevelMap(){
		for(Map.Entry<Method, Integer> entry : this.levelMap.entrySet()){
			String cName = entry.getKey().getClassBelong().getClassName();
			System.out.println(cName+"."+entry.getKey().getMethodName() + "  /   " + entry.getValue());
		}
	}

}
