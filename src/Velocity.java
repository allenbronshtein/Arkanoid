//Allen Bronshtein
//206228751


/***
 * Start of code components.Velocity .
 */
public class Velocity {
    private double dx, dy;

    /***
     * Constructor .
     * @param dx : difference of x .
     * @param dy : difference of y .
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /***
     * Add new coordinates to point .
     * @param p : current point .
     * @return newPoint : new point after add velocity .
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
        return newPoint;
    }

    /***
     *
     * @return dx : x velocity .
     */
    public double getDx() {
        return this.dx;
    }

    /***
     *
     * @return dy : y velocity .
     */
    public double getDy() {
        return this.dy;
    }

    /***
     * Sets velocity .
     *
     * @param velX the dx
     * @param velY the dy
     */
    public void setVelocity(double velX, double velY) {
        this.dx = velX;
        this.dy = velY;
    }

}