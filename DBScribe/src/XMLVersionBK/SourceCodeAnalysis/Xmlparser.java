package XMLVersionBK.SourceCodeAnalysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;


public class Xmlparser {

	Namespace ns = Namespace.getNamespace("http://www.sdml.info/srcML/src");


	public Xmlparser(){

	}


	/**
	 * Parse the xml folder
	 * @param folderName
	 */
	public void parseXMLFolder(String folderName){
		File dir = new File(folderName);
		String fileName = "";


		File file[] = dir.listFiles();

		for (int i = 0; i < file.length; i++) {
			//int i = 0;
			fileName = file[i].getAbsolutePath();
			//fileName = "Admin.java.xml";
			//fileName = "abc.xml";

			parseXMLFile(fileName);
		}
	}


	/**
	 * Parse classes in the file
	 * @param fileName
	 */
	private void parseXMLFile(String fileName){
		System.out.println(fileName);
		try{
			SAXBuilder builder = new SAXBuilder();

			Document document = (Document) builder.build(new File(fileName));
			Element rootNode = document.getRootElement();

			//get all classes in the file
			List < Element> listClass = rootNode.getChildren("class", ns);
			//process each class in the file
			for(Element eleClass : listClass){
				Class newCL = new Class();

				//=====  General info start  =======
				//*********** "package"   ***********
				Element elePackage = rootNode.getChild("package", ns);
				ArrayList <String> packageName = new ArrayList <String>();
				List < Element> packNameList = elePackage.getChildren("name", ns);
				for(int k = 0; k < packNameList.size(); k++){
					String name = packNameList.get(k).getValue();
					packageName.add(name);
					//System.out.println(" * " + name);
				}
				newCL.setPackageName(packageName);

				//*********** "package"   ***********
				List < Element> listImport = rootNode.getChildren("import", ns);
				System.out.println(listImport.size());
				//<import><name><name>realname</name><name/></name> </import>
				for (int j = 0; j < listImport.size(); j++) {
					Element curImport = (Element) listImport.get(j);
					Element curImportName = curImport.getChild("name", ns);  //
					ArrayList <String> strImportName = new ArrayList <String>();
					List < Element> curImportNameList = curImportName.getChildren("name", ns);
					for(int k = 0; k < curImportNameList.size(); k++){
						String name = curImportNameList.get(k).getValue();
						strImportName.add(name);
						//System.out.println(" * " + name);
					}
					newCL.addImport(strImportName);
				}

				//=====  General info end  =======

				//*********** "class name"   ***********
				newCL.setClassName(eleClass.getChildText("name", ns));

				//*********** "class name"   ***********
				Element tempBlock = eleClass.getChild("block", ns);
				if(tempBlock != null){
					List < Element> listMethod = tempBlock.getChildren("function", ns);
					//<function> </function>
					for(Element eleMethod: listMethod) {
						Method curMethod = parseMethodElement(newCL, eleMethod);
						newCL.addMethod(curMethod);
						//save current method (we save each method after we create it)
						DBscribe.storeMethod(curMethod);
						System.out.println(curMethod);
					}

				}else{
					System.out.println("WARNING: Emptey class ");
				}
				DBscribe.storeClass(newCL);
				//System.out.println("class print \n" + newCL.toString());
			}

		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * Given method element, this function can parse the element to Method class
	 * @param classBelong
	 * @param eleMethod
	 * @return
	 */
	private Method parseMethodElement(Class classBelong, Element eleMethod){
		//method name
		Method ret_Method = new Method(classBelong);
		String methodName = eleMethod.getChildText("name", ns);
		ret_Method.setMethodName(methodName);


		//specifier: public private etc.
		String methodSpecifier = "";
		Element tempType = eleMethod.getChild("type", ns);
		if(tempType != null){
			methodSpecifier = tempType.getChildText("specifier", ns);
		}
		ret_Method.setMethodSpecifier(methodSpecifier);

		// Extract number of args
		int numMethodArgs = 0;
		Element tempParasList = eleMethod.getChild("parameter_list", ns);
		if(tempParasList != null){
			List < Element> args = tempParasList.getChildren("param", ns);
			numMethodArgs = args.size();
		}
		ret_Method.setMethodNumArgs(numMethodArgs);

		//sub blocks
		Queue <Element> queueBlocks =  new LinkedList <Element> ();
		queueBlocks.add(eleMethod.getChild("block", ns));
		while(queueBlocks.size() >= 1){

			Element curBlock = queueBlocks.poll();

			/*
			 * catching function calls from statements
			 */
			Element tempEle = null;

			ArrayList<Element> callElementList = new ArrayList<Element>();

			Queue <Element> queueExpr = new LinkedList <Element> ();


			//<decl_stmt><decl><init><expr><call> 
			List <Element> declList = curBlock.getChildren("decl_stmt", ns);
			for(Element curDecl : declList){
				Element declEle = curDecl.getChild("decl", ns); 
				if(declEle!= null){
					tempEle = declEle.getChild("init", ns); 	//<init><expr><call> 
					if(tempEle!= null){
						Element exprEle = tempEle.getChild("expr", ns);	//<expr><call> 
						if(exprEle != null){
							queueExpr.add(exprEle);
						}
					}
				}
			}

			//<expr_stmt><expr><call>
			List <Element> expStmtList = curBlock.getChildren("expr_stmt", ns);
			for(Element curExpstmt : expStmtList){
				Element exprEle = curExpstmt.getChild("expr", ns);	//<expr><call> 
				if(exprEle != null){
					queueExpr.add(exprEle);
				}
			}



			while(queueExpr.size() > 0){
				Element CurExprEle = queueExpr.poll();
				Element callEle = CurExprEle.getChild("call", ns);
				if(callEle != null){
					callElementList.add(callEle);
					//return CallStmt
					//CallStmt cs = parseToCallStmt(callEle);
					//ret_Method.addfuncCallStmt(cs);
				}
				Element nameEle = CurExprEle.getChild("name", ns);
				if(nameEle != null){
					Element indexEle = nameEle.getChild("index", ns);
					if(indexEle != null){
						Element exprEle = indexEle.getChild("expr", ns);
						if(exprEle != null){
							queueExpr.add(exprEle);
						}
					}
				}
			}





			//for each call element
			for(Element callEle:  callElementList){
				CallStmt cs = parseToCallStmt(callEle);
				//System.out.println(cs);
				if(cs != null)
					ret_Method.addfuncCallStmt(cs);
			}


			/*
			 * Add other blocks into the queue
			 * nested while if etc.
			 */
			Element blockNew = null;
			//<if><then><block>
			List <Element> ifList = curBlock.getChildren("if", ns);
			for(Element curif : ifList){
				Element eleThen = curif.getChild("then", ns);
				if(eleThen != null){
					blockNew = eleThen.getChild("block", ns);
					if(blockNew != null){
						queueBlocks.add(blockNew);
					}
				}
				Element eleElse = curif.getChild("else", ns);
				if(eleElse != null){
					blockNew = eleElse.getChild("block", ns);
					if(blockNew != null){
						queueBlocks.add(blockNew);
					}
				}
			}




			//while><block>
			List <Element> whileList = curBlock.getChildren("while", ns);
			for(Element curWhile : whileList){
				blockNew = curWhile.getChild("block", ns);
				if(blockNew != null){
					queueBlocks.add(blockNew);
				}
			}

			//<try><block>
			List <Element> tryList = curBlock.getChildren("try", ns);
			for(Element curTry : tryList){
				blockNew = curTry.getChild("block", ns);
				if(blockNew != null){
					queueBlocks.add(blockNew);
				}
			}


			//<catch><block>
			List <Element> catchList = curBlock.getChildren("catch", ns);
			for(Element curCatch : catchList){
				blockNew = curCatch.getChild("block", ns);
				if(blockNew != null){
					queueBlocks.add(blockNew);
				}
			}

			//<for> <block>
			List <Element> forList = curBlock.getChildren("for", ns);
			for(Element curFor : forList){
				blockNew = curFor.getChild("block", ns);
				if(blockNew != null){
					queueBlocks.add(blockNew);
				}
			}
			//do
			//switch
		}

		return ret_Method;

	}



	/**
	 * Parse  <call> to CallStmt class
	 * @param ele
	 * @return
	 */
	private CallStmt parseToCallStmt(Element ele){


		//might be not necessary to store full name 
		//System.out.XXX or a.out.XXX is difficult in syntax level anyway.
		ArrayList <String> fullName = new ArrayList <String>();
		Element outerName = ele.getChild("name", ns);
		if(outerName == null){
			return null;
		}

		//two level <name>
		//For example: <call><name><name>Employee</name><operator>.</operator><name>addIntoDatabase</name></name><argument_list>
		List < Element> curNameList = outerName.getChildren("name", ns);
		for(int i = 0; i < curNameList.size(); i++){
			String namePartial = curNameList.get(i).getValue();
			fullName.add(namePartial);
		}

		// Extract number of args
		// Example:<argument_list>(<argument><expr><name>retreivedAdminUIN</name></expr></argument>)</argument_list>
		int argsNum = 0;
		Element arg_list = ele.getChild("argument_list", ns);
		if(arg_list != null){
			List < Element> args = arg_list.getChildren("argument", ns);
			argsNum = args.size();
		}

		//Extract function call name (last)
		if(curNameList.size() > 0){
			String functionName = curNameList.get(curNameList.size()-1).getValue();
			CallStmt cs = new CallStmt(functionName, argsNum, fullName);
			//System.out.println(functionName   +  "    "  + argsNum + "  **"); 
			return cs;
		}else{
			return null;
		}
	}


	//testing for XmlParser
	public static void main(String [] args){
		Xmlparser xp = new Xmlparser();
		xp.parseXMLFolder("output\\");
		MethodKey mk = new MethodKey ("updateAdminDept", 1);
		ArrayList<Method> al = DBscribe.mm.getMethodList(mk);
		System.out.println("print updateAdminDept");
		for(Method m : al){
			System.out.println(m);
		}
		 DBscribe.mm.myTest();

	}

}
