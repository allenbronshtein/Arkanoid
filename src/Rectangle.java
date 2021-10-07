//Allen Bronshtein
//206228751

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type geometry.geometry.Rectangle.
 */
public class Rectangle {
    private Point point;
    private double width, height;
    private Line[] l = new Line[4];
    private Line top, left, right, bottom;

    /**
     * Instantiates a new geometry.geometry.Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        point = upperLeft;
        this.width = width;
        this.height = height;
        Line[] li = getLines();
        top = li[0];
        left = li[1];
        right = li[2];
        bottom = li[3];
    }


    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return point;
    }

    /**
     * Get lines line [ ].
     *
     * @return the line [ ]
     */
    public Line[] getLines() {
        Point upperRight = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
        Point bottomLeft = new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
        Point bottomRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY() + getHeight());
        l[0] = new Line(getUpperLeft(), upperRight);
        l[1] = new Line(getUpperLeft(), bottomLeft);
        l[2] = new Line(upperRight, bottomRight);
        l[3] = new Line(bottomLeft, bottomRight);
        return l;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     * @param color   the color
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) width,
                (int) height);
        surface.setColor(Color.black);
        surface.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) width,
                (int) height);
    }

    /**
     * Gets bottom.
     *
     * @return the bottom
     */
    public Line getBottom() {
        return bottom;
    }

    /**
     * Gets left.
     *
     * @return the left
     */
    public Line getLeft() {
        return left;
    }

    /**
     * Gets right.
     *
     * @return the right
     */
    public Line getRight() {
        return right;
    }

    /**
     * Gets top.
     *
     * @return the top
     */
    public Line getTop() {
        return top;
    }
}
