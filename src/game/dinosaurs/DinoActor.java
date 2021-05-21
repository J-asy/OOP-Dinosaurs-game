package game.dinosaurs;

import edu.monash.fit2099.engine.*;

import game.Behaviour;
import game.attack.AttackBehaviour;
import game.attack.Corpse;
import game.Probability;
import game.breed.BreedingBehaviour;
import game.drinking.DrinkingBehaviour;
import game.feeding.FeedOnActorBehaviour;
import game.feeding.FeedOnItemBehaviour;
import game.movement.*;
import game.player.FedByPlayerBehaviour;
import game.pregnancy.LayEggAction;
import game.pregnancy.PregnancyBehaviour;
import game.movement.WanderBehaviour;

import java.util.ArrayList;

/**
 * Base class for Stegosaur, Brachiosaur and Allosaur for representing dinosaur Actors.
 */
public abstract class DinoActor extends CapableActor {

    /**
     * An ArrayList of standard behaviours that the DinoActor should have.
     */
    private ArrayList<Behaviour> behaviour;

    /**
     * ArrayList of Behaviours that requires two Actors to be in adjacent squares.
     */
    private ArrayList<Behaviour> interactiveBehaviours;

    /**
     * A reference to DinoEncyclopedia Enum class that indicates the species
     * of the DinoActor and contains many useful constants for initialization or comparison etc.
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
     * Indicates the number of turns till an unconscious DinoActor
     * has till it dies.
     */
    private int unconsciousPeriod;

    /**
     * Water level.
     */
    private int waterLevel;

    /**
     * The size of DinoActor's jaw, will determine the amount of hit points gained
     * if the food size is too big to be eaten whole.
     */
    private int biteSize;

    /**
     * Constructor.
     * @param dinoType DinoEncyclopedia value that indicates species of the DinoActor
     * @param sex either DinoEncyclopedia.MALE or DinoEncyclopedia.FEMALE should be passed in
     *            to indicate the sex of the DinoActor
     * @param isMatured true if the DinoActor is grown up, false otherwise
     */
    public DinoActor(DinoEncyclopedia dinoType, DinoCapabilities sex, Boolean isMatured, int nextId){
        super(dinoType.getName() + " " + nextId, dinoType.getDisplayChar(), dinoType.getInitialHitPoints());
        this.waterLevel = dinoType.getInitialWaterLevel();
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
        this.waterLevel = dinoType.getInitialWaterLevel();
        this.dinoType = dinoType;
        initialization(isMatured);
        setSex();
    }

    /**
     * Initialize maturity, max hit points, behaviours and capabilities
     * of a DinoActor
     * @param isMatured true if DinoActor should be a grown up DinoActor,
     *                  false otherwise
     */
    private void initialization(boolean isMatured){
        setBiteSize();
        setMaturity(isMatured);
        setMaxHitPoints(dinoType.getMaxHitPoints());
        if (!isMatured) {
            setBabyHitPoints(dinoType.getBabyInitialHitPoints());
        }
        initializeDinoBehaviour();
        initializeCapabilities();
    }

    /**
     * Initializes the dinoActor with the standard behaviour that they should have.
     */
    private void initializeDinoBehaviour(){
        // all behaviours that the DinoActor can perform alone
        behaviour = new ArrayList<>();
        behaviour.add(new PregnancyBehaviour());
        behaviour.add(new EvadeDinoBehaviour());
        behaviour.add(new FeedOnItemBehaviour());
        behaviour.add(new DrinkingBehaviour());
        behaviour.add(new FollowMateBehaviour());
        behaviour.add(new FollowWaterBehaviour());
        behaviour.add(new FollowFoodBehaviour());
        behaviour.add(new FollowVictimBehaviour());
        behaviour.add(new WanderBehaviour());

        // all behaviours that the DinoActor can perform with another
        // Actor on adjacent squares
        interactiveBehaviours = new ArrayList<>();
        interactiveBehaviours.add(new FeedOnActorBehaviour(this));
        interactiveBehaviours.add(new BreedingBehaviour(this));
        interactiveBehaviours.add(new AttackBehaviour(this));
        interactiveBehaviours.add(new FedByPlayerBehaviour(this));
    }

