/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPASystem_Services;

//import GPASystem_entities.Course;
//import GPASystem_entities.Faculty;
//import GPASystem_entities.IUser;
//import GPASystem_entities.Section;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pm.gradingsystem.entity.IUser;

/**
 *
 * @author pkhanal
 */
@Stateless
@LocalBean
public class FacultyService {
    @PersistenceContext
    private EntityManager em;
 //   Faculty faculty=new Faculty();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public IUser findFaculty(int id){
       return em.find(IUser.class,id);
   } 
}
