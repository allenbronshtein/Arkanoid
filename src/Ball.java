//Allen Bronshtein
//206228751

import biuoop.DrawSurface;

import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/***
 * Beginning of class geometry.geometry.Ball .
 */
public class Ball implements Sprite, HitNotifier {
    private List<HitListener> hl = new ArrayList<>();
    private boolean first = true;
    private Point c;
    private double r;
    private Color color;
    private Velocity velocity = new Velocity(0, 0);
    private Velocity originalVelocity = new Velocity(0, 0);
    private Image img;

    /***
     * Name : geometry.geometry.Ball .
     * Constructor of class geometry.geometry.Ball .
     * @param center : center of ball
     * @param r : radius of ball
     * @param color : color of ball
     */
    public Ball(Point center, double r, Color color) {
        this.c = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Change color.
     */
    private void changeColor() {
        if (color == Color.RED) {
            color = Color.YELLOW;
            return;
        }
        if (color == Color.YELLOW) {
            color = Color.GREEN;
            return;
        }
        if (color == Color.GREEN) {
            color = Color.BLUE;
            return;
        }
        if (color == Color.BLUE) {
            color = Color.RED;
            return;
        }
    }

    /***
     * Name : getX .
     * access x center .
     * @return c.getX : x of center .
     */
    public double getX() {

        return c.getX();
    }

    /***
     * Name : getY .
     * access x center .
     * @return c.getY : y of center .
     */
    public double getY() {
        return c.getY();
    }

    /***
     * Name : getSize .
     * access radius .
     * @return r : radius .
     */
    public double getSize() {

        return r;
    }

    /***
     * Name : drawOn .
     * draws ball on given surface .
     * @param surface : surface .
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) getX(), (int) getY(), (int) getSize());
        surface.setColor(Color.black);
        surface.drawCircle((int) getX(), (int) getY(), (int) getSize());
    }


    /***
     * Name : setVelocity .
     * Updates velocity of point.
     * @param dx : x velocity.
     * @param dy : y velocity.
     */
    public void setVelocity(double dx, double dy) {
        if (first) {
            originalVelocity = new Velocity(dx, dy);
            first = false;
        }
        velocity = new Velocity(dx, dy);
    }

    /***
     * Name : getVelocity .
     * @return velocity :velocity .
     */
    public Velocity getVelocity() {

        return velocity;
    }

    /***.
     * Name : getOriginalVelocity
     * returns the non-slowing velocity of ball
     * @return  : originalVelocity
     */
    public Velocity getOriginalVelocity() {
        return originalVelocity;
    }

    /***
     * Name : setOriginalVelocity .
     * sets the original velocity of ball (when hit)
     * @param v : current velocity .
     */
    public void setOriginalVelocity(Velocity v) {
        originalVelocity = v;
    }

    /***
     * Name: moveOneStep .
     * Moves the point according to velocity .
     * @param game : game info .
     */
    public void moveOneStep(GameEnvironment game) {
        checkMap(game);
        slow(game);
        c = getVelocity().applyToPoint(c);
    }

    /***.
     * Name : getTracjectory
     * returns the line start - current center - end center of ending when ball hits border
     * @param game : game information .
     * @return  : line
     */
    public Line getTrajectory(GameEnvironment game) {
        int topBorder = 50, bottomBorder = 950, leftBorder = 50, rightBorder = 1900;
        double distanceX = 0;
        double distanceY = 0;
        double timeX, timeY;
        Point start = new Point(getX(), getY());
        //ball is moving right
        if (getVelocity().getDx() > 0) {
            distanceX = rightBorder - getX() - getSize();
            //ball is moving left
        } else if (getVelocity().getDx() < 0) {
            distanceX = getX() - getSize() - leftBorder;
            //ball isnt moving left or right
        } else if (getVelocity().getDx() == 0) {
            //ball is moving down
            if (getVelocity().getDy() > 0) {
                Point end = new Point(getX(), bottomBorder);
                Line t = new Line(start, end);
                return t;
            }
            //ball is moving up
            if (getVelocity().getDy() < 0) {
                Point end = new Point(getX(), topBorder);
                Line t = new Line(start, end);
                return t;
            }
        }
        //ball is moving down
        if (getVelocity().getDy() > 0) {
            distanceY = bottomBorder - getY() - getSize();
        }
        //ball is moving up
        if (getVelocity().getDy() < 0) {
            distanceY = getY() - getSize() - topBorder;
        }
        //ball isnt moving up or down
        if (getVelocity().getDy() == 0) {
            //ball is moving right
            if (getVelocity().getDx() > 0) {
                Point end = new Point(rightBorder, getY());
                Line t = new Line(start, end);
                return t;
            }
            //ball is moving left
            if (getVelocity().getDx() < 0) {
                Point end = new Point(leftBorder, getY());
                Line t = new Line(start, end);
                return t;
            }
        }
        timeX = Math.abs((distanceX / getVelocity().getDx()));
        timeY = Math.abs(distanceY / getVelocity().getDy());
        //will hit x faster
        if (timeX <= timeY) {
            if (getVelocity().getDx() < 0) {
                distanceX = -distanceX;
            }
            double endX = getX() + distanceX;
            int endY = (int) (getVelocity().getDy() * timeX + getY());
            Point end = new Point(endX, endY);
            Line t = new Line(start, end);
            return t;
        }
        //will hit y faster
        if (timeX > timeY) {
            if (getVelocity().getDy() < 0) {
                distanceY = -distanceY;
            }
            double endY = getY() + distanceY;
            int endX = (int) (getVelocity().getDx() * timeY + getX());
            Point end = new Point(endX, endY);
            Line t = new Line(start, end);
            return t;
        }
        return null;
    }

    /***.
     * Name : checkForHits
     * the function check if the ball hit and object
     * @param game : game info .
     */
    private void checkMap(GameEnvironment game) {
        if (getY() + getSize() >= Tools.SCREEN_HEIGHT) {
            notifyFall();
        }
        ArrayList<Collidable> colidbles = game.getCollidable();
        for (Collidable i : colidbles) {
            double topBorder = i.getCollisionRectangle().getBottom().start().getY();
            double bottomBorder = i.getCollisionRectangle().getTop().start().getY();
            double leftBorder = i.getCollisionRectangle().getRight().start().getX();
            double rightBorder = i.getCollisionRectangle().getLeft().start().getX();
            Point topLeft = new Point(rightBorder, bottomBorder);
            Point bottomLeft = new Point(rightBorder, topBorder);
            Point topRight = new Point(leftBorder, bottomBorder);
            Point bottomRight = new Point(leftBorder, topBorder);
            Line tracjector = getTrajectory(game);
            //borders right
            if (getX() + getSize() == rightBorder) {
                if (getY() <= topBorder && getY() >= bottomBorder) {
                    Point p = new Point(getX() + getSize(), getY());
                    Velocity newV = i.hit(this, p, getOriginalVelocity());
                    setVelocity(newV.getDx(), newV.getDy());
                    setOriginalVelocity(newV);
                    changeColor();
                    continue;
                }
            }
            //borders left
            if (getX() - getSize() == leftBorder) {
                if (getY() <= topBorder && getY() >= bottomBorder) {
                    Point p = new Point(getX() - getSize(), getY());
                    Velocity newV = i.hit(this, p, getOriginalVelocity());
                    setVelocity(newV.getDx(), newV.getDy());
                    setOriginalVelocity(newV);
                    changeColor();
                    continue;
                }
            }
            //borders down
            if (getY() + getSize() == bottomBorder) {
                if (getX() >= rightBorder && getX() <= leftBorder) {
                    Point p = new Point(getX(), getY() + getSize());
                    Velocity newV = i.hit(this, p, getOriginalVelocity());
                    setVelocity(newV.getDx(), newV.getDy());
                    setOriginalVelocity(newV);
                    changeColor();
                    continue;
                }
            }
            //borders up
            if (getY() - getSize() == topBorder) {
                if (getX() >= rightBorder && getX() <= leftBorder) {
                    Point p = new Point(getX(), getY() - getSize());
                    Velocity newV = i.hit(this, p, getOriginalVelocity());
                    setVelocity(newV.getDx(), newV.getDy());
                    setOriginalVelocity(newV);
                    changeColor();
                    continue;
                }
            }
            //hits top-left corner
            if (c.distance(topLeft) <= getSize() && tracjector.getSlope() != null && tracjector.getSlope() > 0) {
                Velocity newV = i.hit(this, topLeft, getOriginalVelocity());
                setVelocity(newV.getDx(), newV.getDy());
                setOriginalVelocity(newV);
                changeColor();
                continue;
            }
            //hits top right
            if (c.distance(topRight) <= getSize() && tracjector.getSlope() != null && tracjector.getSlope() < 0) {
                Velocity newV = i.hit(this, topRight, getOriginalVelocity());
                setVelocity(newV.getDx(), newV.getDy());
                setOriginalVelocity(newV);
                changeColor();
                continue;
            }
            //hits bottom left
            if ((c.distance(bottomLeft) <= getSize())
                    && (tracjector.getSlope() != null) && (tracjector.getSlope() < 0)) {
                Velocity newV = i.hit(this, bottomLeft, getOriginalVelocity());
                setVelocity(newV.getDx(), newV.getDy());
                setOriginalVelocity(newV);
                changeColor();
                continue;
            }
            //hits bottom right
            if ((c.distance(bottomRight) <= getSize())
                    && (tracjector.getSlope() != null) && (tracjector.getSlope() > 0)) {
                Velocity newV = i.hit(this, bottomRight, getOriginalVelocity());
                setVelocity(newV.getDx(), newV.getDy());
                setOriginalVelocity(newV);
                changeColor();
            }
        }
        if (game.getRemoveList() != null) {
            for (Collidable i : game.getRemoveList()) {
                colidbles.remove(i);
            }
            game.setCollidables(colidbles);
        }
    }

    /***
     * Name : slow
     * the function checks if slowing is required , slows if needed .
     * @param game : game info
     */
    private void slow(GameEnvironment game) {
        ArrayList<Collidable> colidbles = game.getCollidable();
        //first velocity
        double curDx = getVelocity().getDx();
        double curDy = getVelocity().getDy();
        for (Collidable i : colidbles) {
            double topBorder = i.getCollisionRectangle().getBottom().start().getY();
            double bottomBorder = i.getCollisionRectangle().getTop().start().getY();
            double leftBorder = i.getCollisionRectangle().getRight().start().getX();
            double rightBorder = i.getCollisionRectangle().getLeft().start().getX();
            Velocity temp = new Velocity(curDx, curDy);
            if (getY() - getSize() + curDy < topBorder && getY() - getSize() > topBorder) {
                if (getX() + curDx >= rightBorder && getX() + curDx <= leftBorder) {
                    temp.setVelocity(getVelocity().getDx(), topBorder - (getY() - getSize()));
                    setVelocity(temp.getDx(), temp.getDy());
                }
            }
            if (getY() + getSize() + curDy > bottomBorder && getY() + getSize() < bottomBorder) {
                if (getX() + curDx <= leftBorder && getX() + curDx >= rightBorder) {
                    temp.setVelocity(getVelocity().getDx(), bottomBorder - (getY() + getSize()));
                    setVelocity(temp.getDx(), temp.getDy());
                }
            }
            if (getX() + getSize() + curDx > rightBorder && getX() + getSize() < rightBorder) {
                if (getY() + curDy <= topBorder && getY() + curDy >= bottomBorder) {
                    temp.setVelocity(rightBorder - (getX() + getSize()), getVelocity().getDy());
                    setVelocity(temp.getDx(), temp.getDy());
                }
            }
            if (getX() - getSize() + curDx < leftBorder && getX() - getSize() > leftBorder) {
                if (getY() + curDy <= topBorder && getY() + curDy >= bottomBorder) {
                    temp.setVelocity(leftBorder - (getX() - getSize()), getVelocity().getDy());
                    setVelocity(temp.getDx(), temp.getDy());
                }
            }
        }
    }

    /***.
     * Name: addToGame
     * add an object to gameLevel .
     * @param gameLevel : gameLevel info
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addPlayer(this);
    }

    @Override
    public void addHitListener(HitListener h) {
        hl.add(h);
    }

    @Override
    public void removeHitListener(HitListener h) {
        hl.remove(h);
    }

    /**
     * Notify fall.
     */
    public void notifyFall() {
        for (HitListener i : hl) {
            i.fallEvent(this);
        }
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.setRequestToRemove(this);
    }
}

