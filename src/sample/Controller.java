package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;

public class Controller implements Initializable {
    private Image backpack = new Image("resources/Backpack.png");

    //Minor Events - Trading, Lose trail, Weather event, Illness, Wheel broke
    //Major events - Visiting a town, River, Hunting
    private FamilyMember main;
    private double Cash;
    private int Food;
    private int Ox;
    private int Bullets;
    private int SpareWheels;
    private int WheelsInUse = 4;
    private int DaysLeft;
    private int Day=0;
    private ArrayList<FamilyMember> family = new ArrayList<>();
    private double foodPrice, oxPrice, bulletPrice, wheelPrice;
    @FXML
    private GridPane huntingGridpane;

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
    private Label animationDays, foodNotice;
    private boolean animationRunning;
    private int metersLeft;
    private boolean eventPicked;
    private int time;
    private int distanceTraveled=0;
    private int distanceTillEvent;
    private int milestoWin = 2880;
    private boolean win = false;
    private String majorEvent;
    private boolean mEvent, lose;
    private boolean riverAction = false;
    @FXML
    private ImageView animationBackpack;
    @FXML
    private Button actionBtn, confirmEventBtn;
    @FXML
    private TextArea eventTA;
    @FXML
            private Label eventMiles, milesTraveled, date;
    int day;
    int month;
    int year = 1848;
    @FXML
    private Label actionHeading, actionDescription, option1, option2, option3, option4, actionWeather, actionRiverW, actionRiverD, actionDate;

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


