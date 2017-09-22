package model;

import java.util.Random;

public class LinhaSimulacao {
	private Float tempoUltimaChegada;
	
	private Float tempoChegadaRelogio;
	
	private Float tempoServico;
	
	private Float tempoInicioServico;
	
	private Float tempoFinalServico;
	
	private Float tempoClienteFila;
	
	private Float tempoClienteSistema;
	
	private Float tempoOciosoOperador;
	
	private Float delay = 0f;
	
	
	public LinhaSimulacao(Float tec, Float ts) {
		this.tempoUltimaChegada = tec;
		this.tempoServico = ts;
	}
	
	public LinhaSimulacao(Float minTEC, Float maxTEC, Float minTS, Float maxTS) {
		if(minTEC == null) {
			minTEC = 0f;
		}
		if(minTS == null) {
			minTS = 0f;
		}
		this.tempoUltimaChegada = new Random().nextFloat() * (maxTEC - minTEC) + minTEC;
		this.tempoServico = new Random().nextFloat() * (maxTS - minTS) + minTS;
	}
	
	public void setupData(LinhaSimulacao previous) {
		if(previous == null) {
			// tempo desde a ultima chegada
			this.tempoChegadaRelogio = this.tempoUltimaChegada;
			// tempo incio de servico
			this.tempoInicioServico = this.tempoChegadaRelogio;
			// tempo de espera
			this.tempoClienteFila = 0f;
			// tempo ocioso operador			
			this.tempoOciosoOperador = this.tempoUltimaChegada;
		} else {
			// tempo desde a ultima chegada
			this.tempoChegadaRelogio = previous.getTempoChegadaRelogio() + this.tempoUltimaChegada;
			
			// tempo incio de servico
			this.delay = 0f;
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

	public Float getTempoUltimaChegada() {
		return tempoUltimaChegada;
	}

	public void setTempoUltimaChegada(Float tempoUltimaChegada) {
		this.tempoUltimaChegada = tempoUltimaChegada;
	}

	public Float getTempoChegadaRelogio() {
		return tempoChegadaRelogio;
	}

	public void setTempoChegadaRelogio(Float tempoChegadaRelogio) {
		this.tempoChegadaRelogio = tempoChegadaRelogio;
	}

	public Float getTempoServico() {
		return tempoServico;
	}

	public void setTempoServico(Float tempoServico) {
		this.tempoServico = tempoServico;
	}

	public Float getTempoInicioServico() {
		return tempoInicioServico;
	}

	public void setTempoInicioServico(Float tempoInicioServico) {
		this.tempoInicioServico = tempoInicioServico;
	}

	public Float getTempoFinalServico() {
		return tempoFinalServico;
	}

	public void setTempoFinalServico(Float tempoFinalServico) {
		this.tempoFinalServico = tempoFinalServico;
	}

	public Float getTempoClienteFila() {
		return tempoClienteFila;
	}

	public void setTempoClienteFila(Float tempoClienteFila) {
		this.tempoClienteFila = tempoClienteFila;
	}

	public Float getTempoClienteSistema() {
		return tempoClienteSistema;
	}

	public void setTempoClienteSistema(Float tempoClienteSistema) {
		this.tempoClienteSistema = tempoClienteSistema;
	}

	public Float getTempoOciosoOperador() {
		return tempoOciosoOperador;
	}

	public void setTempoOciosoOperador(Float tempoOciosoOperador) {
		this.tempoOciosoOperador = tempoOciosoOperador;
	}
}
