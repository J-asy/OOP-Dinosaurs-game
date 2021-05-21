package game.pregnancy;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.movement.FollowBirthingSpotBehaviour;

/**
 * Simulates the behaviour of a pregnant Actor.
 */
public class PregnancyBehaviour implements Behaviour {

    /**
     * Determines whether it is time for a pregnant DinoActor to lay an egg yet.
     * If yes and the DinoActor is on ground that it can lay an egg on, LayEggAction is returned.
     * If yes but the DinoActor is on ground that it cannot lay an egg on,
     * a DynamicMoveAction is returned for the DinoActor to find a spot to lay its egg.
     * If it is not time to lay an egg yet, the pregnancy period is updated.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return LayEggAction if it is time for a pregnant actor to lay an egg and it can do so on the ground it is on,
     * DynamicMoveAction if it is time for a pregnant actor to lay an egg and it cannot do so on the ground it is on,
     * null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor instanceof DinoActor){
            DinoActor dinoActor = (DinoActor) actor;

            if (dinoActor.isPregnant()){
                if (dinoActor.getPregnancyPeriod() > 0){
                    dinoActor.decrementPregnancyPeriod();
                }
                else {
                    Ground ground = map.locationOf(actor).getGround();
                    if (ground.canLayEggHere(dinoActor)) {
                        dinoActor.setPregnant(false);
                        return new LayEggAction();
                    }
                    return new FollowBirthingSpotBehaviour().getAction(actor, map);
                }
            }
        }
        return null;
    }

}
