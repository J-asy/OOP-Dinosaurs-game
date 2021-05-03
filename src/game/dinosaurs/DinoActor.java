package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;

import game.attack.Corpse;
import game.breed.BreedingBehaviour;
import game.follow.FollowMateBehaviour;
import game.follow.FollowVictimBehaviour;
import game.pregnancy.PregnancyBehaviour;
import game.utility.Probability;
import game.wander.WanderBehaviour;

import java.util.ArrayList;

/**
 * Base class for Stegosaur, Brachiosaur and Allosaur for representing dinosaur Actors.
 */
public abstract class DinoActor extends Actor implements DinoInitialization {

    /**
     * An ArrayList of standard behaviours that the DinoActor should have
     */
    private ArrayList<Behaviour> behaviour;
    private final DinoEncyclopedia dinoType;
    private final Sex sex;
    private int age;
    private int pregnancyPeriod;
    private int unconsciousPeriod;
    private Action actionInMotion;

    // default baby dino
    public DinoActor(DinoEncyclopedia dinoType, Sex sex, Boolean isMatured){
        super(dinoType.getName(), dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        this.dinoType = dinoType;
        this.sex = sex;
        setMaturity(isMatured);
        setMaxHitPoints(dinoType.getMaxHitPoints());
        initializeDinoBehaviour();
    }

    public DinoActor(DinoEncyclopedia dinoType, Boolean isMatured){
        super(dinoType.getName(), dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        this.dinoType = dinoType;
        setMaturity(isMatured);
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
//        behaviour.add(new PregnancyBehaviour());
//        behaviour.add(new FollowMateBehaviour());
//        behaviour.add(new FollowVictimBehaviour());
//        behaviour.add(new WanderBehaviour());
    }

    /**
     * Increments the age of the dinosaur and simulates the action of the dinosaur growing up
     */
    private void aging(){
        if (age >= dinoType.getMatureWhen()){
            displayChar = Character.toUpperCase(getDisplayChar());
        }
        else {
            age++;
        }
    }

    public void setAge(int newAge){
        if (newAge > 0){
            age =  newAge;
        }
    }

    private void setMaturity(boolean maturityStatus){
        if (maturityStatus) {
            setAge(dinoType.getMatureWhen());
            displayChar = Character.toUpperCase(displayChar);
        }
        else {
            displayChar = Character.toLowerCase(displayChar);
        }
        adjustBreedingCapability();
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

    public boolean isCarnivorous(){
        return hasCapability(DinoCapabilities.CARNIVORE);
    }

    public boolean canAttack(){return hasCapability(DinoCapabilities.CAN_ATTACK); }

    public boolean canBeAttacked(){
        return hasCapability(DinoCapabilities.CAN_BE_ATTACKED);
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
            System.out.printf("%s at (%d, %d) getting hungry!\n", name, x, y);
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
        return hasCapability(DinoCapabilities.CAN_BREED);
    }

    /**
     * Determines whether the dinoActor should have the Capability to breed and
     * add or remove the capability accordingly.
     */
    public void adjustBreedingCapability() {
        if (!canBreed()){
            if (hitPoints >= dinoType.getBreedingMinFoodLevel() && !isPregnant() && isMatured()){
                addCapability(DinoCapabilities.CAN_BREED);
            }
        }
        else {
            if (hitPoints < dinoType.getBreedingMinFoodLevel()){
                removeCapability(DinoCapabilities.CAN_BREED);
            }
        }
    }

    public void setActionInMotion(Action newAction){
        if (actionInMotion == null){
            actionInMotion = newAction;
        }
    }

    /**
     * Returns true if the dinoActor is pregnant, false otherwise.
     * A dinoActor only has a chance to be pregnant after breeding.
     * @return true if the dinoActor is pregnant, false otherwise
     */
    public boolean isPregnant(){
        return hasCapability(DinoCapabilities.PREGNANT);
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
            addCapability(DinoCapabilities.PREGNANT);
            System.out.println(hasCapability(DinoCapabilities.PREGNANT));
            initializePregnancyPeriod();
            if (hasCapability(DinoCapabilities.CAN_BREED)){
                removeCapability(DinoCapabilities.CAN_BREED);
            }
        }
        else {
            removeCapability(DinoCapabilities.PREGNANT);
        }
    }

    // unconscious
    @Override
    public boolean isConscious(){
        return hasCapability(UnconsciousStatus.CONSCIOUS);
    }

    public void setUnconscious(boolean status){
        if (status){
            addCapability(UnconsciousStatus.UNCONSCIOUS);
            initializeUnconsciousPeriod();
        }
        else {
            removeCapability(UnconsciousStatus.UNCONSCIOUS);
        }
    }

    public void initializeUnconsciousPeriod() {
        unconsciousPeriod = dinoType.getInitialUnconsciousPeriod();
    }

    public int getUnconsciousPeriod() {
        return unconsciousPeriod;
    }

    public void decrementUnconsciousPeriod(){
        unconsciousPeriod--;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions validActions = new Actions();
        ArrayList<Behaviour> adjacentActorBehaviour = new ArrayList<>();
        adjacentActorBehaviour.add(new BreedingBehaviour(this));

        System.out.println("\nhere");
        for (Behaviour b : adjacentActorBehaviour){
            Action resultingAction = b.getAction(otherActor, map);
            System.out.println(resultingAction);
            if (resultingAction != null){
                validActions.add(resultingAction);
            }
        }

        return validActions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!checkUnconsciousPeriod(map)) {
            aging();
            //        System.out.println("");
            System.out.println("age: " + age);
            decrementFoodLevel();
            System.out.println("food lvl: " + hitPoints);
            System.out.println("sex: " + sex);
            roarIfHungry(map);
            adjustBreedingCapability();
            System.out.println("has breeding capability: " + canBreed());
            System.out.println("pregnancy period: " + pregnancyPeriod);

            Action actionToExecute = null;

            // if the actor has been determined to perform an Action with another Actor previously
            // it should always return that Action
            if (actionInMotion != null) {
                actionToExecute = actionInMotion;
                actionInMotion = null;
            }

            // calling getAction for every behaviour can help us to do some necessary processing
            // as well even if it returns null in the end
            for (Behaviour b : behaviour) {
                Action resultingAction = b.getAction(this, map);
                System.out.println(b);
                if (resultingAction != null && actionToExecute == null) {
                    System.out.println(b + "is not null");
                    actionToExecute = resultingAction;
                    System.out.println("changed");
                }
            }


            //        for (Action a : actions){
            //            if (a instanceof LayEggAction){
            //                return a;
            //            }
            //        }
            //

            return actionToExecute;
        }
        return null;
    }

    public boolean checkUnconsciousPeriod(GameMap map ) {
        Location dinoLocation = map.locationOf(this);
        if (!this.isConscious()){
            if (this.getUnconsciousPeriod() > 0){
                this.decrementUnconsciousPeriod();
                this.setUnconscious(true);
            }
            else {
                this.setUnconscious(false);
                System.out.println("Dinosaur at " + (map.locationOf(this).x()) + " " + (map.locationOf(this).y()) + " is dead!")  ;
                map.removeActor(this);
                Corpse corpseDino = new Corpse(dinoType.getDisplayChar());
                dinoLocation.addItem(corpseDino);
            }
            return true;
        }
        else {
            return false;
        }
    }





        // if lastAction has a subsequent action, always return that
//        if (lastAction != null && lastAction.getNextAction() != null){
//            actionToExecute = lastAction.getNextAction();
//        }
//        else if (actions.size() > 0){
//            actionToExecute = actions.get(0);
//        }
//
//
//        return actionToExecute;
}

//TODO: add player feed action in get allowable (also in child classes) and fix playTurn method

    // Precedence
    // layEgg
    // breeding
    // attack for food
    // feeding from player
    // feeding on its own
    // follow mate
    // follow food






