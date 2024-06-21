import studentsAnotations.CreateStudent;
import studentsAnotations.EnsureAbsenceFalse;
import studentsAnotations.MaxMarks;

import java.util.List;
import java.util.ArrayList;

@CreateStudent(StudentTags.SchoolStudent)
public class SchoolStudent implements Student {


    @MaxMarks(value = 50)
    private List<Integer> marks;
    @EnsureAbsenceFalse
    private boolean absence;
    private String anthem;

    public void markAbsence(){
        this.absence = true;
    }
    public void unmarkAbsence(){
        this.absence = false;
    }
    public void changeAnthem(String text){
        this.anthem = text;
    }
    public void addMark(int mark){
        if (mark > 50) {
            System.out.println("School student mark exceeds the maximum allowed value.");
        } else {
            ensureMarksInitialized();
            marks.add(mark);
        }
    }

    private void ensureMarksInitialized() {
        if (marks == null) {
            marks = new ArrayList<>();
        }
    }
    public void deleteMark(int number){
        if(number<0 || number > marks.size())
            System.out.println("Invalid input");
        else {
            ensureMarksInitialized();
            marks.remove(number);
        }
    }
    @Override
    public boolean checkAbsence() {
        return absence;
    }
public void changeAbsence(boolean absence){
        this.absence = absence;
}
public List getMarks(){
        return marks;
}
    public String singAnthem (){
        System.out.println("And here comes the school anthem:");
        System.out.println(anthem);
        return anthem;
    }

    public int getAverageMark(){
        int sum = 0;
        for(int i = 0; i<marks.size(); i++){
            sum += marks.get(i);
        }
        int res = sum/marks.size();
        return res;
    }
}
