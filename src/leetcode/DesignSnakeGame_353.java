package leetcode;

import java.util.HashMap;
import java.util.LinkedList;

// Design a Snake game that is played on a device with screen size = width x height.
// Play the game online if you are not familiar with the game.
//
// The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
//
// You are given a list of food's positions in row-column order.
// When a snake eats the food, its length and the game's score both increase by 1.
//
// Each food appears one by one on the screen.
// For example, the second food will not appear until the first food was eaten by the snake.
//
// When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
public class DesignSnakeGame_353 {

  /**
   * Initialize your data structure here.
   *
   * @param width - screen width
   * @param height - screen height
   * @param food - A list of food positions E.g food = [[1,1], [1,0]] means the first food is
   * positioned at [1,1], the second is at [1,0].
   */
  public DesignSnakeGame_353(int width, int height, int[][] food) {
    snake.add(new int[]{0, 0});
    this.food = food;
    this.width = width;
    this.height = height;

    map.put("U", new int[]{-1, 0});
    map.put("D", new int[]{1, 0});
    map.put("L", new int[]{0, -1});
    map.put("R", new int[]{0, 1});
  }

  /**
   * Moves the snake.
   *
   * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
   * @return The game's score after the move. Return -1 if game over. Game over when snake crosses
   * the screen boundary or bites its body.
   */
  public int move(String direction) {
    int[] dir = map.get(direction);

    int[] headPos = snake.getFirst();
    int x = headPos[0] + dir[0];
    int y = headPos[1] + dir[1];

    if (x < 0 || x >= height || y < 0 || y >= width) {
      return -1;
    }

    if (hitSnake(x, y)) {
      return -1;
    }

    snake.addFirst(new int[]{x, y});

    if (index < food.length && x == food[index][0] && y == food[index][1]) {
      index++;
      return ++score;
    }

    snake.removeLast();
    return score;
  }

  private LinkedList<int[]> snake = new LinkedList<>();
  private int[][] food;
  private int index = 0;
  private int score = 0;
  private HashMap<String, int[]> map = new HashMap<>();
  private int width;
  private int height;

  private boolean hitSnake(int x, int y) {
    // don't need to check the last element
    // since it will be removed, if the
    for (int i = 0; i < snake.size() - 1; i++) {
      if (x == snake.get(i)[0] && y == snake.get(i)[1]) {
        return true;
      }
    }

    return false;
  }
}
