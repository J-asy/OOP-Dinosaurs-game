package game.dinosaurs;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.AttackAction;
//import game.BreedingAction;
import game.DinoEncyclopedia;
import game.Sex;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia dinoType = DinoEncyclopedia.ALLOSAUR;

    public Allosaur(Sex sex) {
        super(dinoType, sex);
    }

    public Allosaur() {
        super(dinoType);
    }

    @Override
    // allosaur can be attacked by player so add attack action
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions allowableActions = new Actions();
        allowableActions.add(new AttackAction(this));
//        allowableActions.add(new BreedingAction(this));

        return allowableActions;
    }


}
