package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User findByCar (String model, int series){
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery
              ("select user from User user inner join user.car uc where uc.model = :car_model and uc.series = :car_series ",User.class);
               query.setParameter("car_model",model)
                       .setParameter("car_series",series)
                       .setFirstResult(0)
                       .setMaxResults(1);
               return query.getSingleResult();
   }

}
