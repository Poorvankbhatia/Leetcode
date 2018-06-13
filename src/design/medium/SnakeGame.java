/*

Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

 */
package design.medium;


import java.util.*;

/**
 * Created by poorvank.b on 12/06/18.
 */
public class SnakeGame {


    private Set<Integer> set; // For fast lookups
    private Deque<Integer> snakeBody; // Update tail
    private int foodIndex;
    private int width;
    private int height;
    private int score;
    private int[][]  food;


    public SnakeGame(int width, int height, int[][] food) {

        set = new HashSet<>();
        snakeBody = new LinkedList<>();
        this.width = width;
        this.height = height;
        this.food = food;
        snakeBody.offerLast(0);
        set.add(0);

    }

    public int move(String direction) {

        if(score==-1) {
            return -1;
        }

        int row = snakeBody.peekFirst()/width;
        int col = snakeBody.peekFirst()%width;

        switch (direction) {
            case "U" :
                row--;
                break;
            case "D" :
                row++;
                break;
            case "L" :
                col--;
                break;
            case "R" :
                col++;
                break;
        }

        int newHead = row*width+col;

        //case 1: out of boundary or eating body
        set.remove(snakeBody.peekLast());
        if(row>=height || row<0 || col<0 || col>=width || set.contains(newHead)) {
            score = -1;
            return score;
        }

        set.add(newHead);
        snakeBody.offerFirst(newHead);

        //case2: eating food, keep tail, add head
        if(foodIndex<food.length && row == food[foodIndex][0] && col == food[foodIndex][1]) {
            set.add(snakeBody.peekLast());
            foodIndex++;
            score++;
            return score;
        }


        snakeBody.pollLast();
        return score;

    }


}
