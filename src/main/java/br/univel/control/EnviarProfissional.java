package br.univel.control;

import java.util.Objects;

import javax.swing.JOptionPane;

import br.univel.model.dto.Profissional;

public class EnviarProfissional {

	private static Object retorno;

	public static boolean enviar(Profissional profissionalIncluir) {
		Objects.requireNonNull(profissionalIncluir, "Profissional nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		retorno = (Object) conexao.ExecutaComunicacao(profissionalIncluir);

		if (retorno.getClass().equals(String.class)) {
			String retornoCast = (String) retorno;
			JOptionPane.showMessageDialog(null, retornoCast);
		}
		return true;
	}
}
