package Slang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import Menu.File;

public class Manager {
    private TreeMap<String, LinkedList<String>> DB;
    private static Manager singleton;
    public static String DbWorkFile=null;

    private Manager(){
        DB = File.readObjectFromFile(DbWorkFile);
        if(DB==null){
            DB = new TreeMap<>();
        }
    }

    public static Manager getInstance() {
        if(DbWorkFile==null){
            throw new RuntimeException("Manager's DbWorkFile is must not null");
        }
        if (singleton == null) {
            singleton = new Manager();
        }
        return singleton;
    }

    public List<String> getDefinitions(String kw){
        return DB.get(kw);
    }

    public SlangWord getRandomWord(){
        var r = new Random();
        int k = r.nextInt(DB.size());
        var kw= (String)DB.keySet().toArray()[k];
        var ldes = DB.get(kw);
        return new SlangWord(kw,ldes);
    }

    public void saveToDB() {
        File.writeObjectToFile(DbWorkFile, DB);
    }

    public void saveToOriginDB(String path) {
        File.writeObjectToFile(path, DB);
        saveToDB();
    }

    public boolean parseFromFile(String path) {
        DB = new TreeMap<>();
        try {
            BufferedReader br = new BufferedReader(
                new FileReader(path)
            );
            String curLine = null;
            while ((curLine = br.readLine()) != null) {
                String[] pdata = curLine.split("`");
                if(pdata.length < 2) continue;
                String kw = pdata[0];
                var definitions = new LinkedList<String>(Arrays.asList(pdata[1].split("\\| ")));
                var ldes = DB.get(kw);
                if (ldes == null) {
                    DB.put(kw, definitions);
                } else {
                    ldes.addAll(definitions);
                }
            }
            br.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
