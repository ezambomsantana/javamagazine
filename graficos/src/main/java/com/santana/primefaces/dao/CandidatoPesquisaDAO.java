package com.santana.primefaces.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.devmedia.primefaces.model.Candidato;
import com.devmedia.primefaces.model.CandidatoPesquisa;
import com.devmedia.primefaces.model.Pesquisa;

public class CandidatoPesquisaDAO {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("devmedia");
	private EntityManager em = factory.createEntityManager();
	
	public boolean salvar(CandidatoPesquisa candidatoPesquisa) {
		try {
			em.getTransaction().begin();
			em.persist(candidatoPesquisa);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CandidatoPesquisa> getAll() {
		return em.createQuery("Select cp from CandidatoPesquisa cp").getResultList();
	}
	
	public List<CandidatoPesquisa> getByPesquisa(Pesquisa pesquisa) {
		return em.createQuery("Select cp from CandidatoPesquisa cp where cp.pesquisa.id = :idPesquisa")
				.setParameter("idPesquisa", pesquisa.getId())
				.getResultList();
	}
	
	public CandidatoPesquisa getByPesquisaAndCandidato(Pesquisa pesquisa, Candidato candidato) {
		return (CandidatoPesquisa) em.createQuery("Select cp from CandidatoPesquisa cp where cp.pesquisa.id = :idPesquisa and cp.candidato.id = :idCandidato")
				.setParameter("idPesquisa", pesquisa.getId())
				.setParameter("idCandidato", candidato.getId())
				.getSingleResult();
	}

}
