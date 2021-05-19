package game.movement;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;

public class FindBirthingSpotBehaviour extends FindBehaviour {

    private static final String DESCRIPTION = "find spot to lay egg";

    private static final int MIN_RADIUS = 1;

    private static final int MAX_RADIUS = 10;

    /**
     * Constructor.
     */
    public FindBirthingSpotBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    @Override
    boolean motivatedToFind(DinoActor dinoActor) {
        return dinoActor.isPregnant();
    }

    @Override
    Location findTarget(GameMap map, Location destination, DinoActor dinoActor) {
        if (destination.getGround().canBreedHere(dinoActor)){
            return destination;
        }
        return null;
    }
}
