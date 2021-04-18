package game;

/**
 * Enum class to store displayChar and name of dinosaurs
 */
public enum DinoType {
    STEGOSAUR('S', "Stegosaur"),
    BRACHIOSAUR('B', "Brachiosaur"),
    ALLOSAUR('A', "Allosaur");

    char displayChar;
    String name;


    DinoType(char displayChar, String name) {
        this.displayChar = displayChar;
        this.name = name;
    }

    public char getDisplayChar(){
        return displayChar;
    }

    public String getName(){
        return name;
    }

}

