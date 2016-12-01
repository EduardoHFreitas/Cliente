package br.univel.control;

import java.io.IOException;
import java.util.Objects;

import javax.swing.JOptionPane;

import br.univel.model.dto.Profissional;

/**
 * Enviar um profissional ao servidor
 *
 * @author Eduardo
 *
 */
public final class EnviarProfissional {

	private EnviarProfissional() {
	}

	/**
	 * Executa a comunicação com o servidor e envia um profissional
	 * @param profissionalIncluir
	 * @return
	 * @throws IOException
	 */
	public static boolean enviar(final Profissional profissionalIncluir) throws IOException {
		Objects.requireNonNull(profissionalIncluir, "Profissional nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		Object retorno = (Object) conexao.executaComunicacao(profissionalIncluir);

		if (retorno.getClass().equals(String.class)) {
			String retornoCast = (String) retorno;
			JOptionPane.showMessageDialog(null, retornoCast);
		}
		return true;
	}
}
