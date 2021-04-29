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
     * Stores the total number of turns to wait till hatching for each dinosaur.
     */
    private final static Map<Character, Integer> DINO_EGG_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR.getDisplayChar(), 30),
            entry(DinoEncyclopedia.BRACHIOSAUR.getDisplayChar(), 30),
            entry(DinoEncyclopedia.ALLOSAUR.getDisplayChar(), 50)
    );

    /**
     * Turns left to wait till the egg hatches.
     */
    private int waitTurns;

    /**
     * Display character of the egg's parent, which will be used to identify the species
     * of the dinosaur to be hatched
     */
    private char parent;

    /**
     * Constructor.
     * @param parent display character of the parent dinosaur of the egg
     */
    public Egg(char parent){
        super("Egg", 'o');
        initializeWaitTurns(DINO_EGG_DICTIONARY.get(parent));
        setParent(parent);
        addCapability(FoodType.CARNIVORE);
    }

    /**
     * Checks if the character argument passed in is appropriate by checking
     * against the DINO_EGG_DICTIONARY. If it is appropriate, sets the value of
     * parent to the argument.
     *
     * @param newParent display character of the parent dinosaur of the egg
     */
    private void setParent(char newParent){
        char dinoParent = Character.toUpperCase(newParent);
        for (DinoEncyclopedia d: DinoEncyclopedia.values()){
            if (d.getDisplayChar() == newParent){
                parent = dinoParent;
                break;
            }
        }
    }

    public void initializeWaitTurns(int newWaitTurn){
        if (newWaitTurn >= 0){
            waitTurns = newWaitTurn;
        }
    }

    public void decrementWaitTurn(){
        if (waitTurns > 0){
            waitTurns--;
        }
    }

    /**
     * Checks if it is time for the egg to hatch yet. If it is time,
     * the Egg object is removed from the location and a DinoActor of appropriate species
     * (determined by the display character of the parent), will be added to that location.
     * If it is not time yet, decrement waitTurn to indicate the remaining turns that
     * the Egg has to wait before hatching.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        DinoActor newDino;
        if (waitTurns == 0) {

            if (parent == DinoEncyclopedia.STEGOSAUR.getDisplayChar()) {
                newDino = new Stegosaur();
            } else if (parent == DinoEncyclopedia.BRACHIOSAUR.getDisplayChar()){
                newDino = new Brachiosaur();
            }
            else if (parent == DinoEncyclopedia.ALLOSAUR.getDisplayChar()) {
                newDino = new Allosaur();
            }
            else {
                throw new IllegalStateException("Unexpected value: " + parent);
            }

            // Dinosaurs which are not grown up yet are indicated with a lowercase display character
            // This function call simply sets the new dinosaur's display character to lowercase.
            newDino.setChildDisplayCharacter();

            currentLocation.removeItem(this);
            currentLocation.addActor(newDino);
        }
        else {
            decrementWaitTurn();
        }
    }


}

