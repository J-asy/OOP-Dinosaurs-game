package game.attack;

import edu.monash.fit2099.engine.Location;
import game.FoodType;
import game.PortableItem;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoEncyclopedia;
import java.util.Map;
import static java.util.Map.entry;

public class Corpse extends PortableItem {

    // dictionary to refer to for the total number of turns to wait till corpse is removed from map
    private final static Map<DinoEncyclopedia, Integer> DINO_CORPSE_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR, 20),
            entry(DinoEncyclopedia.BRACHIOSAUR, 40),
            entry(DinoEncyclopedia.ALLOSAUR, 20)
    );

    private int waitTurns;
    private final DinoEncyclopedia parent;

    public Corpse(DinoEncyclopedia newParent) {
        super("dead",'%');
        parent = newParent;
        initializeWaitTurns(DINO_CORPSE_DICTIONARY.get(newParent));
        addCapability(FoodType.CARNIVORE);
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

    public Character getParentChar() {
        return parent.getDisplayChar();
    }

    public void tick(Location currentLocation) {
        if (waitTurns == 0) {
            currentLocation.removeItem(this);
        }
        else {
            decrementWaitTurn();
        }
    }


}
