package diu_p10_1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;


public class FileCompresor {
    private BufferedInputStream origin = null;
    private FileOutputStream dest;
    private ZipOutputStream out;
    private final byte[] data = new byte[1024];
    
    public FileCompresor(String folder) {
        try {
            dest = new FileOutputStream(folder);
            out = new ZipOutputStream(new BufferedOutputStream(dest));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileCompresor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void compressFile(String filePath){
        try {
            FileInputStream fi = new FileInputStream(filePath);
            origin = new BufferedInputStream(fi, 1024);
            ZipEntry entry = new ZipEntry(filePath);
            out.putNextEntry(entry);
            int count;
            while((count=origin.read(data, 0, 1024))!=-1){
                out.write(data, 0, count);
            }
            origin.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileCompresor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileCompresor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finishCompress(){
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(FileCompresor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
