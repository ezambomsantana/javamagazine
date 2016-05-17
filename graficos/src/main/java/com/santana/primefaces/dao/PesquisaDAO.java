package com.santana.primefaces.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.devmedia.primefaces.model.Pesquisa;

public class PesquisaDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("devmedia");
	private EntityManager em = factory.createEntityManager();
	
	public boolean salvar(Pesquisa pesquisa) {
		try {
			em.getTransaction().begin();
			em.persist(pesquisa);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Pesquisa> getAll() {
		return em.createQuery("Select pesquisa from Pesquisa pesquisa").getResultList();
	}
	

}
