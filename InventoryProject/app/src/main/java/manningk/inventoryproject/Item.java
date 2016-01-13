package manningk.inventoryproject;

/**
 * This Class represents one item
 *
 * @author Kevin Manning
 * @version 1.12.2016
 *
 */
public class Item
{
    private String type;
    private int quantity;
    private double cost;


    public Item()
    {


    }

    public Item(String type, double cost, int quantity) {
        this.type = type;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Item getTestItem()
    {
        Item i = new Item("Widget123", 12.95, 123);
        return i;

    }
}
