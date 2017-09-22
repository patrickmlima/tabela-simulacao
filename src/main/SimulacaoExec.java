package main;

import java.io.File;

import model.TabelaSimulacao;

public class SimulacaoExec {
	public static void main(String args[]) {
		int TOTAL_DIAS = 20;
		float MINUTOS_DIA = (18 - 8) * 60f;
		
		TabelaSimulacao simulacao = new TabelaSimulacao();
		
		//for(int i = 0; i < TOTAL_DIAS; ++i) {
			try {
				File file = new File("resources/simulacao_poison.txt");
				System.out.println("saved in: " + file.getAbsolutePath());
				//simulacao.gerarSimulacao(10f, 15f, 9f, 13f, MINUTOS_DIA, file);
				simulacao.gerarSimulacao(10000,12.49f, 11.01f, file);
				System.out.println("-----------------------------------------------------\n");
			} catch(Exception e) {
				
			}
		//}
	}
}
