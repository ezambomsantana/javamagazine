package com.devmedia.primefaces.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Candidato {
	
    @Id
    @SequenceGenerator(name="candidato_seq", sequenceName="candidato_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="candidato_seq")
	private int id;
    
    @Column(name="nome_candidato", nullable=false)
	private String nomeCandidato;

    @Column(name="nome_partido", nullable=false)
	private String nomePartido;
    
    @Column(name="numero_partido", nullable=false)
	private int numeroPartido;
	
	//gets e sets
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCandidato() {
		return nomeCandidato;
	}
	
	public void setNomeCandidato(String nomeCandidato) {
		this.nomeCandidato = nomeCandidato;
	}
	
	public String getNomePartido() {
		return nomePartido;
	}
	
	public void setNomePartido(String nomePartido) {
		this.nomePartido = nomePartido;
	}
	
	public int getNumeroPartido() {
		return numeroPartido;
	}
	
	public void setNumeroPartido(int numeroPartido) {
		this.numeroPartido = numeroPartido;
	}
	
	 

}
