package game.player;

public enum Catalog {
    FRUIT(30),
    VEGE_MEAL_KIT(100),
    MEAT_MEAL_KIT(500),
    STEGOSAUR_EGG(200),
    BRACHIOSAUR_EGG(500),
    ALLOSAUR_EGG(1000),
    PTERODACTYL_EGG(1000),
    LASER_GUN(500);

    final int PRICE;

    Catalog(int price){
        PRICE = price;
    }

    public int getPrice(){
        return PRICE;
    }




}
