/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gradingsystem.cdi;

import GPASystem_Services.CourseService;
import GPASystem_Services.Evaluation_Service;
import GPASystem_Services.FacultyService;
import GPASystem_Services.SectionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pm.gradingsystem.ejb.UserManagement;
import pm.gradingsystem.entity.Course;
import pm.gradingsystem.entity.Evaluation;
import pm.gradingsystem.entity.Grade;
import pm.gradingsystem.entity.IUser;
import pm.gradingsystem.entity.Section;

/**
 *
 * @author Tahmasebi
 */
@Named
@SessionScoped
public class SearchCourseCDI implements Serializable {

    private IUser user = new IUser();
    @EJB
    private UserManagement userService;
    @EJB
    private Evaluation_Service evaluationEJB;
    private String EvaluationMessage;

    public String getEvaluationMessage() {
        return EvaluationMessage;
    }

    public void setEvaluationMessage(String EvaluationMessage) {
        this.EvaluationMessage = EvaluationMessage;
    }
    private List<Section> sections = new ArrayList<>();
    @Inject
    private SessionCDI session;
    private Section section;
    @EJB
    private SectionService sectionService;
    private Course course;
    @EJB
    private CourseService courseservice;
    private int id;
    private Long sectionID;
    @Inject
    feedbackCDI fCDI;
    @EJB
    FacultyService facEJB;
    int i;
    float num;
    private Evaluation evaluation = new Evaluation();
    float total;
    List<Grade> grades = new ArrayList<>();
    Grade grade=new Grade();

    public Grade getGrade() {
         user = session.getUser();
        return facEJB.findgrade(section, user);
      //  return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public Long getSectionID() {
        return sectionID;
    }

    public void setSectionID(Long sectionID) {
        this.sectionID = sectionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

//    public void setCourse(Course course) {
//        this.course = course;
//    }
    public Section getSection() {
        return section;
    }

    public List<Section> getSections() {
        user = session.getUser();
        sections = userService.findUserInfoById(user);
        for (Section sec : sections) {

            if (facEJB.findgrade(sec, user) != null) {
                grades.add(facEJB.findgrade(sec, user));
            }
        }
        i = 0;
        num = 0;
        total = 0;
        calculateGPA();
        return sections;
    }

    
       
    

    public String viewSection(long id) {
        this.sectionID = id;
        section = sectionService.getSection(id);
        return "studentviewsection";
    }

    public void searchCourseById(int id) {
        this.course = courseservice.findCourse(id);
        sections = sectionService.findSectionByCourse(course);
    }

    public void sendMessage(Section section1) {
        // stdMessage = message;
        System.out.println("section" + section1);
        fCDI.sendMessage(section1);

    }

    public void sendFeedback(Section secname) {
        System.out.println("the section is: " + secname);
        this.section = section;
        evaluation.setSection(secname);
        IUser student = session.getUser();
        evaluation.setStud(student);
        evaluationEJB.saveMsg(evaluation);
        EvaluationMessage = "EVALUATION DONE";
//try {
//
//}
    }
//
//    public void setSections(List<Section> sections) {
//        this.sections = sections;
//    }
////    public IUser getUser() {
////        return user;
////    }
//
//    public void searchSTDById() {
//        user = session.getUser();
//        sections = userService.findUserInfoById(user);
//    }

    private void calculateGPA() {
        for (Grade gradd : grades) {
            int num1 = (gradd.getSection().getCourse().getCredit());
            System.out.println("credit" + num1);
            num = num + (num1 * gradd.getGpa());
            System.out.println("num" + num);
            i = i + num1;
            num1 = 0;
        }

        total = num / i;

    }
}
