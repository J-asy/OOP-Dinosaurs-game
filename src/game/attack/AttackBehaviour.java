package game.attack;
import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.*;
import game.player.Player;

/**
 * Simulates attack behaviour of actors (Player/Allosaur) towards target (Stegosaur)- may or may not occur
 * depending on whether certain criteria is met.
 */
public class AttackBehaviour implements Behaviour {

    /**
     * DinoActor that is to be attacked.
     */
    DinoActor target;

    /**
     * Constructor
     * @param target the DinoActor to attack
     */
    public AttackBehaviour (DinoActor target) {
        this.target = target;
    }

    /**
     * Returns AttackAction if the actor is allowed to attack the target by checking
     * necessary conditions, otherwise null is returned.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return AttackAction if actor can attack target, otherwise null is returned
     */
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
                else {
                    String message = String.format("%s already attacked %s. Wait for %d turns!",
                            actor, target, actorAsAllosaur.getAttackedPeriod(target));
                    System.out.println(message);
                }
            }
        }
        return null;
    }

}

