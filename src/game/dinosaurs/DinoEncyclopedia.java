package game.dinosaurs;

/**
 * Enum class used to store all constants for dinosaur attributes, mainly utilized by DinoActor.
 * The purpose of this class is to have a standardized set of values that can be accessed
 * by other classes we needed. This ensures a single point of change, making code more maintainable.
 */

public enum DinoEncyclopedia {

    STEGOSAUR('S', "Stegosaur", 50, 10, 100, 60,
            100, 90, 40,30, 50, 10,
            20, 100, 30),

    BRACHIOSAUR('B', "Brachiosaur", 100, 10, 160, 60,
            200,140, 40,50, 70, 30,
            15, 160,  80),

    ALLOSAUR('A', "Allosaur", 20, 20, 100, 60,
            100, 90, 40,50, 50, 20,
            20, 100, 30),

    PTERODACTYL('P', "Pterodactyl", 50, 10, 100, 60,
            100, 90, 40,30, 50, 10,
            20, 10, 30);

    /**
     * Display character of the dinosaur shown on the terminal to represent it.
     */
    final char DISPLAY_CHAR;

    /**
     * Name of the dinosaur species.
     */
    final String NAME;

    /**
     * Initial food level / health level of an adult dinosaur.
     */
    final int INITIAL_HIT_POINTS;

    /**
     * Initial food level / health level of a baby dinosaur.
     */
    final int BABY_INITIAL_HIT_POINTS;

    /**
     * Maximum food level / health level.
     */
    final int MAX_HIT_POINTS;

    /**
     * Initial water level.
     */
    final int INITIAL_WATER_LEVEL;

    /**
     * Maximum water level.
     */
    final int MAX_WATER_LEVEL;

    /**
     * Threshold food level that dinosaur starts to get hungry when its food level is below it.
     */
    final int HUNGRY_WHEN;

    /**
     * Threshold water level that dinosaur starts to get thirsty when its water level is below it.
     */
    final int THIRSTY_WHEN;

    /**
     *  Age that the dinosaur will become an adult.
     */
    final int MATURE_WHEN;

    /**
     * Minimum food level for dinosaur to be able to breed.
     */
    final int BREEDING_MIN_FOOD_LEVEL;

    /**
     * Initial pregnancy period of pregnant DinoActors.
     */
    final int PREGNANCY_PERIOD;

    /**
     * Maximum number of turns that the DinoActor can stay unconscious till it dies.
     */
    final int UNCONSCIOUS_PERIOD;

    /**
     * The size of its jaw, which determines how fast it can finish eating a food and
     * the number of food points it gains with one bite.
     */
    final int BITE_SIZE;

    /**
     * The capacity of its mouth, which determines how fast it can drink water and
     * the number of water points it gains with one sip of water.
     */
    final int GULP_SIZE;

    DinoEncyclopedia(char displayChar, String name, int initialHitPoints, int babyInitialHitPoints, int maxHitPoints,
                     int initialWaterLevel, int maxWaterLevel, int hungryWhen, int thirstyWhen, int matureWhen,
                     int breedingMinFoodLevel, int pregnancyPeriod, int unconsciousPeriod, int biteSize, int gulpSize) {
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
        GULP_SIZE = gulpSize;
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

    public int getGulpSize() {
        return GULP_SIZE;
    }

}

