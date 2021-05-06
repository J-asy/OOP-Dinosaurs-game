package game.dinosaurs;

import edu.monash.fit2099.engine.IntrinsicWeapon;

import java.util.HashMap;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;
    private HashMap<DinoActor,Integer> victims = new HashMap<>();
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

    public void addAttackedStego(DinoActor victim) {
        victims.put(victim, 20);
    }

    public boolean hasAttackedVictim(DinoActor victim) {
        return victims.containsKey(victim);
    }

    public void decrementAttackedPeriod(DinoActor victim) {
        victims.put(victim, victims.get(victim) - 1);
    }

    public int getAttackedPeriod(DinoActor victim){
        return victims.get(victim);
    }

    public void removeVictim(DinoActor victim){
        victims.remove(victim);
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
