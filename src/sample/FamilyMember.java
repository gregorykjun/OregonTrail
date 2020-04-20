package sample;

public class FamilyMember {
    public int Health = 100;
    private boolean sick;
    private String illness = "None";
    private String name;
    private String condition = "Good";
    //This is the family class, where I will be able to change some health effects or anything to the family member.
    public FamilyMember(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public String returnIllness(){
        return illness;
    }
    public int getHealth(){
        return Health;
    }
    public boolean getSick(){
        return sick;
    }
    public void changeIllness(String disease){
        illness = disease;
    }
    public void cureIllness(){
        illness = "None";
    }
    public void changeHealthtoIllness(){
        if (!illness.equals("None")){
            if (illness.equals("Typhoid Fever")){
                Health = Health - 2;
            }
            else if (illness.equals("Dysentery")){
                Health = Health - 3;
            }
            else if (illness.equals("Measles")){
                Health = Health - 3;
        }
            else if (illness.equals("Diphtheria")){
                Health = Health - 4;
            }
            else {
                //Cholera
                Health = Health - 5;
            }
        }
        check();
    }
    public void increaseHealth(int amount){
        Health = Health + amount;
        check();
    }

    public void check(){
        if (Health == 0 ){
            condition = "Dead";
        }
        if (Health <25){
            condition = "Very Poor";
        }
        else if (Health < 50){
            condition = "Poor";
        }
        else if (Health<75){
            condition = "Fair";
        }
        else {
            condition = "Good";
        }
    }
    public String returnCondition(){
        return condition;
    }
}
