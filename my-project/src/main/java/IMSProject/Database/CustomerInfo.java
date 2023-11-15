package IMSProject.Database;

import java.util.Date;

public class CustomerInfo {
    private String productId;
    private double quantity;
    private double wholesale;
    private double salePrice;
    private String supplierId;

    public CustomerInfo(String productId, double quantity, double wholesale, double salePrice, String supplierId) {
        this.productId = productId;
        this.quantity = quantity;
        this.wholesale = wholesale;
        this.salePrice = salePrice;
        this.supplierId = supplierId;
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

    public double getWholesale() {
        return wholesale;
    }

    public void setWholesale(double wholesale) {
        this.wholesale = wholesale;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    // toString()
    @Override
    public String toString() {
        return "CustomerInfo [productId=" + productId + ", quantity=" + quantity + ", wholesale=" + wholesale
                + ", salePrice=" + salePrice + ", supplierId=" + supplierId + "]";
    }
}
