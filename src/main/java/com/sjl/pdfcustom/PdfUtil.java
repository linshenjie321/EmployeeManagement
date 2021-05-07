package com.sjl.pdfcustom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.core.io.ClassPathResource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtil {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		pdfMergerUtilMerge();
//		imageToPdf();
	}
	
	private static void pdfMergerUtilMerge() throws IOException {
		PDFMergerUtility pdfboxMerger = new PDFMergerUtility();
		File file1 = new File("");
		File file2 = new File("");
		pdfboxMerger.addSource(file1);
		pdfboxMerger.addSource(file2);
		pdfboxMerger.setDestinationFileName("");
		pdfboxMerger.mergeDocuments();
	}
	
	private static void iTextMerge() {
		
	}
	
	//note this one has image resizing issue, not sure how to resolve yet, but one should properly size the image first
	private static void imageToPdf() {
		Document document = new Document();
		String input = "image absolute location";
		String output = "pdf output absolute location";
		try {
			FileOutputStream fos = new FileOutputStream(output);
			PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
			pdfWriter.open();
			document.open();
			document.add(Image.getInstance(input));
			document.close();
			pdfWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
