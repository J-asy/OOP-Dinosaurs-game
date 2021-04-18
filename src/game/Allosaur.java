package game;

public class Allosaur extends DinoActor{

    private static final int HUNGRY_WHEN = 90;

    public Allosaur(String name, char sex) {
        super(name, 'a', 20, 100, sex);
    }

    @Override
    public void roarIfHungry(){
        if(getFoodLevel() < HUNGRY_WHEN){
            System.out.println(getName() + " getting hungry! ");  // add location?
        }
    }

}
