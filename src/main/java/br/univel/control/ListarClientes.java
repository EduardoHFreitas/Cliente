package br.univel.control;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import br.univel.model.dto.Cliente;

/**
 * Listar clientes do servidor
 *
 * @author Eduardo
 *
 */
public final class ListarClientes {

	private ListarClientes() {

	}

	/**
	 * Faz uma requisicao de lista de clientes ao servidor e retorna a lista obtida
	 * @param cliente
	 * @return
	 * @throws IOException
	 */
	public static List<Cliente> getListaClientes(final Cliente cliente) throws IOException {
		Objects.requireNonNull(cliente, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		Object retorno = (Object) conexao.executaComunicacao(cliente);

		return (List<Cliente>) retorno;
	}
}
