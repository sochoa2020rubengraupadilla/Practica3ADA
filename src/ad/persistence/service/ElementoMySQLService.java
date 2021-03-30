package ad.persistence.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ad.persistence.domain.Elemento;
import ad.persistence.domain.Elemento_;
import ad.persistence.util.HibernateUtil;

public class ElementoMySQLService {

	public ElementoMySQLService() {

	}

	public void anyadirElemento(Elemento elemento) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Elemento e = new Elemento(elemento.getNombreElemento());
			session.save(e);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void borrarElemento(String name) {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Elemento> criteria = builder.createQuery(Elemento.class);

			Root<Elemento> root = criteria.from(Elemento.class);

			criteria.select(root).where(builder.equal(root.get(Elemento_.nombreElemento), name));

			Elemento e = session.createQuery(criteria).getSingleResult();

			session.delete(e);

			txn.commit();
		} catch (Exception e) {
			if (session != null) {
				txn.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void verElementos() {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Elemento> criteria = builder.createQuery(Elemento.class);

			Root<Elemento> root = criteria.from(Elemento.class);

			criteria.select(root);

			List<Elemento> elementos = session.createQuery(criteria).getResultList();
			for (Elemento e : elementos) {
				System.out.println(e.toString());
			}

			txn.commit();
		} catch (Exception e) {
			if (session != null) {
				txn.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void modificarElemento(String name, String nuevoNombre) {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Elemento> criteria = builder.createQuery(Elemento.class);

			Root<Elemento> root = criteria.from(Elemento.class);

			criteria.select(root).where(builder.equal(root.get(Elemento_.nombreElemento), name));

			Elemento e = session.createQuery(criteria).getSingleResult();

			e.setNombreElemento(nuevoNombre);

			session.update(e);

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
			CriteriaQuery<Elemento> criteria = builder.createQuery(Elemento.class);

			Root<Elemento> root = criteria.from(Elemento.class);

			criteria.select(root).where(builder.equal(root.get(Elemento_.nombreElemento), nombre));

			Elemento e = session.createQuery(criteria).getSingleResult();

			if (e != null) {
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
