package br.univel.control;

import java.net.ConnectException;
import java.util.Objects;

import javax.swing.JOptionPane;

import br.univel.model.dto.ObjetoRetorno;
import br.univel.model.dto.Usuario;
import br.univel.view.TelaPrincipal;

/**
 * Verificar o login do usuario no servidor
 *
 * @author Eduardo
 *
 */
public class VerificarLogin {
	private ObjetoRetorno retorno;
	private Usuario usuarioLogado;

	public VerificarLogin(Usuario usuarioLogin) {
		Objects.requireNonNull(usuarioLogin, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		retorno = (ObjetoRetorno) conexao.ExecutaComunicacao(usuarioLogin);
		usuarioLogado = (Usuario) retorno.getObjetoRetorno();

		if (usuarioLogin != null) {
			new TelaPrincipal();
		} else {
			JOptionPane.showMessageDialog(null, "Verifique seu Usuario e/ou Senha!");
			return;
		}
	}
}
