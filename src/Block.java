//Allen Bronshtein
//206228751

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The type gameObjects.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners = new ArrayList<>();
    private int hitPoints;
    private Rectangle rect;
    private double topBorder;
    private double bottomBorder;
    private double leftBorder;
    private double rightBorder;
    private Point topLeft, bottomLeft, topRight, bottomRight;
    private Color color;
    private Picture fill3, fill2, fill, stroke;
    private Image img;


    /**
     * Sets hit points.
     *
     * @param hits the hits
     */
    public void setHitPoints(int hits) {
        hitPoints = hits;
    }

    /***
     * Instantiates a new gameObjects.Block.
     *
     * @param rect the rect
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        topBorder = rect.getBottom().start().getY();
        bottomBorder = rect.getTop().start().getY();
        leftBorder = rect.getRight().start().getX();
        rightBorder = rect.getLeft().start().getX();
        topLeft = new Point(rightBorder, bottomBorder);
        bottomLeft = new Point(rightBorder, topBorder);
        topRight = new Point(leftBorder, bottomBorder);
        bottomRight = new Point(leftBorder, topBorder);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * Sets color.
     *
     * @param c the c
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        List<Picture> pictures = new ArrayList<>();
        pictures.add(fill3);
        pictures.add(fill2);
        pictures.add(fill);
        pictures.add(stroke);
        for (int i = 0; i < pictures.size(); i++) {
            if (pictures.get(i) != null) {
                if (pictures.get(i).exist()) {
                    if (pictures.get(i).isImage()) {
                        drawByImage(surface, pictures.get(i).getImg());
                        return;
                    }
                    drawByColor(surface, pictures.get(i).getColor());
                    return;
                }
            }
        }
        surface.setColor(color);
        surface.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
    }

    @Override
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        notifyHit(hitter);
        Velocity v = null;
        if (collisionPoint.equals(topLeft)) {
            v = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        if (collisionPoint.equals(topRight)) {
            v = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        if (collisionPoint.equals(bottomLeft)) {
            v = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        if (collisionPoint.equals(bottomRight)) {
            v = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        if (bottomBorder == collisionPoint.getY()) {
            v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        if (topBorder == collisionPoint.getY()) {
            v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        if (leftBorder == collisionPoint.getX()) {
            v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            return v;
        }
        if (rightBorder == collisionPoint.getX()) {
            v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            return v;
        }
        return v;
    }

    /**
     * Add to gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notify hit.
     *
     * @param hitter the hitter
     */
    public void notifyHit(Ball hitter) {
        for (HitListener hl : hitListeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Dicrease hit points.
     */
    public void dicreaseHitPoints() {
        hitPoints--;
    }

    /**
     * Get fill 3 picture.
     *
     * @return the picture
     */
    public Picture getFill3() {
        return fill3;
    }

    /**
     * Get fill 2 picture.
     *
     * @return the picture
     */
    public Picture getFill2() {
        return fill2;
    }

    /**
     * Get fill picture.
     *
     * @return the picture
     */
    public Picture getFill() {
        return fill;
    }

    /**
     * Get stroke picture.
     *
     * @return the picture
     */
    public Picture getStroke() {
        return stroke;
    }

    /**
     * Set fill 3.
     *
     * @param p the fill
     */
    public void setFill3(Picture p) {
        this.fill3 = p;
    }

    /**
     * Set fill 2.
     *
     * @param p the fill
     */
    public void setFill2(Picture p) {
        this.fill2 = p;
    }

    /**
     * Set fill.
     *
     * @param p the fill
     */
    public void setFill(Picture p) {
        this.fill = p;
    }

    /**
     * Set stroke.
     *
     * @param p the fill
     */
    public void setStroke(Picture p) {
        this.stroke = p;
    }

    /**
     * Draw by image.
     *
     * @param surface the surface
     * @param image   the image
     */
    public void drawByImage(DrawSurface surface, Image image) {
        int x = (int) getCollisionRectangle().getUpperLeft().getX();
        int y = (int) getCollisionRectangle().getUpperLeft().getY();
        img = image;
        surface.drawImage(x, y, img);
    }

    /**
     * Draw by color.
     *
     * @param surface the surface
     * @param c   the color
     */
    public void drawByColor(DrawSurface surface, Color c) {
        surface.setColor(c);
        surface.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
    }
}
