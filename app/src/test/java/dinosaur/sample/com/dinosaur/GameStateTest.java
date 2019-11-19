package dinosaur.sample.com.dinosaur;

import org.junit.Before;
import org.junit.Test;

import dinosaur.sample.com.dinosaur.data.Dinosaur;
import dinosaur.sample.com.dinosaur.data.GameState;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameStateTest {


    GameState gameState = null;
    @Before
    public void setUp() throws Exception {
        gameState = GameState.getInstance();
    }

    @Test
    public void getInstance() {
        GameState g2 = GameState.getInstance();

        assertEquals(gameState, g2);
    }

    @Test
    public void getNextOptions_size() {

        assertNotNull(gameState.getNextOptions());
        assertEquals(4, gameState.getNextOptions().size());
    }

    @Test
    public void processCorrectAnswer() {
        int valA = gameState.getmCorrectCounter();
        gameState.processCorrectAnswer();
        assertEquals(valA + 1, gameState.getmCorrectCounter());
    }

    @Test
    public void continueQuiz() {
        int valA = gameState.getmCurrentIndex();
        gameState.continueQuiz();
        assertEquals(valA + 1, gameState.getmCurrentIndex());
    }

    @Test
    public void getCurrentGameObject() {
        assertNotNull(gameState.getCurrentGameObject());

        Dinosaur d1 = gameState.getCurrentGameObject();
        gameState.continueQuiz();
        Dinosaur d2 = gameState.getCurrentGameObject();

        assertNotEquals(d1.getName(),d2.getName());
    }
}