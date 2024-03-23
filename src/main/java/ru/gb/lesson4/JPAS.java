package ru.gb.lesson4;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class JPAS {

    // JPA Java (Jakarta) Persistence API
    // Hibernate - реализация спецификации JPA
    // EclipseLink

    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            insertStudents(sessionFactory);
//
//      try (Session session = sessionFactory.openSession()) {
//        // SQL Structure Query Language
//        // JQP Java Query Language
//
//        Query<Department> query = session.createQuery("select p from Department p", Department.class);
//        System.out.println(query.getSingleResult());
//      }

            try (Session session = sessionFactory.openSession()) {
                Transaction tx = session.beginTransaction();
                Student student = session.find(Student.class, 1L);
                System.out.println(student);

                student.setSecondName("NEW NAME");
                session.merge(student);

                tx.commit();
            }

            List<Student> students = new ArrayList<>();
            try (Session session = sessionFactory.openSession()) {
                // SQL Structure Query Language
                // JQP Java Query Language

                Query<Student> query = session.createQuery("select p from Student p where id > :id", Student.class);
                query.setParameter("id", 20);
                List<Student> resultList = query.getResultList();

                Transaction tx = session.beginTransaction();
                for (Student student : resultList) {
                    student.setSecondName("UPDATED");
//          session.merge(person);
                }
                tx.commit();

                students.addAll(session.createQuery("from Student p", Student.class).getResultList());
                System.out.println(students);
            }


            try (Session session = sessionFactory.openSession()) {
                Student student = session.find(Student.class, 1L);
                System.out.println(student);

                Transaction tx = session.beginTransaction();
                session.remove(student);
                tx.commit();
            }

            try (Session session = sessionFactory.openSession()) {
                Student student = session.find(Student.class, 1L);
                System.out.println(student);
            }
        }
    }

    private static void insertStudents(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            for (long i = 1; i <= 10; i++) {
                Student student = new Student();
                student.setId(i);
                student.setFirstName("Student #" + i);
                student.setSecondName("Second");
                student.setAge(12);

                session.persist(student);
            }

            tx.commit();
        }
    }

}
