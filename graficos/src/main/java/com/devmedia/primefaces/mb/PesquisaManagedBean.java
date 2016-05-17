package com.devmedia.primefaces.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.devmedia.primefaces.model.Candidato;
import com.devmedia.primefaces.model.CandidatoPesquisa;
import com.devmedia.primefaces.model.Pesquisa;
import com.santana.primefaces.dao.CandidatoDAO;
import com.santana.primefaces.dao.CandidatoPesquisaDAO;
import com.santana.primefaces.dao.PesquisaDAO;

@ManagedBean(name = "PesquisaMB")
@ViewScoped
public class PesquisaManagedBean {

	private PesquisaDAO pesquisaDAO = new PesquisaDAO();
	private CandidatoDAO candidatoDAO = new CandidatoDAO();
	private CandidatoPesquisaDAO candidatoPesquisaDAO = new CandidatoPesquisaDAO();
	private Pesquisa pesquisa = new Pesquisa();
	private List<CandidatoPesquisa> candidatosPesquisa = new ArrayList<CandidatoPesquisa>();
	private Pesquisa pesquisaSelecionada;

	public PesquisaManagedBean() {

		List<Candidato> candidatos = candidatoDAO.getAll();
		for (Candidato candidato : candidatos) {
			CandidatoPesquisa cp = new CandidatoPesquisa();
			cp.setCandidato(candidato);
			cp.setPesquisa(pesquisa);
			candidatosPesquisa.add(cp);
		}

	}

	public void cadastraPesquisa() {
		pesquisaDAO.salvar(pesquisa);
		for (CandidatoPesquisa cp : candidatosPesquisa) {
			candidatoPesquisaDAO.salvar(cp);
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Candidato Cadastrado com Sucesso!"));
	}

	// getters and setters
	public List<Pesquisa> getPesquisas() {
		return pesquisaDAO.getAll();
	}

	public Pesquisa getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Pesquisa pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<CandidatoPesquisa> getCandidatosPesquisa() {
		return candidatosPesquisa;
	}

	public void setCandidatosPesquisa(List<CandidatoPesquisa> candidatosPesquisa) {
		this.candidatosPesquisa = candidatosPesquisa;
	}
	
	public LineChartModel getGraficoLinhaTempo() {
        LineChartModel lineModel = new LineChartModel();
        lineModel.setShowPointLabels(true);
        
        List<Pesquisa> pesquisas = pesquisaDAO.getAll();
        List<Candidato> candidatos = candidatoDAO.getAll();        
  
        for (Candidato candidato : candidatos) {
        	ChartSeries linha = new ChartSeries();
        	linha.setLabel(candidato.getNomeCandidato()); 
        	
        	for (int i = 0; i < pesquisas.size(); i++) {
        		Pesquisa pesquisa = pesquisas.get(i);
        		CandidatoPesquisa cp = candidatoPesquisaDAO.getByPesquisaAndCandidato(pesquisa, candidato);
        		
        		// calcula a porcentagem de cada candidato para a pesquisa.
        		linha.set(pesquisa.getId(), (cp.getIntencaoVoto() * 100 / pesquisa.getNumeroEleitores()));
        	}        	
        	lineModel.addSeries(linha);        	
        }
        lineModel.setLegendPosition("w");        
        
        // adiciona zoom e animação nos gráficos
        lineModel.setAnimate(true);        
        lineModel.setZoom(true);
                 
        return lineModel;
    }
	

	public PieChartModel getGraficoPizzaPesquisa() {

		if (pesquisaSelecionada != null) {

			PieChartModel graficoPizzaPesquisa = new PieChartModel();

			List<CandidatoPesquisa> listaCandidatos = candidatoPesquisaDAO.getByPesquisa(pesquisaSelecionada);

			for (CandidatoPesquisa cp : listaCandidatos) {
				graficoPizzaPesquisa.set(cp.getCandidato().getNomeCandidato(), cp.getIntencaoVoto());
			}

			graficoPizzaPesquisa.setTitle("Resultados da Pesquisa");
			graficoPizzaPesquisa.setLegendPosition("w");
			
			

			return graficoPizzaPesquisa;
		} else {
			return null;
		}
	}

	public BarChartModel getGraficoBarraPesquisa() {
		
		if (pesquisaSelecionada != null) {

			BarChartModel model = new BarChartModel();

			List<CandidatoPesquisa> listaCandidatos = candidatoPesquisaDAO.getByPesquisa(pesquisaSelecionada);
			ChartSeries candidatos = new ChartSeries();
			candidatos.setLabel("Candidatos");
			for (CandidatoPesquisa cp : listaCandidatos) {
				candidatos.set(cp.getCandidato().getNomeCandidato(), cp.getIntencaoVoto());
			}
			model.addSeries(candidatos);
			return model;
		} else {
			return null;
		}
	}
	
	public CartesianChartModel getGraficoBarraLinha() {		
		
		CartesianChartModel combinedModel = new BarChartModel();		
		List<Pesquisa> pesquisas = pesquisaDAO.getAll();

		// cria gráfico de barras
        BarChartSeries barra = new BarChartSeries();
        barra.setLabel("Pesquisas");
		 
		for (Pesquisa p : pesquisas) {
			barra.set(p.getId(), p.getNumeroEleitores());
		}
		
		combinedModel.addSeries(barra);		
       
        List<Candidato> candidatos = candidatoDAO.getAll();     
        
        // cria linhas para cada candidato da pesquisa
        for (Candidato candidato : candidatos) {
        	LineChartSeries  linha = new LineChartSeries();
        	linha.setLabel(candidato.getNomeCandidato()); 
        	
        	for (int i = 0; i < pesquisas.size(); i++) {
        		Pesquisa pesquisa = pesquisas.get(i);
        		CandidatoPesquisa cp = candidatoPesquisaDAO.getByPesquisaAndCandidato(pesquisa, candidato);
        		
        		// calcula a porcentagem de cada candidato para a pesquisa.
        		linha.set(pesquisa.getId(), (cp.getIntencaoVoto() * 100 / pesquisa.getNumeroEleitores()));
        	}        	
        	combinedModel.addSeries(linha);      	
        }
                  
        combinedModel.setTitle("Número de Eleitores e Candidatos");
        combinedModel.setLegendPosition("ne");
                
        return combinedModel;
	}

	public Pesquisa getPesquisaSelecionada() {
		return pesquisaSelecionada;
	}

	public void setPesquisaSelecionada(Pesquisa pesquisaSelecionada) {
		this.pesquisaSelecionada = pesquisaSelecionada;
	}
	
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selecionado",
                        "Indíce: " + event.getSource() + ", Series Index:" + event.getSeriesIndex());         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
