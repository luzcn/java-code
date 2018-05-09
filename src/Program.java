import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        Program program = new Program();
        List<List<Integer>> res = program.permute(new int[]{1, 2, 3});

        res.forEach(System.out::println);

    }

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> current = new ArrayList<>();

    private void dfs(int[] nums, boolean[] visited) {

        if (this.current.size() == nums.length) {
            this.result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            current.add(nums[i]);

            dfs(nums, visited);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }


    public List<List<Integer>> permute(int[] nums) {

        if (nums == null || nums.length == 0) {
            return this.result;
        }

        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited);

        return this.result;
    }
}