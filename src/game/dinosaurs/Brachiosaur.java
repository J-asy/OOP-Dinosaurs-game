package game.dinosaurs;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.FoodType;


public class Brachiosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.BRACHIOSAUR;

    public Brachiosaur(Sex sex) {
        super(DINO_TYPE, sex);
        hasCapability(FoodType.HERBIVORE);
    }

    public Brachiosaur() {
        super(DINO_TYPE);
        hasCapability(FoodType.HERBIVORE);
    }

    @Override
    public Action playTurn (Actions actions, Action lastAction, GameMap map, Display display) {
        if (getUnconsciousPeriod() > DinoEncyclopedia.BRACHIOSAUR.getUnconsciousPeriod()) {
            return super.playTurn(actions, lastAction, map, display);
        }
        return null;

    }


    // don't need to override getAllowableActions, cuz can only breed - dealt with by DinoActor
    // don't need attack action, since brachiosaur cannot be attacked

}
