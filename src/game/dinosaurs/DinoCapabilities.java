package game.dinosaurs;

/**
 * Enum class for common Capabilities that DinoActors may possess.
 */
public enum DinoCapabilities {
    MALE,
    FEMALE,
    PREGNANT,
    BUSH_DESTROYER, // destroys a bush when steps on it
    CAN_ATTACK,
    CAN_BE_ATTACKED,
    CAN_BREED,
    HERBIVORE,
    CARNIVORE,
    CONSCIOUS,
    CAN_REACH_TREE,
    CAN_TRAVERSE_WATER,
    ARBOREAL, // for DinoActors that live on trees, i.e. breeds and lays egg on trees
}
