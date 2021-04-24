package Menu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
}
