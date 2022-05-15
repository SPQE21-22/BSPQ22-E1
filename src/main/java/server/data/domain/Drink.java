package server.data.domain;

public class Drink {

    private String name;
    private String type;
    private float price;

    public Drink(String name, String type, float price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Drink(){
        this.name = "";
        this.type = "";
        this.price = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

}
