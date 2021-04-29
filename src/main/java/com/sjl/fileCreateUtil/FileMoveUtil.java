package com.sjl.fileCreateUtil;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class FileMoveUtil {

	public static void main(String[] args) throws IOException {
//		Collection<File> fileCollections = step1();
		
//		step2(fileCollections);
		
//		step3();
	}
	
	private static Collection<File> step1() {
		File folder = new File("G:\\NEW PROJECT");
		Collection<File> fileCollections = FileUtils.listFiles(folder, new String[] {"pdf", "docx"}, true);
		System.out.println(fileCollections.size() + " files found");
		return fileCollections;
	}
	
	private static void step2(Collection<File> fileCollections) {
		for (File file : fileCollections) {
		    if (file.isFile()) {
		        File newFile = new File("G:\\NEW PROJECT\\" + file.getName());
		        try {
		            FileUtils.copyFile(file, newFile);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        System.out.println("finished copying " + file.getName());
		    }
		}
	}
	
	private static void step3() throws IOException {
String baseFolder = "G:\\NEW PROJECT";
		
		File folder = new File(baseFolder);
		Collection<File> fileCollections = FileUtils.listFiles(folder, new String[] {"pdf"}, false);
		System.out.println(fileCollections.size() + " files found");
		
		for (File file : fileCollections) {
		    if (file.isFile()) {
		    	String[] separated = file.getName().split("-");
		    	String finalNameNumber = StringUtils.trim(separated[0]);
		        System.out.println(finalNameNumber);
		        File dir = new File(baseFolder + "\\" + finalNameNumber);
		        
		        if(dir.exists()) {
		        	System.out.println("moving " + file.getName());
		        	File newFile = new File(baseFolder + "\\" + finalNameNumber + "\\" + "Monitoring - " + file.getName());
			        FileUtils.moveFile(file, newFile);
			        System.out.println(dir.getName());
			        File newdir = new File(baseFolder + "\\" + finalNameNumber + " - OPEN");
			        
			        dir.renameTo(newdir);
		        }else {
		        	System.out.println("no folder found for " + file.getName());
		        }
		        
		    }
		}
	}

}
