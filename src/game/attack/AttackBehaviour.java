package game.attack;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.FoodType;
import game.HerbivoreType;
import game.Player;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Stegosaur;

public class AttackBehaviour implements Behaviour {

    DinoActor target;

    public AttackBehaviour (DinoActor target) {
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {


            if (target.hasCapability(HerbivoreType.SHORT_HERBIVORE) && actor instanceof Player) {
                return new AttackAction(target);
            }
            else if (target.hasCapability(FoodType.CARNIVORE) && actor instanceof Player) {
                return new AttackAction(target);
            }
            else if (actor.hasCapability(FoodType.CARNIVORE) && target.hasCapability(HerbivoreType.SHORT_HERBIVORE)) {
                if ( !((DinoActor) actor).isMatured() ) {
                    return new AttackAction(target);
                }
                else if ( ((DinoActor) actor).isMatured() || !target.isConscious()){
                    return new AttackAction(target);
                }
            }

        return null;
    }
}

