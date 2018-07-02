package careercup;

import java.util.*;
import java.io.*;

public class ReadFile {


    public void readFile(File file) {

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuffer res = new StringBuffer();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                res.append(line).append("\n");
            }

            fileReader.close();
            System.out.println(res.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //
    // public static void main(String[] args) {
    //
    //     File file = new File("/Users/zhenglu/git_project/java-code/src/datastructure/Trie.java");
    //     ReadFile rf = new ReadFile();
    //     rf.readFile(file);
    // }
}
