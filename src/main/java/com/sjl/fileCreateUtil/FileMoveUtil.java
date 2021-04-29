package com.sjl.fileCreateUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class FileMoveUtil {

	public static void main(String[] args) throws IOException {
		
		String baseFolder = "G:\\NEW PROJECT";
		String fileFolder = "G:\\NEW PROJECT\\ILE - Class Modification and Closure Letters";
		
		File baseFolderF = new File(baseFolder);
		File folder = new File(fileFolder);
		Collection<File> fileCollections = FileUtils.listFiles(folder, new String[] { "pdf" , "docx"}, false);

		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		File[] foldersArray = baseFolderF.listFiles(fileFilter);
		List<String> filterNumberFolderNameList = new ArrayList<>();
		for (File eachfolder : foldersArray) {
			String name = StringUtils.trim(eachfolder.getName().replaceAll("[^0-9]", ""));
			if(StringUtils.isNotBlank(name)) {
				filterNumberFolderNameList.add(name);
			}
			
		}
		System.out.println(filterNumberFolderNameList.size() + " folders found...");
		System.out.println(filterNumberFolderNameList);
		

		for (File file : fileCollections) {
			if (file.isFile()) {
				String extractedNumberName = extractNumberName(file.getName());
				if(StringUtils.isNotBlank(extractedNumberName)) {
					if(filterNumberFolderNameList.contains(extractedNumberName)){
						System.out.println("-------------------------------------------");
						System.out.println("folder found for number - " + extractedNumberName);
						for (File eachfolder : foldersArray) {
							String folderNumberName = eachfolder.getName().replaceAll("[^0-9]", "");
							if (StringUtils.equals(extractedNumberName, folderNumberName)) {
								String newFileName = baseFolder + "\\" + eachfolder.getName() + "\\" + file.getName();
								System.out.println("moving ||" + file.getName() + "|| to ||" + newFileName + "||");
								File newFile = new File(newFileName);
//								FileUtils.moveFile(file, newFile);
								break;
							} 
						}
					}else {
						System.out.println("-------------------------------------------");
						System.out.println("did not found folder for number - " + extractedNumberName);
						System.out.println("creating folder for " + file.getName());
						String newFileName = baseFolder + "\\" + extractedNumberName + " - MODIFICATIONS" + "\\" + file.getName();
						System.out.println("New File will be at ||" + newFileName + "||");
						File newFile = new File(newFileName);
//						FileUtils.moveFile(file, newFile);
					}
				}else {
					System.out.println("-------------------------------------------");
					System.out.println("no number name found in " + file.getName());
				}
			}
		}
		
	}

	
	
	private static String extractNumberName(String input) {
		StringBuilder output = new StringBuilder();
		char[] chars = input.toCharArray();
		boolean foundFirstNumber = false;
		for(char eachchar : chars) {
			if(Character.isDigit(eachchar)) {
				foundFirstNumber = true;
				output.append(eachchar);
			}
			if(foundFirstNumber && !Character.isDigit(eachchar)) {
				break;
			}
		}
		return output.toString();
	}
	
	
	private static Collection<File> step1() {
		File folder = new File("G:\\NEW PROJECT");
		Collection<File> fileCollections = FileUtils.listFiles(folder, new String[] { "pdf", "docx" }, true);
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
		Collection<File> fileCollections = FileUtils.listFiles(folder, new String[] { "pdf" }, false);
		System.out.println(fileCollections.size() + " files found");

		for (File file : fileCollections) {
			if (file.isFile()) {
				String[] separated = file.getName().split("-");
				String finalNameNumber = StringUtils.trim(separated[0]);
				System.out.println(finalNameNumber);
				File dir = new File(baseFolder + "\\" + finalNameNumber);

				if (dir.exists()) {
					System.out.println("moving " + file.getName());
					File newFile = new File(
							baseFolder + "\\" + finalNameNumber + "\\" + "Monitoring - " + file.getName());
					FileUtils.moveFile(file, newFile);
					System.out.println(dir.getName());
					File newdir = new File(baseFolder + "\\" + finalNameNumber + " - OPEN");

					dir.renameTo(newdir);
				} else {
					System.out.println("no folder found for " + file.getName());
				}

			}
		}
	}

}
