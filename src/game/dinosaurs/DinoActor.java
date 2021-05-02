package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;

import game.breed.BreedingAction;
import game.breed.BreedingCapability;
import game.utility.Probability;
import game.wander.WanderBehaviour;

import java.util.ArrayList;


public abstract class DinoActor extends Actor {

    private ArrayList<Behaviour> behaviour;
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

    public DinoEncyclopedia getDinoType() {
        return dinoType;
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
            removeCapability(BreedingCapability.CAN_BREED);
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
            if (getFoodLevel() >= dinoType.getCapableBreedingWhen()){
                addCapability(BreedingCapability.CAN_BREED);
            }
        }
    }

    public void setChildDisplayCharacter(){
        this.displayChar = Character.toLowerCase(displayChar);
    }

    // IGNORE GET ALLOWABLE ACTIONS AND PLAY TURN FOR NOW - JUST TESTING STUFF OUT


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new BreedingAction(this));
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        aging();
        decrementFoodLevel();
        roarIfHungry();

        Action actionToExecute = new DoNothingAction();

        // if lastAction has a subsequent action, always return that
        if (lastAction != null && lastAction.getNextAction() != null){
            actionToExecute = lastAction.getNextAction();
        }
        else if (actions.size() > 0){
            actionToExecute = actions.get(0);
        }

        // calling getAction for every behaviour can help us to do some necessary processing
        // as well even if it returns null in the end
        for (Behaviour b: behaviour){
            if (b.getAction(this, map) != null && actionToExecute instanceof DoNothingAction){
                    actionToExecute = b.getAction(this, map);
                }
            }

        return actionToExecute;
    }

//TODO: add player feed action in get allowable (also in child classes) and fix playTurn method

}


