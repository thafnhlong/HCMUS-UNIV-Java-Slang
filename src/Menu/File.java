package Menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class File {
    public static boolean isExist(String pathName){
        var f = new java.io.File(pathName);
        return f.exists();
    }
    public static <T> void writeObjectToFile(String path,T out){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(path)
            );
            oos.writeObject(out);
            oos.close();
        } catch (IOException e) {
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> T readObjectFromFile(String path){
        try {
            ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path)
            );
            var ret = (T)(ois.readObject());
            ois.close();
            return ret;
        } catch (Exception e) {
        }
        return null;
    }
    public static void copyFile(String source, String dest) {
        try {
            var is = new FileInputStream(source);
            var os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException e) {
        }            
    }
    public static void appendFile(String path, String value){
        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(value);
        } catch (IOException e) {
        }
    }
    public static String readTextFromFile(String path){
        if (isExist(path)){
            try {
                BufferedReader br = new BufferedReader(
                    new FileReader(path)
                );
                StringBuilder sb = new StringBuilder();
                String curLine = null;
                while ((curLine = br.readLine()) != null) {
                    sb.append(curLine).append("\n");
                }
                br.close();
                if(sb.length()>0)
                    return sb.toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
