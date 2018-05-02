package leetcode;

import java.util.Iterator;
import java.util.List;

/**
 * Implement an iterator to flatten a 2d vector.
 *
 * For example,
 * Given 2d vector =
 *
 * [
 * [1,2],
 * [3],
 * [4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,2,3,4,5,6].
 */
public class Flatten2DVector implements Iterator<Integer> {

    private Iterator<List<Integer>> rowIterator;
    private Iterator<Integer> columnIterator;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        rowIterator = vec2d.iterator();
        columnIterator = null;
        moveToNext();
    }

    private void moveToNext() {
        if (columnIterator != null && columnIterator.hasNext()) {
            return;
        }

        while (rowIterator.hasNext()) {
            List<Integer> nextLevel = rowIterator.next();
            if (nextLevel != null && nextLevel.size() > 0) {
                columnIterator = nextLevel.iterator();
                return;
            }
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }

        Integer value = columnIterator.next();
        moveToNext();

        return value;
    }

    @Override
    public boolean hasNext() {
        return rowIterator.hasNext() || (columnIterator != null && columnIterator.hasNext());
    }
}
