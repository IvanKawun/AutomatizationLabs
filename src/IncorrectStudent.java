public class IncorrectStudent {
    private int id;
    private String name;

    public IncorrectStudent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return true; // Некоректна реалізація: завжди повертає true
    }

    @Override
    public int hashCode() {
        return id; // Некоректна реалізація: залежить лише від id, не враховує name
    }

    @Override
    public String toString() {
        return "IncorrectStudent{id=" + id + ", name='" + name + "'}";
    }
}
