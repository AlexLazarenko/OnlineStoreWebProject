package edu.epam.web.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class Order {
    private int id;
    private int userId;
    private BigDecimal price;
    private BigDecimal discount;
    private Date date;
    private OrderStatus status;
    private Map<Dish,Integer> dishMap;

    public Order(int id,int userId, BigDecimal price, BigDecimal discount, Date date, OrderStatus status, Map<Dish,Integer> dishMap) {
        this.id = id;
        this.userId=userId;
        this.price = price;
        this.discount=discount;
        this.date = date;
        this.status = status;
        this.dishMap = dishMap;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Map<Dish, Integer> getDishMap() {
        return dishMap;
    }

    public void setDishMap(Map<Dish, Integer> dishMap) {
        this.dishMap = dishMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userId != order.userId) return false;
        if (!price.equals(order.price)) return false;
        if (!discount.equals(order.discount)) return false;
        if (!date.equals(order.date)) return false;
        if (status != order.status) return false;
        return dishMap.equals(order.dishMap);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + price.hashCode();
        result = 31 * result + discount.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + dishMap.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", price=" + price +
                ", discount=" + discount +
                ", date=" + date +
                ", status=" + status +
                ", dishMap=" + dishMap +
                '}';
    }
}
