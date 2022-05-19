package server.data.domain;

import java.util.ArrayList;

public class Menu {

    private ArrayList<Supply> starter;
    private ArrayList<Supply> main;
    private ArrayList<Supply> pastry;
    private ArrayList<Supply> drink;

    public Menu(ArrayList<Supply> starter, ArrayList<Supply> main, ArrayList<Supply> pastry, ArrayList<Supply> drink) {
        this.starter = starter;
        this.main = main;
        this.pastry = pastry;
        this.drink = drink;
    }
    public Menu() {
        this.starter = new ArrayList<Supply>();
        this.main = new ArrayList<Supply>();
        this.pastry = new ArrayList<Supply>();
        this.drink = new ArrayList<Supply>();
    }

    public ArrayList<Supply> getStarter() {
        return starter;
    }
    public void setStarter(ArrayList<Supply> starter) {
        this.starter = starter;
    }

    public ArrayList<Supply> getMain() {
        return main;
    }
    public void setMain(ArrayList<Supply> main) {
        this.main = main;
    }

    public ArrayList<Supply> getPastry() {
        return pastry;
    }
    public void setPastry(ArrayList<Supply> pastry) {
        this.pastry = pastry;
    }

    public ArrayList<Supply> getDrink() {
        return drink;
    }
    public void setDrink(ArrayList<Supply> drink) {
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
