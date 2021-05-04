package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.DinoActor;
import game.FoodType;
import game.attack.AttackAction;
import game.breed.BreedingAction;
import game.attack.Corpse;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;

    public Allosaur(Sex sex) {
        super(DINO_TYPE, sex);
        addCapability(FoodType.CARNIVORE);
    }

    public Allosaur() {
        super(DINO_TYPE);
        addCapability(FoodType.CARNIVORE);
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
        if (!this.isConscious()) {
            if (getUnconsciousPeriod() > DinoEncyclopedia.ALLOSAUR.getUnconsciousPeriod()) {
                return null;
            } else if (getUnconsciousPeriod() == 0) {
                Location alloLocation = map.locationOf(this);
                // Get rid of dino in this location
                Corpse corpseAllo = new Corpse(DinoEncyclopedia.ALLOSAUR.getDisplayChar());
                alloLocation.addItem(corpseAllo);
            }
        }
        else {
            return super.playTurn(actions, lastAction, map, display);
        }
        return null;
    }
}

// sex?
// attack behaviour
// breeding behaviour
