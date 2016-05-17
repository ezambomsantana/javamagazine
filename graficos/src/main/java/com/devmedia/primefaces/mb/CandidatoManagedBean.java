package com.devmedia.primefaces.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.devmedia.primefaces.model.Candidato;
import com.santana.primefaces.dao.CandidatoDAO;

@ManagedBean(name = "CandidatoMB")
@ViewScoped
public class CandidatoManagedBean {

	private CandidatoDAO candidatoDAO = new CandidatoDAO();

	private Candidato candidato = new Candidato();

	
	public void cadastraCandidato() {
		candidatoDAO.salvar(candidato);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Candidato Cadastrado com Sucesso!"));
	}

	// getters and setters
	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public List<Candidato> getCandidatos() {
		return candidatoDAO.getAll();
	}

}
