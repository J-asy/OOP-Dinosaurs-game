package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.FoodItem;

import java.util.Map;
import static java.util.Map.entry;

/**
 * Represents an egg of a DinoActor.
 */
public class Egg extends FoodItem {


    /**
     * An integer list which first value is the Stegosaur egg's number of turns to wait
     * till it hatches, and second value if the amount of EcoPoints awarded when it hatches.
     */
    private final static int[] STEGOSAUR_VALUE = {30,100};

    /**
     * An integer list which first value is the Brachiosaur egg's number of turns to wait
     * till it hatches, and second value if the amount of EcoPoints awarded when it hatches.
     */
    private final static int[] BRACHIOSAUR_VALUE = {60,1000};

    /**
     * An integer list which first value is the Allosaur egg's number of turns to wait
     * till it hatches, and second value if the amount of EcoPoints awarded when it hatches.
     */
    private final static int[] ALLOSAUR_VALUE = {50,1000};

    /**
     * An integer list which first value is the Pterodactyl egg's number of turns to wait
     * till it hatches, and second value if the amount of EcoPoints awarded when it hatches.
     */
    private final static int[] PTERODACTYL_VALUE = {30,100};

    /**
     * Map that stores useful constants for the Egg object according to different dinosaur species.
     */
    private final static Map<DinoEncyclopedia, int[]> DINO_EGG_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR, STEGOSAUR_VALUE),
            entry(DinoEncyclopedia.BRACHIOSAUR, BRACHIOSAUR_VALUE),
            entry(DinoEncyclopedia.ALLOSAUR, ALLOSAUR_VALUE),
            entry(DinoEncyclopedia.PTERODACTYL, PTERODACTYL_VALUE)
    );

    /**
     * Turns left to wait till the egg hatches.
     */
    private int waitTurns;

    /**
     * Uniquely identifies the species of the Egg's parent,
     * baby dinosaur hatched out of the Egg will be of the same species.
     */
    private DinoEncyclopedia parent;

    /**
     * Amount of food points that will be gained by eating the egg.
     */
    private final static int EGG_HEAL_POINTS = 10;

    /**
     * Constructor.
     * @param parent DinoEncyclopedia enum value of the parent dinosaur to identify its species
     */
    public Egg(DinoEncyclopedia parent){
        super(parent.getName() + " Egg", 'o');
        initializeWaitTurns((DINO_EGG_DICTIONARY.get(parent))[0]);
        setParent(parent);
        setForCarnivores();
        setHealPoints(EGG_HEAL_POINTS);
    }

    private void setParent(DinoEncyclopedia newParent){
        parent = newParent;
    }

    private void initializeWaitTurns(int newWaitTurn){
        if (newWaitTurn >= 0){
            waitTurns = newWaitTurn;
        }
    }

    private void decrementWaitTurn(){
        if (waitTurns > 0){
            waitTurns--;
        }
    }

    /**
     * Returns true if the CapableActor is a carnivorous DinoActor that is of different
     * species with the Egg, false otherwise.
     * @param capableActor a CapableActor
     * @param location location the Egg is at
     * @return true if the Egg can be eaten by the CapableActor, false otherwise.
     */
    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        boolean sameSpecies = false;
        if (capableActor instanceof DinoActor){
            sameSpecies = ((DinoActor)capableActor).getDinoType() == parent;
        }
        return capableActor.isCarnivorous() && !sameSpecies;
    }

    /**
     * Checks if it is time for the egg to hatch yet.
     * If it is time, the Egg object is removed from the location and
     * a DinoActor of appropriate species will be added to that location.
     * If it is not time yet, decrement waitTurn to indicate the remaining turns that
     * the Egg has to wait before hatching.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        DinoActor newDino;
        if (waitTurns == 0) {

            newDino = switch (parent) {
                case STEGOSAUR -> new Stegosaur(false);
                case BRACHIOSAUR -> new Brachiosaur(false);
                case ALLOSAUR -> new Allosaur(false);
                case PTERODACTYL -> new Pterodactyl(false);
            };

            EcoPoints.incrementEcoPoints((DINO_EGG_DICTIONARY.get(parent))[1]);
            currentLocation.removeItem(this);
            if (!currentLocation.containsAnActor()) {
                currentLocation.addActor(newDino);
            }
        }
        else {
            decrementWaitTurn();
        }
    }


}

