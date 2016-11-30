package br.univel.model.dto;

import java.io.Serializable;

import br.univel.model.enums.Solicitacao;

/**
 * Classe persistida que contem as informações dos clientes
 *
 * @author Eduardo
 *
 */

public class Cliente implements Serializable {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private Integer idCliente;

	private String nomeCliente;

	private String dataNascimento;

	private String numeroCPF;

	private String numeroRG;

	private Solicitacao Requisicao;

	/**
	 * @return the idCliente
	 */
	public Integer getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente
	 *            the idCliente to set
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * retorna o nome do cliente
	 *
	 * @return string
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * atribui um nome ao cliente
	 *
	 * @param nomeCliente
	 * @return Objeto em si
	 */
	public Cliente setNomeCliente(final String nomeCliente) {
		this.nomeCliente = nomeCliente;
		return this;
	}

	/**
	 * retorna a data de nascimento do cliente
	 *
	 * @return string
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * atribui uma data de nasc ao cliente
	 *
	 * @param dataNascimento
	 * @return Objeto em si
	 */
	public Cliente setDataNascimento(final String dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}

	/**
	 * retorna o CPF do cliente
	 *
	 * @return string
	 */
	public String getNumeroCPF() {
		return numeroCPF;
	}

	/**
	 * atribui um CPF ao cliente
	 *
	 * @param numeroCPF
	 * @return Objeto em si
	 */
	public Cliente setNumeroCPF(final String numeroCPF) {
		this.numeroCPF = numeroCPF;
		return this;
	}

	/**
	 * retorna o RG do cliente
	 *
	 * @return string
	 */
	public String getNumeroRG() {
		return numeroRG;
	}

	/**
	 * atribui um RG ao cliente
	 *
	 * @param numeroRG
	 * @return Objeto em si
	 */
	public Cliente setNumeroRG(final String numeroRG) {
		this.numeroRG = numeroRG;
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
	public Cliente setRequisicao(Solicitacao requisicao) {
		Requisicao = requisicao;
		return this;
	}

}
