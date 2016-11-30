package br.univel.model;

import br.univel.model.dto.Cliente;

/**
 * Singleton para passagem de Cliente entre telas
 *
 * @author Eduardo
 *
 */
public class ClienteAlterar {

	private static ClienteAlterar instancia = new ClienteAlterar();
	private static Cliente cliente;

	private ClienteAlterar() {

	}

	public static synchronized ClienteAlterar getInstancia() {
		if (instancia == null) {
			instancia = new ClienteAlterar();
		}
		return instancia;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static Cliente getCliente() {
		return cliente;
	}

}
