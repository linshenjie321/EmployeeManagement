package com.sjl.fileCreateUtil;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileCreateUtil {

	public static void main(String[] args) {
		File folder = new File("G:\\NEW PROJECT");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	String[] separated = file.getName().split("-");
		    	String finalName = separated[0];
		        System.out.println("creating directory for " + finalName);
		        File theDir = new File("G:\\NEW PROJECT\\" + finalName);
		        if (!theDir.exists()){
		            theDir.mkdirs();
		        }
		        System.out.println("finished creating directory for " + finalName);
		        System.out.println("start copying " + file.getName() + " to " + theDir.getAbsolutePath());
		        File newFile = new File("G:\\NEW PROJECT\\" + finalName + "\\" + file.getName());
		        try {
		            FileUtils.copyFile(file, newFile);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        System.out.println("finished copying " + file.getName() + " to " + theDir.getAbsolutePath());
		    }
		}

	}

}
