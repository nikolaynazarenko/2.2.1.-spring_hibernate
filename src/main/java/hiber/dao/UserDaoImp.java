package hiber.dao;

import hiber.model.Car;
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
   public User findByCar (String model, int serises){
      TypedQuery<User> query = sessionFactory.getCurrentSession().
              createQuery("select u from User u,Car car where car.model = :car_model and car.series =" +
                      ":car_series",User.class);
               query.setParameter("car_model",model)
                       .setParameter("car_series",serises)
                       .setFirstResult(0)
                       .setMaxResults(1);



      return query.getSingleResult();
   }

}
