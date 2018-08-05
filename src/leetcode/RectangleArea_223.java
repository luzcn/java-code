package leetcode;

import java.util.*;

// Find the total area covered by two rectilinear rectangles in a 2D plane.
//
// Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
public class RectangleArea_223 {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        Point l1 = new Point(A, B);
        Point r1 = new Point(C, D);
        Point l2 = new Point(E, F);
        Point r2 = new Point(G, H);

        int area1 = (C-A) * (D-B);
        int area2 = (G-E) * (H-F);


        if (!isOverlap(l1, r1, l2, r2)) {
            return area1 + area2;
        }

        int leftX = Math.max(l1.x, l2.x);
        int leftY = Math.max(l1.y, l2.y);

        int rightX = Math.min(r1.x, r2.x);
        int rightY = Math.min(r1.y, r2.y);

        return  area1 + area2 -  (rightX - leftX) * (rightY - leftY);
    }


    private boolean isOverlap(Point l1, Point r1, Point l2, Point r2) {
        if (r1.x < l2.x || r2.x < l1.x) {
            return false;
        }

        if (l2.y > r1.y || l1.y > r2.y) {
            return false;
        }

        return true;
    }

    private class Point{
        int x;
        int y;

        Point(int i, int j) {
            x = i;
            y = j;
        }
    }
}
