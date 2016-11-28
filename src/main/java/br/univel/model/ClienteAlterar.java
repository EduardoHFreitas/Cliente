package br.univel.model;

import br.univel.model.dto.Cliente;

public class ClienteAlterar {

	private ClienteAlterar instancia = new ClienteAlterar();
	private static Cliente cliente;

	private ClienteAlterar() {

	}

	public ClienteAlterar getInstancia() {
		if (instancia == null){
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
