package game.dinosaurs;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.attack.AttackAction;
import game.breed.BreedingAction;

import java.util.HashMap;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;
    private HashMap<Stegosaur,Integer> attackedStegos = new HashMap<>();
    private static int nextId = 1;
    private static final int BABY_ATTACK_DAMAGE = 10;
    private static final int ADULT_ATTACK_DAMAGE = 20;


    public Allosaur(DinoCapabilities sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured, nextId);
        nextId += 1;
    }

    public Allosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured, nextId);
        nextId += 1;
    }

    @Override
      void initializeCapabilities(){
        super.initializeCapabilities();
        addCapability(DinoCapabilities.CARNIVORE);
        addCapability(DinoCapabilities.CAN_ATTACK);
    }

    public void addAttackedStego(Stegosaur stegosaur) {
        attackedStegos.put(stegosaur, 20);
    }

    public boolean hasAttackedStegosaur(Stegosaur stegosaur) {
        return attackedStegos.containsKey(stegosaur);
    }

    public void decrementAttackedPeriod(Stegosaur stegosaur) {
        attackedStegos.put(stegosaur, attackedStegos.get(stegosaur) - 1);
    }

    public int getAttackedPeriod(Stegosaur stegosaur){
        return attackedStegos.get(stegosaur);
    }

    public void removeAttackedStego(Stegosaur stegosaur){
        attackedStegos.remove(stegosaur);
    }

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
}
