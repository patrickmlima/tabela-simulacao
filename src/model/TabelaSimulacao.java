package model;

import java.util.ArrayList;
import java.util.List;

public class TabelaSimulacao {
	private ArrayList<LinhaSimulacao> linhas = new ArrayList<LinhaSimulacao>();
	
	public TabelaSimulacao() {
		// construir lista de linhas
	}
	
	public void gerarSimulacao(Integer maxTEC, Integer maxTS, Integer tempoSimulado) {
		gerarSimulacao(0, maxTEC, 0, maxTS, tempoSimulado);
	}
	
	public void gerarSimulacao(Integer minTEC, Integer maxTEC, Integer minTS, Integer maxTS, Integer tempoSimulado) {
		LinhaSimulacao prev = null;
		while(true) {
			LinhaSimulacao linha = new LinhaSimulacao(minTEC, maxTEC, minTS, maxTS);
			linha.setupData(prev);
			linhas.add(linha);
			prev = linha;
			
			System.out.println(linha.getTempoUltimaChegada() + "; " + linha.getTempoChegadaRelogio() + "; " + 
			linha.getTempoServico() + "; " + linha.getTempoInicioServico() + "; " + linha.getTempoClienteFila() + "; " +
					linha.getTempoFinalServico() + "; " + linha.getTempoClienteSistema() + "; " + linha.getTempoOciosoOperador());
			if(linha.getTempoChegadaRelogio() > tempoSimulado) {
				break;
			}
			
		}
	}
	
	public void gerarTeste() {
		int tec[] = {15, 12, 10, 10, 12, 15, 10, 12, 10, 10, 10, 12, 15, 12, 12};
		int ts[] = {11, 10, 9, 10, 9, 10, 11, 9, 11, 10, 11, 9, 10, 9, 11};
		LinhaSimulacao prev = null;
		for(int i = 0; i < 15; i++) {
			LinhaSimulacao linha = new LinhaSimulacao(tec[i], ts[i]);
			linha.setupData(prev);
			linhas.add(linha);
			prev = linha;
			System.out.println(linha.getTempoUltimaChegada() + "; " + linha.getTempoChegadaRelogio() + "; " + 
			linha.getTempoServico() + "; " + linha.getTempoInicioServico() + "; " + linha.getTempoClienteFila() + "; " +
					linha.getTempoFinalServico() + "; " + linha.getTempoClienteSistema() + "; " + linha.getTempoOciosoOperador());
		}
	}

	public List<LinhaSimulacao> getLinhas() {
		return linhas;
	}

	public void setLinhas(ArrayList<LinhaSimulacao> linhas) {
		this.linhas = linhas;
	}
	
	
}
