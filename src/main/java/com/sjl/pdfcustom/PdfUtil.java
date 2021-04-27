package com.sjl.pdfcustom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.core.io.ClassPathResource;

public class PdfUtil {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		PDFMergerUtility pdfboxMerger = new PDFMergerUtility();
		File file1 = new File("location absolute path");
		File file2 = new File("location absolute path");
		pdfboxMerger.addSource(file1);
		pdfboxMerger.addSource(file2);
		pdfboxMerger.setDestinationFileName("location absolute path");
		pdfboxMerger.mergeDocuments();
	}
	
}
