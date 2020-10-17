package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery(
                "CREATE TABLE IF NOT EXISTS `my_first_db`.`users` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `lastname` VARCHAR(45) NOT NULL,\n" +
                        "  `age` TINYINT(3) NOT NULL,\n" +
                        "  PRIMARY KEY (`id`))\n" +
                        "ENGINE = InnoDB\n" +
                        "DEFAULT CHARACTER SET = utf8;"
        ).executeUpdate();
        tx1.commit();
        session.close();




    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS `users`;").executeUpdate();
        tx1.commit();
        session.close();


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(new User(name, lastName, age));
        tx1.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        tx1.commit();
        session.close();


    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> users = (List<User>) session.createQuery("From User").list();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("DELETE FROM `users`;").executeUpdate();
        tx1.commit();
        session.close();

    }


}
