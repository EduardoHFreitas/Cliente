package br.univel.model;

import br.univel.model.dto.Profissional;

/**
 * Singleton para passagem de profissional entre telas
 *
 * @author Eduardo
 *
 */
public final class ProfissionalAlterar {

	private static ProfissionalAlterar instancia = new ProfissionalAlterar();
	private static Profissional profissional;

	private ProfissionalAlterar() {
	}

	/**
	 * Retorna instancia do singleton
	 *
	 * @return
	 */
	public static synchronized ProfissionalAlterar getInstancia() {
		if (instancia == null) {
			instancia = new ProfissionalAlterar();
		}
		return instancia;
	}

	/**
	 * setar um profissional ao atributo do singleton
	 *
	 * @param profissional
	 * @return
	 */
	public ProfissionalAlterar setProfissional(final Profissional profissional) {
		this.profissional = profissional;
		return this;
	}

	/**
	 * retornar o valor do atributo do singleton
	 *
	 * @return
	 */
	public static Profissional getProfissional() {
		return profissional;
	}

}
