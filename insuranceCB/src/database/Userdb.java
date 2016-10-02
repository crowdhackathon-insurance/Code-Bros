package database;

import entities.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.EntityManager;

public class Userdb {

	public List<User> getusers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("insuranceCB");
		EntityManager em = emf.createEntityManager();
		List<User> list=null;
		try {
			em.getTransaction().begin();
		    // Operations that modify the database should come here.
			Query query=em.createNamedQuery("User.findAll");
			list = query.getResultList();
			for(User e:list) {
		         System.out.println("Username :"+e.getUsername());
		     }
		    em.getTransaction().commit();
		}
		finally {
			if (em.getTransaction().isActive())
		          em.getTransaction().rollback();
		    em.close();
		}
		emf.close();
		return list;
	}
	
	public User searchuser(String username,String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("insuranceCB");
		EntityManager em = emf.createEntityManager();
		User u=null;
		try {
			em.getTransaction().begin();
		    // Operations that modify the database should come here.
			Query query=em.createNamedQuery("User.findbyusrandpass");			
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<User> list = query.getResultList();
			if (list != null && list.size() == 1) {
				u=list.get(0);
			}
		    em.getTransaction().commit();
		}
		finally {
			if (em.getTransaction().isActive())
		          em.getTransaction().rollback();
		    em.close();
		}
		emf.close();
		return u;
	}
	
	public int insertuser(String username,String password,String confpass,String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("insuranceCB");
		EntityManager em = emf.createEntityManager();
		int ok=0;
		try {
			em.getTransaction().begin();
			Query query=em.createNamedQuery("User.findbyusr");
			query.setParameter("username", username);
			List<User> list = query.getResultList();
		    if (!list.isEmpty()) {
				ok=0;
			}
		    else {
		    	//em.getTransaction().begin();
		    	User u=new User();
		    	u.setUsername(username);
		    	u.setPassword(password);
		    	u.setEmail(email);
		    	u.setChecked(false);
		    	em.persist(u);
		    	ok=1;
		    }
		    em.getTransaction().commit();
		}
		finally {
			if (em.getTransaction().isActive())
		          em.getTransaction().rollback();
		    em.close();
		}
		emf.close();
		return ok;
	}	
	public User userinfo(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("insuranceCB");
		EntityManager em = emf.createEntityManager();
		User u=null;
		try {
			em.getTransaction().begin();
			Query query=em.createNamedQuery("User.findbyusr");		   
			query.setParameter("username", username);
			List<User> list = query.getResultList();
			if (list != null && list.size() == 1) {
				u=list.get(0);
			}
			em.getTransaction().commit();
		}
		finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
		emf.close();
		return u;
	}
	
	public void validateuser(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("insuranceCB");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			User usr= (User)em.find(User.class , username);
			usr.setChecked(true);	  
			em.getTransaction().commit();
		}
		finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();   
		}
		emf.close();
	}

}
