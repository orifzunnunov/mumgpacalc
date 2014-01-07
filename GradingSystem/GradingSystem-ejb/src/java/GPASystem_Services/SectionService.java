/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPASystem_Services;

//import GPASystem_entities.Section;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pm.gradingsystem.entity.Section;

/**
 *
 * @author Prakriti
 */
@Stateless
@LocalBean
public class SectionService {

    @PersistenceContext
    EntityManager em;
   
  public void makeSection(Section section) {
       em.persist(section);
    }

    public void deleteSection(Long i) {
       Section section=em.find(Section.class, i);
       em.remove(section);
    }
   public void updateIt(Section section){
       em.merge(section);
   }
    public Section getSection(Long i){
       return em.find(Section.class, i);
        
   }
     public List<Section> getSectionList(){
         return em.createQuery("Select s from Section s").getResultList();
     }

   
}
