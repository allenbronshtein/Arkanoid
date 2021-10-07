import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class LoseGameAnimation implements Animation {
    private GameLevel gameLevel;

    /**
     * Instantiates a new Pause screen.
     *
     * @param gameLevel the gameLevel
     */
    public LoseGameAnimation(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        gameLevel.doOneFrame(d);
        d.setColor(Color.orange);
        d.drawText(10, d.getHeight() / 2, "GAME OVER -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}