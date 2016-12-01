package br.univel.control;

import java.io.IOException;
import java.util.Objects;

import javax.swing.JOptionPane;

import br.univel.model.dto.Profissional;

/**
 * Verificar o login do usuario no servidor
 *
 * @author Eduardo
 *
 */
public final class VerificarLogin {

	private VerificarLogin(){

	}

	/**
	 * Enviar um profissional ao servidor e verificar o login
	 * @param usuarioLogin
	 * @return
	 * @throws IOException
	 */
	public static Boolean loginValido(final Profissional usuarioLogin) throws IOException {
		Boolean retorno = true;
		Objects.requireNonNull(usuarioLogin, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		Profissional usuarioLogado = (Profissional) conexao.executaComunicacao(usuarioLogin);

		if (usuarioLogado == null) {
			JOptionPane.showMessageDialog(null, "Verifique seu Usuario e/ou Senha!");
			retorno = false;
		}
		return retorno;
	}
}
