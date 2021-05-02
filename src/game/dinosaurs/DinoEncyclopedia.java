package game.dinosaurs;

/**
 * Enum class used to store all constants of dinosaur attributes, mainly utilized by DinoActor.
 * The purpose of this class is to have a standardized set of values that can be accessed
 * by other classes we needed. This ensures a single point of change, making code more maintainable.
 */


public enum DinoEncyclopedia {
    STEGOSAUR('S', "Stegosaur", 50, 100, 90, 30, 50, 10, 20),
    BRACHIOSAUR('B', "Brachiosaur", 100, 160, 140, 50, 70, 30, 15),
    ALLOSAUR('A', "Allosaur", 20, 100, 90, 50, 50, 20, 20);

    char displayChar;
    String name;
    int initialHitPoints;
    int maxHitPoints;
    int hungryWhen;
    int matureWhen;
    int capableBreedingWhen;
    int pregnancyPeriod;
    int unconsciousPeriod;

    DinoEncyclopedia(char displayChar, String name, int initialHitPoints, int maxHitPoints, int hungryWhen,
                     int matureWhen, int capableBreedingWhen, int pregnancyPeriod, int unconsciousPeriod) {
        this.displayChar = displayChar;
        this.name = name;
        this.initialHitPoints = initialHitPoints;
        this.maxHitPoints = maxHitPoints;
        this.hungryWhen = hungryWhen;
        this.matureWhen = matureWhen;
        this.capableBreedingWhen = capableBreedingWhen;
        this.pregnancyPeriod = pregnancyPeriod;
        this.unconsciousPeriod = unconsciousPeriod;
    }

    public char getDisplayChar(){
        return displayChar;
    }

    public String getName(){
        return name;
    }

    public int getInitialHitPoints(){
        return initialHitPoints;
    }

    public int getMaxHitPoints(){
        return maxHitPoints;
    }

    public int getHungryWhen(){
        return hungryWhen;
    }

    public int getMatureWhen(){
        return matureWhen;
    }

    public int getCapableBreedingWhen(){
        return capableBreedingWhen;
    }

    public int getPregnancyPeriod(){
        return pregnancyPeriod;
    }

    public int getUnconsciousPeriod(){
        return unconsciousPeriod;
    }







}

