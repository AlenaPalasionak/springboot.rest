package org.lenapalasionak.spring.springboot.springboot.rest.dao;

import org.hibernate.Session;

import org.hibernate.query.Query;

import org.lenapalasionak.spring.springboot.springboot.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;//hylited

    @Override
    //@Transactional//будет автоматом открывать и закрывать транзакции - перенесли в сервис
    public List<Employee> getAllEmployees() {
        Session session = entityManager.unwrap(Session.class);//entityManager - обертка сессии

        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = entityManager.unwrap(Session.class);//entityManager - обертка сессии
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);//entityManager - обертка сессии

        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);//entityManager - обертка сессии
        /*Удаление в два запроса - дольше
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);
         */
        //Удаление в один запрос
        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);//произойдет замена названия параметра employeeId на значение
        // параметра, достигается с помощью метода setParameter()
        query.executeUpdate();
    }
}