    /**
     * Initialize capabilities that are common to all dinosaurs.
     */
    void initializeCapabilities(){
        addCapability(DinoCapabilities.CONSCIOUS);
    }

    public DinoEncyclopedia getDinoType() {
        return dinoType;
    }

    /**
     * Randomly assigns a sex for the DinoActor
     */
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

    public DinoCapabilities getSex() {
        if (isMale()) return DinoCapabilities.MALE;
        if (isFemale()) return DinoCapabilities.FEMALE;
        return null;
    }

    /**
     * Increments the age of the dinosaur and simulates the action of the dinosaur growing up
     */
    private void aging(){
        if (isMatured()){
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

    /**
     * Updates the DinoActor's age and display character depending on the
     * argument passed in, and adjusts the breeding capability based on whether
     * it is matured or not.
     * @param maturityStatus true if intend to set DinoActor as matured,
     *                       false otherwise.
     */
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

    public boolean isMatured(){
        return age >= dinoType.getMatureWhen();
    }

    private void setMaxHitPoints(int newMaxHitPoints) {
        if (newMaxHitPoints >= hitPoints) {
            maxHitPoints = newMaxHitPoints;
        }
    }

    private void setBabyHitPoints(int newHitPoints) {
        if (newHitPoints > 0 && newHitPoints <= maxHitPoints) {
            hitPoints = newHitPoints;
        }
    }

    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Decrements the dinoActor's food level, which is equivalent to its hitPoints.
     */
    void decrementHitPoints(int decrementBy){
        int hurtPoints = decrementBy;
        if (hitPoints - decrementBy < 0){
            hurtPoints = hitPoints;
        }
        super.hurt(hurtPoints);
    }

    private void setBiteSize(){
        biteSize = dinoType.BITE_SIZE;
    }

    public int getBiteSize(){
        return biteSize;
    }

    /**
     * Simulates DinoActor drinking water by increasing its water level
     * according to the gulp size.
     */
    public void quench(){
        waterLevel += dinoType.getGulpSize();
        waterLevel = Math.min(waterLevel, dinoType.getMaxWaterLevel());
    }

    void decrementWaterLevel(){
        if (waterLevel > 0) {
            this.waterLevel--;
        }
    }

    /**
     * DinoActor will drink rain water and increment water level when it rains.
     */
    @Override
    public void actionWhenRaining(){
        waterLevel = Math.min(waterLevel + 10, dinoType.getMaxWaterLevel());
    }

    public boolean isHungry(){
        return hitPoints < dinoType.getHungryWhen();
    }

    public boolean isThirsty(){
        return waterLevel < dinoType.getThirstyWhen();
    }

    /**
     * Checks if the food level / hit points has reached
     * the point where the dinoActor will get hungry.
     * If yes, a hunger message will be printed out to notify the player.
     * @param map GameMap that the actor is currently on
     */
    public void roarIfHungry(GameMap map){
        if (isHungry()) {
            int x = map.locationOf(this).x();
            int y = map.locationOf(this).y();
            System.out.printf("%s at (%d, %d) getting hungry!\n", name, x, y);
        }
    }

    /**
     * Checks if the water level has reached
     * the point where the dinoActor will get thirsty.
     * If yes, a hunger message will be printed out to notify the player.
     * @param map GameMap that the actor is currently on
     */
    public void roarIfThirsty(GameMap map){
        if (isThirsty()){
            int x = map.locationOf(this).x();
            int y = map.locationOf(this).y();
            System.out.printf("%s at (%d, %d) getting thirsty!\n", name, x, y);
        }
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

    /**
     * Initializes the dinoActor's pregnancy period to an
     * appropriate value.
     */
    private void initializePregnancyPeriod(){
        pregnancyPeriod = dinoType.getPregnancyPeriod();
    }

    public int getPregnancyPeriod(){
        return pregnancyPeriod;
    }

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
            initializePregnancyPeriod();
            removeCapability(DinoCapabilities.CAN_BREED);
        }
        else {
            removeCapability(DinoCapabilities.PREGNANT);
        }
    }

    /**
     * Sets the DinoActor to unconscious if the argument passed in is true,
     * otherwise the DinoActor is set to be conscious.
     * @param status true if the DinoActor should be unconscious, false otherwise
     */
    public void setUnconscious(boolean status){
        if (status){
            removeCapability(DinoCapabilities.CONSCIOUS);
            initializeUnconsciousPeriod();
        }
        else {
            addCapability(DinoCapabilities.CONSCIOUS);
        }
    }

    /**
     * Adjust the consciousness of the DinoActor appropriately,
     * according to its current hitPoints and/or waterLevel.
     */
    private void adjustConsciousness(){
        if (isConscious() && (hitPoints <= 0 || waterLevel <= 0)) {
            setUnconscious(true);
        }
        else if (!isConscious() && (hitPoints > 0 && waterLevel > 0)) {
            setUnconscious(false);
        }
    }

    private void initializeUnconsciousPeriod() {
        unconsciousPeriod = dinoType.getInitialUnconsciousPeriod();
    }

    private void decrementUnconsciousPeriod(){
        if (unconsciousPeriod > 0) {
            unconsciousPeriod--;
        }
    }

    /**
     * Checks whether the DinoActor is unconscious. If it is, checks
     * whether it is time for it to die yet. If it should be dead, a Corpse
     * will replace the unconscious DinoActor, otherwise the unconsciousPeriod
     * is decremented. Finally, returns true if the DinoActor is unconscious and
     * false otherwise.
     *
     * @param map GameMap that the DinoActor is currently on
     * @return false if the DinoActor is conscious, true otherwise
     */
    private boolean checkUnconsciousPeriod(GameMap map) {
        Location dinoLocation = map.locationOf(this);
        if (!isConscious()){
            if (unconsciousPeriod > 0){
                decrementUnconsciousPeriod();
                System.out.println(this + " at (" + dinoLocation.x() + ", " + dinoLocation.y() + ") is unconscious!");
            }
            else {
                map.removeActor(this);
                dinoLocation.addItem(new Corpse(dinoType));
                System.out.println(this + " at (" + dinoLocation.x() + ", " + dinoLocation.y() + ") is dead!")  ;
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor
     * by going through the interactiveBehaviours array list and calling getAction.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions validActions = new Actions();

        for (Behaviour b : interactiveBehaviours){
            Action resultingAction = b.getAction(otherActor, map);
            if (resultingAction != null){
                validActions.add(resultingAction);
            }
        }

        return validActions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Action actionToExecute = new DoNothingAction();
        System.out.println();
        System.out.println("HP: " + hitPoints);
        System.out.println("WL: " + waterLevel);
        System.out.println(canBreed());


        // do any necessary processing first
        aging();
        decrementHitPoints(1);
        decrementWaterLevel();
        adjustConsciousness();

        if (!checkUnconsciousPeriod(map)) {
            // do any necessary processing first
            roarIfHungry(map);
            roarIfThirsty(map);
            adjustBreedingCapability();

            // calling getAction for every behaviour can help us to do some necessary processing
            // as well even if it returns null in the end
            for (Behaviour b : behaviour) {
                Action resultingAction = b.getAction(this, map);
                if (resultingAction != null && actionToExecute instanceof DoNothingAction) {
                    actionToExecute = resultingAction;
                }
            }

            if (!(actionToExecute instanceof LayEggAction) && actions.size() > 0){
                for (Action a: actions) {
                    if (!(a instanceof MoveActorAction || a instanceof DoNothingAction || a instanceof PickUpItemAction)) {
                        actionToExecute = a;
                        break;
                    }
                }
            }
        }
        return actionToExecute;
    }

}











