package sample;

public class Animal {
    int xPos;
    int yPos;
    int width;
    int height;
    public Animal(int x, int y, int w, int h){
        xPos = x;
        yPos = y;
        width = w;
        height = h;
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

}
