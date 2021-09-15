package edu.epam.web.model.entity;


public class Ingredient {
    private String name;
    private String size;

    public Ingredient(String name, String size) {
        this.name = name;
        this.size = size;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;
        if (!name.equals(that.name)) return false;
        return size.equals(that.size);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + size.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ingridient{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
