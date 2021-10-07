import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class WinGameAnimation implements Animation {
    private GameLevel gameLevel;
    private ScoreTrackingListener score;

    /**
     * Instantiates a new Pause screen.
     *
     * @param gameLevel the gameLevel
     * @param score     the score
     */
    public WinGameAnimation(GameLevel gameLevel, ScoreTrackingListener score) {
        this.gameLevel = gameLevel;
        this.score = score;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        gameLevel.doOneFrame(d);
        d.setColor(Color.orange);
        d.drawText((int) Tools.BORDER_WIDTH, d.getHeight() / 2, "WINNER! YOUR SCORE IS : " + score.
                getCounter().getValue(), 32);
        d.drawText((int) Tools.BORDER_WIDTH, d.getHeight() / 2 + 50, "Press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}