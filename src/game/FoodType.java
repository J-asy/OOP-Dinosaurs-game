package game;

/**
 * Enum class for different types of food.
 */
public enum FoodType {
    CARNIVORE,
    HERBIVORE,
    SMALL, // if food size small, can be eaten whole by all DinoActors
    BIG // if food size big, cannot be eaten whole by small DinoActors
}
