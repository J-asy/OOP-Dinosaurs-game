package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;

import game.utility.Probability;
import java.util.ArrayList;


public abstract class DinoActor extends Actor {

    private ArrayList<Behaviour> behaviour;  // should have priority of behaviours
    private DinoEncyclopedia dinoType;
    private Sex sex;
    private int age = 0;
    private int pregnancyPeriod;

    public DinoActor(DinoEncyclopedia dinoType, Sex sex){
        super(dinoType.getName(), dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        setDinoType(dinoType);
        setMaxHitPoints(dinoType.getMaxHitPoints());
        initializeDinoBehaviour();
        this.sex = sex;
    }

    public DinoActor(DinoEncyclopedia dinoType){
        super(dinoType.getName(), dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        setDinoType(dinoType);
        setMaxHitPoints(dinoType.getMaxHitPoints());
        initializeDinoBehaviour();

        if (Probability.generateProbability(0.5F)){
            sex = Sex.MALE;
        }
        else {
            sex = Sex.FEMALE;
        }
    }

    protected void setDinoType(DinoEncyclopedia dinoType){
        this.dinoType = dinoType;
    }

    private void setMaxHitPoints(int newMaxHitPoints){
        if (newMaxHitPoints > 0 && newMaxHitPoints >= hitPoints){
            maxHitPoints = newMaxHitPoints;
        }
    }

    private void initializeDinoBehaviour(){
        behaviour = new ArrayList<>();
        behaviour.add(new WanderBehaviour());
    }

    // Food level
    int getFoodLevel(){
        return hitPoints;
    }

    private void decrementFoodLevel(){
        if (getFoodLevel() > 0){
            super.hurt(1);
        }
    }

    public void roarIfHungry(){
        if(getFoodLevel() < dinoType.getHungryWhen()){
            System.out.println(name + " getting hungry! ");  // add location?
        }
    }

    // sex
    public Sex getSex(){
        return sex;
    }

    // pregnancy
    public boolean isPregnant(){
        return hasCapability(PregnancyStatus.PREGNANT);
    }

    public void setPregnant(boolean status){
        if (status){
            addCapability(PregnancyStatus.PREGNANT);
            initializePregnancyPeriod();
        }
        else {
            removeCapability(PregnancyStatus.PREGNANT);
        }
    }

    public void initializePregnancyPeriod(){
        pregnancyPeriod = dinoType.getPregnancyPeriod();
    }

    public int getPregnancyPeriod(){
        return pregnancyPeriod;
    }

    public void decrementPregnancyPeriod(){
        pregnancyPeriod--;
    }

    // age
    private int getAge(){
        return age;
    }

    public boolean isMatured(){
        return getAge() >= dinoType.getMatureWhen();
    }

    private void aging(){
        age++;
        if (getAge() >= dinoType.getMatureWhen()){
            displayChar = Character.toUpperCase(getDisplayChar());
        }
    }

    public void setChildDisplayCharacter(){
        this.displayChar = Character.toLowerCase(displayChar);
    }

    // unnecessary method to be remove just for testing
    public void setAge(int age){ this.age = age; }

    // IGNORE GET ALLOWABLE ACTIONS AND PLAY TURN FOR NOW - JUST TESTING STUFF OUT

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions allowableActions = new Actions();

        if (otherActor instanceof DinoActor){
            if (this.getSex() != ((DinoActor)otherActor).getSex()){
//                allowableActions.add(new BreedingAction(this));
            }
        }
        return allowableActions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        System.out.println(lastAction);
        aging();
        decrementFoodLevel();
        roarIfHungry();

//        ArrayList<Action> allActions = new ArrayList<>();
//        allActions.addAll(actions);
//
//        for (Behaviour b: behaviour){
//            if (b.getAction() != null){
//                allActions.add(b);
//            }
//        }

        // sort
        // return the first action




//
//        if (getSex() == Sex.FEMALE && hasCapability(PregnancyStatus.PREGNANT)) {
//            if (turnsTillLayEgg == 0) {
//                System.out.println("Lay egg! ");
//                removeCapability(PregnancyStatus.PREGNANT);
//                return new LayEggAction();
//            } else {
//                System.out.println("decrement");
//                turnsTillLayEgg--;
//            }
//        }
//
//        for (Action a: actions){
//            if (a instanceof BreedingAction && a != null && firstTime){
//                firstTime = false;
//                return a;
//            }
//        }
//        Action follow = new FollowMateBehaviour().getAction(this, map);
//        if (follow != null){
//            System.out.println("ret follow");
//            return follow;
//        }
//        else {
//            System.out.println("follow null");
//        }


        return behaviour.get(0).getAction(this, map);
    }



}


