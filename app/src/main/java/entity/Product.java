package entity;

/**
 * Created by jr on 8/30/14.
 */
public class Product {
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private UOM uom;

    public UOM getUom() {
        return uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }

    private String product_name;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private boolean is_light;

    public boolean isIs_light() {
        return is_light;
    }

    public void setIs_light(boolean is_light) {
        this.is_light = is_light;
    }



    //Currently independent entities will not included
}
