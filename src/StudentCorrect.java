import java.util.Objects;

public class StudentCorrect {
    private int id;
    private String name;

    public StudentCorrect(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCorrect that = (StudentCorrect) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "StudentCorrect{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}