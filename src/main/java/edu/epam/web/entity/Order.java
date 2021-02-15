package edu.epam.web.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {//todo
    private String id;
    private BigDecimal price;
    private Date date;
    private OrderStatus status;
    private List<Dish> list;

    public Order(String id, BigDecimal price, Date date, OrderStatus status, List<Dish> list) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.status = status;
        this.list = list;
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

    public List<Dish> getList() {
        return list;
    }

    public void setList(List<Dish> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                price.equals(order.price) &&
                date.equals(order.date) &&
                status == order.status &&
                list.equals(order.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, date, status, list);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", status=" + status +
                ", list=" + list +
                '}';
    }
}
