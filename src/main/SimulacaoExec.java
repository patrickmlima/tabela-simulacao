package main;

import model.TabelaSimulacao;

public class SimulacaoExec {
	// definir parâmetros
	public static void main(String args[]) {
		// construir objeto da tabela e executar
		TabelaSimulacao simulacao = new TabelaSimulacao();
		simulacao.gerarSimulacao(10, 15, 9, 11, 300);
	}
}
