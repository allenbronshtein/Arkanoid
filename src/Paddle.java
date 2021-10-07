//Allen Bronshtein
//206228751

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type gameObjects.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private boolean first = true;
    private Velocity originalVelocity;
    private Rectangle r;
    private double reg1, reg2, reg3, reg4, reg5, regLength;
    private double speed = 0;

    /**
     * Instantiates a new gameObjects.Paddle.
     *
     * @param rect the rect
     */
    public Paddle(Rectangle rect) {
        r = rect;
        regLength = getCollisionRectangle().getWidth() / 5;
        reg1 = getCollisionRectangle().getUpperLeft().getX();
        reg2 = reg1 + regLength;
        reg3 = reg2 + regLength;
        reg4 = reg3 + regLength;
        reg5 = reg4 + regLength;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        double width = r.getWidth();
        double height = r.getHeight();
        double x = r.getTop().start().getX();
        double y = r.getTop().start().getY();
        if (x - speed <= Tools.BORDER_WIDTH) {
            r = new Rectangle(new Point(Tools.BORDER_WIDTH, y), width, height);
            reg1 = getCollisionRectangle().getUpperLeft().getX();
            reg2 = reg1 + regLength;
            reg3 = reg2 + regLength;
            reg4 = reg3 + regLength;
            reg5 = reg4 + regLength;
            return;
        }
        r = new Rectangle(new Point(x - speed, y), width, height);
        reg1 = getCollisionRectangle().getUpperLeft().getX();
        reg2 = reg1 + regLength;
        reg3 = reg2 + regLength;
        reg4 = reg3 + regLength;
        reg5 = reg4 + regLength;
    }

    /**
     * Move right.
     */
    public void moveRight() {
        double width = r.getWidth();
        double height = r.getHeight();
        double x = r.getTop().start().getX();
        double y = r.getTop().start().getY();
        if (x + speed >= Tools.SCREEN_WIDTH - Tools.BORDER_WIDTH - getCollisionRectangle().getWidth()) {
            r = new Rectangle(new Point(Tools.SCREEN_WIDTH - Tools.BORDER_WIDTH - getCollisionRectangle().getWidth(),
                    y), width, height);
            reg1 = getCollisionRectangle().getUpperLeft().getX();
            reg2 = reg1 + regLength;
            reg3 = reg2 + regLength;
            reg4 = reg3 + regLength;
            reg5 = reg4 + regLength;
            return;
        }
        r = new Rectangle(new Point(x + speed, y), width, height);
        reg1 = getCollisionRectangle().getUpperLeft().getX();
        reg2 = reg1 + regLength;
        reg3 = reg2 + regLength;
        reg4 = reg3 + regLength;
        reg5 = reg4 + regLength;
    }

    // Sprite

    /***
     * Name: timePassed .
     */
    public void timePassed() {
    }

    /***
     * Name : drawOn .
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        r.drawOn(d, Color.ORANGE.darker());
    }


    /***
     * Name : getCollisionRectangle .
     * @return geometry.geometry.Rectangle : r .
     */
    public Rectangle getCollisionRectangle() {
        return r;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    /***
     * Name : components.Velocity .
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return v : new components.Velocity .
     */
    public Velocity hit(Ball b, Point collisionPoint, Velocity currentVelocity) {
        if (first) {
            originalVelocity = currentVelocity;
        }
        double x = collisionPoint.getX();
        double wideLeft = -60, left = -30, right = 30, wideRight = 60;
        wideLeft = Math.toRadians(wideLeft);
        left = Math.toRadians(left);
        right = Math.toRadians(right);
        wideRight = Math.toRadians(wideRight);
        if (reg1 <= x && x < reg2) {
            double dx = -Math.abs(Math.tan(wideLeft) * (originalVelocity.getDy()));
            Velocity v = new Velocity(dx, -Math.abs(currentVelocity.getDy()));
            return v;
        }
        if (reg2 <= x && x < reg3) {
            double dx = -Math.abs(Math.tan(left) * (originalVelocity.getDy()));
            Velocity v = new Velocity(dx, -Math.abs(currentVelocity.getDy()));
            return v;
        }
        if (reg3 <= x && x < reg4) {
            Velocity v = new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
            return v;
        }
        if (reg4 <= x && x < reg5) {
            double dx = Math.tan(right) * Math.abs(originalVelocity.getDy());
            Velocity v = new Velocity(dx, -Math.abs(currentVelocity.getDy()));
            return v;
        }
        if (reg5 <= x) {
            double dx = Math.tan(wideRight) * Math.abs(originalVelocity.getDy());
            Velocity v = new Velocity(dx, -Math.abs(currentVelocity.getDy()));
            return v;
        }
        Velocity v = new Velocity(-currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        return v;
    }

    /**
     * Set speed.
     *
     * @param s the s
     */
    public void setSpeed(double s) {
        speed = s;
    }
}
