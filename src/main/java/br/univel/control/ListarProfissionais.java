package br.univel.control;

import java.util.ArrayList;
import java.util.Objects;

import br.univel.model.dto.ObjetoRetorno;
import br.univel.model.dto.Profissional;

public class ListarProfissionais {

	private static ObjetoRetorno retorno;
	private static ArrayList<Profissional> listaProfissionais = null;

	public static ArrayList<Profissional> getListaProfissionais(Profissional profissional) {
		Objects.requireNonNull(profissional, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		retorno = (ObjetoRetorno) conexao.ExecutaComunicacao(profissional);
		listaProfissionais = (ArrayList<Profissional>) retorno.getObjetoRetorno();

		return listaProfissionais;
	}
}
