package SourceCodeAnalysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;


public class Xmlparser {

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

		//for (int i = 0; i < file.length; i++) {
		int i = 0;
		fileName = file[i].getAbsolutePath();
		//fileName = "Admin.java.xml";
		//fileName = "abc.xml";

		parseXMLFile(fileName);
		//}



	}


	/**
	 * Parse classes in the file
	 * @param fileName
	 */
	private void parseXMLFile(String fileName){
		System.out.println(fileName);
		try{

			SAXBuilder builder = new SAXBuilder();
			Namespace ns = Namespace.getNamespace("http://www.sdml.info/srcML/src");
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

				//*********** "import"   ***********
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
				List < Element> listMethod = eleClass.getChildren("function", ns);
				for(Element eleMethod: listMethod) {
					Method curMethod = parseMethodElement(eleMethod);
				}


				System.out.println("class print \n" + newCL.toString());
			}
			
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private Method parseMethodElement(Element eleMethod){
		Method ret_Method = new Method();
		
		
		return ret_Method;
		
	}
	
	
	

	//testing for XmlParser
	public static void main(String [] args){
		Xmlparser xp = new Xmlparser();
		xp.parseXMLFolder("output\\");

	}

}
