package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.attack.AttackAction;
import game.breed.BreedingAction;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;

    public Allosaur(Sex sex) {
        super(DINO_TYPE, sex);
    }

    public Allosaur() {
        super(DINO_TYPE);
    }

    @Override
    // allosaur can be attacked by player so add attack action
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions allowableActions = new Actions();
        allowableActions.add(new AttackAction(this));
        allowableActions.add(new BreedingAction(this));

        return allowableActions;
    }

    @Override
    public Action playTurn (Actions actions, Action lastAction, GameMap map, Display display) {
        if (getUnconsciousPeriod() > DinoEncyclopedia.ALLOSAUR.getUnconsciousPeriod()) {
            return super.playTurn(actions, lastAction, map, display);
        }
        return null;
    }
}

// sex?
// attack behaviour
// breeding behaviour
