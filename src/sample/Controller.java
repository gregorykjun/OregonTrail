package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

public class Controller implements Initializable {
    private Image backpack = new Image("resources/Backpack.png");

    //Minor Events - Trading, Lose trail, Weather event, Illness, Wheel broke
    //Major events - Visiting a town, River, Hunting
    private double Cash;
    private int Food;
    private int Ox;
    private int Bullets;
    private int SpareWheels;
    private int WheelsInUse = 4;
    private int DaysLeft;
    private int Day=0;

    private double foodPrice, oxPrice, bulletPrice, wheelPrice;

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

    //Tabpane 3
    @FXML
    private Label animationDays;
    private boolean animationRunning;
    private int metersLeft;
    private boolean eventPicked;
    private int timePerDay = 3;
    private int time;
    private int distanceTraveled=0;
    private int distanceTillEvent;
    @FXML
            private Label eventMiles, milesTraveled, date;
    int day;
    int month;
    int year = 1848;

    //Tab pane 4
    @FXML
    private Label cashOwned, foodOwned, oxOwned, bulletsOwned, wheelsOwned, foodPricelbl, oxPricelbl, bulletPricelbl, wheelPricelbl, foodCostlbl, oxCostlbl, bulletCostlbl, wheelCostlbl;
    @FXML
    private TextArea shopDescription;
    @FXML
    private TextField foodPriceTF, oxPriceTF, bulletPriceTF, wheelPriceTF;
    private String numberFoodtoBuy = "0", numberOxtoBuy= "0", numberBullettoBuy= "0", numberWheeltoBuy= "0";

