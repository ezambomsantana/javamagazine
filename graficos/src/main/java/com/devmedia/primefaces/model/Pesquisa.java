package com.devmedia.primefaces.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pesquisa {
	
    @Id
    @SequenceGenerator(name="pesquisa_seq", sequenceName="pesquisa_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pesquisa_seq")
	private int id;    

    @Column(name="numero_eleitores", nullable=false)
    private int numeroEleitores;
    
    @Temporal(TemporalType.DATE)
	private Date dataPesquisa;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroEleitores() {
		return numeroEleitores;
	}
	
	public void setNumeroEleitores(int numeroEleitores) {
		this.numeroEleitores = numeroEleitores;
	}
	
	public Date getDataPesquisa() {
		return dataPesquisa;
	}
	
	public void setDataPesquisa(Date dataPesquisa) {
		this.dataPesquisa = dataPesquisa;
	}

}
