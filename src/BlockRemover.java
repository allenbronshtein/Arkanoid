

/**
 * The type gameObjects.Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter removedBlocks;

    /**
     * Instantiates a new gameObjects.Block remover.
     *
     * @param gameLevel          the gameLevel
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.removedBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.dicreaseHitPoints();
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(gameLevel);
            removedBlocks.increase(1);
        }
    }

    @Override
    public void fallEvent(Ball ball) {
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return removedBlocks;
    }
}
