import biuoop.DialogManager;
import java.io.IOException;
import java.util.List;

/**
 * The type Run levels task.
 */
public class RunLevelsTask implements Task<Void> {
    private GameFlow gameFlow;
    private AnimationRunner runner;
    private final int numOfLives = 7;
    private LivesIndicator lives = new LivesIndicator(numOfLives);
    private int count = 0;
    private HighScoresTable scores;

    /**
     * Instantiates a new Run levels task.
     *
     * @param gameFlow the game flow
     * @param runner   the runner
     * @param scores   the scores
     */
    public RunLevelsTask(GameFlow gameFlow, AnimationRunner runner, HighScoresTable scores) {
        this.gameFlow = gameFlow;
        this.runner = runner;
        this.scores = scores;
    }

    @Override
    public Void run() throws IOException {
        List<Level> levels = gameFlow.getLevels();
        ScoreTrackingListener score;
        for (int i = 0; i < levels.size(); i++) {
            GameLevel gameLevel = new GameLevel();
            score = new ScoreTrackingListener(gameLevel, new Counter(count));
            gameLevel.initialize(new Level(levels.get(i).getLevel()), score, lives, runner);
            gameLevel.run();
            count = score.getCounter().getValue();
            lives = new LivesIndicator(lives.getNumLives());
            if (lives.getNumLives() == 0) {
                break;
            }
            if (i + 1 == levels.size()) {
                score.getCounter().increase(100);
                count = score.getCounter().getValue();
                runner.run(new KeyPressStoppableAnimation(runner.getGui().getKeyboardSensor(), runner.
                        getGui().getKeyboardSensor().SPACE_KEY, new WinGameAnimation(gameLevel, score)));
            }
        }
        int rank = scores.getRank(count);
        if (rank <= scores.size()) {
            DialogManager dialog = runner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Enter name", "What is your name?", "");
            scores.add(new ScoreInfo(name, count));
        }
//        scores.save(new File("/highscores/HighScores.txt"));
        runner.run(new KeyPressStoppableAnimation(runner.getGui().getKeyboardSensor(), runner.
                getGui().getKeyboardSensor().SPACE_KEY, new HighScoresAnimation(scores)));
        count = 0;
        lives = new LivesIndicator(numOfLives);
        return null;
    }

    /**
     * Gets game flow.
     *
     * @return the game flow
     */
    public GameFlow getGameFlow() {
        return gameFlow;
    }
}
