package com.company;

import java.io.File;

public class FileInfo {
    public int frequency;
    public String filename;
    private String sourcePath;
    public String targetPath;

    public FileInfo (int frequency, String filename, String sourcePath, String targetPath) {
        this.frequency = frequency;
        this.filename = filename;
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
    }

    public void moveFile() {
        try {
            File file = new File(this.sourcePath + "\\" + this.filename);
            File targetDir = new File(this.targetPath);

            if (!targetDir.exists()) {
                targetDir.mkdir();
            }
            if (file.renameTo(new File(this.targetPath + "\\" + this.filename))) {
                System.out.println("File: " + this.filename + " is moved to " + this.targetPath);
            } else {
                System.out.println("File: " + this.filename + " failed to move to " + this.targetPath);
            }

        }
        catch (Exception e) {}
    }
}
