/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pm.gradingsystem.cdi;

import GPASystem_Services.CourseService;
import GPASystem_Services.FacultyService;
import GPASystem_Services.SectionService;
import GPASystem_Services.UserService;
//import GPASystem_entities.Course;
//import GPASystem_entities.IUser;
//import GPASystem_entities.Section;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import pm.gradingsystem.entity.Course;
import pm.gradingsystem.entity.IUser;
import pm.gradingsystem.entity.Section;

/**
 * 
 * @author Prakriti
 */
@Named
@RequestScoped
public class SectionCDI {
 private Section section=new Section();
   private List<Section> clist=new ArrayList<>();
    private List<SelectItem> coursesItemList;
     private List<SelectItem> facultyItemList;
   @EJB
   private SectionService sectionEJB;

    public List<SelectItem> getFacultyItemList() {
        return facultyItemList;
    }

    public void setFacultyItemList(List<SelectItem> facultyItemList) {
        this.facultyItemList = facultyItemList;
    }
   @EJB
   private CourseService courseEJB;
    @EJB
   private UserService userEJB;
     @EJB
   private FacultyService facultyEJB;
   private List<Course> courselist=new ArrayList<>();
    private List<IUser> facultymembers=new ArrayList<>();
    private List<Section> sectionlist=new ArrayList<>();
   private int courseId;
    private int facultyId;

    public List<IUser> getFacultymembers() {
         setFacultymembers(userEJB.getFacultyUsers());
        return facultymembers;
    }

    public void setFacultymembers(List<IUser> facultymembers) {
        this.facultymembers = facultymembers;
    }
 @EJB
    private CourseService courseService;
    public List<Section> getSectionlist() {
        return sectionlist;
    }

    public void setSectionlist(List<Section> sectionlist) {
        this.sectionlist = sectionlist;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public List<Course> getCourselist() {
        setCourselist(courseEJB.getAllCourses());
        return courselist;
    }

    public void setCourselist(List<Course> courselist) {
        this.courselist = courselist;
    }
    public List<Section> getClist() {

        return sectionEJB.getSectionList();
    }

    public void setClist(List<Section> clist) {
        this.clist = clist;
    }

 
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    public List<SelectItem> getCoursesItemList() {
        coursesItemList = new ArrayList<>();
        getCourselist();
        for (Course course1 : courselist) {
            SelectItem item = new SelectItem(course1.getId(), course1.getCoursename());
            coursesItemList.add(item);
        }
        return coursesItemList;
    }
     public List<SelectItem> getfacultyItemList() {
        facultyItemList = new ArrayList<>();
        getFacultymembers();
        for (IUser faculty1 : facultymembers) {
            SelectItem item = new SelectItem(faculty1.getId(), faculty1.getFirstname());
            facultyItemList.add(item);
        }
        return facultyItemList;
    }
    public SectionCDI() {
            }
    public String createSection(){
       this.section.setCourse(courseService.findCourse(courseId));
     //  this.section.setFaculty(facultyEJB.findFaculty(facultyId));
       sectionEJB.makeSection(section);
       return "sectionManagement";
    }
     public String deleteSection(long id){
       sectionEJB.deleteSection(id);
       return "sectionManagement";
    }
     public String updateSection(Long id){
       this.section=  sectionEJB.getSection(id);
       return "sectionManagement";
    }
     public String updateFinal(){
        sectionEJB.updateIt(section);
        return "sectionManagement"; 
     }
     
}
