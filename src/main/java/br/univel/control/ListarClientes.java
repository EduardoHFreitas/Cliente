package br.univel.control;

import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JOptionPane;

import br.univel.model.dto.Cliente;
import br.univel.model.dto.ObjetoRetorno;
import br.univel.model.dto.Usuario;
import br.univel.view.TelaPrincipal;

public class ListarClientes {

	private static ObjetoRetorno retorno;
	private static ArrayList<Cliente> listaClientes = null;

	public static ArrayList<Cliente> getListaClientes(Cliente cliente) {
		Objects.requireNonNull(cliente, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		retorno = (ObjetoRetorno) conexao.ExecutaComunicacao(cliente);
		listaClientes = (ArrayList<Cliente>) retorno.getObjetoRetorno();

		return listaClientes;
	}
}
