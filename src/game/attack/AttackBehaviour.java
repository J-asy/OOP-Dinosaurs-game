package game.attack;
import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.*;
import game.player.LaserGun;
import game.player.Player;
import edu.monash.fit2099.engine.WeaponItem;

import java.util.List;

public class AttackBehaviour implements Behaviour {

    DinoActor target;

    public AttackBehaviour (DinoActor target) {
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (map.getActorAt(destination) == target){
                if (target.canBeAttacked() && actor instanceof Player) {
                        return new AttackAction(target);
                }
                else if (target.canAttack() && actor instanceof Player) {
                    return new AttackAction(target);
                }
                else if (((DinoActor) actor).canAttack() && target.canBeAttacked()) {

                    if (!((Allosaur) actor).hasAttackedStegosaur((Stegosaur) target)) {

                        if (!((DinoActor) actor).isMatured()) {
                            return new AttackAction(target);
                        } else if (((DinoActor) actor).isMatured() || !target.isConscious()) {
                            return new AttackAction(target);
                        }
                    } else {
                        if (((Allosaur) actor).getAttackedPeriod((Stegosaur) target) > 0 &&
                                ((Allosaur) actor).getAttackedPeriod((Stegosaur) target) <= 20) {
                            ((Allosaur) actor).decrementAttackedPeriod((Stegosaur) target);
                        } else if (((Allosaur) actor).getAttackedPeriod((Stegosaur) target) == 0) {
                            ((Allosaur) actor).removeAttackedStego((Stegosaur) target);
                            return new AttackAction(target);
                        }

                    }
                }
            }
        }
        return null;
    }



}

