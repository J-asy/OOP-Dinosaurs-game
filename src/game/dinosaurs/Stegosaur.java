package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.DinoActor;
import game.HerbivoreType;
import game.attack.AttackAction;
import game.FoodType;
import game.attack.Corpse;


/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends DinoActor {


    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.STEGOSAUR;

    public Stegosaur(Sex sex) {
        super(DINO_TYPE, sex);
        addCapability(FoodType.HERBIVORE);
        addCapability(HerbivoreType.SHORT_HERBIVORE);
    }

    public Stegosaur() {
        super(DINO_TYPE);
        addCapability(FoodType.HERBIVORE);
        addCapability(HerbivoreType.SHORT_HERBIVORE);
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
        if (!this.isConscious()) {
            if (getUnconsciousPeriod() > DinoEncyclopedia.STEGOSAUR.getUnconsciousPeriod()) {
                return null;
            } else if (getUnconsciousPeriod() == 0) {
                Location stegoLocation = map.locationOf(this);
                // Get rid of dino in this location
                Corpse corpseStego = new Corpse(DinoEncyclopedia.STEGOSAUR.getDisplayChar());
                stegoLocation.addItem(corpseStego);
            }
        }
        else {
            return super.playTurn(actions, lastAction, map, display);
        }
        return null;
    }

}

