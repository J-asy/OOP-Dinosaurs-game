package game.dinosaurs;

import edu.monash.fit2099.engine.Ground;
import game.environment.CapableGround;

public class Pterodactyl extends DinoActor {

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

    private boolean isOnTree(Ground ground){
        if (ground instanceof CapableGround){
            return ((CapableGround) ground).isTree();
        }
        return false;
    }

    @Override
    public boolean canLayEggHere(Ground ground) {
        return isOnTree(ground);
    }

    @Override
    public boolean canBreedHere(Ground ground) {
        return isOnTree(ground);
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
    }

}
