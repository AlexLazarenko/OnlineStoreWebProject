package edu.epam.web.entity;

import java.util.Objects;

public class Ingridient {
    private String id;
    private String name;
    private String info;
    private int size;

    public Ingridient(String id, String name, String info, int size) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.size = size;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingridient that = (Ingridient) o;
        return size == that.size &&
                id.equals(that.id) &&
                name.equals(that.name) &&
                info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, info, size);
    }

    @Override
    public String toString() {
        return "Ingridient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", size=" + size +
                '}';
    }
}
