package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable {
    private Image backpack = new Image("resources/Backpack.png");

    @FXML
    private Label lbl1,lbl2;
    @FXML
    private Button startMenuBtn, window;
    @FXML
    private TabPane tabPane;
    @FXML
    private void handleButtonAction (ActionEvent event) throws Exception {
        tabPane.getSelectionModel().select(1);
        initialize();
    }
    @FXML
    private void handleItems() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) window.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Items.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //Switches to items animation
    }
    public void start(){
        new AnimationTimer(){
            @Override
            public void handle(long now) {

//                counter.setText((now/1000000000)-x + "");
//                countdown.setText(y - ((now/1000000000)-x) + "");

            }
        }.start();
    }




    //Animation Code
    //Tab Pane (1)
    private int BACKGROUND_WIDTH = 750;
    private ParallelTransition parallelTransition;
    @FXML
    private Button btnControl;
    @FXML
    private ImageView background1, background2;
    private void initialize(){
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(10000), background1);
        translateTransition.setFromX(0);
        translateTransition.setToX(-1 * BACKGROUND_WIDTH);
        translateTransition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition translateTransition2 =
                new TranslateTransition(Duration.millis(10000), background2);
        translateTransition2.setFromX(0);
        translateTransition2.setToX(-1 * BACKGROUND_WIDTH);
        translateTransition2.setInterpolator(Interpolator.LINEAR);

        parallelTransition =
                new ParallelTransition( translateTransition, translateTransition2 );
        parallelTransition.setCycleCount(Animation.INDEFINITE);

        //
        // Sets the label of the Button based on the animation state
        //
        parallelTransition.statusProperty().addListener((obs, oldValue, newValue) -> {
            if( newValue == Animation.Status.RUNNING ) {
                btnControl.setText( "||" );
            } else {
                btnControl.setText( ">" );
            }
        });
    }
    public void startAnimation() {

        parallelTransition.play();
    }

    public void pauseAnimation() {
        parallelTransition.pause();
    }

    @FXML
    public void controlPressed() {
        if( parallelTransition.getStatus() == Animation.Status.RUNNING ) {
            pauseAnimation();
        } else {
            startAnimation();
        }
    }

    //This code will pause and start the looping animation of my code
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
} 