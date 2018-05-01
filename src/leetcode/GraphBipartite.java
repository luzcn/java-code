package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B
 * such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
 * Each node is an integer between 0 and graph.length - 1.
 *
 * There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 *
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 *
 *
 * Note:
 *
 * -graph will have length in range [1, 100].
 * - graph[i] will contain integers in range [0, graph.length - 1].
 * - graph[i] will not contain i or duplicate values.
 * - The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 *
 * http://mathworld.wolfram.com/BipartiteGraph.html
 * - All acyclic graphs are bipartite.
 * - A cyclic graph is bipartite iff all its cycles are of even length (Skiena 1990, p. 213).
 */
public class GraphBipartite {


    private boolean dfs(int u, int[] colors, int currentColor, int[][] graph) {
        for (int v : graph[u]) {

            if (colors[v] == -1) {
                colors[v] = currentColor;

                if (!dfs(v, colors, currentColor == 0 ? 1 : 0, graph)) {
                    return false;
                }
            } else if (colors[u] == colors[v]) {
                return false;
            }

        }
        return true;
    }



    private boolean bfs(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int start = 0; start < n; start++) {

            if (colors[start] != -1) {
                continue;
            }

            // not visited yet
            Stack<Integer> stack = new Stack<>();

            stack.push(start);
            colors[start] = 0;

            while (!stack.isEmpty()) {
                int u = stack.pop();

                for (int v : graph[u]) {

                    if (colors[v] == -1) {
                        stack.push(v);
                        colors[v] = colors[u] == 0 ? 1 : 0;
                    } else if (colors[v] == colors[u]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        if (graph.length == 0) {
            return false;
        }

        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int i = 0; i < n; i++) {
            if (colors[i] != -1) {
                continue;
            }

            if (!dfs(i, colors, 0, graph)) {
                return false;
            }
        }
        return true;
        // return bfs(graph);
    }
}
