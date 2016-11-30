package br.univel.model.dto;

import java.io.Serializable;

import br.univel.model.enums.Solicitacao;

/**
 * Classe persistida que contem as informações dos Profissionais
 *
 * @author Eduardo
 *
 */

public class Profissional implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	private Integer idProfissional;

	private String nomeProfissional;

	private String dataNascimento;

	private String login;

	private String senha;

	private Solicitacao Requisicao;

	/**
	 * @return the idUsuario
	 */
	public Integer getIdProfissional() {
		return idProfissional;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public Profissional setIdProfissional(Integer idUsuario) {
		this.idProfissional = idUsuario;
		return this;
	}

	/**
	 * @return the requisicao
	 */
	public Solicitacao getRequisicao() {
		return Requisicao;
	}

	/**
	 * @param requisicao
	 *            the requisicao to set
	 */
	public Profissional setRequisicao(Solicitacao requisicao) {
		Requisicao = requisicao;
		return this;
	}

	/**
	 * @return the nomeProfissional
	 */
	public String getNomeProfissional() {
		return nomeProfissional;
	}

	/**
	 * @param nomeProfissional
	 *            the nomeProfissional to set
	 */
	public Profissional setNomeProfissional(final String nomeProfissional) {
		this.nomeProfissional = nomeProfissional;
		return this;
	}

	/**
	 * @return the dataNascimento
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 *            the dataNascimento to set
	 */
	public Profissional setDataNascimento(final String dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public Profissional setLogin(final String login) {
		this.login = login;
		return this;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public Profissional setSenha(final String senha) {
		this.senha = senha;
		return this;
	}

}
