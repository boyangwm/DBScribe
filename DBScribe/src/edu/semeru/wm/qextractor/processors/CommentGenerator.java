package edu.semeru.wm.qextractor.processors;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import edu.semeru.wm.qextractor.model.MethodQueryVO;
import edu.semeru.wm.qextractor.model.QueryType;
import edu.semeru.wm.qextractor.model.QueryVO;
import edu.semeru.wm.qextractor.model.TableConstraintsVO;

public class CommentGenerator {

	/*private HashMap<String, TableConstraintsVO> constraints;
	private HashMap<String, List<String>> foreignKeyTables;
	private HashMap<String, MethodQueryVO> methodQueriesMap;*/
	ResourceBundle templates;
	
	public CommentGenerator(){
		templates = ResourceBundle.getBundle("edu.semeru.wm.qextractor.processors.templates");
		
	}

	
	
	public void process( HashMap<String, TableConstraintsVO> constraints,
						HashMap<String, List<String>> foreignKeyTables,
						HashMap<String, MethodQueryVO> methodQueriesMap){
		StringBuilder comment = null;
		Set<String> keys = methodQueriesMap.keySet();
		MethodQueryVO methodQueryVO = null;
		for(String method: keys){
			comment = new StringBuilder();
			methodQueryVO = methodQueriesMap.get(method);
			
			
			
			
			if(methodQueryVO.getNonErrorQueriesNumber() > 0){
				//The method has local queries
				comment.append(templates.getString("local_header"));
				comment.append(getSentenceFromSelectQueries(methodQueryVO.getSelectQueries()));
				comment.append(getSentencesFromUpdateQueries(methodQueryVO.getUpdateQueries()));
				comment.append(getSentencesFromDeleteQueries(methodQueryVO.getDeleteQueries()));
				comment.append(getSentencesFromInsertQueries(methodQueryVO.getInsertQueries()));
				comment.append(getSentenceFromDropQueries(methodQueryVO.getDropQueries()));
				comment.append(getSentenceFromAlterQueries(methodQueryVO.getAlterQueries()));
				comment.append(getSentenceFromCreateQueries(methodQueryVO.getCreateQueries()));
				comment.append(getSentenceFromTruncateQueries(methodQueryVO.getTruncateQueries()));
			}
			
			if(methodQueryVO.getCalledMethods().size() > 0){
				//The method is a caller of other methods
				comment.append(templates.getString("delegate_header"));
				
			}
			
			System.out.println(methodQueryVO.getSignature());
			System.out.println(comment.toString());              
			
		}
		
	}
	
	

	private String getSentenceFromSelectQueries(List<QueryVO> queries){
		String sentence = "";
		String replacement = "";
		
		if(queries.size() > 0){
			sentence = templates.getString("local_queries");
			for (QueryVO queryVO : queries) {
				replacement += queryVO.getTables().toString();
			}
			
			sentence = sentence.replace("#table", formatList(replacement));
		}
	
		return sentence;
	}
	
	private String getSentenceFromCreateQueries(List<QueryVO> queries){
		String sentence = "";
		String replacement = "";
		
		if(queries.size() > 0){
			sentence = templates.getString("local_create");
			for (QueryVO queryVO : queries) {
				replacement += queryVO.getTables().toString();
			}
			
			sentence = sentence.replace("#table", formatList(replacement));
		}
	
		return sentence;
	}
	
	private String getSentenceFromDropQueries(List<QueryVO> queries){
		String sentence = "";
		String replacement = "";
		
		if(queries.size() > 0){
			sentence = templates.getString("local_drop");
			for (QueryVO queryVO : queries) {
				replacement += queryVO.getTables().toString();
			}
			
			sentence = sentence.replace("#table", formatList(replacement));
		}
	
		return sentence;
	}
	
	private String getSentenceFromAlterQueries(List<QueryVO> queries){
		String sentence = "";
		String replacement = "";
		
		if(queries.size() > 0){
			sentence = templates.getString("local_alter");
			for (QueryVO queryVO : queries) {
				replacement += queryVO.getTables().toString();
			}
			
			sentence = sentence.replace("#table", formatList(replacement));
		}
	
		return sentence;
	}
	
	private String getSentenceFromTruncateQueries(List<QueryVO> queries){
		String sentence = "";
		String replacement = "";
		
		if(queries.size() > 0){
			sentence = templates.getString("local_truncate");
			for (QueryVO queryVO : queries) {
				replacement += queryVO.getTables().toString();
			}
			sentence = sentence.replace("#table", formatList(replacement));
		}
	
		return sentence;
	}
	
	private String getSentencesFromUpdateQueries(List<QueryVO> queries){
		String baseSentence;
		String sentence = "";
		StringBuffer sentences = new StringBuffer();
		String replacement = "";
		
		if(queries.size() > 0){
			baseSentence = templates.getString("local_update");
			for (QueryVO queryVO : queries) {
				sentence = new String();
				sentence = baseSentence;
				replacement = queryVO.getAttributes().toString();
				
				sentence = sentence.replace("#table", queryVO.getTables().get(0));
				sentence = sentence.replace("#attr", formatList(replacement));
				sentences.append(sentence);
			}
			
		}
	
		return sentences.toString();
	}
	
	private String getSentencesFromInsertQueries(List<QueryVO> queries){
		String baseSentence;
		String sentence = "";
		StringBuffer sentences = new StringBuffer();
		String replacement = "";
		
		if(queries.size() > 0){
			baseSentence = templates.getString("local_insert");
			for (QueryVO queryVO : queries) {
				sentence = new String();
				sentence = baseSentence;
				replacement = queryVO.getAttributes().toString();
				
				sentence = sentence.replace("#table", queryVO.getTables().get(0));
				sentence = sentence.replace("#attr", formatList(replacement));
				sentences.append(sentence);
			}
			
		}
	
		return sentences.toString();
	}
	
	private String getSentencesFromDeleteQueries(List<QueryVO> queries){
		String baseSentence;
		StringBuffer sentences = new StringBuffer();
		String sentence = "";
		String replacement = "";
		
		if(queries.size() > 0){
			baseSentence = templates.getString("local_delete");
			for (QueryVO queryVO : queries) {
				sentence = new String();
				sentence = baseSentence;
				replacement = queryVO.getTables().toString();
				sentence = sentence.replace("#table", formatList(replacement));
				sentences.append(sentence);
			}
			
		}
	
		return sentences.toString();
	}
	
	private String formatList(String str){
		String line = str.replace("][",", ").replace("[","").replace("]", "");
		
		return line;
	}
    
}
