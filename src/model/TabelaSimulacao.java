package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabelaSimulacao {
	private ArrayList<LinhaSimulacao> linhas = new ArrayList<LinhaSimulacao>();
	
	private Float tempoServicoTotal = 0f;
	
	private Float tempoClienteFilaTotal = 0f;
	
	private Float tempoClienteSistemaTotal = 0f;
	
	private Float tempoLivreOperadorTotal = 0f;
	
	
	public TabelaSimulacao() {
		// construir lista de linhas
	}
	
	public void gerarSimulacao(Float maxTEC, Float maxTS, Float tempoSimulado) {
		gerarSimulacao(0f, maxTEC, 0f, maxTS, tempoSimulado);
	}
	
	public void gerarSimulacao(Float minTEC, Float maxTEC, Float minTS, Float maxTS, Float tempoSimulado) {
		reset();
		
		LinhaSimulacao prev = null;
		while(true) {
			LinhaSimulacao linha = new LinhaSimulacao(minTEC, maxTEC, minTS, maxTS);
			linha.setupData(prev);
			linhas.add(linha);
			prev = linha;
			
			System.out.println(
					linha.getTempoUltimaChegada() + "; " + linha.getTempoChegadaRelogio() + "; " + 
					linha.getTempoServico() + "; " + linha.getTempoInicioServico() + "; " + 
					linha.getTempoClienteFila() + "; " + linha.getTempoFinalServico() + "; " + 
					linha.getTempoClienteSistema() + "; " + linha.getTempoOciosoOperador()
					);
			
			tempoServicoTotal += linha.getTempoServico();
			tempoClienteFilaTotal += linha.getTempoClienteFila();
			tempoClienteSistemaTotal += linha.getTempoClienteSistema();
			tempoLivreOperadorTotal += linha.getTempoOciosoOperador();
			
			if(linha.getTempoChegadaRelogio() > tempoSimulado) {
				break;
			}
		}
		
		System.out.println("Tempo de servico total: " + tempoServicoTotal);
		System.out.println("Tempo do cliente na fila: " + tempoClienteFilaTotal);
		System.out.println("Tempo do cliente no sistema: " + tempoClienteSistemaTotal);
		System.out.println("Tempo livre operador: " + tempoLivreOperadorTotal);
	}
	
	public void gerarSimulacao(Float minTEC, Float maxTEC, Float minTS, Float maxTS, 
			Float tempoSimulado, File output) throws IOException {
		reset();
		FileWriter writer = new FileWriter(output);
		
		LinhaSimulacao prev = null;
		while(true) {
			LinhaSimulacao linha = new LinhaSimulacao(minTEC, maxTEC, minTS, maxTS);
			linha.setupData(prev);
			linhas.add(linha);
			prev = linha;
			
			String resultStr = 
					linha.getTempoUltimaChegada() + "; " + linha.getTempoChegadaRelogio() + "; " + 
					linha.getTempoServico() + "; " + linha.getTempoInicioServico() + "; " + 
					linha.getTempoClienteFila() + "; " + linha.getTempoFinalServico() + "; " + 
					linha.getTempoClienteSistema() + "; " + linha.getTempoOciosoOperador() + "\n";
			
			writer.write(resultStr);
			
			tempoServicoTotal += linha.getTempoServico();
			tempoClienteFilaTotal += linha.getTempoClienteFila();
			tempoClienteSistemaTotal += linha.getTempoClienteSistema();
			tempoLivreOperadorTotal += linha.getTempoOciosoOperador();
			
			if(linha.getTempoChegadaRelogio() > tempoSimulado) {
				break;
			}
		}
		
		System.out.println("Tempo de servico total: " + tempoServicoTotal);
		System.out.println("Tempo do cliente na fila: " + tempoClienteFilaTotal);
		System.out.println("Tempo do cliente no sistema: " + tempoClienteSistemaTotal);
		System.out.println("Tempo livre operador: " + tempoLivreOperadorTotal);
		writer.flush();
		writer.close();
	}
	
	public void gerarSimulacao(int k, float lambda, float lambdaTS, File output) throws IOException {
		reset();
		LinhaSimulacao prev = null;
		FileWriter writer = new FileWriter(output);
		//while(true) {
		for(int i=0; i < k; i++) {
			
			LinhaSimulacao linha = new LinhaSimulacao(lambda, lambdaTS);
			linha.setupData(prev);
			linhas.add(linha);
			prev = linha;
			
			String resultStr = 
					linha.getTempoUltimaChegada() + "; " + linha.getTempoChegadaRelogio() + "; " + 
					linha.getTempoServico() + "; " + linha.getTempoInicioServico() + "; " + 
					linha.getTempoClienteFila() + "; " + linha.getTempoFinalServico() + "; " + 
					linha.getTempoClienteSistema() + "; " + linha.getTempoOciosoOperador() + "\n";
			
			writer.write(resultStr);
			
			tempoServicoTotal += linha.getTempoServico();
			tempoClienteFilaTotal += linha.getTempoClienteFila();
			tempoClienteSistemaTotal += linha.getTempoClienteSistema();
			tempoLivreOperadorTotal += linha.getTempoOciosoOperador();
			
	
		}
		writer.flush();
		writer.close();
		
		
	}
	
	
	
	
	/*public void gerarTeste() {
		float tec[] = {15, 12, 10, 10, 12, 15, 10, 12, 10, 10, 10, 12, 15, 12, 12};
		float ts[] = {11, 10, 9, 10, 9, 10, 11, 9, 11, 10, 11, 9, 10, 9, 11};
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
	}*/

	public List<LinhaSimulacao> getLinhas() {
		return linhas;
	}

	public void setLinhas(ArrayList<LinhaSimulacao> linhas) {
		this.linhas = linhas;
	}
	
	private void reset() {
		tempoServicoTotal = 0f;
		tempoClienteFilaTotal = 0f;
		tempoClienteSistemaTotal = 0f;
		tempoLivreOperadorTotal = 0f;
	}
}
