//Allen Bronshtein
//206228751

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite i : sprites) {
            i.drawOn(d);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}
