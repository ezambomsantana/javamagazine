package com.devmedia.primefaces.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class CandidatoPesquisa {
	
    @Id
    @SequenceGenerator(name="cand_pesq_seq", sequenceName="cand_pesq_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cand_pesq_seq")
	private int id;
	
	@ManyToOne(optional=false)
    @JoinColumn(name = "id_candidato") 
	private Candidato candidato;
	
	@ManyToOne(optional=false)
    @JoinColumn(name = "id_pesquisa") 
	private Pesquisa pesquisa;
	
    @Column(name="intencao_voto", nullable=false)
	private int intencaoVoto;
	
	//gets e sets
	
	public Candidato getCandidato() {
		return candidato;
	}
	
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	
	public Pesquisa getPesquisa() {
		return pesquisa;
	}
	
	public void setPesquisa(Pesquisa pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public int getIntencaoVoto() {
		return intencaoVoto;
	}
	
	public void setIntencaoVoto(int intencaoVoto) {
		this.intencaoVoto = intencaoVoto;
	}
	

}
