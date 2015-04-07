package SourceCodeAnalysis;

import java.io.File;
import java.io.IOException;
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



	public void parseXMLFolder(String folderName){
		File dir = new File(folderName);
		String fileName = "";
		SAXBuilder builder = new SAXBuilder();
		Namespace ns = Namespace.getNamespace("http://www.sdml.info/srcML/src");

		File file[] = dir.listFiles();
		try {
			//for (int i = 0; i < file.length; i++) {
			int i = 0;
			fileName = file[i].getAbsolutePath();
			//fileName = "Admin.java.xml";
			//fileName = "abc.xml";
			
			System.out.println(fileName);
			Document document;

			document = (Document) builder.build(new File(fileName));
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("class", ns);
			System.out.println(list.size());
			for (int j = 0; j < list.size(); j++) {

				Element node = (Element) list.get(j);
				String nameClass = node.getChildText("name", ns);
				System.out.println("Class Name : " + nameClass);
			}
			
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




	//testing for XmlParser
	public static void main(String [] args){
		Xmlparser xp = new Xmlparser();
		xp.parseXMLFolder("output\\");

	}

}
