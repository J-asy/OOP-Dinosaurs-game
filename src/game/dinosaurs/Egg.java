package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.FoodType;
import game.PortableItem;

import java.util.Map;
import static java.util.Map.entry;

/**
 * Represents an egg of a DinoActor.
 */
public class Egg extends PortableItem {


    /**
     * An integer list which first value is the Stegosaur egg's number of turns to wait
     * till it hatches, and second value if the amount of EcoPoints awarded when it hatches.
     */
    private final static int[] STEGOSAUR_VALUE = {30,100};

    /**
     * An integer list which first value is the Brachiosaur egg's number of turns to wait
     * till it hatches, and second value if the amount of EcoPoints awarded when it hatches.
     */
    private final static int[] BRACHIOSAUR_VALUE = {30,1000};

    /**
     * An integer list which first value is the Allosaur egg's number of turns to wait
     * till it hatches, and second value if the amount of EcoPoints awarded when it hatches.
     */
    private final static int[] ALLOSAUR_VALUE = {50,1000};

    /**
     * Map that stores useful constants for the Egg object according to different dinosaur species.
     */
    private final static Map<DinoEncyclopedia, int[]> DINO_EGG_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR, STEGOSAUR_VALUE),
            entry(DinoEncyclopedia.BRACHIOSAUR, BRACHIOSAUR_VALUE),
            entry(DinoEncyclopedia.ALLOSAUR, ALLOSAUR_VALUE)
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
     * Constructor.
     * @param parent DinoEncyclopedia enum value of the parent dinosaur to identify its species
     */
    public Egg(DinoEncyclopedia parent){
        super("Egg", 'o');
        initializeWaitTurns((DINO_EGG_DICTIONARY.get(parent))[0]);
        setParent(parent);
        addCapability(FoodType.CARNIVORE);
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

