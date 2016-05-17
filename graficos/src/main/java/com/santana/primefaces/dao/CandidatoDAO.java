package com.santana.primefaces.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.devmedia.primefaces.model.Candidato;

public class CandidatoDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("devmedia");
	private EntityManager em = factory.createEntityManager();
	
	public boolean salvar(Candidato candidato) {
		try {
			em.getTransaction().begin();
			em.persist(candidato);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Candidato> getAll() {
		return em.createQuery("Select candidato from Candidato candidato").getResultList();
	}

}
