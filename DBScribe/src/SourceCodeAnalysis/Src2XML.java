package SourceCodeAnalysis;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Src2XML {


	public Src2XML(){

	}


	/**
	 * sourceLoc is the source code location (folder)
	 * outputLoc is the output location (folder) for xml files
	 * @param outputLoc
	 */
	public void sourceFolderToXML(String sourceLoc, String outputLoc){
		//Worklist
		LinkedList<File> list = new LinkedList <File> ();
		File dir = new File(sourceLoc);
		String fileName = "";
		//Start with the root directory. 
		File file[] = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory())
				list.add(file[i]);
			else
				if(file[i].getName().contains(".java")){
					fileName = file[i].getAbsolutePath();
					//System.out.println(fileName);
					sourceFileToXML(fileName, outputLoc+ fileName.substring(fileName.lastIndexOf("\\")+1) +".xml");
				}
		}

		//recursively check until list is empty
		File tmp;
		while (!list.isEmpty()) {
			tmp = list.removeFirst();
			if (tmp.isDirectory()) {
				file = tmp.listFiles();
				if (file == null)
					continue;
				for (int i = 0; i < file.length; i++) {
					if (file[i].isDirectory())
						list.add(file[i]);
					else
						if(file[i].getName().contains(".java")){
							fileName = file[i].getAbsolutePath();
							//System.out.println(fileName);
							sourceFileToXML(fileName, outputLoc+ fileName.substring(fileName.lastIndexOf("\\")+1) +".xml");
						}
				}
			}
		}
	}

	
	/**
	 * Translate a source file to XML file
	 * @param source
	 * @param XMLOutput
	 */
	public void sourceFileToXML(String source, String XMLOutput){
		try{

			String cmd = "RunSrcml.bat " + source + " " + XMLOutput;
			System.out.println("generating XML file: " + XMLOutput);
			//System.out.println(cmd);
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedInputStream in = new BufferedInputStream(p.getInputStream());     
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));     
			String lineStr;     
			while ((lineStr = inBr.readLine()) != null){        
				System.out.println(lineStr);
			}
			p.waitFor();
		}catch(Exception ex){
			ex.printStackTrace(); 
		}
	}


	public static void main(String[] args){
		Src2XML sx = new Src2XML();

		String sourceLoc = "D:\\workspace_test\\CallGraphSubject";
		//String sourceLoc = "C:\\Users\\Boyang\\Desktop\\ASE15\\subjects\\UMAS";
		String outputLoc = "output2\\";
		sx.sourceFolderToXML(sourceLoc, outputLoc);
		System.out.println("Done.");
	}

}
