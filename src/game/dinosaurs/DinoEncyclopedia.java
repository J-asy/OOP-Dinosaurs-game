package game.dinosaurs;

/**
 * Enum class used to store all constants of dinosaur attributes, mainly utilized by DinoActor.
 * The purpose of this class is to have a standardized set of values that can be accessed
 * by other classes we needed. This ensures a single point of change, making code more maintainable.
 */


public enum DinoEncyclopedia {
    STEGOSAUR('S', "Stegosaur", 39, 10,100,  48, 5, 40, 10, 5),
    BRACHIOSAUR('B', "Brachiosaur", 39, 10,160, 10, 8, 40, 11, 4),
    ALLOSAUR('A', "Allosaur", 40, 20,100, 38, 10, 40, 12, 3);

    //    STEGOSAUR('S', "Stegosaur", 50, 10, 100, 90, 30, 50, 10, 20);
    //    BRACHIOSAUR('B', "Brachiosaur", 100, 10, 160, 140, 50, 70, 30, 15),
//    ALLOSAUR('A', "Allosaur", 20, 20, 100, 90, 50, 50, 20, 20);

    final char DISPLAY_CHAR;
    final String NAME;
    final int INITIAL_HIT_POINTS;
    final int BABY_INITIAL_HIT_POINTS;
    final int MAX_HIT_POINTS;
    final int HUNGRY_WHEN;
    final int MATURE_WHEN;
    final int BREEDING_MIN_FOOD_LEVEL;
    final int PREGNANCY_PERIOD;
    final int UNCONSCIOUS_PERIOD;

    DinoEncyclopedia(char displayChar, String name, int initialHitPoints, int babyInitialHitPoints, int maxHitPoints,
                     int hungryWhen, int matureWhen, int breedingMinFoodLevel, int pregnancyPeriod, int unconsciousPeriod) {
        DISPLAY_CHAR = displayChar;
        NAME = name;
        INITIAL_HIT_POINTS = initialHitPoints;
        BABY_INITIAL_HIT_POINTS = babyInitialHitPoints;
        MAX_HIT_POINTS = maxHitPoints;
        HUNGRY_WHEN = hungryWhen;
        MATURE_WHEN = matureWhen;
        BREEDING_MIN_FOOD_LEVEL = breedingMinFoodLevel;
        PREGNANCY_PERIOD = pregnancyPeriod;
        UNCONSCIOUS_PERIOD = unconsciousPeriod;
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

    public int getMaxHitPoints(){
        return MAX_HIT_POINTS;
    }

    public int getHungryWhen(){
        return HUNGRY_WHEN;
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

    public int getBabyInitialHitPoints() {
        return BABY_INITIAL_HIT_POINTS;
    }

}

