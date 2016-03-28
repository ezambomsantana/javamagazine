package com.devmedia.primefaces.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.devmedia.primefaces.lazyload.AlunoLazyDataModel;
import com.devmedia.primefaces.model.Aluno;

@ManagedBean(name="alunoMB")
@ViewScoped
public class AlunoManagedBean {
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	private Aluno selectedAluno = new Aluno();
	
	private LazyDataModel<Aluno> lazyModel;
	
	public AlunoManagedBean() {
		for (int i = 0; i < 40; i++) {
			alunos.add(generateRandomAluno());
		}
		lazyModel = new AlunoLazyDataModel(alunos);
	}
	
	public String [] nomes = {"Eduardo", "Luiz", "Henrique", "Felipe", "Bruna", "Brianda", "Sonia", "Carlos"};
	public String [] cursos = {"Ciência da Computação", "Medicina", "Direito", "Engenharia", "Arquitetura"};
		
	public List<Aluno> getAlunos() {
		return alunos;		
	}
	
	public Aluno generateRandomAluno() {
		
		int indiceNome = (int) Math.floor(Math.random()*7);
		int indiceCurso = (int) Math.floor(Math.random()*4);
		Aluno aluno = new Aluno();
		aluno.setNome(nomes[indiceNome]);
		aluno.setCurso(cursos[indiceCurso]);
		aluno.setEndereco("Rua " + indiceNome);
		aluno.setCpf("123456");		
		aluno.setTelefone(indiceNome * 20 + "123");
		aluno.setDataMatricula(new Date());
		aluno.setRa("4312");
		
		return aluno;
				
	}

	public Aluno getSelectedAluno() {
		return selectedAluno;
	}

	public void setSelectedAluno(Aluno selectedAluno) {
		this.selectedAluno = selectedAluno;
	}

	public LazyDataModel<Aluno> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Aluno> lazyModel) {
		this.lazyModel = lazyModel;
	}

}
