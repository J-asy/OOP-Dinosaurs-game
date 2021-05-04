package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.FoodType;
import game.PortableItem;

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

    private final static Map<DinoEncyclopedia, Integer> DINO_EGG_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR, 3),
            entry(DinoEncyclopedia.BRACHIOSAUR, 2),
            entry(DinoEncyclopedia.ALLOSAUR, 1)
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
        initializeWaitTurns(DINO_EGG_DICTIONARY.get(parent));
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
        System.out.println("Wait: " + waitTurns);
        DinoActor newDino;
        if (waitTurns == 0) {

            newDino = switch (parent) {
                case STEGOSAUR -> new Stegosaur(false);
                case BRACHIOSAUR -> new Brachiosaur(false);
                case ALLOSAUR -> new Allosaur(false);
            };

            currentLocation.removeItem(this);
            currentLocation.addActor(newDino);
        }
        else {
            decrementWaitTurn();
        }
    }


}
