package br.univel.view.profissional;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.univel.control.EnviarProfissional;
import br.univel.model.EncriptarSenhaSha256;
import br.univel.model.ProfissionalAlterar;
import br.univel.model.dto.Profissional;
import br.univel.model.enums.Solicitacao;
import br.univel.model.enums.Telas;
import br.univel.view.TelaPrincipal;

public class DadosProfissional extends JPanel {
	private JTextField tfNome;
	private JTextField tfDataNasc;
	private JTextField tfLogin;
	private JPasswordField tfSenha;
	private JButton btnCadastrar;
	private JButton btnVoltar;

	public DadosProfissional() {
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
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		tfNome = new JTextField();
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.anchor = GridBagConstraints.NORTH;
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.gridx = 2;
		gbc_tfNome.gridy = 1;
		add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);

		JLabel lblDataNasc = new JLabel("Data Nasc:");
		GridBagConstraints gbc_lblDataNasc = new GridBagConstraints();
		gbc_lblDataNasc.anchor = GridBagConstraints.EAST;
		gbc_lblDataNasc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataNasc.gridx = 1;
		gbc_lblDataNasc.gridy = 2;
		add(lblDataNasc, gbc_lblDataNasc);

		tfDataNasc = new JTextField();
		tfDataNasc.setColumns(10);
		GridBagConstraints gbc_tfDataNasc = new GridBagConstraints();
		gbc_tfDataNasc.anchor = GridBagConstraints.NORTH;
		gbc_tfDataNasc.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDataNasc.insets = new Insets(0, 0, 5, 0);
		gbc_tfDataNasc.gridx = 2;
		gbc_tfDataNasc.gridy = 2;
		add(tfDataNasc, gbc_tfDataNasc);

		JLabel lblCpf = new JLabel("Login:");
		GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.anchor = GridBagConstraints.EAST;
		gbc_lblCpf.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpf.gridx = 1;
		gbc_lblCpf.gridy = 3;
		add(lblCpf, gbc_lblCpf);

		tfLogin = new JTextField();
		tfLogin.setColumns(10);
		GridBagConstraints gbc_tfCpf = new GridBagConstraints();
		gbc_tfCpf.anchor = GridBagConstraints.NORTH;
		gbc_tfCpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCpf.insets = new Insets(0, 0, 5, 0);
		gbc_tfCpf.gridx = 2;
		gbc_tfCpf.gridy = 3;
		add(tfLogin, gbc_tfCpf);

		JLabel lblRg = new JLabel("Senha:");
		GridBagConstraints gbc_lblRg = new GridBagConstraints();
		gbc_lblRg.anchor = GridBagConstraints.EAST;
		gbc_lblRg.insets = new Insets(0, 0, 5, 5);
		gbc_lblRg.gridx = 1;
		gbc_lblRg.gridy = 4;
		add(lblRg, gbc_lblRg);

		tfSenha = new JPasswordField();
		tfSenha.setColumns(10);
		GridBagConstraints gbc_tfRg = new GridBagConstraints();
		gbc_tfRg.anchor = GridBagConstraints.NORTH;
		gbc_tfRg.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfRg.insets = new Insets(0, 0, 5, 0);
		gbc_tfRg.gridx = 2;
		gbc_tfRg.gridy = 4;
		add(tfSenha, gbc_tfRg);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profissional profissionalEnviar = new Profissional();
				profissionalEnviar.setNomeProfissional(tfNome.getText()).setDataNascimento(tfDataNasc.getText())
						.setLogin(tfLogin.getText());
				if (ProfissionalAlterar.getInstancia().getProfissional() == null) {
					profissionalEnviar.setRequisicao(Solicitacao.INCLUIR)
							.setSenha(EncriptarSenhaSha256.hashString(tfSenha.getText()));
				} else {
					if (!tfSenha.getText().equals("")) {
						profissionalEnviar.setSenha(EncriptarSenhaSha256.hashString(tfSenha.getText()));
					} else {
						profissionalEnviar.setSenha(ProfissionalAlterar.getInstancia().getProfissional().getSenha());
					}
					profissionalEnviar.setRequisicao(Solicitacao.ALTERAR).setIdProfissional(
							ProfissionalAlterar.getInstancia().getProfissional().getIdProfissional());
				}

				try {
					EnviarProfissional.enviar(profissionalEnviar);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			}
		});
		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCadastrar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnCadastrar.gridx = 2;
		gbc_btnCadastrar.gridy = 5;
		add(btnCadastrar, gbc_btnCadastrar);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) (TelaPrincipal.getCards().getLayout());
				cardLayout.show(TelaPrincipal.getCards(), Telas.PROFISSIONAL.toString());
			}
		});
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnVoltar.gridx = 2;
		gbc_btnVoltar.gridy = 6;
		add(btnVoltar, gbc_btnVoltar);
	}

	public void preencheCampos() {
		if (ProfissionalAlterar.getProfissional() != null) {
			tfNome.setText(ProfissionalAlterar.getProfissional().getNomeProfissional());
			tfDataNasc.setText(ProfissionalAlterar.getProfissional().getDataNascimento());
			tfLogin.setText(ProfissionalAlterar.getProfissional().getLogin());

			btnCadastrar.setText("Salvar");
		} else {
			tfNome.setText("");
			tfDataNasc.setText("");
			tfLogin.setText("");

			btnCadastrar.setText("Cadastrar");
		}
	}
}
