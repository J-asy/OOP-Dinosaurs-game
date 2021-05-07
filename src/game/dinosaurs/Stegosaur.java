package game.dinosaurs;

/**
 * A herbivorous dinosaur that can eat fruits on the ground and on bushes,
 * and can be attacked by Allosaur.
 */
public class Stegosaur extends DinoActor {

    /**
     * Enum type that can be referenced for a lot of useful constants
     * that are values for Stegosaur attributes.
     */
    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.STEGOSAUR;

    /**
     * Id to append to a Stegosaur's name to be able to differentiate between
     * different Stegosaurs.
     */
    public static int nextId = 1;

    /**
     * Constructor.
     * @param sex DinoCapabilities.MALE is Stegosaur should be male,
     *            DinoCapabilities.FEMALE is Stegosaur should be female,
     * @param isMatured true if Stegosaur is grown up, false otherwise.
     */
    public Stegosaur(DinoCapabilities sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured, nextId);
        nextId += 1;
    }

    /**
     * Constructor.
     * @param isMatured true if Stegosaur is grown up, false otherwise.
     */
    public Stegosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured, nextId);
        nextId += 1;
    }

    /**
     * Initialize necessary capabilities of a Stegosaur.
     */
    @Override
    void initializeCapabilities(){
        super.initializeCapabilities();
        addCapability(DinoCapabilities.HERBIVORE);
        addCapability(DinoCapabilities.CAN_BE_ATTACKED);
    }

}

