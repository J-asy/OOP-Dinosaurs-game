package game.attack;
import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.*;
import game.player.Player;

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
            if (map.getActorAt(destination) == target && target.isConscious()){
                if (actor instanceof Player && target.canBeAttacked()){
                    return new AttackAction(target);
                }
                else if (actor instanceof DinoActor){
                    DinoActor actorAsDino = (DinoActor) actor;
                    if (actorAsDino.canAttack() && target.canBeAttacked()){
                        if ( !((Allosaur) actor).hasAttackedVictim(target)) {
                            ((Allosaur) actor).addAttackedStego(target);
                            return new AttackAction(target);
                        }
                        else
                        {
                            if ((((Allosaur) actor).getAttackedPeriod(target)) > 0
                                    && (((Allosaur) actor).getAttackedPeriod(target) <= 20))
                            {
                                ((Allosaur) actor).decrementAttackedPeriod(target);
                                System.out.println("Allosaur already attacked Stegosaur. Wait for " +
                                        ((Allosaur) actor).getAttackedPeriod(target)+" turns!");
                            }
                            else if (((Allosaur) actor).getAttackedPeriod(target) == 0)
                            {
                                ((Allosaur) actor).removeVictim(target);
                                return new AttackAction(target);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }



}

