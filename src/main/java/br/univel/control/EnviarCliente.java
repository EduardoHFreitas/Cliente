package br.univel.control;

import java.util.Objects;

import javax.swing.JOptionPane;

import br.univel.model.dto.Cliente;
import br.univel.model.dto.Profissional;

public class EnviarCliente {
	private static Object retorno;

	public static boolean enviar(Cliente clienteIncluir) {
		Objects.requireNonNull(clienteIncluir, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		retorno = (Object) conexao.ExecutaComunicacao(clienteIncluir);

		if (retorno.getClass().equals(String.class)) {
			String retornoCast = (String) retorno;
			JOptionPane.showMessageDialog(null, retornoCast);
		}
		return true;
	}

}
