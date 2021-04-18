package game;

import edu.monash.fit2099.engine.Location;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Stegosaur;

import java.util.Map;
import static java.util.Map.entry;

// DONE
public class Egg extends PortableItem {

    // dictionary to refer to for the total number of turns to wait till hatch for each dinosaur
    private final static Map<Character, Integer> DINO_EGG_DICTIONARY = Map.ofEntries(
            entry(DinoEncyclopedia.STEGOSAUR.getDisplayChar(), 30),
            entry(DinoEncyclopedia.BRACHIOSAUR.getDisplayChar(), 30),
            entry(DinoEncyclopedia.ALLOSAUR.getDisplayChar(), 50)
    );

    private int waitTurns; // turns left to wait till egg hatches
    private char parent;  // displayChar of parent used to identify the species of egg

    public Egg(char parent){
        super("Egg", 'o');
        initializeWaitTurns(DINO_EGG_DICTIONARY.get(parent));
        setParent(parent);
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
        // check if supposed to hatch yet, if yes, baby dino born, baby dino added and egg removed from that location
        // otherwise decrement wait turn
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

            newDino.setChildDisplayCharacter();  // initialize character with lower case since default is upper case
            currentLocation.removeItem(this);
            currentLocation.addActor(newDino);
        }
        else {
            decrementWaitTurn();
        }
    }


}

