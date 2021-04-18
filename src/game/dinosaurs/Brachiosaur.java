package game.dinosaurs;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
//import game.BreedingAction;
import game.DinoEncyclopedia;
import game.Sex;


public class Brachiosaur extends DinoActor {

    private static final DinoEncyclopedia dinoType = DinoEncyclopedia.BRACHIOSAUR;

    public Brachiosaur(Sex sex) {
        super(dinoType, sex);
    }

    public Brachiosaur() {
        super(dinoType);
    }

    @Override
    // no attack action because brachiosaur cannot be attacked
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions allowableActions = new Actions();
//        allowableActions.add(new BreedingAction(this));
        return allowableActions;
    }

}
