package model;

import java.util.Random;

public class LinhaSimulacao {
	private Integer tempoUltimaChegada;
	
	private Integer tempoChegadaRelogio;
	
	private Integer tempoServico;
	
	private Integer tempoInicioServico;
	
	private Integer tempoFinalServico;
	
	private Integer tempoClienteFila;
	
	private Integer tempoClienteSistema;
	
	private Integer tempoOciosoOperador;
	
	private Integer delay = 0;
	
	
	public LinhaSimulacao(Integer tec, Integer ts) {
		this.tempoUltimaChegada = tec;
		this.tempoServico = ts;
	}
	
	public LinhaSimulacao(Integer minTEC, Integer maxTEC, Integer minTS, Integer maxTS) {
		if(minTEC == null) {
			minTEC = 0;
		}
		if(minTS == null) {
			minTS = 0;
		}
		this.tempoUltimaChegada = new Random().nextInt(maxTEC - minTEC + 1) + minTEC;
		this.tempoServico = new Random().nextInt(maxTS - minTS + 1) + minTS;
	}
	
	public void setupData(LinhaSimulacao previous) {
		if(previous == null) {
			// tempo desde a ultima chegada
			this.tempoChegadaRelogio = this.tempoUltimaChegada;
			// tempo incio de servico
			this.tempoInicioServico = this.tempoChegadaRelogio;
			// tempo de espera
			this.tempoClienteFila = 0;
			// tempo ocioso operador			
			this.tempoOciosoOperador = this.tempoUltimaChegada;
		} else {
			// tempo desde a ultima chegada
			this.tempoChegadaRelogio = previous.getTempoChegadaRelogio() + this.tempoUltimaChegada;
			
			// tempo incio de servico
			this.delay = 0;
			if(this.tempoUltimaChegada < (previous.getTempoServico() + previous.delay)) {
				this.delay = previous.getTempoServico() - this.tempoUltimaChegada + previous.delay;
			}
			this.tempoInicioServico = this.tempoChegadaRelogio + this.delay;
			// tempo de espera
			this.tempoClienteFila = this.delay;
			// tempo ocioso operador
			this.tempoOciosoOperador = Math.abs(previous.getTempoServico() + previous.delay - this.tempoUltimaChegada);
		}
		this.tempoFinalServico = this.tempoInicioServico + this.tempoServico;
		this.tempoClienteSistema = this.tempoClienteFila + this.tempoServico;
		
	}

	public Integer getTempoUltimaChegada() {
		return tempoUltimaChegada;
	}

	public void setTempoUltimaChegada(Integer tempoUltimaChegada) {
		this.tempoUltimaChegada = tempoUltimaChegada;
	}

	public Integer getTempoChegadaRelogio() {
		return tempoChegadaRelogio;
	}

	public void setTempoChegadaRelogio(Integer tempoChegadaRelogio) {
		this.tempoChegadaRelogio = tempoChegadaRelogio;
	}

	public Integer getTempoServico() {
		return tempoServico;
	}

	public void setTempoServico(Integer tempoServico) {
		this.tempoServico = tempoServico;
	}

	public Integer getTempoInicioServico() {
		return tempoInicioServico;
	}

	public void setTempoInicioServico(Integer tempoInicioServico) {
		this.tempoInicioServico = tempoInicioServico;
	}

	public Integer getTempoFinalServico() {
		return tempoFinalServico;
	}

	public void setTempoFinalServico(Integer tempoFinalServico) {
		this.tempoFinalServico = tempoFinalServico;
	}

	public Integer getTempoClienteFila() {
		return tempoClienteFila;
	}

	public void setTempoClienteFila(Integer tempoClienteFila) {
		this.tempoClienteFila = tempoClienteFila;
	}

	public Integer getTempoClienteSistema() {
		return tempoClienteSistema;
	}

	public void setTempoClienteSistema(Integer tempoClienteSistema) {
		this.tempoClienteSistema = tempoClienteSistema;
	}

	public Integer getTempoOciosoOperador() {
		return tempoOciosoOperador;
	}

	public void setTempoOciosoOperador(Integer tempoOciosoOperador) {
		this.tempoOciosoOperador = tempoOciosoOperador;
	}
	
	
}