    private int rtime;
    private double y =0;
    private boolean riverStart = false;
    private boolean riverCross = false;
    @FXML
    private TextArea riverTA;
    @FXML
    private Button riverConfirmBtn;
    @FXML
    private ImageView landLeft, landRight, wagon;
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
                    family.add(new FamilyMember(WName.getText()));
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
                        family.add(new FamilyMember(WName.getText()));
                        family.add(new FamilyMember(C1Name.getText()));
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
                            family.add(new FamilyMember(WName.getText()));
                            family.add(new FamilyMember(C1Name.getText()));
                            family.add(new FamilyMember(C2Name.getText()));
                            switchtoTabPane4();
                            initialize();
                        }
                    }
                }
            }
        }
        main = new FamilyMember(MCName.getText());
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
                    double y = (double)(now/1000000000);
                    if (y- time >=1){
                        //New day
                        increaseDistance();
                        changeDoT();
                        time = (int) (System.nanoTime()/1000000000);
                    }
                }
                else if (tabPane.getSelectionModel().isSelected(6)){
                    double y = (double)(now/1000000000);
                    int timeAmount = RandomNumber(4,2);
                    for (int i =0 ; i < animals.size(); i ++){
                        if (!animals.get(i).dead){
                            if (y- animals.get(i).time >=timeAmount){
                                //New direction
                                int r = RandomNumber(5,1);
                                if (r==1){
                                    animals.get(i).moveDirection = "Up";
                                }
                                if (r==2){
                                    animals.get(i).moveDirection = "Left";
                                    boarArrayList.get(i).setImage(new Image("resources/boarLeft.png"));
                                }
                                if (r==3){
                                    animals.get(i).moveDirection = "Down";
                                }
                                if (r==4){
                                    animals.get(i).moveDirection = "Right";
                                    boarArrayList.get(i).setImage(new Image("resources/boarRight.png"));
                                }
                                if (r==5){
                                    animals.get(i).moveDirection = "Still";
                                }
                                animals.get(i).time = (int) (System.nanoTime()/1000000000);
                            }
                            else {
                                switch (animals.get(i).moveDirection) {
                                    case "Up":
                                        if (boarArrayList.get(i).getY() >= 0) {
                                            boarArrayList.get(i).setY(boarArrayList.get(i).getY() - 1);
                                        }
                                        animals.get(i).yPos = animals.get(i).yPos - 1;

                                        break;
                                    case "Left":
                                        if (boarArrayList.get(i).getX() >= 0) {
                                            boarArrayList.get(i).setX(boarArrayList.get(i).getX() - 1);
                                        }
                                        animals.get(i).xPos = animals.get(i).xPos - 1;
                                        break;
                                    case "Down":
                                        if (boarArrayList.get(i).getY() + 53 <= 400) {
                                            boarArrayList.get(i).setY(boarArrayList.get(i).getY() + 1);
                                        }
                                        animals.get(i).yPos = animals.get(i).yPos + 1;
                                        break;
                                    case "Right":
                                        if (boarArrayList.get(i).getX() <= 750) {
                                            boarArrayList.get(i).setX(boarArrayList.get(i).getX() + 1);
                                        }
                                        animals.get(i).xPos = animals.get(i).xPos + 1;
                                        break;
                                }
                            }
                        }
                    }
                }
                else if (riverStart){
                    if (riverCross){
                        if (y-time<.5){
                            y = (double) (now / 1000000000);
                        }
                        else if (y-rtime<4.5) {
                            y = (double) (now / 1000000000);
                            landRight.setX(landRight.getX() + 2.5);
                        }
                        else if (y-rtime<8.5){
                            y = (double) (now / 1000000000);
                            landLeft.setX(landLeft.getX() +1.45);
                        }
                        else {
                            riverStart = false;
                            RiverCross(true);
                        }
                    }
                    else {
                        if (y-time<.5){
                            y = (double) (now / 1000000000);
                        }
                        else if (y-rtime<4.5) {
                            y = (double) (now / 1000000000);
                            landRight.setX(landRight.getX() + 2.5);
                        }
                        else if (y-rtime<8.5){
                            y = (double) (now / 1000000000);
                            wagon.setImage(new Image("resources/brokenWagon.png"));
                        }
                        else {
                            riverStart = false;
                            RiverCross(false);
                        }
                    }
                }
                //This is to see if the animation is running and will do a day to day sort of thing.
            }
        }.start();
    }
    //Once the timer hits 2 seconds, a new day will occur and there will be an event check and changing of the dates.
    //Weather will also possibly change.
    private boolean majorEventHappening = false;
    private void increaseDistance(){
        if (WheelsInUse == 4){
            int distance = RandomNumber(17,12);
            distanceTraveled = distanceTraveled + distance;
            if (milestoWin - distanceTraveled <=0){
                win = true;
                eventTA.setText("You have reached Oregon! You win!!");
                controlPressed();
                eventTA.setVisible(true);
                confirmEventBtn.setVisible(true);
                animationBackpack.setVisible(false);
                actionBtn.setVisible(false);
            }
            else if (distanceTillEvent - distance>0){
                distanceTillEvent = distanceTillEvent - distance;
                newDay();
            }
            else {
                Day++;
                distanceTillEvent = 0;
                date.setText(month + "/" + day + "/" + year);
                animationDays.setText("Day " + Day);
                eventMiles.setText("Distance until " + majorEvent +":"  + distanceTillEvent + " Miles");
                milesTraveled.setText("Distance Traveled: " + distanceTraveled + " Miles");
                controlPressed();
                eventTA.setVisible(true);
                confirmEventBtn.setVisible(true);
                animationBackpack.setVisible(false);
                actionBtn.setVisible(false);
                if (majorEvent.equals("Store")){
                    eventTA.setText("You have reached a store.");
                }
                else {
                    eventTA.setText("You have reached a river.");
                }
                majorEventHappening = true;
                eventPicked = false;
                //Distance event reached, reset a new event and trigger this one.
            }
        }
        else {
            int distance = RandomNumber(6,11);
            distanceTraveled = distanceTraveled + distance;
            if (distanceTillEvent - distance>0){
                distanceTillEvent = distanceTillEvent - distance;
                newDay();
            }
            else {
                Day++;
                distanceTillEvent = 0;
                date.setText(month + "/" + day + "/" + year);
                animationDays.setText("Day " + Day);
                eventMiles.setText("Distance until " + majorEvent +":"  + distanceTillEvent + " Miles");
                milesTraveled.setText("Distance Traveled: " + distanceTraveled + " Miles");
                controlPressed();
                eventTA.setVisible(true);
                confirmEventBtn.setVisible(true);
                animationBackpack.setVisible(false);
                actionBtn.setVisible(false);
                if (majorEvent.equals("Store")){
                    eventTA.setText("You have reached the store.");
                }
                else {
                    eventTA.setText("You have reached a river.");
                }
                majorEventHappening = true;
                eventPicked = false;
                //Distance event reached, reset a new event and trigger this one.
            }
        }
    }
    private void newDay(){
        boolean minor = false;
        //If a character dies or runs out of food, that will be the minor event for the day.
        Day++;
        if (Day >= DaysLeft){
            eventTA.setText("You ran out of days!!");
            controlPressed();
            eventTA.setVisible(true);
            confirmEventBtn.setVisible(true);
            animationBackpack.setVisible(false);
            actionBtn.setVisible(false);
            lose = true;
            //You lose
        }
        if (!lose){
            int foodEaten = ((1 + family.size())* RandomNumber(5,3));
            if (Food - foodEaten <=0){
                foodNotice.setVisible(true);
                foodNotice.setText("You have no Food!");
                if (Food >0){
                    Food = 0;
                }
                else {
                    if (family.size()>0){
                        for (FamilyMember a : family){
                            a.increaseHealth(-1 * RandomNumber(3,1));
                        }
                    }
                    else {
                        main.increaseHealth(-1 * RandomNumber(6,3));
                        if (main.Health<=0){
                            eventTA.setText("You died!!");
                            controlPressed();
                            eventTA.setVisible(true);
                            confirmEventBtn.setVisible(true);
                            animationBackpack.setVisible(false);
                            actionBtn.setVisible(false);
                            lose = true;
                        }
                    }
                }
            }
            else {
                Food = Food - foodEaten;
                foodNotice.setVisible(false);
                if (main.getHealth()<100){
                    int healthIncrease = RandomNumber(4,2);
                    if (main.getHealth() + healthIncrease >100){
                        main.Health = 100;
                    }
                    else {
                        main.increaseHealth(healthIncrease);
                    }
                }
                for (FamilyMember a : family){
                    a.increaseHealth(RandomNumber(4,2));
                }
            }
        }
        if (!lose){
            for (FamilyMember a : family){
                a.changeHealthtoIllness();
            }
            String deathMessage = "";
            for (int i =0; i < family.size(); i ++){
                if (family.get(i).getHealth()<=0){
                    deathMessage = deathMessage + "\n" + family.get(i).getName() + " has died.";
                }
            }
            if (!deathMessage.equals("")){
                minor = true;
                eventTA.setVisible(true);
                eventTA.setText(deathMessage);
                confirmEventBtn.setVisible(true);
                animationBackpack.setVisible(false);
                actionBtn.setVisible(false);
                controlPressed();
            }
            //Food Deduction
            date.setText(month + "/" + day + "/" + year);
            //This will change the food amount, change weather, distance traveled and left till next landmark.
            animationDays.setText("Day " + Day);
            eventMiles.setText("Distance until " + majorEvent +":"  + distanceTillEvent + " Miles");
            milesTraveled.setText("Distance Traveled: " + distanceTraveled + " Miles");
            changeDoT();
            if (!minor){
                minorEvent();
            }
        }
    }
    @FXML
    private void performanAction(){
        tabPane.getSelectionModel().select(7);
        controlPressed();
        tabPane7perform();
    }
    private void tabPane7perform(){
        actionHeading.setText("Perform an Action");
        actionDescription.setText("What would you like to do?");
        option1.setText("1. Check the map");
        option2.setText("2. Hunt for food");
        option3.setText("3. Attempt to trade with other people.");
        option4.setText("4. Continue on trail");
        actionWeather.setVisible(false);
        actionRiverD.setVisible(false);
        actionRiverW.setVisible(false);
        actionDate.setText(month + "/" + day + "/" + year);
        riverAction = false;
        //This takes to the perform an action screen, allowing players to do multiple actions.
    }
    private int ferryamountCost= 0;
    private void tabPane7river(){
        actionHeading.setText("Cross the River");
        actionDescription.setText("You must cross the river in order to continue. What would you like to do?");
        ferryamountCost = RandomNumber(75,20);
        option1.setText("1. Ford the River");
        option2.setText("2. Caulk the River");
        option3.setText("3. Pay for a ferry. Ferry costs "+ferryamountCost + " dollars.");
        option4.setText("4. Wait to see if conditions improve");
        actionWeather.setVisible(true);
        actionRiverD.setVisible(true);
        actionRiverW.setVisible(true);
        actionDate.setText(month + "/" + day + "/" + year);
        riverAction = true;
        tabPane.getSelectionModel().select(7);
        double decimal = ((int)(Math.random()*10))/10.0;
        depth =  RandomNumber(3,1) + decimal;
        width = RandomNumber(225,150);
        actionRiverW.setText("River Width: " + width);
        actionRiverD.setText("River Depth: " + depth);
        changeConditions();
        //This sets up the pre screen to crossing the river.
    }
    private String condition = "";
    private double depth = 0 ;
    private int width = 0;
    private void changeConditions(){
        double decimal = ((int)(Math.random()*10))/10.0;
        depth =  RandomNumber(3,1) + decimal;
        actionRiverD.setText("River Depth: " + depth);
        actionDate.setText(month + "/" + day + "/" + year);
        int randomCondition = RandomNumber(4,1);
        if (randomCondition==1){
            condition = "Good";
        }
        else if (randomCondition==2){
            condition = "Hot";
        }
        else if (randomCondition==2){
            condition = "Cold";
        }
        else {
            condition = "Freezing";
        }
        actionWeather.setText("Weather: " + condition);
    }


