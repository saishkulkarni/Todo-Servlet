package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.Customer;
import dto.Task;

public class MyDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();

	public void saveCustomer(Customer customer) {
		manager.getTransaction().begin();
		manager.persist(customer);
		manager.getTransaction().commit();
	}

	public Customer findByEmail(String email) {
		List<Customer> customers = manager.createQuery("select x from Customer x where email=?1").setParameter(1, email)
				.getResultList();
		if (customers.isEmpty())
			return null;
		else
			return customers.get(0);
	}

	public void saveTask(Task task) {
		manager.getTransaction().begin();
		manager.persist(task);
		manager.getTransaction().commit();
	}

	public List<Task> fetchTasks(int id) {
		return manager.createQuery("select x from Task x where customer_id=?1").setParameter(1, id).getResultList();
	}

	public Task findById(int id) {
		return manager.find(Task.class, id);
	}

	public void updateTask(Task task) {
		manager.getTransaction().begin();
		manager.merge(task);
		manager.getTransaction().commit();
	}
	
	public void deleteTask(Task task) {
		manager.getTransaction().begin();
		manager.remove(task);
		manager.getTransaction().commit();
	}
}
