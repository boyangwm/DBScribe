package LevelGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import SourceCodeAnalysis.Method;



public class LevelGraph {

	ArrayList <Method> nodes = new  ArrayList <Method>();
	HashMap<Method, Integer> index = new HashMap <Method, Integer>();

	/**
	 * size of current graph
	 */
	private int size = 0;




	/**
	 * record all callees
	 */
	ArrayList <HashSet <Integer>> calleeEdge = new ArrayList <HashSet <Integer>> ();

	ArrayList <HashSet <Integer>> callerEdge = new ArrayList <HashSet <Integer>> ();
	//callerEdge







	/**
	 * add a node to the level graph and build a index
	 * @param m
	 */
	public void addNode(Method m){
		if(!index.containsKey(m)){
			this.nodes.add(m);
			this.index.put(m, size++);
			this.calleeEdge.add(new HashSet <Integer>());
			this.callerEdge.add(new HashSet <Integer>());
		} 	
	}





	/**
	 * @param m_caller
	 * @param m_callee
	 * @return true if this set did not already contain the specified element
	 */
	public boolean addCalleeEdge(Method m_caller,  Method m_callee) {
		int colPos = index.get(m_caller);
		int linkedNodePos = index.get(m_callee);
		HashSet <Integer> hs = calleeEdge.get(colPos);
		return hs.add(linkedNodePos);
	}





	/**
	 * @param m
	 * @return true if this levelgraph contains a node for the method m.
	 */
	public boolean containsMethod(Method m){
		return index.containsKey(m);
	}




	public void lgPrint(){
		System.out.println("all nodes" );
		for(int i = 0; i < nodes.size(); i++){
			System.out.println("ID : " + i  + nodes.get(i).getMethodName());
		}
		System.out.println("calleeEdge : " );
		for(int i = 0; i < calleeEdge.size(); i++){
			System.out.print(i  + " --- ");

			HashSet <Integer> hs = calleeEdge.get(i);
			for(Integer ind : hs){
				System.out.print(nodes.get(ind).getMethodName() +" ;  ");
			}
			System.out.println("");
		}
	}


}
