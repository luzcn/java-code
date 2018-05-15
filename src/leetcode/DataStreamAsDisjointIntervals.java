package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

// Given a data stream input of non-negative integers a1, a2, ..., an, ...,
// summarize the numbers seen so far as a list of disjoint intervals.
//
// For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
//
// [1, 1]
// [1, 1], [3, 3]
// [1, 1], [3, 3], [7, 7]
// [1, 3], [7, 7]
// [1, 3], [6, 7]
// Follow up:
// What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
public class DataStreamAsDisjointIntervals {

    // tree set is red-black tree, self-balanced binary search tree
    // sort the interval by start time
    // use TreeSet.HeadSet to get a set of items that are strictly less than given item
    // use TreeSet.TailSet to get items that are greater or equal to the given item.
    private TreeSet<Interval> bst = new TreeSet<>(Comparator.comparingInt(x -> x.start));

    private Interval getFirst(SortedSet<Interval> set) {
        if (set.isEmpty()) {
            return null;
        }

        // the first or last function gives exception if the given set is empty
        // here we treat it as null.
        return set.first();
    }

    private Interval getLast(SortedSet<Interval> set) {
        if (set.isEmpty()) {
            return null;
        }

        return set.last();
    }


    /**
     * Initialize your data structure here.
     */
    public DataStreamAsDisjointIntervals() {

    }

    public void addNum(int val) {

        // first create an interval [val, val]
        Interval newInterval = new Interval(val, val);

        // get the last element that are < val
        Interval before = this.getLast(bst.headSet(newInterval));

        if (before != null && before.end >= val) {

            // the input val is already wrapped in some interval
            // e.g. the before is[6,8], input val is 7,
            // 7 is already included in [6,8] range
            // we don't need to change anything
            return;
        }

        // get the first element that are >= val
        Interval after = getFirst(bst.tailSet(newInterval));

        boolean leftOverlap = before != null && before.end + 1 == val;
        boolean rightOverlap = after != null && val + 1 == after.start;

        if (leftOverlap && rightOverlap) {

            // after adding the new value,
            // before and after are connected
            // remove them from bst first then add new interval [before.start, after.end]
            bst.remove(before);
            bst.remove(after);
            bst.add(new Interval(before.start, after.end));
        } else if (leftOverlap) {
            // before will be connected with val
            // extend it's end
            before.end++;
        } else if (rightOverlap) {
            after.start--;
        } else {
            // NO overlap, just add the interval [val, val]
            bst.add(newInterval);
        }
    }

    public List<Interval> getIntervals() {

        return new ArrayList<>(this.bst);
    }


    public class Interval {

        public int start;
        public int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
