package br.univel.view.cliente;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.univel.control.EnviarCliente;
import br.univel.model.ClienteAlterar;
import br.univel.model.dto.Cliente;
import br.univel.model.enums.Solicitacao;
import br.univel.model.enums.Telas;
import br.univel.view.TelaPrincipal;

public class DadosCliente extends JPanel {
	private JTextField tfNome;
	private JTextField tfDataNasc;
	private JTextField tfCpf;
	private JTextField tfRg;
	private JButton btnCadastrar;

	public DadosCliente() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				preencheCampos();
			}
		});

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 30, 46, 359, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 20, 20, 20, 20, 23, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Nome:");
		final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		tfNome = new JTextField();
		final GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.anchor = GridBagConstraints.NORTH;
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.gridx = 2;
		gbc_tfNome.gridy = 1;
		add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);

		JLabel lblDataNasc = new JLabel("Data Nasc:");
		final GridBagConstraints gbc_lblDataNasc = new GridBagConstraints();
		gbc_lblDataNasc.anchor = GridBagConstraints.EAST;
		gbc_lblDataNasc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataNasc.gridx = 1;
		gbc_lblDataNasc.gridy = 2;
		add(lblDataNasc, gbc_lblDataNasc);

		tfDataNasc = new JTextField();
		tfDataNasc.setColumns(10);
		final GridBagConstraints gbc_tfDataNasc = new GridBagConstraints();
		gbc_tfDataNasc.anchor = GridBagConstraints.NORTH;
		gbc_tfDataNasc.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDataNasc.insets = new Insets(0, 0, 5, 0);
		gbc_tfDataNasc.gridx = 2;
		gbc_tfDataNasc.gridy = 2;
		add(tfDataNasc, gbc_tfDataNasc);

		JLabel lblCpf = new JLabel("CPF");
		final GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.anchor = GridBagConstraints.EAST;
		gbc_lblCpf.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpf.gridx = 1;
		gbc_lblCpf.gridy = 3;
		add(lblCpf, gbc_lblCpf);

		tfCpf = new JTextField();
		tfCpf.setColumns(10);
		final GridBagConstraints gbc_tfCpf = new GridBagConstraints();
		gbc_tfCpf.anchor = GridBagConstraints.NORTH;
		gbc_tfCpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCpf.insets = new Insets(0, 0, 5, 0);
		gbc_tfCpf.gridx = 2;
		gbc_tfCpf.gridy = 3;
		add(tfCpf, gbc_tfCpf);

		JLabel lblRg = new JLabel("RG");
		final GridBagConstraints gbc_lblRg = new GridBagConstraints();
		gbc_lblRg.anchor = GridBagConstraints.EAST;
		gbc_lblRg.insets = new Insets(0, 0, 5, 5);
		gbc_lblRg.gridx = 1;
		gbc_lblRg.gridy = 4;
		add(lblRg, gbc_lblRg);

		tfRg = new JTextField();
		tfRg.setColumns(10);
		final GridBagConstraints gbc_tfRg = new GridBagConstraints();
		gbc_tfRg.anchor = GridBagConstraints.NORTH;
		gbc_tfRg.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfRg.insets = new Insets(0, 0, 5, 0);
		gbc_tfRg.gridx = 2;
		gbc_tfRg.gridy = 4;
		add(tfRg, gbc_tfRg);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente clienteEnviar = new Cliente();
				clienteEnviar.setNomeCliente(tfNome.getText()).setDataNascimento(tfDataNasc.getText())
						.setNumeroCPF(tfCpf.getText()).setNumeroRG(tfRg.getText());
				if (ClienteAlterar.getInstancia().getCliente() == null) {
					clienteEnviar.setRequisicao(Solicitacao.INCLUIR);
				} else {
					clienteEnviar.setRequisicao(Solicitacao.ALTERAR)
							.setIdCliente(ClienteAlterar.getInstancia().getCliente().getIdCliente());
				}

				new EnviarCliente().enviar(clienteEnviar);

				CardLayout cardLayout = (CardLayout) (TelaPrincipal.getCards().getLayout());
				cardLayout.show(TelaPrincipal.getCards(), Telas.CLIENTE.toString());
			}
		});
		final GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCadastrar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnCadastrar.gridx = 2;
		gbc_btnCadastrar.gridy = 5;
		add(btnCadastrar, gbc_btnCadastrar);

		JButton bntVoltar = new JButton("Voltar");
		bntVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) (TelaPrincipal.getCards().getLayout());
				cardLayout.show(TelaPrincipal.getCards(), Telas.CLIENTE.toString());
			}
		});
		GridBagConstraints gbc_bntVoltar = new GridBagConstraints();
		gbc_bntVoltar.anchor = GridBagConstraints.NORTHEAST;
		gbc_bntVoltar.gridx = 2;
		gbc_bntVoltar.gridy = 6;
		add(bntVoltar, gbc_bntVoltar);

	}

	public void preencheCampos() {
		if (ClienteAlterar.getInstancia().getCliente() != null) {
			tfNome.setText(ClienteAlterar.getCliente().getNomeCliente());
			tfDataNasc.setText(ClienteAlterar.getCliente().getDataNascimento());
			tfCpf.setText(ClienteAlterar.getCliente().getNumeroCPF());
			tfRg.setText(ClienteAlterar.getCliente().getNumeroRG());

			btnCadastrar.setText("Salvar");
		} else {
			tfNome.setText("");
			tfDataNasc.setText("");
			tfCpf.setText("");
			tfRg.setText("");
			btnCadastrar.setText("Cadastrar");
		}
	}

}
