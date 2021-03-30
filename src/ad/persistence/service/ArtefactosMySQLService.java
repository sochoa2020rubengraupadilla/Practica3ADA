package ad.persistence.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ad.persistence.domain.Artefactos;
import ad.persistence.domain.Artefactos_;
import ad.persistence.util.HibernateUtil;

public class ArtefactosMySQLService {

	public ArtefactosMySQLService() {

	}

	public void anyadirArtefacto(Artefactos artefacto) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Artefactos a = new Artefactos(artefacto.getNombreArtefacto(), artefacto.getTipoArtefacto());
			session.save(a);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void borrarArtefactoPorNombreYTipo(String name, String tipo) {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Artefactos> criteria = builder.createQuery(Artefactos.class);

			Root<Artefactos> root = criteria.from(Artefactos.class);

			criteria.select(root).where(builder.equal(root.get(Artefactos_.nombreArtefacto), name),
					builder.and(builder.equal(root.get(Artefactos_.tipoArtefacto), tipo)));

			Artefactos a = session.createQuery(criteria).getSingleResult();

			session.delete(a);

			txn.commit();
		} catch (Exception e) {
			if (session != null) {
				txn.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void verArtefactos() {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Artefactos> criteria = builder.createQuery(Artefactos.class);

			Root<Artefactos> root = criteria.from(Artefactos.class);

			criteria.select(root);

			List<Artefactos> artefactos = session.createQuery(criteria).getResultList();
			for (Artefactos a : artefactos) {
				System.out.println(a.toString());
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

	public void modificarArtefacto(String name, String tipo, String nuevoNombre, String nuevoTipo) {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Artefactos> criteria = builder.createQuery(Artefactos.class);

			Root<Artefactos> root = criteria.from(Artefactos.class);

			criteria.select(root).where(builder.equal(root.get(Artefactos_.nombreArtefacto), name),
					builder.and(builder.equal(root.get(Artefactos_.tipoArtefacto), tipo)));

			Artefactos a = session.createQuery(criteria).getSingleResult();

			a.setNombreArtefacto(nuevoNombre);
			a.setTipoArtefacto(nuevoTipo);

			session.update(a);

			txn.commit();
		} catch (Exception e) {
			if (session != null) {
				txn.rollback();
			}
		} finally {
			session.close();
		}
	}

	public boolean comprobarExistencia(String nombre, String tipo) {
		Session session = null;
		Transaction txn = null;
		Boolean existe = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			txn = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Artefactos> criteria = builder.createQuery(Artefactos.class);

			Root<Artefactos> root = criteria.from(Artefactos.class);

			criteria.select(root).where(builder.equal(root.get(Artefactos_.nombreArtefacto), nombre),
					builder.and(builder.equal(root.get(Artefactos_.tipoArtefacto), tipo)));

			Artefactos a = session.createQuery(criteria).getSingleResult();

			if (a != null) {
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
