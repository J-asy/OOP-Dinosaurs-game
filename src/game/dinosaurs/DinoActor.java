package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;

import game.breed.BreedingAction;
import game.breed.BreedingCapability;
import game.follow.FollowActorBehaviour;
import game.pregnancy.PregnancyBehaviour;
import game.utility.Probability;
import game.wander.WanderBehaviour;

import java.util.ArrayList;

/**
 * Base class for Stegosaur, Brachiosaur and Allosaur for representing dinosaur Actors.
 */
public abstract class DinoActor extends Actor {

    /**
     * An ArrayList of standard behaviours that the DinoActor should have
     */
    private ArrayList<Behaviour> behaviour;
    private final DinoEncyclopedia dinoType;
    private final Sex sex;
    private int age = 0;
    private int pregnancyPeriod;

    public DinoActor(DinoEncyclopedia dinoType, Sex sex){
        super(dinoType.getName(), dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        this.dinoType = dinoType;
        this.sex = sex;
        setMaxHitPoints(dinoType.getMaxHitPoints());
        initializeDinoBehaviour();
    }

    public DinoActor(DinoEncyclopedia dinoType){
        super(dinoType.getName(), dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        this.dinoType = dinoType;
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

    public Sex getSex(){
        return sex;
    }

    /**
     * Initializes the dinoActor with the standard behaviour that they should have.
     */
    private void initializeDinoBehaviour(){
        behaviour = new ArrayList<>();
        behaviour.add(new PregnancyBehaviour());
        behaviour.add(new FollowActorBehaviour());
        behaviour.add(new WanderBehaviour());
    }

    /**
     * Increments the age of the dinosaur and simulates the action of the dinosaur growing up
     */
    private void aging(){
        age++;
        if (age >= dinoType.getMatureWhen()){
            displayChar = Character.toUpperCase(getDisplayChar());
        }
    }

    /**
     * Sets the display character of the dinoActor to lowercase
     * to show that the dinoActor is a baby dinosaur / not matured yet.
     */
    public void setChildDisplayCharacter(){
        this.displayChar = Character.toLowerCase(displayChar);
    }

    /**
     * Returns true if the dinoActor is matured, false otherwise.
     * @return true if the dinoActor is matured, false otherwise
     */
    public boolean isMatured(){
        return age >= dinoType.getMatureWhen();
    }

    /**
     * Initializes the maxHitPoints of the dinoActor if the argument
     * passed in is greater than its hitPoints.
     * @param newMaxHitPoints maximum food level of the dinoActor
     */
    private void setMaxHitPoints(int newMaxHitPoints) {
        if (newMaxHitPoints >= hitPoints) {
            maxHitPoints = newMaxHitPoints;
        }
    }

    /**
     * Decrements the dinoActor's food level, which is equivalent to its hitPoints.
     */
    private void decrementFoodLevel(){
        if (hitPoints > 0){
            super.hurt(1);
        }
    }

    /**
     * Checks if the food level / hit points has reached
     * the point where the dinoActor will get hungry.
     * If yes, a hunger message will be printed out to notify the player.
     * @param map GameMap that the actor is currently on
     */
    public void roarIfHungry(GameMap map){
        if (hitPoints < dinoType.getHungryWhen()) {
            int x = map.locationOf(this).x();
            int y = map.locationOf(this).y();
            System.out.printf("%s at (%d, %d) getting hungry!", name, x, y);
        }
    }

    /**
     * Returns true if the dinoActor has the capability to breed, false otherwise.
     * A dinoActor should only be able to breed if:
     * - it is matured
     * - it is not pregnant
     * @return true if the dinoActor can breed, false otherwise
     */
    public boolean canBreed() {
        return hasCapability(BreedingCapability.CAN_BREED);
    }

    /**
     * Determines whether the dinoActor should have the Capability to breed and
     * add or remove the capability accordingly.
     */
    public void adjustBreedingCapability() {
        if (!canBreed()){
            if (hitPoints >= dinoType.capableBreedingWhen && !isPregnant() && isMatured()){
                addCapability(BreedingCapability.CAN_BREED);
            }
        }
        else {
            if (hitPoints < dinoType.capableBreedingWhen){
                removeCapability(BreedingCapability.CAN_BREED);
            }
        }
    }

    /**
     * Returns true if the dinoActor is pregnant, false otherwise.
     * A dinoActor only has a chance to be pregnant after breeding.
     * @return true if the dinoActor is pregnant, false otherwise
     */
    public boolean isPregnant(){
        return hasCapability(PregnancyStatus.PREGNANT);
    }

    /**
     * Initializes the dinoActor's pregnancy period to an
     * appropriate value.
     */
    public void initializePregnancyPeriod(){
        pregnancyPeriod = dinoType.getPregnancyPeriod();
    }

    public int getPregnancyPeriod(){
        return pregnancyPeriod;
    }

    /**
     * Decrements the dinoActor's pregnancy period if it is greater than 0.
     */
    public void decrementPregnancyPeriod(){
        if (pregnancyPeriod > 0){
            pregnancyPeriod--;
        }
    }

    /**
     * Change the dinoActor's state appropriately depending on argument passed in.
     * If true is given as an argument, the dinoActor is set to pregnant,
     * otherwise dinoActor is set to not pregnant.
     * @param status true if the dinoActor should be pregnant, false otherwise
     */
    public void setPregnant(boolean status){
        if (status){
            addCapability(PregnancyStatus.PREGNANT);
            initializePregnancyPeriod();
            if (hasCapability(BreedingCapability.CAN_BREED)){
                removeCapability(BreedingCapability.CAN_BREED);
            }
        }
        else {
            removeCapability(PregnancyStatus.PREGNANT);
        }
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new BreedingAction(this));
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        aging();
        decrementFoodLevel();
        roarIfHungry(map);
        adjustBreedingCapability();

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


