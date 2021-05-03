package game.dinosaurs;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.attack.AttackAction;
import game.breed.BreedingAction;

import java.util.HashMap;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;
    private HashMap<Stegosaur,Integer> attackedStegos = new HashMap<>();

    public Allosaur(Sex sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured);
        initializeCapabilities();
    }

    public Allosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured);
        initializeCapabilities();
    }

    public void initializeCapabilities(){
        addCapability(DinoCapabilities.CARNIVORE);
    }


    public boolean hasAttackedStegosaur(Stegosaur stegosaur, GameMap map) {
        if (attackedStegos.containsKey(stegosaur) ) {
            return true;
        }
        else {
            return false;
        }
    }

    public void decrementAttackedPeriod(Stegosaur stegosaur) {
        attackedStegos.put(stegosaur, attackedStegos.get(stegosaur) - 1);
    }
//    @Override
    // allosaur can be attacked by player so add attack action
//    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//        Actions allowableActions = new Actions();
//        allowableActions.add(new AttackAction(this));
//        allowableActions.add(new BreedingAction(this));
//
//        return allowableActions;
//    }


}
