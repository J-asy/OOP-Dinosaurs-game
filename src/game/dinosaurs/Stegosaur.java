package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.attack.AttackAction;
import game.BreedingAction;
import game.FoodType;


/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends DinoActor {


    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.STEGOSAUR;

    public Stegosaur(Sex sex) {
        super(DINO_TYPE, sex);
        hasCapability(FoodType.HERBIVORE);
    }

    public Stegosaur() {
        super(DINO_TYPE);
        hasCapability(FoodType.HERBIVORE);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions allowableActions = new Actions();
        allowableActions.add(new AttackAction(this));
        allowableActions.add(new BreedingAction(this));

        return allowableActions;
    }

    @Override
    public Action playTurn (Actions actions, Action lastAction, GameMap map, Display display) {
        if (getUnconsciousPeriod() > DinoEncyclopedia.STEGOSAUR.getUnconsciousPeriod()) {
            return super.playTurn(actions, lastAction, map, display);
        }
        return null;

    }

}

