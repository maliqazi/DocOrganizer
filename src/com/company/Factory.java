package com.company;

import java.io.*;
import java.util.Scanner;

import jxl.CellType;
import jxl.Sheet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;


public class Factory {
    public static int getDocTypes(File file){
        String fileExt = getFileExtension(file);
        int frequency=0;
        switch (fileExt) {
            case "txt" :
                frequency = readTextFile(file);
                break;
            case "doc" :
                frequency = readDocFile(file);
                break;
            case "docx" :
                frequency = readDocFile(file);
                break;
            case "xls" :
                frequency = readExcelFile(file);
                break;
            case "xlsx" :
                frequency = readExcelFile(file);
                break;
            case "pdf" :
                frequency = readPDFFile(file);
                break;
            default: break;
        }
        return frequency;
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    private static int readTextFile(File file) {
        int frequency = 0;
        try {
            Scanner reader = new Scanner(new FileReader(file));
            String lineRead = "";
            while(reader.hasNext()) {
                lineRead = reader.next();
                if ( lineRead.toLowerCase().contains(InputVariables.orgParameter))
                {
                    frequency++;
                }
            }
            reader.close();
        }
        catch (Exception e){}
        return frequency;
    }

    private static int readDocFile(File file)
    {
        int frequency = 0;
        WordExtractor extractor = null;
        try {
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();

            for (int i = 0; i < fileData.length; i++) {
                if (fileData[i] != null) {
                    if (fileData[i].toLowerCase().contains(InputVariables.orgParameter)) {
                        frequency++;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return frequency;
    }


    private static int readPDFFile(File file) {
        int frequency = 0;
        try {
            PDDocument document = PDDocument.load(file);

            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                if (text.toLowerCase().contains(InputVariables.orgParameter))
                {
                    frequency++;

                }
            }
            document.close();
        } catch(Exception e) {}
        return frequency;
    }

    private static int readExcelFile(File file) {
        int frequency = 0;
        jxl.Workbook workbook;
        try {
            workbook = jxl.Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);

            for (int j=0; j < sheet.getColumns(); j++)
            {
                for (int i =0; i <sheet.getRows(); i++)
                {
                    jxl.Cell cell = sheet.getCell(j,i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL || type == CellType.NUMBER) {
                        if ( cell.getContents().toLowerCase().contains(InputVariables.orgParameter)) {
                            frequency++;

                        }
                    }
                }
            }
        }
        catch (Exception e) {}
        return frequency;
    }
}
