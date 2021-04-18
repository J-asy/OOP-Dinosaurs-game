package game;

// enum class to store all values that don't need to be changed
// utilized by DinoActor and its child classes
// this way we dont need to have attributes to store these values in the actors - more clean and maintainable

public enum DinoEncyclopedia {
    STEGOSAUR('S', "Stegosaur", 50, 100, 90, 30, 50, 30, 20),
    BRACHIOSAUR('B', "Brachiosaur", 100, 160, 140, 50, 70, 50, 15),
    ALLOSAUR('A', "Allosaur", 20, 100, 90, 50, 50, 50, 20);

    char displayChar;
    String name;
    int initialHitPoints;
    int maxHitPoints;
    int hungryWhen;
    int matureWhen;
    int capableBreedingWhen;
    int pregnancyPeriod;
    int unconsciousPeriod;
    // others ?

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

