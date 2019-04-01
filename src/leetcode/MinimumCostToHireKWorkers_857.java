package leetcode;

import java.util.*;

// There are N workers.
// The i-th worker has a quality[i] and a minimum wage expectation wage[i].
//
// Now we want to hire exactly K workers to form a paid group.
// When hiring a group of K workers, we must pay them according to the following rules:
//
// - Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
// - Every worker in the paid group must be paid at least their minimum wage expectation.
//
// Return the least amount of money needed to form a paid group satisfying the above conditions.
//
//
// Here, we are given array of quality and wages.
// Now, the first condition of the payment to the workers demands that every worker should be paid in the ratio of their quality among themselves.
// So, in the first example, if the given array is [10,20,5] then if payment of index 2 person is x then payment of index 0 people should be 2x and that of index 1 person should be 4x.
// So, suppose if you're paying last person 1$ then first person would get 2$ and the mid person gets 4$.
//
// The second rule states that every ith person should be rewarded ATLEAST wage[i].
// So, you need to heapify in such a manner that both the conditions are met and the total cost is minimized when you pick K persons.
//
// Example 1:
//
// Input: quality = [10,20,5], wage = [70,50,30], K = 2
// Output: 105.00000
// Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
// Example 2:
//
// Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
// Output: 30.66667
// Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
public class MinimumCostToHireKWorkers_857 {


  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {

    // max heap, if the heap size is > k, need to remove the highest quality
    PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
    int n = quality.length;
    int sum = 0;
    double res = Double.MAX_VALUE; // 1e9

    ArrayList<Worker> workers = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      workers.add(new Worker(quality[i], wage[i]));
    }

    // sort the worker list by wage/quality ascending
    workers.sort(Comparator.comparingDouble(Worker::ratio));

    for (Worker w : workers) {
      queue.add(w.quality);
      sum += w.quality;

      if (queue.size() > k) {
        sum -= queue.poll();
      }
      if (queue.size() == k) {
        res = Math.min(res, sum * w.ratio());
      }
    }

    return res;
  }


  private class Worker {

    int wage;
    int quality;

    Worker(int q, int w) {
      quality = q;
      wage = w;
    }

    private double ratio() {
      return (double) wage / quality;
    }
  }
  //
  // private class Worker implements Comparable<Worker> {
  //
  //   int wage;
  //   int quality;
  //
  //   public Worker(int q, int w) {
  //     quality = q;
  //     wage = w;
  //   }
  //
  //   private double ratio() {
  //     return (double) wage / quality;
  //   }
  //
  //   @Override
  //   public int compareTo(Worker w) {
  //     return Double.compare(ratio(), w.ratio());
  //   }
  // }


  private double hireWorkersBruteForce(int[] quality, int[] wage, int k) {
    int n = quality.length;

    double res = Double.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      List<Double> cost = new ArrayList<>();

      for (int j = 0; j < n; j++) {
        double price = (double) wage[i] * quality[j] / quality[i];
        if (price < wage[j]) {
          continue;
        }

        cost.add(price);
      }

      if (cost.size() < k) {
        continue;
      }

      double sum = 0;
      Collections.sort(cost);
      for (int l = 0; l < k; l++) {
        sum += cost.get(l);
      }

      res = Math.min(res, sum);
    }

    return res;
  }
}
