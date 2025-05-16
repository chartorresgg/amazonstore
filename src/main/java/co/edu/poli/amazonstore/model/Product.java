package co.edu.poli.amazonstore.model;

/**
 * Representa un producto que puede aceptar visitantes.
 * Implementa la interfaz ElementStore.
 * Contiene información sobre el nombre y el precio del producto.
 */
public class Product implements ElementStore {

    private String nameProduct;
    private double price;
    private double taxAmount = 0.0;

    public Product(String nameProduct, double price, double taxAmount) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.taxAmount = taxAmount;
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

    

public void setTaxAmount(double taxAmount) {
    this.taxAmount = taxAmount;
}

public double getTaxAmount() {
    return taxAmount;
}

public double getPriceWithTax() {
    return price + taxAmount;
}

    /**
     * Método accept que permite a un Visitor visitar este producto.
     * 
     * @param visitor El Visitor que visita este producto.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitProduct(this);
    }

    @Override
    public String toString() {
        return nameProduct + " - $" + price;

    }

}