package careercup;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {


    private void readFile(File file) {

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

    // read input data from stdin
    public void stdin() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }

    // read and open a list of files from a given folder
    private List<Path> listFilesFromFolder(final String folder) {

        try (Stream<Path> paths = Files.walk(Paths.get(folder))) {
            List<Path> fileList = paths.filter(Files::isRegularFile)
                    .filter(x -> x.toString().toLowerCase().endsWith(".java")).collect(Collectors.toList());
            return fileList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void readFilesFromFolder(final String folderName) {
        List<Path> fileList = this.listFilesFromFolder(folderName);

        for (Path filePath : fileList) {
            File file = new File(filePath.toUri());
            this.readFile(file);
        }
    }
    // public static void main(String[] args) {
    //
    //     // File file = new File("/Users/zhenglu/git_project/java-code/src/datastructure/Trie.java");
    //     ReadFile rf = new ReadFile();
    //     // rf.readFile(file);
    //
    //     rf.stdin();
    // }
}
