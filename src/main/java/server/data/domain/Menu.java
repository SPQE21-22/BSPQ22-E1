package server.data.domain;

public class Menu {

    private Food starter;
    private Food main;
    private Food pastry;
    private Drink drink;

    public Menu(Food starter, Food main, Food pastry, Drink drink) {
        this.starter = starter;
        this.main = main;
        this.pastry = pastry;
        this.drink = drink;
    }
    public Menu() {
        this.starter = new Food();
        this.main = new Food();
        this.pastry = new Food();
        this.drink = new Drink();
    }

    public Food getStarter() {
        return starter;
    }
    public void setStarter(Food starter) {
        this.starter = starter;
    }

    public Food getMain() {
        return main;
    }
    public void setMain(Food main) {
        this.main = main;
    }

    public Food getPastry() {
        return pastry;
    }
    public void setPastry(Food pastry) {
        this.pastry = pastry;
    }

    public Drink getDrink() {
        return drink;
    }
    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "starter=" + starter +
                ", main=" + main +
                ", pastry=" + pastry +
                ", drink=" + drink +
                '}';
    }
}
