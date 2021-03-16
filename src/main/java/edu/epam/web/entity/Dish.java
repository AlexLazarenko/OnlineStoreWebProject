package edu.epam.web.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Dish {
    private List<Ingridient> ingridientList;
    private DishStatus status;
    private String id;
    private String name;
    private int size;
    private BigDecimal price;
    private String clientInfo;
    private String staffInfo;
    private byte[] dishImage;

    public Dish(List<Ingridient> ingridientList, DishStatus status, String id,
                String name, int size, BigDecimal price, String clientInfo,
                String staffInfo, byte[] dishImage) {
        this.ingridientList = ingridientList;
        this.status = status;
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.clientInfo = clientInfo;
        this.staffInfo = staffInfo;
        this.dishImage = dishImage;
    }

    public List<Ingridient> getIngridientList() {
        return ingridientList;
    }

    public void setIngridientList(List<Ingridient> ingridientList) {
        this.ingridientList = ingridientList;
    }

    public DishStatus getStatus() {
        return status;
    }

    public void setStatus(DishStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
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

    public byte[] getDishImage() {
        return dishImage;
    }

    public void setDishImage(byte[] dishImage) {
        this.dishImage = dishImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return size == dish.size &&
                ingridientList.equals(dish.ingridientList) &&
                status == dish.status &&
                id.equals(dish.id) &&
                name.equals(dish.name) &&
                price.equals(dish.price) &&
                clientInfo.equals(dish.clientInfo) &&
                staffInfo.equals(dish.staffInfo) &&
                Arrays.equals(dishImage, dish.dishImage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ingridientList, status, id, name, size, price, clientInfo, staffInfo);
        result = 31 * result + Arrays.hashCode(dishImage);
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "ingridientList=" + ingridientList +
                ", status=" + status +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", clientInfo='" + clientInfo + '\'' +
                ", staffInfo='" + staffInfo + '\'' +
                ", dishImage=" + Arrays.toString(dishImage) +
                '}';
    }
}
