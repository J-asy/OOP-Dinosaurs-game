package game.dinosaurs;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.attack.AttackAction;
import game.breed.BreedingAction;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;

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