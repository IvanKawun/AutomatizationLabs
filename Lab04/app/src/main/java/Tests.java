import studentsAnotations.CreateStudent;
import studentsAnotations.MaxMarks;

import java.lang.reflect.Field;
import java.util.List;

public class Tests{

    public static void main(String[] args) throws Exception {
        testSchoolStudent();
        testUniStudent();
    }

    public static void testSchoolStudent() throws Exception {
        SchoolStudent schoolStudent = new SchoolStudent();
        schoolStudent.addMark(34);
        schoolStudent.addMark(55); // Це повинно викликати попередження про перевищення значення написане в самому коді класу

        schoolStudent.changeAnthem("School Anthem");

        runRuntimeAnnotationChecks(schoolStudent);
        System.out.println("Average mark: " + schoolStudent.getAverageMark());
        schoolStudent.singAnthem();
    }

    public static void testUniStudent() throws Exception {
        UniStudent uniStudent = new UniStudent();
        uniStudent.addMark(90);
        uniStudent.addMark(110); // Це повинно викликати помилку, яка перевіряється в анотації
        uniStudent.changeAnthem("University Anthem");

        runRuntimeAnnotationChecks(uniStudent);
        System.out.println("Average mark: " + uniStudent.getAverageMark());
        uniStudent.singAnthem();
    }

    public static void runRuntimeAnnotationChecks(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();

        // Перевірка анотації CreateStudent
        if (clazz.isAnnotationPresent(CreateStudent.class)) {
            CreateStudent createStudent = clazz.getAnnotation(CreateStudent.class);
            System.out.println("CreateStudent annotation found with value: " + createStudent.value());
        }

        // Перевірка полів з анотацією MaxMarks
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(MaxMarks.class)) {
                field.setAccessible(true);
                List<Integer> marks = (List<Integer>) field.get(obj);
                MaxMarks maxMarks = field.getAnnotation(MaxMarks.class);
                for (int mark : marks) {
                    if (mark > maxMarks.value()) {
                        System.out.println("Error: Mark " + mark + " exceeds the maximum allowed value of " + maxMarks.value());
                    }
                }
            }
        }
    }
}