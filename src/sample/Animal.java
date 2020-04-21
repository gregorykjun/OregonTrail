package sample;

import javafx.scene.control.PasswordField;

public class Animal {
    int xPos;
    int yPos;
    int width;
    int height;
    public int weight;
    public int time;
    public boolean dead = false;
    public String moveDirection = "";
    public Animal(int x, int y, int w, int h, int t, int a){
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        time = t;
        weight = a;
        double random = Math.random();
        if (random<.2){
            moveDirection = "Up";
        }
        else if (random<.4){
            moveDirection = "Down";
        }
        else if (random<.6){
            moveDirection = "Left";
        }
        else if (random<.8){
            moveDirection = "Right";
        }
        else {
            moveDirection = "Still";
        }
    }
    public int returnX(){
        return xPos;
    }
    public int returnY(){
        return yPos;
    }
    public int returnWidth() {
        return width;
    }
    public int returnHeight(){
        return height;
    }
    public void changeX(int newPos){
        xPos = newPos;
    }
    public void changeY(int newPos){
        yPos = newPos;
    }
    public void isDead(){
        dead = true;
    }
    public void notDead() {
        dead = false;
    }


}
