package br.univel.control;

import java.io.IOException;
import java.util.Objects;

import javax.swing.JOptionPane;

import br.univel.model.dto.Cliente;

/**
 * Enviar um Cliente para o servidor
 *
 * @author Eduardo
 *
 */
public final class EnviarCliente {

	private EnviarCliente() {
	}

	/**
	 * Executa a comunicação com o server e envia o objeto cliente
	 *
	 * @param clienteIncluir
	 * @return
	 * @throws IOException
	 */
	public static boolean enviar(final Cliente clienteIncluir) throws IOException {
		Objects.requireNonNull(clienteIncluir, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		Object retorno = (Object) conexao.executaComunicacao(clienteIncluir);

		if (retorno.getClass().equals(String.class)) {
			String retornoCast = (String) retorno;
			JOptionPane.showMessageDialog(null, retornoCast);
		}
		return true;
	}

}
