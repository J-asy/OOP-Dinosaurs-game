package game.dinosaurs;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.attack.AttackAction;
import game.BreedingAction;
import game.FoodType;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;

    public Allosaur(Sex sex) {
        super(DINO_TYPE, sex);
        hasCapability(FoodType.CARNIVORE);
    }

    public Allosaur() {
        super(DINO_TYPE);
        hasCapability(FoodType.CARNIVORE);
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
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "punches");
    }

}
