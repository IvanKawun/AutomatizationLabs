import java.lang.String;

public final class StudentFactory {
  public static Student createStudent(String type) {
    switch(type) {
    }
    throw new RuntimeException("not support type");
  }
}
