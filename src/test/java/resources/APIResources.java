package resources;

public enum APIResources {
    loginAPI("/api/ecom/auth/login"),
    addProductAPI("/api/ecom/product/add-product"),
    createOrderAPI("/api/ecom/order/create-order"),
    getOrderAPI("/api/ecom/order/get-orders-details"),
    deletePlaceAPI("/api/ecom/product/delete-product");

    private String resources;

    APIResources(String resources) {
        this.resources = resources;
    }

    public String getResources() {
        return resources;
    }
}
