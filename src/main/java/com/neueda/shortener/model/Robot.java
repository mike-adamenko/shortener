package com.neueda.shortener.model;

/**
 * Robot
 *
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public class Robot {

    public static final Integer MAX_POSITION = 4;
    public static final Integer MIN_POSITION = 0;

    private Integer x;

    private Integer y;

    private Direction direction;

    public Robot() {
    }

    public Robot(Integer x, Integer y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isOnTable() {
        return x != null && y != null && direction != null && x >= MIN_POSITION && x <= MAX_POSITION && y >= MIN_POSITION && y <= MAX_POSITION;
    }

    public String getCurrentStatus() {
        return isOnTable() ? String.join(",", x.toString(), y.toString(), direction.toString()) : "IGNORED";
    }


    public void increaseY(int steps) {
        y += steps;
    }

    public void decreaseY(int steps) {
        y -= steps;
    }

    public void increaseX(int steps) {
        x += steps;
    }

    public void decreaseX(int steps) {
        x -= steps;
    }
}
