package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;

import game.attack.Corpse;
import game.breed.BreedingBehaviour;
import game.Probability;
import game.follow.FollowMateBehaviour;
import game.follow.FollowVictimBehaviour;
import game.pregnancy.PregnancyBehaviour;
import game.wander.WanderBehaviour;

import java.util.ArrayList;

/**
 * Base class for Stegosaur, Brachiosaur and Allosaur for representing dinosaur Actors.
 */

public abstract class DinoActor extends Actor {

    /**
     * An ArrayList of standard behaviours that the DinoActor should have.
     */
    private ArrayList<Behaviour> behaviour;

    /**
     * A reference to DinoEncyclopedia Enum class that indicates the species
     * of the DinoActor and contains many useful constants for initialization or comparison etc
     */
    private final DinoEncyclopedia dinoType;

    /**
     * Age of the dinosaur, will only be incremented until the maturity age
     * (since we only need to know when the dinoActor is mature).
     */
    private int age;

    /**
     * Indicates the number of turns till a pregnant female DinoActor
     * has to wait till it is time to lay an Egg.
     */
    private int pregnancyPeriod;

    /**
     * Indicates the number of turns since the DinoActor first became unconscious.
     */
    private int unconsciousPeriod;

    /**
     * An Action that should be returned in the playTurn method
     * as the DinoActor is interacting with another Actor and their actions need to be in sync.
     */
    private Action actionInMotion;

    /**
     * Constructor.
     * @param dinoType DinoEncyclopedia value that indicates species of the DinoActor
     * @param sex either DinoEncyclopedia.MALE or DinoEncyclopedia.FEMALE should be passed in
     *            to indicate the sex of the DinoActor
     * @param isMatured true if the DinoActor is grown up, false otherwise
     */
    public DinoActor(DinoEncyclopedia dinoType, DinoCapabilities sex, Boolean isMatured, int nextId){
        super(dinoType.getName() + " " + nextId, dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        this.dinoType = dinoType;
        initialization(isMatured);
        setSex(sex);
    }

    /**
     * Constructor that randomly assigns the DinoActor as male or female.
     * @param dinoType DinoEncyclopedia value that indicates species of the DinoActor
     * @param isMatured true if the DinoActor is grown up, false otherwise
     */
    public DinoActor(DinoEncyclopedia dinoType, Boolean isMatured, int nextId){
        super(dinoType.getName() + " " + nextId, dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        this.dinoType = dinoType;
        initialization(isMatured);
        setSex();
    }


    private void initialization(boolean isMatured){
        setMaturity(isMatured);
        setMaxHitPoints(dinoType.getMaxHitPoints());
        initializeDinoBehaviour();
        initializeCapabilities();
    }

    /**
     * Initializes the dinoActor with the standard behaviour that they should have.
     */
    private void initializeDinoBehaviour(){
        behaviour = new ArrayList<>();
        behaviour.add(new PregnancyBehaviour());
        behaviour.add(new FollowMateBehaviour());
        behaviour.add(new FollowVictimBehaviour());
        behaviour.add(new WanderBehaviour());
    }

    void initializeCapabilities(){
        addCapability(DinoCapabilities.CONSCIOUS);
    }

    public DinoEncyclopedia getDinoType() {
        return dinoType;
    }

    private void setSex(){
        if (Probability.generateProbability(0.5F)){
            addCapability(DinoCapabilities.MALE);
        }
        else {
            addCapability(DinoCapabilities.FEMALE);
        }
    }

    private void setSex(DinoCapabilities sex) {
        if (sex == DinoCapabilities.MALE || sex == DinoCapabilities.FEMALE) {
            addCapability(sex);
        }
    }

    public DinoCapabilities getSex(){
        DinoCapabilities sex;
        if (hasCapability(DinoCapabilities.MALE)){
            sex = DinoCapabilities.MALE;
        }
        else  {
            sex = DinoCapabilities.FEMALE;
        }
        return sex;
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

    private void setAge(int newAge){
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
//            System.out.println(hasCapability(DinoCapabilities.PREGNANT));
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
        return hasCapability(DinoCapabilities.CONSCIOUS);
    }

    public void setUnconscious(boolean status){
        if (status){
            removeCapability(DinoCapabilities.CONSCIOUS);
            addCapability(DinoCapabilities.UNCONSCIOUS);
            initializeUnconsciousPeriod();
        }
        else {
            removeCapability(DinoCapabilities.UNCONSCIOUS);
            addCapability(DinoCapabilities.CONSCIOUS);
        }
    }

    private void initializeUnconsciousPeriod() {
        unconsciousPeriod = dinoType.getInitialUnconsciousPeriod();
    }

    private int getUnconsciousPeriod() {
        return unconsciousPeriod;
    }

    private void decrementUnconsciousPeriod(){
        unconsciousPeriod--;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions validActions = new Actions();
        ArrayList<Behaviour> adjacentActorBehaviour = new ArrayList<>();
        adjacentActorBehaviour.add(new BreedingBehaviour(this));

        for (Behaviour b : adjacentActorBehaviour){
            Action resultingAction = b.getAction(otherActor, map);
            if (resultingAction != null){
                validActions.add(resultingAction);
            }
        }

        return validActions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (this.isConscious() && this.hitPoints == 0) {
                this.setUnconscious(true);
        }

        if (!checkUnconsciousPeriod(map)) {
            aging();
            decrementFoodLevel();
            roarIfHungry(map);
            adjustBreedingCapability();

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
                if (resultingAction != null && actionToExecute == null) {
                    actionToExecute = resultingAction;
                }
            }

            return actionToExecute;
        }
        return null;
    }

    public boolean checkUnconsciousPeriod(GameMap map ) {
        Location dinoLocation = map.locationOf(this);
        if (!this.isConscious()){
            if (this.getUnconsciousPeriod() > 0){
                this.decrementUnconsciousPeriod();
            }
            else {
                System.out.println("Dinosaur at " + dinoLocation.x() + " " + dinoLocation.y() + " is dead!")  ;
                map.removeActor(this);
                Corpse corpseDino = new Corpse(dinoType);
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






