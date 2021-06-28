package genesis.school.webapi.model;

public class BTCDto {
    private String base;
    private String target;
    private double price;

    public BTCDto(String base, String target, double price) {
        this.base = base;
        this.target = target;
        this.price = price;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