    //Tabpane 5
    @FXML
    private ImageView itemsCharacterImage;
    @FXML
    private TextArea itemsCharacterDescription;
    @FXML
    private Label itemsFoodOwned, itemsOxOwned, itemsBulletOwned, itemsWheelOwned, itemsDay;

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
            Cash = 4500;
            Ox = 2;
            Food = 50;
            Bullets = 10;
            SpareWheels = 0;
            DaysLeft=300;
        }
        else if (NormalSelected){
            Normal = true;
            Cash = 4000;
            Ox = 0;
            Food = 30;
            Bullets = 15;
            SpareWheels = 0;
            DaysLeft= 270;
        }
        else {
            Hard = true;
            Cash = 3000;
            Ox = 0;
            Food = 0;
            Bullets = 0;
            SpareWheels = 0;
            DaysLeft=240;
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
                    day = 1;
                    month = 3;
                    switchtoTabPane4();
                    initialize();
                }
            }
        }
        else if (Normal){
            if (!MCName.getText().equals("")){
                if (!WName.getText().equals("")){
                    if (!C1Name.getText().equals("")){
                        day = 31;
                        month = 3;
                        switchtoTabPane4();
                        initialize();
                    }
                }
            }
        }
        else if (Hard){
            if (!MCName.getText().equals("")){
                if (!WName.getText().equals("")){
                    if (!C1Name.getText().equals("")){
                        if (!C2Name.getText().equals("")){
                            day = 30;
                            month = 4;
                            switchtoTabPane4();
                            initialize();
                        }
                    }
                }
            }
        }
        start();
        newMajorEvent();
        //This checks to make sure the player has filled out something for the text field and proceeds onto the next screen
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
    private void handleItems() {
        controlPressed();
        switchtoTabPane5();
        //Switches to items animation
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

       //Animation for the third tabpane to a looping background.
        parallelTransition.statusProperty().addListener((obs, oldValue, newValue) -> {
        });
    }
    private void startAnimation() {
        parallelTransition.play();
        time = (int) (System.nanoTime()/1000000000);
    }

    private void pauseAnimation() {
        parallelTransition.pause();
    }

    private void controlPressed() {
        if( parallelTransition.getStatus() == Animation.Status.RUNNING ) {
            pauseAnimation();
            animationRunning = false;
        } else {
            startAnimation();
            animationRunning = true;
        }
    }
    //This code will pause and start the looping animation of my code
    public void start(){
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                if (animationRunning){
                    int y = (int)(now/1000000000);
                    if (y- time >=2){
                        //New day
                        newDay();
                        time = (int) (System.nanoTime()/1000000000);
                    }
                }
                //This is to see if the animation is running and will do a day to day sort of thing.
            }
        }.start();
    }
    //Once the timer hits 2 seconds, a new day will occur and there will be an event check and changing of the dates.
    //Weather will also possibly change.
    private void newDay(){
        //This will change the food amount, change weather, distance traveled and left till next landmark.
        if (WheelsInUse == 4){
            int distance = RandomNumber(20,16);
            distanceTraveled = distanceTraveled + distance;
            if (distanceTillEvent - distance>0){
                distanceTillEvent = distanceTillEvent - distance;
            }
            else {
                tabPane.getSelectionModel().select(6);
                newMajorEvent();
                controlPressed();
                eventPicked = false;
                //Distance event reached, reset a new event and trigger this one.
            }
        }
        else {
            int distance = RandomNumber(15,11);
            distanceTraveled = distanceTraveled + distance;
            if (distanceTillEvent - distance>0){
                distanceTillEvent = distanceTillEvent - distance;
            }
            else {
                tabPane.getSelectionModel().select(6);
                newMajorEvent();
                controlPressed();
                eventPicked = false;
                //Distance event reached, reset a new event and trigger this one.
            }
        }

        eventMiles.setText("Distance until event: " + distanceTillEvent + " Miles");
        milesTraveled.setText("Distance Traveled: " + distanceTraveled + " Miles");
        changeDoT();

    }
    @FXML
    private void performanAction(){

    }

    private void minorEvent(){

        //this is possibly something that can negatively affect the player.
    }
    private void newMajorEvent(){
        distanceTillEvent=100;
        //This will pick a new event and distance to travel for the player.

    }
    private int RandomNumber(int high, int low){
        return (int)(Math.random()*high)+low;
    }

    private void changeDoT(){
        day++;
        if (year%4==0){
            if (month==2){
                if (day==30){
                    month++;
                    day=1;
                }
            }
            if (month ==4 || month == 6 || month ==9 ||month==11){
                if (day==31){
                    month++;
                    day=1;
                }
            }
            else {
                if (day==32) {
                    month++;
                    day = 1;
                }
            }
        }
        else{
            if (month==2){
                if (day==29){
                    month++;
                    day=1;
                }
            }
            if (month ==4 || month == 6 || month ==9 ||month==11){
                if (day==31){
                    month++;
                    day=1;
                }
            }
            else {
                if (day==32){
                    month++;
                    day=1;
                }
            }
        }
        if (month>12){
            year++;
            month=1;
        }
        //Small UI thing, shows the date for the player.
    }



    //Tabpane (4)
    @FXML
    private void foodDescription(){
        shopDescription.clear();
        shopDescription.setText("This will get you X pounds of food. Each family member will eat 4 pounds of food a day.");
        //Sets the food description
    }
    @FXML
    private void oxDescription(){
        shopDescription.clear();
        shopDescription.setText("Ox is what is pulling your wagon to the Oregon Trail. Each ox (up to 4) will increase your travel speed. It's recommended to have around 3-5 ox in case of any injuries.");
        //Sets the ox description
    }
    @FXML
    private void bulletDescription(){
        shopDescription.clear();
        shopDescription.setText("Bullets are used when hunting for more food. Cheap and very useful as shops become less frequent further along the trail.");
        //Sets the food description

    }
    @FXML
    private void wheelDescription(){
        shopDescription.clear();
        shopDescription.setText("It's always recommended to carry extra wheel in case they get damaged during crossing a river or any other unforeseen events.");
        //Sets the wheel description

    }
    @FXML
    private void clearShopDescription(){
        shopDescription.clear();
        shopDescription.setText("Welcome! Hover over an icon and I'll tell you its description.");
        //Clears all description
    }
    @FXML
    private void foodBought(InputEvent event){
        if (foodPriceTF.getText().length()>0){
            numberFoodtoBuy = foodPriceTF.getText();
            boolean checkforNonNumbers =true;
            for (int i =0; i < foodPriceTF.getText().length(); i ++){
                if (foodPriceTF.getText().charAt(i) > 57 || foodPriceTF.getText().charAt(i) < 48){
                    checkforNonNumbers=false;
                }
            }
            if (checkforNonNumbers){
                foodCostlbl.setText("$" + (Integer.parseInt(numberFoodtoBuy)*foodPrice));
            }
            else {
                foodCostlbl.setText("Error.");
            }
        }
        //This function helps the UI with only allowing numbers and showing the price of the items.
        //This idea is from my stock project.
    }

    @FXML
    private void oxBought(InputEvent event){
        if (oxPriceTF.getText().length()>0){
            numberOxtoBuy = oxPriceTF.getText();
            boolean checkforNonNumbers =true;
            for (int i =0; i < oxPriceTF.getText().length(); i ++){
                if (oxPriceTF.getText().charAt(i) > 57 || oxPriceTF.getText().charAt(i) < 48){
                    checkforNonNumbers=false;
                }
            }
            if (checkforNonNumbers){
                oxCostlbl.setText("$" + (Integer.parseInt(numberOxtoBuy)*oxPrice));
            }
            else {
                oxCostlbl.setText("Error.");
            }
        }
        //This function helps the UI with only allowing numbers and showing the price of the items.
    }
    @FXML
    private void bulletBought(InputEvent event){
        if (bulletPriceTF.getText().length()>0){
            numberBullettoBuy = bulletPriceTF.getText();
            boolean checkforNonNumbers =true;
            for (int i =0; i < bulletPriceTF.getText().length(); i ++){
                if (bulletPriceTF.getText().charAt(i) > 57 || bulletPriceTF.getText().charAt(i) < 48){
                    checkforNonNumbers=false;
                }
            }
            if (checkforNonNumbers){
                bulletCostlbl.setText("$" + (Integer.parseInt(numberBullettoBuy)*bulletPrice));
            }
            else {
                bulletCostlbl.setText("Error.");
            }
        }
        //This function helps the UI with only allowing numbers and showing the price of the items.
    }

    @FXML
    private void wheelBought(InputEvent event){
        if (wheelPriceTF.getText().length()>0){
            numberWheeltoBuy = wheelPriceTF.getText();
            boolean checkforNonNumbers =true;
            for (int i =0; i < wheelPriceTF.getText().length(); i ++){
                if (wheelPriceTF.getText().charAt(i) > 57 || wheelPriceTF.getText().charAt(i) < 48){
                    checkforNonNumbers=false;
                }
            }
            if (checkforNonNumbers){
                wheelCostlbl.setText("$" + (Integer.parseInt(numberWheeltoBuy)*wheelPrice));
            }
            else {
                wheelCostlbl.setText("Error.");
            }
        }
        //This function helps the UI with only allowing numbers and showing the price of the items.
    }

    @FXML
    private void foodConfirm(){
        if (Cash - (Integer.parseInt(numberFoodtoBuy)*foodPrice) > 0){
            Food = Food + Integer.parseInt(numberFoodtoBuy);
            Cash = Cash - (Integer.parseInt(numberFoodtoBuy)*foodPrice);
            foodPriceTF.clear();
            numberFoodtoBuy = "0";
            foodCostlbl.setText("$0");
            switchtoTabPane4();
        }
        //purchases the item
    }
    @FXML
    private void oxConfirm(){
        if (Cash - (Integer.parseInt(numberOxtoBuy)*oxPrice) > 0){
            Ox = Ox + Integer.parseInt(numberOxtoBuy);
            Cash = Cash - (Integer.parseInt(numberOxtoBuy)*oxPrice);
            oxPriceTF.clear();
            numberOxtoBuy= "0";
            oxCostlbl.setText("$0");
            switchtoTabPane4();
        }
        //purchases the item
    }
    @FXML
    private void bulletConfirm(){
        if (Cash - (Integer.parseInt(numberBullettoBuy)*bulletPrice) > 0){
            Bullets = Bullets + Integer.parseInt(numberBullettoBuy);
            Cash = Cash - (Integer.parseInt(numberBullettoBuy)*bulletPrice);
            bulletPriceTF.clear();
            numberBullettoBuy= "0";
            bulletCostlbl.setText("$0");
            switchtoTabPane4();
        }
        //purchases the item
    }
    @FXML
    private void wheelConfirm(){
        if (Cash - (Integer.parseInt(numberWheeltoBuy)*wheelPrice) > 0){
            SpareWheels =  SpareWheels + Integer.parseInt(numberWheeltoBuy);
            Cash = Cash - (Integer.parseInt(numberWheeltoBuy)*wheelPrice);
            wheelPriceTF.clear();
            numberWheeltoBuy= "0";
            wheelCostlbl.setText("$0");
            switchtoTabPane4();
        }
        //purchases the item
    }

    private double roundtoHundreds( double multiplier){
        return ((int)((Day*multiplier)*100))/100.0;
    }

    private void switchtoTabPane4(){
        tabPane.getSelectionModel().select(4);
        if (Easy){
            foodPrice =2 + roundtoHundreds(.005);
            oxPrice = 300 + roundtoHundreds(.5);
            bulletPrice = .5 + roundtoHundreds(.001);
            wheelPrice = 20 + roundtoHundreds(.5);
            //The price of the items increase by x amount for each day you are in the trail
        }
        else if (Normal){
            foodPrice =2.5 + roundtoHundreds(.01);
            oxPrice = 325 + roundtoHundreds(.75);
            bulletPrice = .5 + roundtoHundreds(.0025);
            wheelPrice = 25 + roundtoHundreds(.1);
            //The price of the items increase by x amount for each day you are in the trail
        }
        else{
            foodPrice =3 + roundtoHundreds(.015);
            oxPrice = 350 + roundtoHundreds(1);
            bulletPrice = .5 + roundtoHundreds(.003);
            wheelPrice = 30 + roundtoHundreds(.15);
            //The price of the items increase by x amount for each day you are in the trail
        }
        cashOwned.setText("Cash Owned: " + Cash);
        bulletsOwned.setText("Bullets Owned " + Bullets);
        foodOwned.setText("Food Owned: " + Food);
        oxOwned.setText("Ox Owned: " + Ox);
        wheelsOwned.setText("Spare Wheels Owned: " + SpareWheels);
        foodPricelbl.setText("$" + foodPrice);
        oxPricelbl.setText("$" + oxPrice);
        bulletPricelbl.setText("$" +bulletPrice);
        wheelPricelbl.setText("$" + wheelPrice);
        //This is necessary to update all of the information as the tabpane is switched to the shop
    }
    @FXML
    private void returnToGame(){
        controlPressed();
        tabPane.getSelectionModel().select(3);
        //This returns to the oregon trail animation
    }




    private void switchtoTabPane5(){
        tabPane.getSelectionModel().select(5);
        itemsFoodOwned.setText("x "+ Food);
        itemsOxOwned.setText("x " + Ox);
        itemsBulletOwned.setText("x " + Bullets);
        itemsWheelOwned.setText("x " + SpareWheels);
        itemsDay.setText("Day " + Day);
        if (Easy){
            itemsCharacterImage.setImage(new Image("resources/ForwardEasyCharacter.png"));
        }
        else if (Normal){
            itemsCharacterImage.setImage(new Image("resources/ForwardNormalCharacter.png"));
        }
        else {
            itemsCharacterImage.setImage(new Image("resources/ForwardHardCharacter.png"));
        }
        //To set up : Health description of family
        //This sets up the items tab
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
} 