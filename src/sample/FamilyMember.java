package sample;

public class FamilyMember {
    private int Health = 100;
    private boolean sick;
    private String illness = "";
    private String name;
    //This is the family class, where I will be able to change some health effects or anything to the family member.
    public FamilyMember(String n){
        name = n;
    }
    public String getName(){
        return name;
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
        illness = "";
    }
    public void changeHealthtoIlness(){
        if (!illness.equals("")){
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
    }
}
