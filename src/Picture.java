import java.awt.Color;
import java.awt.Image;

/**
 * The type Picture.
 */
//Allen bronshtein
//206228751
public class Picture {
    private int hits;
    private Image img;
    private Color color;

    /**
     * Instantiates a new Picture.
     *
     * @param hits  the hits
     * @param color the color
     */
    public Picture(int hits, Color color) {
        this.hits = hits;
        this.color = color;
    }

    /**
     * Instantiates a new Picture.
     *
     * @param hits the hits
     * @param img  the img
     */
    public Picture(int hits, Image img) {
        this.hits = hits;
        this.img = img;

    }

    /**
     * Is image boolean.
     *
     * @return the boolean
     */
    public boolean isImage() {
        return img != null;
    }

    /**
     * Is color boolean.
     *
     * @return the boolean
     */
    public boolean isColor() {
        return color != null;
    }

    /**
     * Exist boolean.
     *
     * @return the boolean
     */
    public boolean exist() {
        return img != null || color != null;
    }

    /**
     * Remove one hit.
     */
    public void removeOneHit() {
        hits--;
    }

    /**
     * Refresh.
     */
    public void refresh() {
        removeOneHit();
        clear();
    }

    /**
     * Clear.
     */
    public void clear() {
        if (hits == 0) {
            img = null;
            color = null;
        }
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets img.
     *
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * Sets hits.
     *
     * @param h the h
     */
    public void setHits(int h) {
        this.hits = h;
    }

    /**
     * Sets picture with color.
     *
     * @param c the c
     */
    public void setPictureWithColor(Color c) {
        this.color = c;
        this.img = null;
    }

    /**
     * Sets picture with img.
     *
     * @param i the
     */
    public void setPictureWithImg(Image i) {
        this.img = i;
        this.color = null;
    }
}
