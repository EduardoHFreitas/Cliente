package br.univel.model.enums;

public enum Telas {

	MENU("Menu"),
	INICIAL("Tela inicial"),
	CLIENTE("Tela de clientes"),
	PROFISSIONAL("Tela de profissionais"),
	DADOS_CLIENTE("Tela de dados do cliente"),
	DADOS_PROFISSIONAL("Tela de dados do profissional");

	private String valor;

	Telas(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}

}
