package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import java.util.HashMap;

/**
 * An aggressive carnivorous dinosaur that can attack Stegosaur.
 */
public class Allosaur extends DinoActor {

    /**
     * Enum type that can be referenced for a lot of useful constants
     * that are values for Allosaur attributes.
     */
    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;

    /**
     * A hashmap to keep track of all DinoActors that the Allosaur has attacked
     * by the Allosaur before within 20 turns.
     */
    private HashMap<DinoActor,Integer> victims = new HashMap<>();

    /**
     * Id to append to a Allosaur's name to be able to differentiate between
     * different Allosaurs.
     */
    private static int nextId = 1;

    /**
     * Amount of damage that a baby Allosaur can cause by attacking.
     */
    private static final int BABY_ATTACK_DAMAGE = 10;

    /**
     * Amount of damage that a grown up Allosaur can cause by attacking.
     */
    private static final int ADULT_ATTACK_DAMAGE = 20;

    /**
     * Constructor.
     * @param sex DinoCapabilities.MALE is Allosaur should be male,
     *            DinoCapabilities.FEMALE is Allosaur should be female,
     * @param isMatured true if Allosaur is grown up, false otherwise.
     */
    public Allosaur(DinoCapabilities sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured, nextId);
        nextId += 1;
    }

    /**
     * Constructor.
     * @param isMatured true if Allosaur is grown up, false otherwise.
     */
    public Allosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured, nextId);
        nextId += 1;
    }

    /**
     * Initialize necessary capabilities of a Allosaur.
     */
    @Override
    void initializeCapabilities(){
        super.initializeCapabilities();
        addCapability(DinoCapabilities.CARNIVORE);
        addCapability(DinoCapabilities.CAN_ATTACK);
    }

    /**
     * Creates an IntrinsicWeapon for the Allosaur for attacking,
     * which is its jaw it uses to bite Stegosaur.
     * @return IntrinsicWeapon which amount of damage depends on whether
     * Allosaur has matured
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        int damage;
        if (isMatured()){
            damage = ADULT_ATTACK_DAMAGE;
        }
        else {
            damage = BABY_ATTACK_DAMAGE;
        }
        return new IntrinsicWeapon(damage, "bites");
    }

    /**
     * Add a DinoActor that was just attacked into the victims hashMap.
     * @param victim DinoActor that was attacked.
     */
    public void addAttackedVictims(DinoActor victim) {
        victims.put(victim, 20);
    }

    /**
     * Returns true if the Allosaur has attacked the same DinoActor within 20 turns, false otherwise.
     * @param victim DinoActor
     * @return true if the Allosaur has attacked the same DinoActor within 20 turns, false otherwise
     */
    public boolean hasAttackedVictim(DinoActor victim) {
        return victims.containsKey(victim);
    }

    /**
     * Decrements the attacked period of the victim
     * @param victim DinoActor that has been attacked by the Allosaur before within 20 turns
     */
    public void decrementAttackedPeriod(DinoActor victim) {
        victims.put(victim, victims.get(victim) - 1);
    }

    /**
     * Returns the number of turns that the Allosaur has to wait before it can
     * attack the victim again.
     * @param victim DinoActor that has been attacked by the Allosaur before within 20 turns
     * @return Number of turns that the Allosaur has to wait before it can
     * attack the victim again.
     */
    public int getAttackedPeriod(DinoActor victim){
        return victims.get(victim);
    }

    /**
     * Remove a DinoActor from the victims hashMap.
     * @param victim a DinoActor that can be attacked by Allosaur again now
     */
    public void removeVictim(DinoActor victim){
        victims.remove(victim);
    }

    /**
     * Updates the attacked period of the Allosaur's victims and removes them from
     * the hashMap if they can now be attacked again, then the super classes playTurn
     * method is called to select an appropriate Action the Allosaur should return for
     * that turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things
     *                   in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for (DinoActor victim : victims.keySet()) {
            if (getAttackedPeriod(victim) > 0) {
                decrementAttackedPeriod(victim);
            }
            else {
                removeVictim(victim);
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }

}
