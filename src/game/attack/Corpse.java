package game.attack;

import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.PortableItem;
import game.dinosaurs.DinoEncyclopedia;
import java.util.Map;
import static java.util.Map.entry;

/**
 * A dead dinosaur
 */
public class Corpse extends FoodItem {


    private final static int[] STEGOSAUR_VALUE = {20,50};


    private final static int[] BRACHIOSAUR_VALUE = {40,100};

    /**
     * first int number of turns to wait till corpse removed,
     * second int healPoints
     */
    private final static int[] ALLOSAUR_VALUE = {20,50};

    private final static int[] PTERODACTYL_VALUE = {20,30};

    /** dictionary to refer to for the total number of turns to wait till corpse is removed from map
     *
     */
    private final static Map<DinoEncyclopedia, int[]> DINO_CORPSE_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR, STEGOSAUR_VALUE),
            entry(DinoEncyclopedia.BRACHIOSAUR, BRACHIOSAUR_VALUE),
            entry(DinoEncyclopedia.ALLOSAUR, ALLOSAUR_VALUE),
            entry(DinoEncyclopedia.PTERODACTYL, PTERODACTYL_VALUE)
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
        super("Corpse",'%');
        parent = newParent;
        setForCarnivores();
        initializeWaitTurns(getInitialWaitTurns());
        setHealPoints(getInitialHealPoints());
    }

    /**
     * Initializes number of turns until corpse is removed from map
     * @param newWaitTurn new number of wait turns
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
    public DinoEncyclopedia getParent() {
        return parent;
    }

    private int getInitialWaitTurns(){
        return DINO_CORPSE_DICTIONARY.get(parent)[0];
    }

    private int getInitialHealPoints(){
        return DINO_CORPSE_DICTIONARY.get(parent)[1];
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
