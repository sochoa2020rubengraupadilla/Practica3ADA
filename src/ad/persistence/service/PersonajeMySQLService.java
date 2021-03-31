package ad.persistence.service;

import java.util.List;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ad.persistence.domain.Personaje;
import ad.persistence.domain.Personaje_;
import ad.persistence.util.HibernateUtil;

public class PersonajeMySQLService {
	// Usar session
	public PersonajeMySQLService() {

	}

	public void anyadirPersonaje(Personaje p) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void borrarPersonajePorNombre(String name) {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Personaje> criteria = builder.createQuery(Personaje.class);

			Root<Personaje> root = criteria.from(Personaje.class);

			criteria.select(root).where(builder.equal(root.get(Personaje_.nombrePersonaje), name));

			Personaje p = session.createQuery(criteria).getSingleResult();

			session.delete(p);

			txn.commit();
		} catch (Exception e) {
			if (session != null) {
				txn.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void verPersonajes() {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Personaje> criteria = builder.createQuery(Personaje.class);

			Root<Personaje> root = criteria.from(Personaje.class);

			criteria.select(root);

			List<Personaje> personajes = session.createQuery(criteria).getResultList();
			for (Personaje p : personajes) {
				System.out.println(p.toString());
			}

			txn.commit();
		} catch (Exception e) {
			if (session != null) {
				txn.rollback();
			}else {
				System.out.println("No se puede conectar a la base de datos");
			}
		} finally {
			session.close();
		}
	}

	public void modificarPersonaje(String nombreABuscar, String nuevoNombre, String nuevoNivel) {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Personaje> criteria = builder.createQuery(Personaje.class);

			Root<Personaje> root = criteria.from(Personaje.class);

			criteria.select(root).where(builder.equal(root.get(Personaje_.nombrePersonaje), nombreABuscar));

			Personaje p = session.createQuery(criteria).getSingleResult();

			p.setNombrePersonaje(nuevoNombre);
			p.setNivelPersonaje(nuevoNivel);

			session.update(p);

			txn.commit();
		} catch (Exception e) {
			if (session != null) {
				txn.rollback();
			}
		} finally {
			session.close();
		}
	}
	
	public boolean comprobarExistencia(String nombre) {
		Session session = null;
		Transaction txn = null;
		Boolean existe = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Personaje> criteria = builder.createQuery(Personaje.class);

			Root<Personaje> root = criteria.from(Personaje.class);

			criteria.select(root).where(builder.equal(root.get(Personaje_.nombrePersonaje), nombre));

			Personaje p = session.createQuery(criteria).getSingleResult();

			if (p != null) {
				existe = true;
			}

			txn.commit();
		} catch (Exception e) {
			if (session != null) {
				txn.rollback();
			}
		} finally {
			session.close();
		}
		return existe;
	}
}