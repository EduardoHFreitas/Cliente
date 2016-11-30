package br.univel.control;

import java.util.ArrayList;
import java.util.Objects;

import br.univel.model.dto.Profissional;

public class ListarProfissionais {

	private static Object retorno;
	private static ArrayList<Profissional> listaProfissionais = null;

	public static ArrayList<Profissional> getListaProfissionais(Profissional profissional) {
		Objects.requireNonNull(profissional, "Usuario nao pode ser nulo!");

		ConexaoServidor conexao = new ConexaoServidor();
		retorno = (Object) conexao.ExecutaComunicacao(profissional);
		listaProfissionais = (ArrayList<Profissional>) retorno;

		return listaProfissionais;
	}
}
