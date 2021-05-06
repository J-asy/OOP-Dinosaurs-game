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
        if (target.isConscious() && target.canBeAttacked()){
            if (actor instanceof Player){
                return new AttackAction(target);
            }
            else if (actor instanceof Allosaur){
                Allosaur actorAsAllosaur = (Allosaur) actor;
                if (!actorAsAllosaur.hasAttackedVictim(target)) {
                    return new AttackAction(target);
                }
            }
        }
        return null;
    }

}

