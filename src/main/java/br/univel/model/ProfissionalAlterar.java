package br.univel.model;

import br.univel.model.dto.Cliente;
import br.univel.model.dto.Profissional;

public class ProfissionalAlterar {

	private ProfissionalAlterar instancia = new ProfissionalAlterar();
	private static Profissional profissional;

	private ProfissionalAlterar() {

	}

	public ProfissionalAlterar getInstancia() {
		if (instancia == null) {
			instancia = new ProfissionalAlterar();
		}
		return instancia;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public static Profissional getProfissional() {
		return profissional;
	}

}
