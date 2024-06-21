import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessorTest {

    @Test
    public void testMaxMarksAnnotationProcessor() {
        // Given
        SchoolStudent student = new SchoolStudent();

        // When
        student.addMark(90);
        student.addMark(110);

        // Then
        List<Integer> marks = student.getMarks();
        assertNotNull(marks);
        assertEquals(1, marks.size()); // Це тест покаже, що лише одна оцінка 90 була додана через перевищення

        // Ви також можете перевірити, чи виведені діагностичні повідомлення про виклик методу
    }

    @Test
    public void testEnsureAbsenceFalseAnnotationProcessor() {
        // Given
        SchoolStudent student = new SchoolStudent();

        // When
        student.changeAbsence(false);

        // Then
        assertFalse(student.checkAbsence()); // Впевніться, що поле absence було ініціалізовано на false
    }
}