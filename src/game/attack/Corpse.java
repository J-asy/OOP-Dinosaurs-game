package game.attack;

import edu.monash.fit2099.engine.Location;
import game.FoodType;
import game.PortableItem;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoEncyclopedia;
import java.util.Map;
import static java.util.Map.entry;

/**
 * A dead dinosaur
 */
public class Corpse extends PortableItem {

    // dictionary to refer to for the total number of turns to wait till corpse is removed from map
    private final static Map<DinoEncyclopedia, Integer> DINO_CORPSE_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR, 20),
            entry(DinoEncyclopedia.BRACHIOSAUR, 40),
            entry(DinoEncyclopedia.ALLOSAUR, 20)
    );

    /**
     * number of turns until corpse is removed from map
     */
    private int waitTurns;

    /**
     * Basic state of corpse's parent class instance
     */
    private final DinoEncyclopedia parent;

    /**
     * Constructor
     * @param newParent parent class of corpse
     */
    public Corpse(DinoEncyclopedia newParent) {
        super("dead",'%');
        parent = newParent;
        initializeWaitTurns(DINO_CORPSE_DICTIONARY.get(newParent));
        addCapability(FoodType.CARNIVORE);
    }

    /**
     * Initializes number of turns until corpse is removed from map
     * @param newWaitTurn
     */
    public void initializeWaitTurns(int newWaitTurn){
        if (newWaitTurn >= 0){
            waitTurns = newWaitTurn;
        }
    }

    /**
     * Decrements corpse's number of turns
     */
    public void decrementWaitTurn(){
        if (waitTurns > 0){
            waitTurns--;
        }
    }

    /**
     * Retrieves parent class character on the map
     * @return parent class character
     */
    public Character getParentChar() {
        return parent.getDisplayChar();
    }

    /**
     * Checks if wait turn is over, if yes, remove the corpse.Otherwise, decrement wait turn
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


}
