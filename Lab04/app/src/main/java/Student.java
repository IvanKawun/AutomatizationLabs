import studentsAnotations.CreateObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Creating a student object using annotation
@CreateObject
public interface Student {

    boolean checkAbsence();

    String singAnthem();

    int getAverageMark();
}