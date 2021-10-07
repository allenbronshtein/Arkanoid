/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
// This method is called whenever the beingHit object is hit.
    // The hitter parameter is the geometry.geometry.Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);

    /**
     * Fall event.
     *
     * @param ball the ball
     */
    void fallEvent(Ball ball);
}