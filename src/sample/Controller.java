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
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable {
    private Image backpack = new Image("resources/Backpack.png");


    private int Cash;
    private int Food;
    private int Ox;
    private int Bullets;
    private int SpareWheels;
    private int WheelsInUse = 4;


    //@FXML
    //private Ellipse easyDescription, normalDescription;
    @FXML
    private TabPane tabPane;
    private String MainCharacterName, WifeName, Child1Name, Child2Name;
    private DropShadow borderSelect = new DropShadow();


    @FXML
    private ImageView easyModeImage, normalModeImage, hardModeImage;
    @FXML
    private TextArea characterDescription;
    @FXML
    private Label selection;
    @FXML
    private Button window, confirmBtn;
    //This is tabPane 0 and 1

    //Tab pane 2
    @FXML
    private ImageView MCImage;
    @FXML
    private TextField MCName, WName, C1Name, C2Name;
    @FXML
    private Label C1lbl, C2lbl;
    @FXML
    private Button startGameBtn;

    //Tab pane 4
    @FXML
    private Label cashOwned, foodOwned, oxOwned, bulletsOwned, wheelsOwned, foodPrice, oxPrice, bulletPrice, wheelPrice, foodCost, oxCost, bulletCost, wheelCost;
    @FXML
    private TextArea shopDescription;

    @FXML
    private Button startMenuBtn;
    private boolean Easy=false, Normal=false, Hard=false          , EasySelected = false, NormalSelected = false, HardSelected = false;
    @FXML
    private void handleButtonAction (ActionEvent event) throws Exception {
        tabPane.getSelectionModel().select(1);
        easyModeImage.setImage(new Image("resources/ForwardEasyCharacter.png"));
        normalModeImage.setImage(new Image("resources/ForwardNormalCharacter.png"));
        hardModeImage.setImage(new Image("resources/ForwardHardCharacter.png"));
    }
    @FXML
    private void easyDescription(){
        characterDescription.setVisible(true);
        characterDescription.setText("Easiest character to play. Increased chance of succeeding events, receiving beneficial events, and receives a boost to amount of food gained from each hunt. Has a wife but no children. Starts with X gold");
        //This is the on mouse hover action to show the description of the character
    }
    @FXML
    private void easyModeClicked(){
        borderSelect.setOffsetY(easyModeImage.getX());
        borderSelect.setOffsetX(easyModeImage.getY());
        borderSelect.setColor(Color.GOLD);
        borderSelect.setWidth(easyModeImage.getFitWidth());
        borderSelect.setHeight(easyModeImage.getFitHeight());
        easyModeImage.setEffect(borderSelect);
        normalModeImage.setEffect(null);
        hardModeImage.setEffect(null);
        selection.setVisible(true);
        confirmBtn.setVisible(true);
        EasySelected = true;
        NormalSelected = false;
        HardSelected = false;
        //When clicked, this highlights the image of the character and prepares the player onto the next screen
    }
    @FXML
    private void normalDescription(){
        characterDescription.setVisible(true);
        characterDescription.setText("Average character. Has one wife and one child to travel with. Starts with X gold");
        //This is the on mouse hover action to show the description of the character
    }
    @FXML
    private void normalModeClicked(){
        borderSelect.setOffsetY(normalModeImage.getX());
        borderSelect.setOffsetX(normalModeImage.getY());
        borderSelect.setColor(Color.GOLD);
        borderSelect.setWidth(normalModeImage.getFitWidth());
        borderSelect.setHeight(normalModeImage.getFitHeight());
        normalModeImage.setEffect(borderSelect);
        easyModeImage.setEffect(null);
        hardModeImage.setEffect(null);
        selection.setVisible(true);
        confirmBtn.setVisible(true);
        EasySelected = false;
        NormalSelected = true;
        HardSelected = false;
        //When clicked, this highlights the image of the character and prepares the player onto the next screen
    }
    @FXML
    private void hardDescription(){
        characterDescription.setVisible(true);
        characterDescription.setText("Hardest character to play. 1 wife, 2 children, increased chance to fail events and receive negative events. People really aren't sure why he's travelling. Starts with X gold");
        //This is the on mouse hover action to show the description of the character
    }
    @FXML
    private void hardModeClicked(){
        borderSelect.setOffsetY(hardModeImage.getX());
        borderSelect.setOffsetX(hardModeImage.getY());
        borderSelect.setColor(Color.GOLD);
        borderSelect.setWidth(hardModeImage.getFitWidth());
        borderSelect.setHeight(hardModeImage.getFitHeight());
        hardModeImage.setEffect(borderSelect);
        normalModeImage.setEffect(null);
        easyModeImage.setEffect(null);
        selection.setVisible(true);
        confirmBtn.setVisible(true);
        EasySelected = false;
        NormalSelected = false;
        HardSelected = true;
        //When clicked, this highlights the image of the character and prepares the player onto the next screen
    }
    @FXML
    private void clearDescription(){
        characterDescription.setVisible(false);
        characterDescription.clear();
        //This clears the text field when the mouse has left the image.
    }
    @FXML
    private void confirmSelectionCharacter(){
        if (EasySelected){
            Easy = true;
            Cash = 7500;
            Ox = 2;
            Food = 50;
            Bullets = 10;
            SpareWheels = 0;
        }
        else if (NormalSelected){
            Normal = true;
            Cash = 5000;
            Ox = 0;
            Food = 30;
            Bullets = 15;
            SpareWheels = 0;
        }
        else {
            Hard = true;
            Cash = 3000;
            Ox = 0;
            Food = 0;
            Bullets = 0;
            SpareWheels = 0;
        }
        tabPane.getSelectionModel().select(2);
        checkMode();
        //This is a button that confirms the difficulty the player selected and will load up all the stats needed. I plan on using subs and supers to use this class.
    }
    @FXML
    private void startGame(){
        if (Easy){
            if (!MCName.getText().equals("")){
                if (!WName.getText().equals("")){
                    switchtoTabPane4();
                    initialize();
                    controlPressed();
                }
            }
        }
        else if (Normal){
            if (!MCName.getText().equals("")){
                if (!WName.getText().equals("")){
                    if (!C1Name.getText().equals("")){
                        switchtoTabPane4();
                        initialize();
                        controlPressed();
                    }
                }
            }
        }
        else if (Hard){
            if (!MCName.getText().equals("")){
                if (!WName.getText().equals("")){
                    if (!C1Name.getText().equals("")){
                        if (!C2Name.getText().equals("")){
                            switchtoTabPane4();
                            initialize();
                            controlPressed();
                        }
                    }
                }
            }
        }
    }
    private void checkMode(){
        if (Easy){
            C1lbl.setVisible(false);
            C1Name.setVisible(false);
            C2lbl.setVisible(false);
            C2Name.setVisible(false);
            MCImage.setImage(new Image("resources/ForwardEasyCharacter.png"));
        }
        else if (Normal){
            C2lbl.setVisible(false);
            C2Name.setVisible(false);
            MCImage.setImage(new Image("resources/ForwardNormalCharacter.png"));
        }
        else {
            MCImage.setImage(new Image("resources/ForwardHardCharacter.png"));
        }
        //This sets up tabpane 2 in order to make the player fill out the only tabs needed.
    }
    @FXML
    private void handleItems() throws IOException {

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
    //Tab Pane (3)
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
        });
    }
    private void startAnimation() {

        parallelTransition.play();
    }

    private void pauseAnimation() {
        parallelTransition.pause();
    }

    private void controlPressed() {
        if( parallelTransition.getStatus() == Animation.Status.RUNNING ) {
            pauseAnimation();
        } else {
            startAnimation();
        }
    }
    //This code will pause and start the looping animation of my code




    //Tabpane (4)
    @FXML
    private void foodDescription(){
        shopDescription.clear();
        shopDescription.setText("This will get you X pounds of food. Each family member will eat 4 pounds of food a day.");
    }
    @FXML
    private void oxDescription(){
        shopDescription.clear();
        shopDescription.setText("Ox is what is pulling your wagon to the Oregon Trail. Each ox (up to 4) will increase your travel speed. It's recommended to have around 3-5 ox in case of any injuries.");
    }
    @FXML
    private void bulletDescription(){
        shopDescription.clear();
        shopDescription.setText("Bullets are used when hunting for more food. Cheap and very useful as shops become less frequent further along the trail.");
    }
    @FXML
    private void wheelDescription(){
        shopDescription.clear();
        shopDescription.setText("It's always recommended to carry extra wheel in case they get damaged during crossing a river or any other unforeseen events.");
    }
    @FXML
    private void clearShopDescription(){
        shopDescription.clear();
        shopDescription.setText("Welcome! Hover over an icon and I'll tell you its description.");
    }
    @FXML
    private void foodConfirm(){

    }
    @FXML
    private void oxConfirm(){

    }
    @FXML
    private void bulletConfirm(){

    }
    @FXML
    private void wheelConfirm(){

    }

    private void switchtoTabPane4(){
        tabPane.getSelectionModel().select(4);
        cashOwned.setText("Cash Owned: " + Cash);
        bulletsOwned.setText("Bullets Owned " + Bullets);
        foodOwned.setText("Food Owned: " + Food);
        oxOwned.setText("Ox Owned: " + Ox);
        wheelsOwned.setText("Spare Wheels Owned: " + SpareWheels);
        //This is necessary to update all of the information as the tabpane is switched to the shop
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
} 