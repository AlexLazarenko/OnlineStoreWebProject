package edu.epam.web.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Order {
    private String id;
    private BigDecimal price;
    private Date date;
    private OrderStatus status;
    private Map<Dish,Integer> dishMap;

    public Order(String id, BigDecimal price, Date date, OrderStatus status, Map<Dish,Integer> dishMap) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.status = status;
        this.dishMap = dishMap;
    }

    public Order() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

        if (!id.equals(order.id)) return false;
        if (!price.equals(order.price)) return false;
        if (!date.equals(order.date)) return false;
        if (status != order.status) return false;
        return dishMap.equals(order.dishMap);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + dishMap.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", status=" + status +
                ", dishMap=" + dishMap +
                '}';
    }
}
