package studentsAnotations;
import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})

public @interface CreateStudent {
    String value();
}
