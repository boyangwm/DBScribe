package edu.semeru.wm.qextractor.helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;

public class ASTHelper {

	
	public static CompilationUnit getAST(String source) {
		
		 Map options = new HashMap();
		    
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
		parser.setCompilerOptions(options);
		
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		
		return (CompilationUnit) parser.createAST(null);
	}
	
	
public static CompilationUnit getASTAndBindings(String source, String projectPath, String unitName) {
 	  

		String[] sources = { projectPath };
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setEnvironment(null, sources, new String[] { "UTF-8" }, true);
		
		Hashtable<String, String> options = JavaCore.getDefaultOptions();
	    options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
	    options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
		
	    parser.setCompilerOptions(options);
	    parser.setUnitName(unitName);
	    
		return (CompilationUnit) parser.createAST(null);
	}
	
	public static List<Statement> getStatementsFromCU(CompilationUnit cu){
		StatementVisitor stmVisitor = new StatementVisitor();
		cu.accept(stmVisitor);
		return stmVisitor.getStatements();		
	}
	
	public static List<StringLiteral> getStringLiteralsFromCU(CompilationUnit cu){
		StringLiteralVisitor stlVisitor = new StringLiteralVisitor();
		cu.accept(stlVisitor);
		return stlVisitor.getLiterals();	
	}
	
	public static List<MethodDeclaration> getMethodDeclarationsFromCU(CompilationUnit cu){
		MethodDeclarationVisitor mdVisitor = new MethodDeclarationVisitor();
		cu.accept(mdVisitor);
		return mdVisitor.getMethods();
	}
	
	
	public static HashMap<String, HashSet<String>> getMethodCallsFromCU(CompilationUnit cu){
		MethodCallVisitor mcVisitor = new MethodCallVisitor();
		cu.accept(mcVisitor);
		return  mcVisitor.getCalls();
	}
}
