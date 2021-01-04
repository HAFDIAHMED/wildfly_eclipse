package ma.ensias.ejb3.tp3;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class CustomerOrderManager
 */
@Stateless
@LocalBean
public class CustomerOrderManager implements CustomerOrderManagerRemote, CustomerOrderManagerLocal {
	@PersistenceContext(unitName = "TP3")
    private EntityManager em;
    /**
     * Default constructor. 
     */
    public CustomerOrderManager() {
        // TODO Auto-generated constructor stub
    }
    
    public void addCustomer(int id, String name, String email) {
    	Customer customer = new Customer();
    	customer.setId(id);
    	customer.setName(name);
    	customer.setEmail(email);
    	em.persist(customer);
    }
    public List<String> getCustomers() {
    	return em.createNamedQuery("getAllCustomers").getResultList();
    }
    
    public int deleteCustomerByName(String name) {
    	return em.createQuery("delete from Customer c wherec.name=:name").setParameter("name", name).executeUpdate();
    }
}
