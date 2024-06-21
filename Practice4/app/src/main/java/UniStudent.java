import studentsAnotations.CreateStudent;
import studentsAnotations.EnsureAbsenceFalse;
import studentsAnotations.MaxMarks;

import java.util.ArrayList;
import java.util.List;

@CreateStudent(StudentTags.UniStudent)
public class UniStudent implements Student{

    @MaxMarks(100)
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
            ensureMarksInitialized();
            marks.add(mark);
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

    public String singAnthem (){
        System.out.println("And here comes the anthem of our university:");
        System.out.println(anthem);
        return anthem;
    }

    public int getAverageMark(){
        int sum = 0;
        for(int i = 0; i<marks.size(); i++){
            sum += marks.get(i);
        }
        int res = sum/marks.size()*4/10;
        return res;
    }
}

