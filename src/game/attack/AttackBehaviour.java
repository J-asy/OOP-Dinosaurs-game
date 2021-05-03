package game.attack;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.FoodType;
import game.dinosaurs.*;
import game.Player;

public class AttackBehaviour implements Behaviour {

    DinoActor target;

    public AttackBehaviour (DinoActor target) {
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {


            if (target.canBeAttacked() && actor instanceof Player) {
                return new AttackAction(target);
            }
            else if (target.isCarnivorous() && actor instanceof Player) {
                return new AttackAction(target);
            }
            else if (((DinoActor) actor).isCarnivorous() && target.canBeAttacked()) {
                if (!((Allosaur)actor).hasAttackedStegosaur((Stegosaur)target, map)){

                    if (!((DinoActor) actor).isMatured()) {
                        return new AttackAction(target);
                    } else if (((DinoActor) actor).isMatured() || !target.isConscious()) {
                        return new AttackAction(target);
                    }
                }
                else{
                    if ( ((Allosaur) actor).getAttackedPeriod((Stegosaur) target) > 0 && ((Allosaur) actor).getAttackedPeriod((Stegosaur) target) <= 20 ) {
                        ((Allosaur) actor).decrementAttackedPeriod((Stegosaur) target);
                    }
                    else if (((Allosaur) actor).getAttackedPeriod((Stegosaur) target) == 0){
                        ((Allosaur)actor).removeAttackedStego((Stegosaur)target);
                        return new AttackAction(target);
                    }

                }
            }

        return null;
    }



}

