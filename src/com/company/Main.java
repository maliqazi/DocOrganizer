package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        InputVariables.orgParameter = args[0];
        InputVariables.numWords = Integer.parseInt(args[1]);

        String folderPath = (System.getProperty("user.home") + "\\Documents\\Organizer");

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        List<FileInfo> filesList = new ArrayList<FileInfo>();


        for (File file : listOfFiles) {
            filesList.add(new FileInfo(
                    Factory.getDocTypes(file),
                    file.getName().toString(),
                    folderPath,
                    folderPath + "\\" + InputVariables.orgParameter));
        }

        for (FileInfo testFile : filesList) {
            if ( testFile.frequency >= InputVariables.numWords) {
                testFile.moveFile();
            }
        }
    }
}
