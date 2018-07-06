package careercup;

import java.util.*;


// Axon OA
// 1,2
// 3,4
// 2,4
// all the criminals are connected, return true
public class CriminalDetect {
    // public static void main(String[] args) {
    //     Scanner in = new Scanner(System.in);
    //     List<List<Integer>> list = new ArrayList<>();
    //
    //     while (in.hasNext()) {
    //
    //         // String[] line =
    //
    //         ArrayList<Integer> line = new ArrayList<>();
    //
    //         for (String s : in.nextLine().split(",")) {
    //             line.add(Integer.parseInt(s));
    //         }
    //
    //         list.add(line);
    //     }
    //
    //
    // }

    private HashMap<Integer, List<Integer>> graph = new HashMap<>();
    private HashSet<Integer> visited = new HashSet<>();

    private void dfs(int node) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);

        for (int nei : graph.getOrDefault(node, new ArrayList<>())) {
            dfs(nei);
        }
    }

    public boolean detect(List<List<Integer>> criminals) {
        for (int i = 0; i < criminals.size(); i++) {

            List<Integer> criminal = criminals.get(i);
            int node = criminal.get(0);

            for (int j = 1; j < criminal.size(); j++) {
                graph.computeIfAbsent(node, k -> new ArrayList<>()).add(criminal.get(j));
                graph.computeIfAbsent(criminal.get(j), k -> new ArrayList<>()).add(node);
            }

        }

        dfs(criminals.get(0).get(0));

        for (int i = 1; i <= graph.size(); i++) {
            if (!visited.contains(i)) {
                return false;
            }
        }

        return true;
    }
}
