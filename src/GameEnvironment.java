//Allen Bronshtein
//206228751

import java.util.ArrayList;
/**
 * The type user.GameLevel environment.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables = new ArrayList<>();
    private ArrayList<Collidable> requiredRemove = new ArrayList<>();

    /**
     * Add collidable.
     *
     * @param col : the c
     */
    public void addCollidable(Collidable col) {
        collidables.add(col);
    }

    /**
     * Gets collidable.
     *
     * @return the collidable
     */
    public ArrayList<Collidable> getCollidable() {
        return collidables;
    }

    /**
     * Sets collidables.
     *
     * @param list the list
     */
    public void setCollidables(ArrayList<Collidable> list) {
        collidables = new ArrayList<>(list);
    }

    /**
     * Add collidable.
     *
     * @param col the col
     */
    public void addToRemoveList(Collidable col) {
        requiredRemove.add(col);
    }

    /**
     * Gets remove list.
     *
     * @return the remove list
     */
    public ArrayList<Collidable> getRemoveList() {
        return requiredRemove;
    }
}