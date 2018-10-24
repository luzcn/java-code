package leetcode;

import java.util.HashSet;

public class RobotRoomCleaner_489 {

  private Robot robot;

  public void cleanRoom(Robot robot) {

    this.robot = robot;
  }

  private HashSet<String> visited = new HashSet<>();
  private int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};   //west south east north

  // direction = 0, the robot is facing to west
  // direction = 1, the robot is facing to south
  // direction = 2, the robot is facing to east
  // direction = 3, the robot is facing to north
  private void dfs(int x, int y, int direction) {
    if (visited.contains(toString(x, y))) {
      return;
    }

    robot.clean();
    visited.add(toString(x, y));

    for (int i = 0; i < 4; i++) {
      if (!robot.move()) {

        direction = (direction + 1) % 4;
        turnLeft(1);
        visited.add(toString(x + dirs[direction][0], y + dirs[direction][1]));
      }

      dfs(x + dirs[direction][0], y + dirs[direction][1], direction);
    }

  }

  private void turnLeft(int k) {
    for (int i = 0; i < k; i++) {
      robot.turnLeft();
    }
  }

  private void turnRight(int k) {
    for (int i = 0; i < k; i++) {
      robot.turnRight();
    }
  }


  private String toString(int i, int j) {
    return i + "#" + j;
  }

  private class Robot {

    boolean move() {
      return true;
    }

    void turnLeft() {

    }

    void turnRight() {

    }

    void clean() {
    }
  }
}
