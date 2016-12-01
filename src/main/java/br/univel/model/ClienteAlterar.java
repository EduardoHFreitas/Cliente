package br.univel.model;

import br.univel.model.dto.Cliente;

/**
 * Singleton para passagem de Cliente entre telas
 *
 * @author Eduardo
 *
 */
public final class ClienteAlterar {

	private static ClienteAlterar instancia = new ClienteAlterar();
	private static Cliente cliente;

	private ClienteAlterar() {

	}

	/**
	 * Retornar instancia do singleton
	 * @return
	 */
	public static synchronized ClienteAlterar getInstancia() {
		if (instancia == null) {
			instancia = new ClienteAlterar();
		}
		return instancia;
	}

	/**
	 * Setar um valor ao atributo cliente
	 * @param cliente
	 */
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Retornar o valor do atributo cliente
	 * @return
	 */
	public static Cliente getCliente() {
		return cliente;
	}

}
