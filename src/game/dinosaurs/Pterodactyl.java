package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.environment.FertileGround;
import game.movement.DynamicMovement;
import game.Food;
import game.environment.Lake;

/**
 * A small carnivorous DinoActor that can fly.
 */
public class Pterodactyl extends DinoActor implements DynamicMovement, Food {

    /**
     * Enum type that can be referenced for a lot of useful constants
     * that are values for Pterodactyl attributes.
     */
    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.PTERODACTYL;

    /**
     * Id to append to a Pterodactyl's name to be able to differentiate between
     * different Pterodactyls.
     */
    private static int nextId = 1;

    /**
     * Initial energy for flying that the Pterodactyl starts with when it is well rested.
     */
    private static final int INITIAL_FLYING_ENERGY = 30;

    /**
     * Current amount of energy left for flying.
     */
    private int flyingEnergy = INITIAL_FLYING_ENERGY;

    /**
     * Constructor.
     * @param sex DinoCapabilities.MALE is Pterodactyl should be male,
     *            DinoCapabilities.FEMALE is Pterodactyl should be female,
     * @param isMatured true if Pterodactyl is grown up, false otherwise.
     */
    public Pterodactyl(DinoCapabilities sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured, nextId);
        nextId += 1;
    }

    /**
     * Constructor.
     * @param isMatured true if Pterodactyl is grown up, false otherwise.
     */
    public Pterodactyl(Boolean isMatured) {
        super(DINO_TYPE, isMatured, nextId);
        nextId += 1;
    }

    /**
     * Initialize necessary capabilities of a Pterodactyl.
     */
    @Override
    void initializeCapabilities(){
        super.initializeCapabilities();
        addCapability(DinoCapabilities.CARNIVORE);
        addCapability(DinoCapabilities.CAN_REACH_TREE);
        addCapability(DinoCapabilities.CAN_TRAVERSE_WATER);
        addCapability(DinoCapabilities.ARBOREAL);
    }

    /**
     * Returns true if the CapableActor is aggressive and carnivorous and the Pterodactyl
     * is within its reach (not on a tree), false otherwise.
     * @param capableActor a CapableActor
     * @param location location the Pterodactyl is at
     * @return true if the Pterodactyl can be eaten by the CapableActor, false otherwise.
     */
    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isCarnivorous() && capableActor.canAttack() && !isOnTree(location.getGround());
    }

    /**
     * Simulates the Pterodactyl being bitten / eaten by other Actors,
     * if the Actor has a big jaw, the Pterodactyl will be consumed whole and disappears from the map,
     * otherwise it will suffer a decrement in its health level / hit points.
     * @param map map the Pterodactyl is on
     * @param location location the Pterodactyl is at
     * @param biteSize biteSize of the Actor that is trying to eat it
     * @return amount of food points that the Actor that is eating it should gain
     */
    @Override
    public int eat(GameMap map, Location location, int biteSize) {
        decrementHitPoints(biteSize);
        if (getHitPoints() == 0) {
            map.removeActor(this);
        }
        return biteSize;
    }

    /**
     * Returns Pterodactyl's name.
     * @return Pterodactyl's name
     */
    @Override
    public String foodName() {
        return this.name;
    }

    /**
     * Returns true if the Pterodactyl is on a tree, false otherwise.
     * @param ground Ground that the Pterodactyl is on
     * @return true if the Pterodactyl is on a tree, false otherwise.
     */
    private boolean isOnTree(Ground ground){
        if (ground instanceof FertileGround){
            return ((FertileGround)ground).isTree();
        }
        return false;
    }

    /**
     * Returns the string "flies" if the Pterodactyl has energy to fly,
     * returns "walks" otherwise.
     * @return "flies" if the Pterodactyl has energy to fly, "walks" otherwise
     */
    @Override
    public String getMovement() {
        String movementType = "walks";
        if (isSpecialMovementUsed()){
            movementType = "flies";
        }
        return movementType;
    }

    /**
     * Decrements the flying energy if it flies during the turn.
     */
    @Override
    public void depleteEnergy() {
        flyingEnergy = Math.max(flyingEnergy - 1, 0);
    }

    /**
     * Returns true if Pterodactyl uses special movement, i.e. flying to move, false otherwise.
     * @return true if Pterodactyl flies, false otherwise.
     */
    @Override
    public boolean isSpecialMovementUsed() {
        return flyingEnergy > 0;
    }

    /**
     * Executes special activities (drinking water and eating fish) if the Pterodactyl
     * flies over a lake.
     * @param map map the actor is on
     * @param location location the actor will be on after using special movement
     */
    @Override
    public void activityDuringSpecialMovement(GameMap map, Location location){
        Ground ground = location.getGround();
        if (ground instanceof Lake){
            Lake lake = (Lake) ground;
            if (isHungry() && lake.canEat(this, location)){
                int healPoints = lake.eat(map, location, getBiteSize());
                if (healPoints > 0) {
                    heal(healPoints);
                    System.out.printf("%s eats some %s while flying over lake.%n", name, lake.foodName());
                }
            }

            if (isThirsty() && lake.hasWater()){
                quench();
                System.out.printf("%s drinks water while flying over lake.%n", name);
            }
        }
    }

    /**
     * Replenishes the flying energy of the Pterodactyl.
     * @param ground ground the actor is on
     */
    @Override
    public void rechargeEnergy(Ground ground){
        if (flyingEnergy == 0 && isOnTree(ground)){
            flyingEnergy = INITIAL_FLYING_ENERGY;
        }
    }

    /**
     * Checks if the Pterodactyl's flying energy should be recharged,
     * then invokes the super class's playTurn method.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return super class's playTurn method
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Ground ground = map.locationOf(this).getGround();
        rechargeEnergy(ground);
        return super.playTurn(actions, lastAction, map, display);
    }

}
