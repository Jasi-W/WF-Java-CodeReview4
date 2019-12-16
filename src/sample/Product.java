package sample;

public class Product {
    String productName;
    String quantity;
    double oldPrice;
    double newPrice;
    String image;
    String description;

    public Product(String productName, String quantity, double oldPrice, double newPrice, String image, String description){
        this.productName = productName;
        this.quantity = quantity;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.image = image;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return "Product Name: " +productName + "\nQuantity: " +quantity+ "\nold price: " +oldPrice+ "\nnew price: " +newPrice+ "\n";
    }
}
