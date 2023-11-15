package IMSProject.Database;

public class ProductInfo {

    private String supplierId;
    private String productId;
    private double quantity;
    private double salePrice;
    private double wholesale;
    private double location;

    public ProductInfo(String productId, String email, double salePrice, double wholesale, String supplierId, double location) {
        super();
        this.productId = productId;
        this.quantity = quantity; // Make sure to initialize quantity as needed
        this.salePrice = salePrice;
        this.wholesale = wholesale; // Correct assignment to the correct attribute
        this.supplierId = supplierId;
        this.location = location;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getWholesale() {
        return wholesale;
    }

    public void setWholesale(double wholesale) {
        this.wholesale = wholesale;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    // toString()
    @Override
    public String toString() {
        return "ProductInfo [ productId=" + productId + ", quantity="
                + quantity + ",wholesale=" + wholesale + " salePrice=" + salePrice + ", supplierId=" + supplierId + ", location=" + location + "]";
    }
}
