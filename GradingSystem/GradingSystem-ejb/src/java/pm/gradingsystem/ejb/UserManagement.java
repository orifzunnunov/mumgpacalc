/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gradingsystem.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pm.gradingsystem.entity.IUser;
import pm.gradingsystem.entity.Role;

/**
 *
 * @author Tahmasebi
 */
@Stateless
@LocalBean
public class UserManagement {

    @PersistenceContext
    private EntityManager em;

    public IUser find(String username,String password) {
        TypedQuery<IUser> query = em.createQuery("SELECT u FROM IUser u WHERE u.username = :username AND u.password = :password", IUser.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
    
    public IUser findByUserCode(String username,int securityCode) {
        TypedQuery<IUser> query = em.createQuery("SELECT u FROM IUser u WHERE u.username = :username AND u.securityCode = :securityCode", IUser.class);
        query.setParameter("username", username);
        query.setParameter("securityCode", securityCode);
        return query.getSingleResult();
    }
    public Role findRoles(int id) {
        TypedQuery<Role> query = em.createQuery("SELECT u FROM Role u WHERE u.id = :id", Role.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    public IUser getUser(int id) {
        IUser user = em.find(IUser.class, id);
        return user;
    }

    public void create(IUser user) {
        em.persist(user);
    }

    public List<IUser> getUsers() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<IUser> criQuery = builder.createQuery(IUser.class);
        Root<IUser> root = criQuery.from(IUser.class);
        criQuery.select(root);
        TypedQuery<IUser> query = em.createQuery(criQuery);
        List<IUser> users = query.getResultList();
        return users;
    }

    public void update(IUser edituser) {
        //user = em.find(IUser.class, user.getId());
        em.merge(edituser);
    }

    public void delete(int id) {
        IUser foundUser = em.find(IUser.class, id);
        em.remove(foundUser);
    }
}