private void minorEvent(){
    if (Easy){
        if (Math.random() < (.15 + (Day*.001))){
            if (Math.random()<.5){
                boolean noillness = false;
                for (FamilyMember a : family){
                    if (!a.getSick()) {
                        noillness = true;
                        break;
                    }
                }
                if (noillness){
                    if (family.size()>0){
                        double random = Math.random();
                        if (random<.4){
                            int familySize = family.size();
                            int randomNumber = RandomNumber(familySize , 1);
                            if (!family.get(randomNumber-1).getSick()){
                                int illnessChoose = RandomNumber(5,1);
                                if (illnessChoose == 1){
                                    family.get(randomNumber-1).changeIllness("Cholera");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Cholera.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }
                                else if (illnessChoose == 2){
                                    family.get(randomNumber-1).changeIllness("Diphtheria");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Diphtheria.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else if (illnessChoose == 3){
                                    family.get(randomNumber-1).changeIllness("Dysentery");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Dysentery.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else if (illnessChoose == 4){
                                    family.get(randomNumber-1).changeIllness("Measles");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Measles.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else {
                                    family.get(randomNumber-1).changeIllness("Typhoid Fever");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Typhoid Fever.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }
                            }
                            else {
                                family.get(randomNumber-1).cureIllness();
                                eventTA.setVisible(true);
                                eventTA.setText(family.get(randomNumber-1).getName() + " has been cured.");
                                confirmEventBtn.setVisible(true);
                                animationBackpack.setVisible(false);
                                actionBtn.setVisible(false);
                                controlPressed();
                            }
                        }
                    }
                }
            }
            else {
                int randomNumber = RandomNumber(4, 1);
                if (randomNumber == 1){
                    eventTA.setVisible(true);
                    int daysLost = RandomNumber(5,3);
                    eventTA.setText("Massive thunderstorm. Lose " + daysLost +" days.");
                    Day = Day + daysLost;
                    while (daysLost>0){
                        changeDoT();
                        daysLost--;
                    }
                    controlPressed();
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 2){
                    eventTA.setVisible(true);
                    int daysLost = RandomNumber(5,3);
                    eventTA.setText("Lose Trail. Lose " + daysLost +" days.");
                    Day = Day + daysLost;
                    while (daysLost>0){
                        changeDoT();
                        daysLost--;
                    }
                    controlPressed();
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 3){
                    if (SpareWheels>0){
                        SpareWheels--;
                        eventTA.setText("Break a wheel. " + SpareWheels + " spare wheels left.");
                    }
                    else {
                        WheelsInUse--;
                        eventTA.setText("Break a wheel. You only have " + WheelsInUse + " wheels in use.");
                    }
                    controlPressed();
                    eventTA.setVisible(true);
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 4){
                    Ox--;
                    controlPressed();
                    if (Ox == 0){
                        lose = true;
                        eventTA.setText("Ox has died. You have " + Ox + " ox left. You lose!");
                    }
                    else {
                        eventTA.setText("Ox has died. You have " + Ox + " ox left.");
                    }

                    eventTA.setVisible(true);
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
            }
            //Rain , Cholera , Diphtheria, Dysentery, Measles, Typhoid Fever, Lose trail - lose X days, Lose a wheel, Ox has died
            //Find gold, Gain food, Gain a wheel, Gain bullets
            //The chance that a negative event will occur is 30% and will increase by 1% every 10 days.
        }
        else if (Math.random()<.2){
            double random = Math.random();
            if (random<.4){
                //Positive event

                int event = RandomNumber(4,1);
                if (event == 1){
                    int amount = RandomNumber(3,1);
                    if (WheelsInUse<4){
                        int remainder = (WheelsInUse+amount)%amount;
                        WheelsInUse = 4;
                        SpareWheels = SpareWheels + remainder;
                    }
                    else {
                        SpareWheels = SpareWheels + amount;
                    }
                    eventTA.setText("Lucky Find! Gain " + amount + " wheels.");
                    controlPressed();
                }
                else if (event == 2){
                    int amount = RandomNumber(250,1);
                    Cash = Cash + amount;
                    eventTA.setText("Found gold! Gain " + amount + " dollar(s).");
                    controlPressed();
                }
                else if (event == 3){
                    int amount = RandomNumber(75,25);
                    Food = Food + amount;
                    eventTA.setText("Helpful neighbor! Gain " + amount + " food.");
                    controlPressed();
                }
                else {
                    int amount = RandomNumber(25,10);
                    Bullets = Bullets + amount;
                    eventTA.setText("Helpful soldier! Gain " + amount + " bullets.");
                    controlPressed();
                }
                eventTA.setVisible(true);
                confirmEventBtn.setVisible(true);
                animationBackpack.setVisible(false);
                actionBtn.setVisible(false);
            }
        }
    }
    else if (Normal){
        if (Math.random() < (.2 + (Day*.003))){
            if (Math.random()<.5){
                double random = Math.random();
                boolean noillness = false;
                for (FamilyMember a : family){
                    if (!a.getSick()) {
                        noillness = true;
                        break;
                    }
                }
                if (noillness){
                    if (family.size()>0){
                        if (random<.4){
                            int familySize = family.size();
                            int randomNumber = RandomNumber(familySize,1 );
                            if (!family.get(randomNumber-1).getSick()){
                                int illnessChoose = RandomNumber(5,1);
                                if (illnessChoose == 1){
                                    family.get(randomNumber-1).changeIllness("Cholera");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Cholera.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }
                                else if (illnessChoose == 2){
                                    family.get(randomNumber-1).changeIllness("Diphtheria");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Diphtheria.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else if (illnessChoose == 3){
                                    family.get(randomNumber-1).changeIllness("Dysentery");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Dysentery.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else if (illnessChoose == 4){
                                    family.get(randomNumber-1).changeIllness("Measles");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Measles.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else {
                                    family.get(randomNumber-1).changeIllness("Typhoid Fever");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Typhoid Fever.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }
                            }
                        }
                    }
                }
            }
            else {
                int randomNumber = RandomNumber(4, 1);
                if (randomNumber == 1){
                    eventTA.setVisible(true);
                    int daysLost = RandomNumber(5,3);
                    eventTA.setText("Massive thunderstorm. Lose " + daysLost +" days.");
                    Day = Day + daysLost;
                    while (daysLost>0){
                        changeDoT();
                        daysLost--;
                    }
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 2){
                    eventTA.setVisible(true);
                    int daysLost = RandomNumber(5,3);
                    eventTA.setText("Lose Trail. Lose " + daysLost +" days.");
                    Day = Day + daysLost;
                    while (daysLost>0){
                        changeDoT();
                        daysLost--;
                    }
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 3){
                    if (SpareWheels>0){
                        SpareWheels--;
                        eventTA.setText("Break a wheel. " + SpareWheels + " spare wheels left.");
                    }
                    else {
                        WheelsInUse--;
                        eventTA.setText("Break a wheel. You only have " + WheelsInUse + " wheels in use.");
                    }
                    eventTA.setVisible(true);
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 4){
                    eventTA.setText("Ox has died. You have " + Ox + " ox left.");
                    eventTA.setVisible(true);
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                controlPressed();

            }
            //Rain , Cholera , Diphtheria, Dysentery, Measles, Typhoid Fever, Lose trail - lose X days, Lose a wheel, Ox has died
            //Find gold, Gain food, Gain a wheel, Gain bullets
            //The chance that a negative event will occur is 40% and will increase by 1% every 10 days.
        }
    }
    else {
        if (Math.random() < (.23 + (Day*.005))){
            double random = Math.random();
            boolean noillness = false;
            if (Math.random()<.6){
                for (FamilyMember a : family){
                    if (!a.getSick()) {
                        noillness = true;
                        break;
                    }
                }
                if (noillness){
                    if (family.size()>0){
                        if (random<.4){
                            int familySize = family.size();
                            int randomNumber = RandomNumber(familySize,1 );
                            if (!family.get(randomNumber-1).getSick()){
                                int illnessChoose = RandomNumber(5,1);
                                if (illnessChoose == 1){
                                    family.get(randomNumber-1).changeIllness("Cholera");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Cholera.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }
                                else if (illnessChoose == 2){
                                    family.get(randomNumber-1).changeIllness("Diphtheria");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Diphtheria.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else if (illnessChoose == 3){
                                    family.get(randomNumber-1).changeIllness("Dysentery");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Dysentery.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else if (illnessChoose == 4){
                                    family.get(randomNumber-1).changeIllness("Measles");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Measles.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }else {
                                    family.get(randomNumber-1).changeIllness("Typhoid Fever");
                                    eventTA.setVisible(true);
                                    eventTA.setText(family.get(randomNumber-1).getName() + " has Typhoid Fever.");
                                    confirmEventBtn.setVisible(true);
                                    animationBackpack.setVisible(false);
                                    actionBtn.setVisible(false);
                                    controlPressed();
                                }
                            }
                        }
                    }
                }
            }
            else {
                int randomNumber = RandomNumber(4, 1);
                if (randomNumber == 1){
                    eventTA.setVisible(true);
                    int daysLost = RandomNumber(5,3);
                    eventTA.setText("Massive thunderstorm. Lose " + daysLost +" days.");
                    Day = Day + daysLost;
                    while (daysLost>0){
                        changeDoT();
                        daysLost--;
                    }
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 2){
                    eventTA.setVisible(true);
                    int daysLost = RandomNumber(5,3);
                    eventTA.setText("Lose Trail. Lose " + daysLost +" days.");
                    Day = Day + daysLost;
                    while (daysLost>0){
                        changeDoT();
                        daysLost--;
                    }
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 3){
                    if (SpareWheels>0){
                        SpareWheels--;
                        eventTA.setText("Break a wheel. " + SpareWheels + " spare wheels left.");
                    }
                    else {
                        WheelsInUse--;
                        eventTA.setText("Break a wheel. You only have " + WheelsInUse + " wheels in use.");
                    }
                    eventTA.setVisible(true);
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                if (randomNumber == 4){
                    eventTA.setText("Ox has died. You have " + Ox + " ox left.");
                    eventTA.setVisible(true);
                    confirmEventBtn.setVisible(true);
                    animationBackpack.setVisible(false);
                    actionBtn.setVisible(false);
                }
                controlPressed();

            }
            //Rain , Cholera , Diphtheria, Dysentery, Measles, Typhoid Fever, Lose trail - lose X days, Lose a wheel, Ox has died
            //Find gold, Gain food, Gain a wheel, Gain bullets
            //The chance that a negative event will occur is 50% and will increase by 1% every 10 days.
        }
    }
    //this is possibly something that can negatively affect the player.
    //Rain , Cholera , Diphtheria, Dysentery, Measles, Typhoid Fever, Lose trail - lose X days, Lose a wheel, Ox has died
    //Find gold, Gain food, Gain a wheel, Gain bullets
    mEvent = true;
}
@FXML
private void confirmEvent(){
        if (win){
            winScreen();
        }
        else if (majorEventHappening){
            eventTA.setVisible(false);
            confirmEventBtn.setVisible(false);
            majorEventHappening = false;
            actionBtn.setVisible(true);
            animationBackpack.setVisible(true);
            if (majorEvent.equals("Store")){
                switchtoTabPane4();
            }
            else {
                tabPane7river();
            }
            newMajorEvent();
        }
        else if (mEvent){
            eventTA.setVisible(false);
            confirmEventBtn.setVisible(false);
            actionBtn.setVisible(true);
            animationBackpack.setVisible(true);
            returnToGame();
            mEvent = false;
        }
       else if (lose){
           loseScreen();
        }
}
    private void newMajorEvent(){
        distanceTillEvent= RandomNumber(150, 90);
        double eventcast = Math.random();
        if (eventcast <.4){
            majorEvent = "Store";
            //Get more supplies
        }
        else {
            majorEvent = "River";
            //Fording and caulking a river
        }
        //This will pick a new event and distance to travel for the player.
    }
    private int RandomNumber(int high, int low){
        return (int)(Math.random()*(high-low+1)) + low;
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
        eventMiles.setText("Distance until " + majorEvent +":"  + distanceTillEvent + " Miles");
        milesTraveled.setText("Distance Traveled: " + distanceTraveled + " Miles");
        date.setText(month + "/" + day + "/" + year);
        animationDays.setText("Day " + Day);
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
            itemsCharacterDescription.setText(family.get(0).getName() + "'s Condition: " + family.get(0).returnCondition()+ ". Illness: " + family.get(0).returnIllness() );
        }
        else if (Normal){
            itemsCharacterImage.setImage(new Image("resources/ForwardNormalCharacter.png"));
            itemsCharacterDescription.setText(family.get(0).getName() + "'s Condition: " + family.get(0).returnCondition() + ". Illness: " + family.get(0).returnIllness() + "\n" +
                    family.get(1).getName() + "'s Condition: " + family.get(1).returnCondition()+ ". Illness: " + family.get(1).returnIllness() );
        }
        else {
            itemsCharacterImage.setImage(new Image("resources/ForwardHardCharacter.png"));
            itemsCharacterDescription.setText(family.get(0).getName() + "'s Condition: " + family.get(0).returnCondition() + ". Illness: " + family.get(0).returnIllness() + "\n" +
                    family.get(1).getName() + "'s Condition: " + family.get(1).returnCondition() + ". Illness: " + family.get(1).returnIllness() + "\n" +
                    family.get(2).getName() + "'s Condition: " + family.get(2).returnCondition()+ ". Illness: " + family.get(2).returnIllness() );
        }
        //To set up : Health description of family
        //This sets up the items tab
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //This code ahead will be for hunting.
    //I will use a similar method to movement as i did in last year's space invaders project.
    private String direction;
    @FXML
    private ImageView mainCharacterHunting;
    //750 x 400
    @FXML
    private void move(KeyEvent event){
        if (tabPane.getSelectionModel().isSelected(6)){
            if (event.getCode().equals(KeyCode.W)){
                if (mainCharacterHunting.getY()>=0){
                    mainCharacterHunting.setY(mainCharacterHunting.getY() - 5);
                }
                if (Easy){
                    mainCharacterHunting.setImage(new Image("resources/BackwardEasyCharacter.png"));
                }
                else if (Normal){
                    mainCharacterHunting.setImage(new Image("resources/BackwardNormalCharacter.png"));
                }
                else {
                    mainCharacterHunting.setImage(new Image("resources/BackwardHardCharacter.png"));
                }
                direction = "Up";
            }
            else if (event.getCode().equals(KeyCode.S)){
                if (mainCharacterHunting.getY()+56<=400){
                    mainCharacterHunting.setY(mainCharacterHunting.getY() + 5);
                }
                if (Easy){
                    mainCharacterHunting.setImage(new Image("resources/ForwardEasyCharacter.png"));
                }
                else if (Normal){
                    mainCharacterHunting.setImage(new Image("resources/ForwardNormalCharacter.png"));
                }
                else {
                    mainCharacterHunting.setImage(new Image("resources/ForwardHardCharacter.png"));
                }
                direction = "Down";
            }
            else if (event.getCode().equals(KeyCode.A)){
                if (mainCharacterHunting.getX()>=0){
                    mainCharacterHunting.setX(mainCharacterHunting.getX() - 5);
                }
                if (Easy){
                    mainCharacterHunting.setImage(new Image("resources/LeftEasyCharacter.png"));
                }
                else if (Normal){
                    mainCharacterHunting.setImage(new Image("resources/LeftNormalCharacter.png"));
                }
                else {
                    mainCharacterHunting.setImage(new Image("resources/LeftHardCharacter.png"));
                }
                direction = "Left";
            }
            else if (event.getCode().equals(KeyCode.D)){
                if (mainCharacterHunting.getX()+38<=750){
                    mainCharacterHunting.setX(mainCharacterHunting.getX() + 5);
                }
                if (Easy){
                    mainCharacterHunting.setImage(new Image("resources/RightEasyCharacter.png"));
                }
                else if (Normal){
                    mainCharacterHunting.setImage(new Image("resources/RightNormalCharacter.png"));
                }
                else {
                    mainCharacterHunting.setImage(new Image("resources/RightHardCharacter.png"));
                }
                direction = "Right";
            }
            else if (event.getCode().equals(KeyCode.SPACE)) {
                if (tabPane.getSelectionModel().isSelected(6)){
                if (Bullets > 0) {
                    switch (direction) {
                        case "Left":
                            for (int j = (int) mainCharacterHunting.getX(); j > -10; j--) {
                                int k = (int) mainCharacterHunting.getY() + 28;
                                for (int i = 0; i < animals.size(); i++) {
                                    for (int a = animals.get(i).xPos; a < (animals.get(i).xPos + animals.get(i).width); a++) {
                                        for (int b = animals.get(i).yPos; b < (animals.get(i).yPos + animals.get(i).height); b++) {
                                            if (j == a) {
                                                if (k == b) {
                                                    animals.get(i).isDead();
                                                    boarArrayList.get(i).setImage(new Image("resources/deadBoar.png"));
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case "Right":
                            for (int j = (int) mainCharacterHunting.getX(); j < 760; j++) {
                                int k = (int) mainCharacterHunting.getY() + 28;
                                for (int i = 0; i < animals.size(); i++) {
                                    for (int a = animals.get(i).xPos; a < (animals.get(i).xPos + animals.get(i).width); a++) {
                                        for (int b = animals.get(i).yPos; b < (animals.get(i).yPos + animals.get(i).height); b++) {
                                            if (j == a) {
                                                if (k == b) {
                                                    animals.get(i).isDead();
                                                    boarArrayList.get(i).setImage(new Image("resources/deadBoar.png"));
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            break;
                        case "Up":
                            for (int k = (int) mainCharacterHunting.getY(); k > -10; k--) {
                                int j = (int) mainCharacterHunting.getX() + 19;
                                for (int i = 0; i < animals.size(); i++) {
                                    for (int a = animals.get(i).xPos; a < (animals.get(i).xPos + animals.get(i).width); a++) {
                                        for (int b = animals.get(i).yPos; b < (animals.get(i).yPos + animals.get(i).height); b++) {
                                            if (j == a) {
                                                if (k == b) {
                                                    animals.get(i).isDead();
                                                    boarArrayList.get(i).setImage(new Image("resources/deadBoar.png"));
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }

                            }

                            break;
                        case "Down":
                            for (int k = (int) mainCharacterHunting.getY(); k < 410; k++) {
                                int j = (int) mainCharacterHunting.getX() + 19;
                                for (int i = 0; i < animals.size(); i++) {
                                    for (int a = animals.get(i).xPos; a < (animals.get(i).xPos + animals.get(i).width); a++) {
                                        for (int b = animals.get(i).yPos; b < (animals.get(i).yPos + animals.get(i).height); b++) {
                                            if (j == a) {
                                                if (k == b) {
                                                    animals.get(i).isDead();
                                                    boarArrayList.get(i).setImage(new Image("resources/deadBoar.png"));
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            break;
                    }
                    Bullets--;
                }

                huntingBulletCounter.setText("Bullets Left: " + Bullets);
            }
                }
            //This allows for player movement and being able to shoot their gun.
            }

        else if (tabPane.getSelectionModel().isSelected(7)){
            if (riverAction){
                if (event.getCode().equals(KeyCode.DIGIT1)){
                    riverCross = action("Ford");
                    wagon.setImage(new Image("resources/ford.png"));
                    wagon.setFitWidth(84);
                    wagon.setFitHeight(44);
                    wagon.setLayoutX(334);
                    wagon.setLayoutY(179);
                    wagon.setX(0);
                    wagon.setY(0);
                    riverStart = true;
                    initializeRiver();
                }
                else if (event.getCode().equals(KeyCode.DIGIT2)){
                    riverCross = action("Caulk");
                    wagon.setImage(new Image("resources/caulk.png"));
                    wagon.setFitWidth(82);
                    wagon.setFitHeight(58);
                    wagon.setLayoutX(334);
                    wagon.setLayoutY(180);
                    wagon.setX(0);
                    wagon.setY(0);
                    riverStart = true;
                    initializeRiver();
                }
                else if (event.getCode().equals(KeyCode.DIGIT3)){
                    if (ferryamountCost<Cash){
                        riverCross = action("Ferry");
                        wagon.setImage(new Image("resources/ferry.png"));
                        wagon.setFitWidth(82);
                        wagon.setFitHeight(58);
                        wagon.setLayoutY(180);
                        wagon.setLayoutX(334);
                        wagon.setX(0);
                        wagon.setY(0);
                        riverStart = true;
                        initializeRiver();
                    }
                    else {
                        actionDescription.setText("Insufficient Funds. What would you like to do?");
                    }

                }
                else {
                    Day++;
                    changeDoT();
                    changeConditions();
                }
            }
            else {
                if (event.getCode().equals(KeyCode.DIGIT1)){
                    tabPane.getSelectionModel().select(10);
                }
                else if (event.getCode().equals(KeyCode.DIGIT2)){
                    initializeHunting();
                    tabPane.getSelectionModel().select(6);
                }
                else if (event.getCode().equals(KeyCode.DIGIT3)){
                    tabPane.getSelectionModel().select(9);
                    setUpTrade();
                }
                else if (event.getCode().equals(KeyCode.DIGIT4)){
                    returnToGame();
                }
            }
            //eoption1.setText("1. Check the map");
            //eoption2.setText("2. Hunt for food");
            //eoption3.setText("3. Attempt to trade with other people.");
            //eoption4.setText("4. Continue on trail");
            //option1.setText("1. Ford the River");
            //        option2.setText("2. Caulk the River");
            //        option3.setText("3. Pay for a ferry.");
            //        option4.setText("4. Wait to see if conditions improve");
            //Good hot cold freezing
        }
        else if (tabPane.getSelectionModel().isSelected(9)){
            if (event.getCode().equals(KeyCode.DIGIT1)){
                acceptTrade();
            }
            else if (event.getCode().equals(KeyCode.DIGIT2)){
                setUpTrade();
                doneTrade = false;
            }
            else if (event.getCode().equals(KeyCode.SPACE)){
                exitTrade();
            }
        }
        //Confirm or look for another trader
        else if (tabPane.getSelectionModel().isSelected(10)){
            if (event.getCode().equals(KeyCode.SPACE)){
                returnToGame();
            }
        }
        else if (tabPane.getSelectionModel().isSelected(12)){
            if (event.getCode().equals(KeyCode.SPACE)){
                scoreScreen();
            }
        }


        //Checking collisions to first boar shot
            //Movement and shooting
        }
        private boolean action(String a){
        int probabilty = 100;
        if (a.equals("Ford")){
            probabilty = probabilty - 15;
            if (depth>3){
                probabilty = probabilty - 40;
            }
            if (condition.equals("Good")){
                probabilty = probabilty + 10;
            }
            else if (condition.equals("Hot")){
                probabilty = probabilty - 10;
            }
            else if (condition.equals("Cold")){
                probabilty = probabilty - 10;
            }
            else {
                probabilty = probabilty -20;
            }
            return RandomNumber(100, 1) <= probabilty;
        }
        else if (a.equals("Caulk")){
            probabilty = probabilty - 15;
            if (depth<3){
                probabilty = probabilty - 40;
            }
            if (condition.equals("Good")){
                probabilty = probabilty + 10;
            }
            else if (condition.equals("Hot")){
                probabilty = probabilty - 10;
            }
            else if (condition.equals("Cold")){
                probabilty = probabilty - 10;
            }
            else {
                probabilty = probabilty -20;
            }
            return RandomNumber(100, 1) <= probabilty;
        }
        else {
            if (condition.equals("Hot")){
                probabilty = probabilty - 5;
            }
            else if (condition.equals("Cold")){
                probabilty = probabilty - 5;
            }
            else {
                probabilty = probabilty -10;
            }
            return RandomNumber(100, 1) <= probabilty;
            }
        //This checks to see if the player successfully crossed the river or not.
        }
    private void initializeRiver(){
        tabPane.getSelectionModel().select(8);
        landRight.setLayoutX(424);
        landRight.setLayoutY(0);
        landRight.setX(0);
        landRight.setFitWidth(1000);
        landRight.setFitHeight(500);
        landLeft.setLayoutX(-467);
        landLeft.setLayoutY(-4);
        landLeft.setX(0);
        landLeft.setY(0);
        landLeft.setFitWidth(1620);
        landLeft.setFitHeight(703);
        rtime = (int) (System.nanoTime()/1000000000);
        //This initializes how the river will look.
    }

    @FXML
    private void riverConfirm(){
        if (lose){
            tabPane.getSelectionModel().select(9);
        }
        else {
            returnToGame();
            riverConfirmBtn.setVisible(false);
            riverTA.setVisible(false);
        }
    }


    private void initializeHunting(){

        foodWeight = 0;
        huntingReturn.setVisible(false);
        huntingExit.setVisible(true);
        confirmHuntingBtn.setVisible(false);
        huntingBulletCounter.setText("Bullets Left: " + Bullets);
        if (Easy){
            mainCharacterHunting.setImage(new Image("resources/ForwardEasyCharacter.png"));
        }
        else if (Normal){
            mainCharacterHunting.setImage(new Image("resources/ForwardNormalCharacter.png"));
        }
        else {
            mainCharacterHunting.setImage(new Image("resources/ForwardHardCharacter.png"));
        }

        mainCharacterHunting.setX(25);
        mainCharacterHunting.setY(175);
        int numberofAnimals = RandomNumber(5,1);
        while (numberofAnimals>0){
            int xPos = RandomNumber(665,0);
            int yPos = RandomNumber(350,0);
            if (checkPosition(xPos,yPos,85,50)){
                animals.add(new Animal(xPos,yPos, 85,50,(int) (System.nanoTime()/1000000000), RandomNumber(800,500)));
            }
            numberofAnimals--;
        }
        initializeAnimals(animals.size());
        //This will reset the hunting screen and randomize where the animals will be.
    }
    private boolean checkPosition(int x, int y, int width, int height){
        if (x+width>750){
            return false;
        }
        if (y+height>400){
            return false;
        }
        for (int i =x;i < x+width; i++){
            for (int j = y; j< y+height; j ++){
                if (animals.size()>0){
                    for (int k =0 ; k < animals.size(); k ++){
                        if (animals.get(k).returnX()==i){
                            return false;
                        }
                        if (animals.get(k).returnY() == j){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    private void initializeAnimals(int n){
        if (n==1){
            boar1.setVisible(true);
            boar1.setX(animals.get(0).returnX());
            boar1.setY(animals.get(0).returnY());
            boarArrayList.add(boar1);
        }
        else if (n==2){
            boar1.setVisible(true);
            boar2.setVisible(true);
            boar1.setX(animals.get(0).returnX());
            boar1.setY(animals.get(0).returnY());
            boar2.setX(animals.get(1).returnX());
            boar2.setY(animals.get(1).returnY());
            boarArrayList.add(boar1);
            boarArrayList.add(boar2);
        }
        else if (n==3){
            boar1.setVisible(true);
            boar2.setVisible(true);
            boar3.setVisible(true);
            boar1.setX(animals.get(0).returnX());
            boar1.setY(animals.get(0).returnY());
            boar2.setX(animals.get(1).returnX());
            boar2.setY(animals.get(1).returnY());
            boar3.setX(animals.get(2).returnX());
            boar3.setY(animals.get(2).returnY());
            boarArrayList.add(boar1);
            boarArrayList.add(boar2);
            boarArrayList.add(boar3);

        }
        else if (n==4){
            boar1.setVisible(true);
            boar2.setVisible(true);
            boar3.setVisible(true);
            boar4.setVisible(true);
            boar1.setX(animals.get(0).returnX());
            boar1.setY(animals.get(0).returnY());
            boar2.setX(animals.get(1).returnX());
            boar2.setY(animals.get(1).returnY());
            boar3.setX(animals.get(2).returnX());
            boar3.setY(animals.get(2).returnY());
            boar4.setX(animals.get(3).returnX());
            boar4.setY(animals.get(3).returnY());
            boarArrayList.add(boar1);
            boarArrayList.add(boar2);
            boarArrayList.add(boar3);
            boarArrayList.add(boar4);
        }
        else if (n==5){
            boar1.setVisible(true);
            boar2.setVisible(true);
            boar3.setVisible(true);
            boar4.setVisible(true);
            boar5.setVisible(true);
            boar1.setX(animals.get(0).returnX());
            boar1.setY(animals.get(0).returnY());
            boar2.setX(animals.get(1).returnX());
            boar2.setY(animals.get(1).returnY());
            boar3.setX(animals.get(2).returnX());
            boar3.setY(animals.get(2).returnY());
            boar4.setX(animals.get(3).returnX());
            boar4.setY(animals.get(3).returnY());
            boar5.setX(animals.get(4).returnX());
            boar5.setY(animals.get(4).returnY());
            boarArrayList.add(boar1);
            boarArrayList.add(boar2);
            boarArrayList.add(boar3);
            boarArrayList.add(boar4);
            boarArrayList.add(boar5);
        }
        else {
            boar1.setVisible(true);
            boar2.setVisible(true);
            boar3.setVisible(true);
            boar4.setVisible(true);
            boar5.setVisible(true);
            boar6.setVisible(true);
            boar1.setX(animals.get(0).returnX());
            boar1.setY(animals.get(0).returnY());
            boar2.setX(animals.get(1).returnX());
            boar2.setY(animals.get(1).returnY());
            boar3.setX(animals.get(2).returnX());
            boar3.setY(animals.get(2).returnY());
            boar4.setX(animals.get(3).returnX());
            boar4.setY(animals.get(3).returnY());
            boar5.setX(animals.get(4).returnX());
            boar5.setY(animals.get(4).returnY());
            boar6.setX(animals.get(5).returnX());
            boar6.setY(animals.get(5).returnY());
            boarArrayList.add(boar1);
            boarArrayList.add(boar2);
            boarArrayList.add(boar3);
            boarArrayList.add(boar4);
            boarArrayList.add(boar5);
            boarArrayList.add(boar6);
        }
        //Sets up the animation for the animals
    }
    @FXML
    private void exitHunting(){
        for (int i =0 ; i <animals.size(); i ++){
            if (animals.get(i).dead){
                foodWeight = foodWeight + animals.get(i).weight;
            }
        }

        huntingReturn.setVisible(true);
        huntingExit.setVisible(false);
        confirmHuntingBtn.setVisible(true);
        if (foodWeight>0){
            huntingReturn.setText("You hunted " + foodWeight + " pounds of food. Since it is too heavy, you can only bring back 200 pounds.");
            Food = Food +200;
        }
        else {
            huntingReturn.setText("You didn't hunt anything!");
        }
        foodWeight = 0;
        //This exits the hunting animation
    }
    @FXML
    private void confirmHunting(){
        boar1.setImage(new Image ("resources/boarLeft.png"));
        boar2.setImage(new Image ("resources/boarRight.png"));
        boar3.setImage(new Image ("resources/boarLeft.png"));
        boar4.setImage(new Image ("resources/boarRight.png"));
        boar5.setImage(new Image ("resources/boarLeft.png"));
        boar6.setImage(new Image ("resources/boarRight.png"));
        confirmHuntingBtn.setVisible(false);
        huntingReturn.setVisible(false);
        animals.clear();
        boarArrayList.clear();
        returnToGame();
        //This returns to the animation
    }
    @FXML
    private Label huntingBulletCounter;
    @FXML
    private Button confirmHuntingBtn;
    @FXML
    private TextArea huntingReturn;
    @FXML
    private ImageView huntingExit;
    @FXML
    private ImageView boar1, boar2, boar3, boar4, boar5, boar6;
    private ArrayList<ImageView> boarArrayList = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private int foodWeight = 0;
    private void RiverCross(boolean crossed) {
        riverTA.setVisible(true);
        riverConfirmBtn.setVisible(true);
        //This sets up the consequences or text for when the river animation is completed.
        if (crossed) {
            riverTA.setText("You have successfully crossed the river.");
        } else {
            int consequence = RandomNumber(100, 1);
            //Lose wheel, bullets, food, ox, money, people
            boolean notdead = false;
            for (int i = 0; i < family.size(); i++) {
                if (family.get(i).Health > 0) {
                    notdead = true;
                }
            }
            if (notdead){
                if (consequence<25){
                    boolean notdeath = true;
                    while (notdeath) {
                        int n = RandomNumber(family.size() - 1, 0);
                        if (family.get(n).Health > 0) {
                            notdeath = false;
                            family.get(n).Health = 0;
                            riverTA.setText(family.get(n).getName() + " has drowned.");
                        }
                    }
                }
                else if (consequence < 60) {
                    Ox--;
                    if (Ox ==0){
                        riverTA.setText("You have lost an ox. You're out of Ox! Game Over.");
                        lose = true;
                    }
                    else {
                        riverTA.setText("You have lost an ox.");
                    }

                } else {
                    riverTA.setText("You have lost some supplies.");
                    Food = Food - RandomNumber(Food/2,Food/5);
                    Bullets = Bullets - RandomNumber(Bullets/2, Bullets/5);
                }
            }
            else {
                if (consequence < 5) {
                    riverTA.setText(" You have drowned. Game Over.");
                    lose = true;
                }
                else if (consequence < 35) {
                    Ox--;
                    if (Ox ==0){
                        riverTA.setText("You have lost an ox. You're out of Ox! Game Over.");
                        lose = true;
                    }
                    else {
                        riverTA.setText("You have lost an ox.");
                    }
                }
                else {
                    riverTA.setText("You have lost some supplies.");
                    Food = Food - RandomNumber(Food/2,Food/5);
                    Bullets = Bullets - RandomNumber(Bullets/2, Bullets/5);
                }
            }

            riverConfirmBtn.setVisible(true);
            riverTA.setVisible(true);
        }
        //This sets up what would happen if the wagon failed to cross and thus subtracts or removes some family members
    }
    @FXML
    private Label loseMain, loseWife, loseC1, loseC2;
    private void loseScreen(){
        tabPane.getSelectionModel().select(11);
        loseMain.setText(MainCharacterName);
        loseWife.setText(WifeName);
        if (Normal){
            loseC1.setText(Child1Name);
        }
        else if (Hard){
            loseC1.setText(Child1Name);
            loseC2.setText(Child2Name);
        }
    }
    private void winScreen(){
        tabPane.getSelectionModel().select(12);
    }

    @FXML
    private Label scoreHealth, scoreOx, scoreWheels, scoreBullets, scoreFood, scoreDifficulty, scoreCash;
    @FXML
    private Label pointsHealth, pointsOx, pointsWheels, pointsBullets, pointsFood, pointsDifficulty, pointsCash, totalScore;
    private void scoreScreen() {
        tabPane.getSelectionModel().select(13);
        int healthy = 0;
        for (FamilyMember a : family) {
            if (a.returnCondition().equals("Good")) {
                healthy++;
            }
        }
        int points = 0;
        scoreHealth.setText(healthy + "");
        pointsHealth.setText((healthy*100) + "");
        points = points + (healthy*100);

        scoreOx.setText(Ox + "");
        pointsOx.setText((Ox*4)+"");
        points = points + (Ox*4);

        scoreWheels.setText(SpareWheels + "");
        pointsWheels.setText((SpareWheels*2)+ "");
        points = points + (SpareWheels*2);

        scoreBullets.setText(Bullets + "");
        pointsBullets.setText((Bullets/50)+"");
        points = points + (Bullets/50);

        scoreFood.setText(Food+"");
        pointsFood.setText((Food/25)+"");
        points = points + (Food/25);

        scoreCash.setText((int)Cash + "");
        pointsCash.setText(((int)Cash/3)+"");
        points = points + ((int)Cash/3);
        if (Easy){
            scoreDifficulty.setText("Easy");
            pointsDifficulty.setText("x1 Bonus");
        }
        else if (Normal){
            scoreDifficulty.setText("Normal");
            pointsDifficulty.setText("x2 Bonus");
            points = points*2;
        }
        else {
            scoreDifficulty.setText("Hard");
            pointsDifficulty.setText("x3 Bonus");
            points = points*3;
        }
        totalScore.setText("Total:" + points);
    }
    private void setUpTrade(){
        doneTrade= false;
        tabPane.getSelectionModel().select(9);
        int give = RandomNumber(5,1);
        int want = RandomNumber(5,1);
        while (want==give){
            want = RandomNumber(5,1);
        }
        if (want == 1){
            tradeWant = "Bullets";
        }
        else if (want == 2){
            tradeWant = "Ox";
        }
        else if (want == 3){
            tradeWant = "Money";
        }
        else if (want==4){
            tradeWant = "Wheels";
        }
        else {
            tradeWant = "Food";
        }
        if (give == 1){
            tradeGiven = RandomNumber(40,100);
            tradeGive = "Bullets";
            if (tradeWant.equals("Ox")){
                tradeWanted = RandomNumber(1,3);
            }
            else if (tradeWant.equals("Money")){
                tradeWanted = RandomNumber(4,50);
            }
            else if (tradeWant.equals("Wheels")){
                tradeWanted = RandomNumber(1,7);
            }
            else {
                tradeWanted = RandomNumber(25,50);
            }
        }
        else if (give == 2){
            tradeGiven = RandomNumber(1,3);
            tradeGive = "Ox";
            if (tradeWant.equals("Bullets")){
                tradeWanted = tradeGiven*RandomNumber(75,200);
            }
            else if (tradeWant.equals("Money")){
                tradeWanted = tradeGiven*RandomNumber(125,200);
            }
            else if (tradeWant.equals("Wheels")){
                tradeWanted = tradeGiven*RandomNumber(5,15);
            }
            else {
                tradeWanted = tradeGiven*RandomNumber(100,300);
            }
        }
        else if (give == 3){
            tradeGiven = RandomNumber(75,200);
            tradeGive = "Money";
            if (tradeWant.equals("Bullets")){
                tradeWanted =RandomNumber(50,100);
            }
            else if (tradeWant.equals("Ox")){
                tradeWanted = RandomNumber(1,2);
            }
            else if (tradeWant.equals("Wheels")){
                tradeWanted = RandomNumber(5,15);
            }
            else {
                tradeWanted = RandomNumber(75,250);
            }
        }
        else if (give==4){
            tradeGiven = RandomNumber(1,6);
            tradeGive = "Wheels";
            if (tradeWant.equals("Bullets")){
                tradeWanted = tradeGiven*RandomNumber(20,45);
            }
            else if (tradeWant.equals("Ox")){
                tradeWanted = RandomNumber(1,2);
            }
            else if (tradeWant.equals("Money")){
                tradeWanted = tradeGiven*RandomNumber(20,30);
            }
            else {
                tradeWanted = tradeGiven*RandomNumber(40,75);
            }
        }
        else {
            tradeGiven = RandomNumber(50,150);
            tradeGive = "Food";
            if (tradeWant.equals("Bullets")){
                tradeWanted = tradeGiven*RandomNumber(1,2);
            }
            else if (tradeWant.equals("Ox")){
                tradeWanted = RandomNumber(1,2);
            }
            else if (tradeWant.equals("Money")){
                tradeWanted = tradeGiven*RandomNumber(2,3);
            }
            else {
                tradeWanted = RandomNumber(5,15);
            }
        }
        tradeInformation.setText("This trader is willing to give you " + tradeGiven + " "+tradeGive+ " for " + tradeWanted + tradeWant+".");

        //Bullets, Ox, Money, Wheels, Food
        //This will set up the new trade information
    }
    private void acceptTrade() {
        if (!doneTrade) {
            if (tradeWant.equals("Bullets")) {
                if (Bullets >= tradeWanted) {
                    Bullets = Bullets - tradeWanted;
                    if (tradeGive.equals("Bullets")) {
                        Bullets = Bullets + tradeGiven;
                    } else if (tradeGive.equals("Ox")) {
                        Ox = Ox + tradeGiven;
                    } else if (tradeGive.equals("Money")) {
                        Cash = Cash + tradeGiven;
                    } else if (tradeGive.equals("Wheels")) {
                        SpareWheels = SpareWheels + tradeGiven;
                    } else {
                        Food = Food + tradeGiven;
                    }
                    doneTrade = true;
                    tradeInformation.setText("The person thanks you for trading with them.");
                } else {
                    tradeInformation.setText("Insufficient amount. This trader is willing to give you " + tradeGiven + " " + tradeGive + " for " + tradeWanted + tradeWant + ".");
                }
            } else if (tradeWant.equals("Ox")) {
                if (Ox >= tradeWanted) {
                    Ox = Ox - tradeWanted;
                    if (tradeGive.equals("Bullets")) {
                        Bullets = Bullets + tradeGiven;
                    } else if (tradeGive.equals("Ox")) {
                        Ox = Ox + tradeGiven;
                    } else if (tradeGive.equals("Money")) {
                        Cash = Cash + tradeGiven;
                    } else if (tradeGive.equals("Wheels")) {
                        SpareWheels = SpareWheels + tradeGiven;
                    } else {
                        Food = Food + tradeGiven;
                    }
                    doneTrade = true;
                    tradeInformation.setText("The person thanks you for trading with them.");
                } else {
                    tradeInformation.setText("Insufficient amount. This trader is willing to give you " + tradeGiven + " " + tradeGive + " for " + tradeWanted + tradeWant + ".");
                }
            } else if (tradeWant.equals("Money")) {
                if (Cash >= tradeWanted) {
                    Cash = Cash - tradeWanted;
                    if (tradeGive.equals("Bullets")) {
                        Bullets = Bullets + tradeGiven;
                    } else if (tradeGive.equals("Ox")) {
                        Ox = Ox + tradeGiven;
                    } else if (tradeGive.equals("Money")) {
                        Cash = Cash + tradeGiven;
                    } else if (tradeGive.equals("Wheels")) {
                        SpareWheels = SpareWheels + tradeGiven;
                    } else {
                        Food = Food + tradeGiven;
                    }
                    doneTrade = true;
                    tradeInformation.setText("The person thanks you for trading with them.");
                } else {
                    tradeInformation.setText("Insufficient amount. This trader is willing to give you " + tradeGiven + " " + tradeGive + " for " + tradeWanted + tradeWant + ".");
                }
            } else if (tradeWant.equals("Wheels")) {
                if (SpareWheels >= tradeWanted) {
                    SpareWheels = SpareWheels - tradeWanted;
                    if (tradeGive.equals("Bullets")) {
                        Bullets = Bullets + tradeGiven;
                    } else if (tradeGive.equals("Ox")) {
                        Ox = Ox + tradeGiven;
                    } else if (tradeGive.equals("Money")) {
                        Cash = Cash + tradeGiven;
                    } else if (tradeGive.equals("Wheels")) {
                        SpareWheels = SpareWheels + tradeGiven;
                    } else {
                        Food = Food + tradeGiven;
                    }
                    doneTrade = true;
                    tradeInformation.setText("The person thanks you for trading with them.");
                } else {
                    tradeInformation.setText("Insufficient amount. This trader is willing to give you " + tradeGiven + " " + tradeGive + " for " + tradeWanted + tradeWant + ".");
                }
            } else {
                if (Food >= tradeWanted) {
                    Food = Food - tradeWanted;
                    if (tradeGive.equals("Bullets")) {
                        Bullets = Bullets + tradeGiven;
                    } else if (tradeGive.equals("Ox")) {
                        Ox = Ox + tradeGiven;
                    } else if (tradeGive.equals("Money")) {
                        Cash = Cash + tradeGiven;
                    } else if (tradeGive.equals("Wheels")) {
                        SpareWheels = SpareWheels + tradeGiven;
                    } else {
                        Food = Food + tradeGiven;
                    }
                    doneTrade = true;
                    tradeInformation.setText("The person thanks you for trading with them.");
                } else {
                    tradeInformation.setText("Insufficient amount. This trader is willing to give you " + tradeGiven + " " + tradeGive + " for " + tradeWanted + " "+tradeWant + ".");
                }
            }
        }
    }
    private void exitTrade(){
        tradeGiven = 0; tradeWanted = 0;
        tradeGive=""; tradeWant = "";
        returnToGame();
    }
    private int tradeGiven = 0, tradeWanted = 0;
    private String tradeGive="", tradeWant = "";
    @FXML
    private Label tradeInformation;
    private boolean doneTrade = false;
} 