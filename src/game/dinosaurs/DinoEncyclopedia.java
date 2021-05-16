package game.dinosaurs;

/**
 * Enum class used to store all constants for dinosaur attributes, mainly utilized by DinoActor.
 * The purpose of this class is to have a standardized set of values that can be accessed
 * by other classes we needed. This ensures a single point of change, making code more maintainable.
 */

public enum DinoEncyclopedia {

    STEGOSAUR('S', "Stegosaur", 50, 10, 100, 60,
            100, 90, 40,30, 50, 10,
            20, 100),

    BRACHIOSAUR('B', "Brachiosaur", 100, 10, 160, 60,
            200,140, 40,50, 70, 30,
            15, 160),

    ALLOSAUR('A', "Allosaur", 20, 20, 100, 60,
            100, 90, 40,50, 50, 20,
            20, 100),

    PTERODACTYL('P', "Pterodactyl", 50, 10, 100, 60,
            100, 90, 40,30, 50, 10,
            20, 10);

    final char DISPLAY_CHAR;
    final String NAME;
    final int INITIAL_HIT_POINTS;
    final int BABY_INITIAL_HIT_POINTS;
    final int MAX_HIT_POINTS;
    final int INITIAL_WATER_LEVEL;
    final int MAX_WATER_LEVEL;
    final int HUNGRY_WHEN;
    final int THIRSTY_WHEN;
    final int MATURE_WHEN;
    final int BREEDING_MIN_FOOD_LEVEL;
    final int PREGNANCY_PERIOD;
    final int UNCONSCIOUS_PERIOD;
    final int BITE_SIZE;

    DinoEncyclopedia(char displayChar, String name, int initialHitPoints, int babyInitialHitPoints, int maxHitPoints,
                     int initialWaterLevel, int maxWaterLevel, int hungryWhen, int thirstyWhen, int matureWhen,
                     int breedingMinFoodLevel, int pregnancyPeriod, int unconsciousPeriod, int biteSize) {
        DISPLAY_CHAR = displayChar;
        NAME = name;
        INITIAL_HIT_POINTS = initialHitPoints;
        BABY_INITIAL_HIT_POINTS = babyInitialHitPoints;
        MAX_HIT_POINTS = maxHitPoints;
        INITIAL_WATER_LEVEL = initialWaterLevel;
        MAX_WATER_LEVEL = maxWaterLevel;
        HUNGRY_WHEN = hungryWhen;
        THIRSTY_WHEN = thirstyWhen;
        MATURE_WHEN = matureWhen;
        BREEDING_MIN_FOOD_LEVEL = breedingMinFoodLevel;
        PREGNANCY_PERIOD = pregnancyPeriod;
        UNCONSCIOUS_PERIOD = unconsciousPeriod;
        BITE_SIZE = biteSize;
    }

    public char getDisplayChar(){
        return DISPLAY_CHAR;
    }

    public String getName(){
        return NAME;
    }

    public int getInitialHitPoints(){
        return INITIAL_HIT_POINTS;
    }

    public int getBabyInitialHitPoints() {
        return BABY_INITIAL_HIT_POINTS;
    }

    public int getMaxHitPoints(){
        return MAX_HIT_POINTS;
    }

    public int getInitialWaterLevel() {
        return INITIAL_WATER_LEVEL;
    }

    public int getMaxWaterLevel(){
        return MAX_WATER_LEVEL;
    }

    public int getHungryWhen(){
        return HUNGRY_WHEN;
    }

    public int getThirstyWhen(){
        return THIRSTY_WHEN;
    }

    public int getMatureWhen(){
        return MATURE_WHEN;
    }

    public int getBreedingMinFoodLevel(){
        return BREEDING_MIN_FOOD_LEVEL;
    }

    public int getPregnancyPeriod(){
        return PREGNANCY_PERIOD;
    }

    public int getInitialUnconsciousPeriod(){
        return UNCONSCIOUS_PERIOD;
    }

}

