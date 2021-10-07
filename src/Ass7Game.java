//Allen Bronshtein
//206228751
import java.io.IOException;

/***.
 * Beginning of class Ass7Game
 */
public class Ass7Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            GameFlow game = new GameFlow(args[0]);
            game.run();
        }
        GameFlow game = new GameFlow(null);
        game.run();
    }
}