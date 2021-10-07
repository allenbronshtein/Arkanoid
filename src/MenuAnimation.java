//Allen Bronshtein

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu {
    private Background defualt = new Background();
    private String name;
    private AnimationRunner runner;
    private GameFlow gameFlow;
    private HighScoresAnimation highScoresAnimation;
    private Exit exit;
    private HighScoresTable scores;

    /**
     * Instantiates a new Menu animation.
     *
     * @param name the name
     * @param r    the r
     * @param g    the g
     * @param h    the h
     * @param e    the e
     * @param s    the s
     */
    public MenuAnimation(String name, AnimationRunner r, GameFlow g, HighScoresAnimation h, Exit e, HighScoresTable s) {
        this.name = name;
        this.runner = r;
        this.gameFlow = g;
        this.highScoresAnimation = h;
        this.exit = e;
        this.scores = s;
    }

    private Map<String, Selection> selections = new HashMap<>();

    @Override
    public void addSelection(String key, String message, Object data) {
        selections.put(key, new Selection<>(message, data));
    }

    @Override
    public Task getStatus(String key) {
        return (Task) selections.get(key).getData();
    }

    @Override
    public void addSubMenu(String key, String message, Menu sub) {

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        defualt.drawOn(d);
        d.setColor(Color.cyan.darker());
        d.drawText(50, 120, name, 150);
        d.setColor(Color.cyan.darker());
        d.drawText(100, 250, "S TO START", 100);
        d.setColor(Color.cyan.darker().darker());
        d.drawText(20, 350, "H TO HIGHSCORES", 80);
        d.setColor(Color.cyan.darker().darker());
        d.drawText(50, 450, "Q TO QUIT", 100);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }

    /**
     * Initiliaze.
     */
    public void initiliaze() {
        addSelection("s", "Start", new RunSubMenuTask(new RunLevelsTask(gameFlow, runner, scores), runner));
        addSelection("h", "HighScores", new HighScoresTask(highScoresAnimation, runner));
        addSelection("q", "Exit", exit);
    }
}