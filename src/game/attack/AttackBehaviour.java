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
                else if (actor instanceof Allosaur){
                    Allosaur actorAsAllosaur = (Allosaur) actor;
                    if (actorAsAllosaur.canAttack() && target.canBeAttacked()){
                        if ( !(actorAsAllosaur.hasAttackedVictim(target))) {
                            return new AttackAction(target);
                        }
                        else
                        {
                            if (actorAsAllosaur.getAttackedPeriod(target) > 0) {
                                actorAsAllosaur.decrementAttackedPeriod(target);
                                String message = String.format("%s already attacked %s. Wait for %d turns!",
                                        actorAsAllosaur, target, actorAsAllosaur.getAttackedPeriod(target));
                                System.out.println(message);
                            }
                            else if (actorAsAllosaur.getAttackedPeriod(target) == 0)
                            {
                                actorAsAllosaur.removeVictim(target);
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

