package edu.semeru.wm.qextractor.processors;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.semeru.wm.CallGraph.LeveledCallGraphComponent;
import edu.semeru.wm.qextractor.helper.ConnectionManager;
import edu.semeru.wm.qextractor.model.ConnectionVO;
import edu.semeru.wm.qextractor.model.MethodQueryVO;
import edu.semeru.wm.qextractor.model.TableConstraintsVO;


public class DBScribe {

	/**
	 * depth constraint of analysis
	 */
	public static final int LEVELTHRESHOLD = 15;
	
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		// *Step 1: Process source code to (i) extract couples (caller-->callee), 
		// (ii) locate methods using sql queries/statements, and (iii) parse the sql queries/statements
		System.out.println("DBScribe is running");
		System.out.println("1. Running JDBCProcessor");
		JDBCProcessor processor = new JDBCProcessor();

		// Change the path here to the folder of the system under analysis
		//processor.processFolder("/Users/mariolinares/Documents/academy/SEMERU/Code-tools/Q-Extractor/examples4test/CS597-PROJECT/src/");
		processor.processFolder("C:\\Users\\Boyang\\Desktop\\ASE15\\subjects\\UMAS\\src");
		//processor.processFolder("D:\\workspace_test\\CallGraphSubject");
		System.out.println("--- JDBC processing: DONE");
		//----------------------------------------------------------------------------



		//*Step 2: extract partial graph including only call-chains related to db operations
		//TODO: Boyang here you can call your code using the following structures:

		// The key is the caller signature, and the value is a set of callees (signatures).
		HashMap<String, HashSet<String>> methodCalls = processor.getMethodCalls();

		//The key is a method signature,and the value is a VO object with all the information
		// of the queries/statements declared in that method.
		HashMap<String, MethodQueryVO> methodQueriesMap = processor.getMethodQueriesMap();

		System.out.println("2. Running Callgraph extractor");

		//TODO: Boyang's part that returns the partial call graph

		//------ assume all methods have been provided   in allMethod --------------
		HashSet <String> allMethods = new HashSet<String>();
		System.out.println("numberOfMethod : " + methodCalls.size());

		Iterator<Entry<String, HashSet<String>>> it = methodCalls.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, HashSet<String>> pair = (Map.Entry<String, HashSet<String>>)it.next();
			String caller = pair.getKey(); 
			allMethods.add(caller);
			HashSet<String> hsCallees = pair.getValue();
			for(String cStr : hsCallees){
				allMethods.add(cStr);
			}
			//it.remove(); // avoids a ConcurrentModificationException
		}
		
		
		
		
		//--------------------------------------------

		
		

		LeveledCallGraphComponent lcgComponent = new LeveledCallGraphComponent();
		lcgComponent.analyze(methodCalls, methodQueriesMap, allMethods);



		System.out.println("--- Callgraph extraction: DONE");


		/* Commented by Boyang  --- start   No DB setup on my pc yet.

		//----------------------------------------------------------------------------
		// *Step 3: Extract schema constraints from db

		DBInfoExtractor dbie = new DBInfoExtractor();
		// Change here the db connection settings
    	ConnectionVO vo = new ConnectionVO("localhost", "fusion", "fusion12345", "fusion");

    	dbie.setConnection(ConnectionManager.getConnection(vo));
    	System.out.println("3. Running Constraints extractor");

    	dbie.processTables(vo.getSchemaName());

    	HashMap<String, TableConstraintsVO> constraints = dbie.getConstraints();    	
    	HashMap<String, List<String>> foreignKeyTables = dbie.getForeignKeyTables();
    	System.out.println("--- Constraints extraction: DONE");


		//----------------------------------------------------------------------------
		// *Step 4: Generate comments using partial call graph, parsed queries, 
		// and constraints extracted from the db

		CommentGenerator comGenerator = new CommentGenerator();
		System.out.println("4. Running Comments generator");
		comGenerator.process(constraints, foreignKeyTables, processor.getMethodQueriesMap());
		System.out.println("--- Comments generation: DONE");

		Commented by Boyang  --- start */
	}

}
