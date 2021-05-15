package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.DynamicMovement;
import game.environment.CapableGround;

public class Pterodactyl extends DinoActor implements DynamicMovement {

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

    private static final int INITIAL_FLYING_ENERGY = 2;

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
        addCapability(DinoCapabilities.CAN_BE_ATTACKED);
        addCapability(DinoCapabilities.CAN_REACH_TREE);
        addCapability(DinoCapabilities.CAN_TRAVERSE_WATER);
        addCapability(DinoCapabilities.ARBOREAL);
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
    public void rechargeEnergy(Ground ground){
        if (ground instanceof CapableGround){
            if (((CapableGround)ground).isTree()){
                flyingEnergy = INITIAL_FLYING_ENERGY;
            }
        }
    }

}
