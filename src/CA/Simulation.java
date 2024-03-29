package CA;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Simulation extends Application {
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 2;
    //public static final double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final String SIMULATION_CONFIG_FILE = "game.csv";

    private Scene myScene;
    private Timeline myAnimation;
    private Text info;
    private Text end;
    private Group myRoot;
    private Grid myGrid;

    public void start(Stage stage){
        // attach scene to the stage and display it
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.show();
        // attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step());
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private void step () {
        myGrid.update();
        myGrid.addUpdatedToScene();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame (int width, int height, Paint background) {
        myGrid = new SquareGrid(SIMULATION_CONFIG_FILE, SIZE, SIZE);
        // create one top level collection to organize the things in the scene
        myRoot = new Group();
        // create a place to see the shapes
        var scene = new Scene(myRoot, width, height, background);
        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        // create blocks according to configuration fill
        addSimulationNodes();
        return scene;
    }


    private void addSimulationNodes() {
        myGrid.addToScene(myRoot);
    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        // move the player
        //myMover.movePlayer(code);
        // pause the animation
        if (code == KeyCode.SPACE) {
            if (myAnimation.getStatus() == Animation.Status.RUNNING) {
                myAnimation.pause();
            } else {
                myAnimation.play();
            }
        }
        if (code == KeyCode.ESCAPE) { System.exit(0); }
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
