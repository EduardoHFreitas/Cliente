package br.univel.control;

import java.util.ArrayList;
import java.util.Objects;

import br.univel.model.dto.Cliente;

public class ListarClientes {

	private static Object retorno;
	private static ArrayList<Cliente> listaClientes = null;

	public static ArrayList<Cliente> getListaClientes(Cliente cliente) {
		Objects.requireNonNull(cliente, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		retorno = (Object) conexao.ExecutaComunicacao(cliente);
		listaClientes = (ArrayList<Cliente>) retorno;

		return listaClientes;
	}
}
