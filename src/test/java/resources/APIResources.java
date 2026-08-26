package resources;

public enum APIResources {
    loginAPI("/api/ecom/auth/login"),
    addProductAPI("/api/ecom/product/add-product"),
    createOrderAPI("/api/ecom/order/create-order");

    private String resources;

    APIResources(String resources) {
        this.resources = resources;
    }

    public String getResources() {
        return resources;
    }
}
