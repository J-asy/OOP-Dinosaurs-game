package game.attack;

import edu.monash.fit2099.engine.Location;
import game.dinosaurs.FoodType;
import game.PortableItem;
import game.dinosaurs.DinoEncyclopedia;
import java.util.Map;
import static java.util.Map.entry;

public class Corpse extends PortableItem {

    // dictionary to refer to for the total number of turns to wait till corpse is removed from map
    private final static Map<Character, Integer> DINO_CORPSE_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR.getDisplayChar(), 20),
            entry(DinoEncyclopedia.BRACHIOSAUR.getDisplayChar(), 40),
            entry(DinoEncyclopedia.ALLOSAUR.getDisplayChar(), 20)
    );

    private int waitTurns;
    private char parent;

    public Corpse(char parent) {
        super("dead",'%');
        initializeWaitTurns(DINO_CORPSE_DICTIONARY.get(parent));
        setParent(parent);
        addCapability(FoodType.CARNIVORE);
    }

    private void setParent(char newParent){
        char dinoParent = Character.toUpperCase(newParent);
        for (DinoEncyclopedia d: DinoEncyclopedia.values()){
            if (d.getDisplayChar() == newParent){
                parent = dinoParent;
                break;
            }
        }
    }

    public Character getParent(){
        return parent;
    }

    public void initializeWaitTurns(int newWaitTurn){
        if (newWaitTurn >= 0){
            waitTurns = newWaitTurn;
        }
    }

    public void decrementWaitTurn(){
        if (waitTurns > 0){
            waitTurns--;
        };
    }

    public void tick(Location currentLocation) {
        PortableItem dinoCorpse;

        if (waitTurns == 0) {
            dinoCorpse = new PortableItem("dead",'%');
        }
        else {
            decrementWaitTurn();
        }
    }


}
