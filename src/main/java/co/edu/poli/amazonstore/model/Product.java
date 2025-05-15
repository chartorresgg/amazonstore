package co.edu.poli.amazonstore.model;

public class Product implements ElementStore {

    private String nameProduct;
    private double price;

    public Product(String nameProduct, double price) {
        this.nameProduct = nameProduct;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitProduct(this);
    }

    @Override
    public String toString() {
        return nameProduct + " - $" + price;

    }

}