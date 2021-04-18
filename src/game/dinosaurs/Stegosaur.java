package game.dinosaurs;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.AttackAction;
//import game.BreedingAction;
import game.DinoEncyclopedia;
import game.Sex;


/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends DinoActor {


    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.STEGOSAUR;

    public Stegosaur(Sex sex) {
        super(DINO_TYPE, sex);
    }

    public Stegosaur() {
        super(DINO_TYPE);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions allowableActions = new Actions();
        allowableActions.add(new AttackAction(this));
//        allowableActions.add(new BreedingAction(this));

        return allowableActions;
    }


}

