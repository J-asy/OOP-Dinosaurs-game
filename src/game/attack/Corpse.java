package game.attack;

import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.dinosaurs.CapableActor;
import game.dinosaurs.DinoEncyclopedia;
import java.util.Map;
import static java.util.Map.entry;

/**
 * The remains of a dead dinosaur which can be eaten by other DinoActors.
 */
public class Corpse extends FoodItem {

    /**
     * An integer list which first value is the total number of turns to wait till a Stegosaur's corpse
     * rots and disappears, and second value is the initial food points that the Corpse contains.
     */
    private final static int[] STEGOSAUR_VALUE = {20,50};

    /**
     * An integer list which first value is the total number of turns to wait till a Brachiosaur's corpse
     * rots and disappears, and second value is the initial food points that the Corpse contains.
     */
    private final static int[] BRACHIOSAUR_VALUE = {40,100};

    /**
     * An integer list which first value is the total number of turns to wait till a Allosaur's corpse
     * rots and disappears, and second value is the initial food points that the Corpse contains.
     */
    private final static int[] ALLOSAUR_VALUE = {20,50};

    /**
     * An integer list which first value is the total number of turns to wait till a Pterodactyl's corpse
     * rots and disappears, and second value is the initial food points that the Corpse contains.
     */
    private final static int[] PTERODACTYL_VALUE = {20,30};

    /**
     * Map that stores useful constants for the Corpse Item according to different dinosaur species.
     */
    private final static Map<DinoEncyclopedia, int[]> DINO_CORPSE_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR, STEGOSAUR_VALUE),
            entry(DinoEncyclopedia.BRACHIOSAUR, BRACHIOSAUR_VALUE),
            entry(DinoEncyclopedia.ALLOSAUR, ALLOSAUR_VALUE),
            entry(DinoEncyclopedia.PTERODACTYL, PTERODACTYL_VALUE)
    );

    /**
     * Number of turns until corpse is removed from map.
     */
    private int waitTurns;

    /**
     * Basic state of the species of the dead DinoActor.
     */
    private final DinoEncyclopedia species;

    /**
     * Constructor.
     * @param species of the dead DinoActor
     */
    public Corpse(DinoEncyclopedia species) {
        super("Corpse",'%');
        this.species = species;
        setForCarnivores();
        initializeWaitTurns();
        setHealPoints(getInitialHealPoints());
    }

    /**
     * Initializes number of turns until corpse is removed from map.
     */
    private void initializeWaitTurns(){
        waitTurns = DINO_CORPSE_DICTIONARY.get(species)[0];
    }

    /**
     * Decrements corpse's number of turns until corpse is removed from map.
     */
    private void decrementWaitTurn(){
        if (waitTurns > 0){
            waitTurns--;
        }
    }

    /**
     * Returns a constant that represents the species of the corpse.
     * @return a constant that represents the species of the corpse.
     */
    public DinoEncyclopedia getSpecies() {
        return species;
    }

    /**
     * Returns the initial amount of food points / heal points that the corpse of
     * the corresponding species contains.
     * @return initial amount of food points the corpse contains
     */
    private int getInitialHealPoints(){
        return DINO_CORPSE_DICTIONARY.get(species)[1];
    }

    /**
     * Checks if wait turn is over, if yes, remove the corpse. Otherwise, decrement wait turn
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        if (waitTurns == 0) {
            currentLocation.removeItem(this);
        }
        else {
            decrementWaitTurn();
        }
    }

    /**
     * Returns true if the CapableActor attempting to eat it is carnivorous, returns false otherwise.
     * @param capableActor CapableActor that is attempting to eat the Corpse
     * @param location location of the CapableActor
     * @return true if the CapableActor is carnivorous, returns false otherwise
     */
    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isCarnivorous();
    }

}
