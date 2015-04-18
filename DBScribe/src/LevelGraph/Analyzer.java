package LevelGraph;

import java.util.ArrayList;

import DatabaseInfo.DatabaseUsage;
import SourceCodeAnalysis.CallStmt;
import SourceCodeAnalysis.DBscribe;
import SourceCodeAnalysis.Method;
import SourceCodeAnalysis.MethodKey;

public class Analyzer {
	
	//To Do 1: need a level tracker  <Method, depth> and use depth to bounded loop
	//keep track the highest one
	//To Do 2: caller graph has to be built
	
	/**
	 * DB usage info
	 */
	LevelGraph lg = new LevelGraph(); 



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
			}
		}

		boolean hasChange = true;
		while(hasChange){
			hasChange = false;
			for(Method m: allMethods){
				lg.addNode(m);
				ArrayList<CallStmt> curCallStmts = m.getfuncCallStmt();
				for(CallStmt cs: curCallStmts){
					MethodKey mk = new MethodKey ( cs.functionName, cs.numParas);
					ArrayList<Method> alMethod = DBscribe.mm.getMethodList(mk);
					for(Method calleeM : alMethod){
						//means calleeM is not related with DB
						if(!lg.containsMethod(calleeM)){
							continue;
						}else{
							boolean localChange = lg.addCalleeEdge(m, calleeM);
							if(localChange){
								hasChange = true;
							}
						}
					}

				}

			}
		}
		lg.lgPrint();



	}

}
