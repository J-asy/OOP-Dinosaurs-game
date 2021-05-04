package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.DinoActor;
import game.FoodType;
import game.HerbivoreType;
import game.attack.Corpse;


public class Brachiosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.BRACHIOSAUR;

    public Brachiosaur(Sex sex) {
        super(DINO_TYPE, sex);
        addCapability(FoodType.HERBIVORE);
        addCapability(HerbivoreType.TALL_HERBIVORE);
    }

    public Brachiosaur() {
        super(DINO_TYPE);
        addCapability(FoodType.HERBIVORE);
        addCapability(HerbivoreType.TALL_HERBIVORE);
    }

    @Override
    public Action playTurn (Actions actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            if (getUnconsciousPeriod() > DinoEncyclopedia.BRACHIOSAUR.getUnconsciousPeriod()) {
                return null;
            } else if (getUnconsciousPeriod() == 0) {
                Location brachioLocation = map.locationOf(this);
                // Get rid of dino in this location
                Corpse corpseBrachio = new Corpse(DinoEncyclopedia.BRACHIOSAUR.getDisplayChar());
                brachioLocation.addItem(corpseBrachio);
            }
        }
        else {
            return super.playTurn(actions, lastAction, map, display);
        }
        return null;
    }


    // don't need to override getAllowableActions, cuz can only breed - dealt with by DinoActor
    // don't need attack action, since brachiosaur cannot be attacked

}
