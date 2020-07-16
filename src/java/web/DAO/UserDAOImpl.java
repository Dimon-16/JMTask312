package web.DAO;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    //@Transactional
    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.save(user);
        } finally {
            session.close();
        }
    }

    //@Transactional
    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query query = session.createQuery("DELETE FROM User WHERE id = :idUser");
            query.setParameter("idUser", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception exc) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    //@Transactional
    @Override
    public void updateUser(long id, User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query query = session.createQuery("UPDATE User Set name = :newName, lastName = :newLastName," +
                    "age = :newAge WHERE id = :id");
            query.setParameter("newName", user.getName());
            query.setParameter("newLastName", user.getLastName());
            query.setParameter("newAge", user.getAge());
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception exc) {
            transaction.rollback();
        }
    }

    //@Transactional
    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
}
