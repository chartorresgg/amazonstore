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

    /**
     * Constructor de la clase Product.
     * 
     * @param nameProduct El nombre del producto.
     * @param price El precio del producto.
     * @param taxAmount El monto del impuesto aplicado al producto.
     */
    public Product(String nameProduct, double price, double taxAmount) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.taxAmount = taxAmount;
    }

    //============ Visitor Pattern ==============
    /**
     * Método accept que permite a un Visitor visitar este producto.
     * 
     * @param visitor El Visitor que visita este producto.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitProduct(this);
    }

    /**
     * Método para calcular el precio total del producto con impuestos.
     * 
     * @return El precio total del producto con impuestos.
     */
    public double getPriceWithTax() {
        return price + taxAmount;
    }

    // =========== Getters and Setters ==============
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

    public double getTaxAmount() {
    return taxAmount;
}

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public String toString() {
        return nameProduct + " - $" + price;

    }
    

}