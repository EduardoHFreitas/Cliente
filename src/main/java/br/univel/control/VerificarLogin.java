package br.univel.control;

import java.util.Objects;

import javax.swing.JOptionPane;

import br.univel.model.dto.Profissional;
import br.univel.view.TelaPrincipal;

/**
 * Verificar o login do usuario no servidor
 *
 * @author Eduardo
 *
 */
public class VerificarLogin {
	private static Profissional usuarioLogado;

	public static Boolean loginValido(Profissional usuarioLogin) {
		Objects.requireNonNull(usuarioLogin, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		usuarioLogado = (Profissional) conexao.ExecutaComunicacao(usuarioLogin);

		if (usuarioLogado == null) {
			JOptionPane.showMessageDialog(null, "Verifique seu Usuario e/ou Senha!");
			return false;
		}
		return true;
	}
}
