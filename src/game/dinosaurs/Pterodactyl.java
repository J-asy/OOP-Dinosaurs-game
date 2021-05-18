package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.follow.DynamicMovement;
import game.Food;
import game.environment.CapableGround;
import game.environment.Lake;

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

    private static final int INITIAL_FLYING_ENERGY = 30;

    private int flyingEnergy = INITIAL_FLYING_ENERGY;

    /**
     * Constructor.
     * @param sex DinoCapabilities.MALE is Pterodactyl should be male,
     *            DinoCapabilities.FEMALE is Pterodactyl should be female,
     * @param isMatured true if Brachiosaur is grown up, false otherwise.
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

    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isCarnivorous() && capableActor.canAttack() && !isOnTree(location.getGround());
    }

    @Override
    public int eat(GameMap map, Location location, int biteSize) {
        decrementHitPoints(biteSize);
        if (getHitPoints() == 0) {
            map.removeActor(this);
        }
        return biteSize;
    }

    @Override
    public String foodName() {
        return this.name;
    }

    private boolean isOnTree(Ground ground){
        if (ground instanceof CapableGround){
            return ((CapableGround)ground).isTree();
        }
        return false;
    }

    @Override
    public String getMovement() {
        String movementType = "walks";
        if (useSpecialMovement()){
            movementType = "flies";
        }
        return movementType;
    }

    @Override
    public void depleteEnergy() {
        flyingEnergy = Math.max(flyingEnergy - 1, 0);
    }

    @Override
    public boolean useSpecialMovement() {
        return flyingEnergy > 0;
    }

    @Override
    public void activityDuringSpecialMovement(GameMap map, Location location){
        Ground ground = location.getGround();
        if (ground instanceof Lake){
            Lake lake = (Lake) ground;
            if (lake.canEat(this, location)){
                int healPoints = lake.eat(map, location, getBiteSize());
                if (healPoints > 0) {
                    heal(healPoints);
                    System.out.printf("%s eats %s.%n", name, lake.foodName());
                }
            }

            if (lake.hasWater()){
                quench();
            }
        }
    }

    public void rechargeEnergy(Ground ground){
        if (flyingEnergy == 0 && isOnTree(ground)){
            flyingEnergy = INITIAL_FLYING_ENERGY;
        }
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Ground ground = map.locationOf(this).getGround();
        rechargeEnergy(ground);
        return super.playTurn(actions, lastAction, map, display);
    }

}
