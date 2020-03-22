/**
 *
 *  @author Batorski Bartłomiej PD2654
 *
 */


package zad2;

import java.util.StringTokenizer;

public class Purchase {
    private double quantity;
    private double cost;
    private double price;
    private String id;
    private String lastName;
    private String fiestName;
    private String product;
    private String line;
    private String lineWithCost;

    public Purchase(String order) {
        StringTokenizer sToken = new StringTokenizer(order, ";");
        if(sToken.countTokens() !=5) {
            throw new IllegalArgumentException("Zle dane wejsciowe");
        }
        this.id = sToken.nextToken();
        String[] name = sToken.nextToken().split(" ");
        this.lastName = name[0];
        this.fiestName = name[1];
        this.product = sToken.nextToken();
        this.price = Double.parseDouble(sToken.nextToken());
        this.quantity = Double.parseDouble(sToken.nextToken());
        this.cost = this.quantity * this.price;
        this.line = order;
        lineWithCost = order + " (koszt: " +  cost + ")";
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFiestName() {
        return fiestName;
    }

    public String getProduct() {
        return product;
    }

    public String getLine() {
        return line;
    }

    public String getLineWithCost() {
        return lineWithCost;
    }
}
