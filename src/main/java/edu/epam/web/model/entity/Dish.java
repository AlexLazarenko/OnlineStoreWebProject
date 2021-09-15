package edu.epam.web.model.entity;

import java.math.BigDecimal;
import java.util.List;

public class Dish {
    private List<Ingredient> ingredientList;
    private DishStatus status;
    private int id;
    private String name;
    private String size;
    private BigDecimal price;
    private String clientInfo;
    private String staffInfo;
    private String dishImage;

    public Dish( int id, String name, String size, BigDecimal price, String clientInfo,
                String staffInfo, String dishImage, DishStatus status, List<Ingredient> ingredientList ) {
        this.ingredientList = ingredientList;
        this.status = status;
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.clientInfo = clientInfo;
        this.staffInfo = staffInfo;
        this.dishImage = dishImage;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public DishStatus getStatus() {
        return status;
    }

    public void setStatus(DishStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(String staffInfo) {
        this.staffInfo = staffInfo;
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (!ingredientList.equals(dish.ingredientList)) return false;
        if (status != dish.status) return false;
        if (!name.equals(dish.name)) return false;
        if (!size.equals(dish.size)) return false;
        if (!price.equals(dish.price)) return false;
        if (!clientInfo.equals(dish.clientInfo)) return false;
        if (!staffInfo.equals(dish.staffInfo)) return false;
        return dishImage != null ? dishImage.equals(dish.dishImage) : dish.dishImage == null;
    }

    @Override
    public int hashCode() {
        int result = ingredientList.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + id;
        result = 31 * result + name.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + clientInfo.hashCode();
        result = 31 * result + staffInfo.hashCode();
        result = 31 * result + (dishImage != null ? dishImage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "ingredientList=" + ingredientList +
                ", status=" + status +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", clientInfo='" + clientInfo + '\'' +
                ", staffInfo='" + staffInfo + '\'' +
                ", dishImage='" + dishImage + '\'' +
                '}';
    }
}
