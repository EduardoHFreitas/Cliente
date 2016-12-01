package br.univel.control;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import br.univel.model.dto.Profissional;

/**
 * Listar profissionais do servidor
 *
 * @author Eduardo
 *
 */
public final class ListarProfissionais {

	private ListarProfissionais() {
	}

	/**
	 * Faz uma requisicao de lista de profissionais ao servidor e retorna a
	 * lista obtida
	 *
	 * @param profissional
	 * @return
	 * @throws IOException
	 */
	public static List<Profissional> getListaProfissionais(final Profissional profissional) throws IOException {
		Objects.requireNonNull(profissional, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		Object retorno = (Object) conexao.executaComunicacao(profissional);

		return (List<Profissional>) retorno;
	}
}
