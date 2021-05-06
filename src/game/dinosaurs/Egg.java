package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.FoodType;
import game.PortableItem;

import java.util.Arrays;
import java.util.Map;
import static java.util.Map.entry;

/**
 * Represents an egg of a DinoActor.
 */
public class Egg extends PortableItem {

    /**
     * Stores constants representing the total number of turns to wait till hatching for each dinosaur.
     */
//    private final static Map<DinoEncyclopedia, Integer> DINO_EGG_DICTIONARY = Map.ofEntries(
//            entry(DinoEncyclopedia.STEGOSAUR, 30),
//            entry(DinoEncyclopedia.BRACHIOSAUR, 30),
//            entry(DinoEncyclopedia.ALLOSAUR, 50)
//    );
    private final static int[] STEGOVALUE = {3,100};
    private final static int[] BRACHIOVALUE = {2,1000};
    private final static int[] ALLOVALUE = {1,1000};

    private final static Map<DinoEncyclopedia, int[]> DINO_EGG_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR, STEGOVALUE),
            entry(DinoEncyclopedia.BRACHIOSAUR, BRACHIOVALUE),
            entry(DinoEncyclopedia.ALLOSAUR, ALLOVALUE)
    );

    /**
     * Turns left to wait till the egg hatches.
     */
    private int waitTurns;

    /**
     * Uniquely identifies the species of the Egg's parent,
     * baby dinosaur hatched out of the Egg will be of the same species
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
            if (!currentLocation.containsAnActor()){
                currentLocation.removeItem(this);
                currentLocation.addActor(newDino);
            }
        }
        else {
            decrementWaitTurn();
        }
    }


}

