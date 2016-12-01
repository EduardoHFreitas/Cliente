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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.univel.control.EnviarProfissional;
import br.univel.control.ListarProfissionais;
import br.univel.model.ProfissionalAlterar;
import br.univel.model.ProfissionalAlterar;
import br.univel.model.dto.Profissional;
import br.univel.model.dto.Profissional;
import br.univel.model.dto.Profissional;
import br.univel.model.enums.Solicitacao;
import br.univel.model.enums.Telas;
import br.univel.view.TelaPrincipal;

public class PainelProfissional extends JPanel {
	private JTable tabelaProfissionais;
	private DefaultTableModel modelo;

	public PainelProfissional() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				carregarTabela();
			}
		});

		this.setSize(700, 600);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 119, 310, 112, 41, 37, 61, 0 };
		gridBagLayout.rowHeights = new int[] { 47, 437, 94, 33, 23, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		modelo = createModel();

		tabelaProfissionais = new JTable();
		tabelaProfissionais.setModel(modelo);
		atualizarTabela();
		scrollPane.setViewportView(tabelaProfissionais);

		JButton btnAdicionar = new JButton("+");
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAdicionar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdicionar.gridx = 3;
		gbc_btnAdicionar.gridy = 3;
		add(btnAdicionar, gbc_btnAdicionar);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProfissionalAlterar.getInstancia().setProfissional(null);
				CardLayout cardLayout = (CardLayout) (TelaPrincipal.getCards().getLayout());
				cardLayout.show(TelaPrincipal.getCards(), Telas.DADOS_PROFISSIONAL.toString());
			}
		});

		JButton btnRemover = new JButton("-");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabelaProfissionais.getSelectedRow() >= 0) {
					Profissional profissionalAuxiliar = new Profissional();
					profissionalAuxiliar
							.setNomeProfissional(
									(String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 0))
							.setDataNascimento(
									(String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 1))
							.setLogin((String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 2))
							.setSenha((String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 3))
							.setIdProfissional(Integer.parseInt(
									(String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 4)))
							.setRequisicao(Solicitacao.EXCLUIR);
					try {
						EnviarProfissional.enviar(profissionalAuxiliar);
					} catch (IOException e1) {
						throw new RuntimeException(e1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum PROFISSIONAL selecionado!");
					return;
				}
				carregarTabela();
			}
		});
		GridBagConstraints gbc_btnRemover = new GridBagConstraints();
		gbc_btnRemover.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRemover.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemover.gridx = 4;
		gbc_btnRemover.gridy = 3;
		add(btnRemover, gbc_btnRemover);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabelaProfissionais.getSelectedRow() >= 0) {
					Profissional profissionalAuxiliar = new Profissional();
					profissionalAuxiliar
							.setNomeProfissional(
									(String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 0))
							.setDataNascimento(
									(String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 1))

							.setLogin((String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 2))
							.setSenha((String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 3))
							.setIdProfissional(Integer.parseInt(
									(String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 4)))
							.setRequisicao(Solicitacao.ALTERAR);
					ProfissionalAlterar.getInstancia().setProfissional(profissionalAuxiliar);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum PROFISSIONAL selecionado!");
					return;
				}
				CardLayout cardLayout = (CardLayout) (TelaPrincipal.getCards().getLayout());
				cardLayout.show(TelaPrincipal.getCards(), Telas.DADOS_PROFISSIONAL.toString());

			}
		});
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEditar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnEditar.gridx = 5;
		gbc_btnEditar.gridy = 3;
		add(btnEditar, gbc_btnEditar);

		carregarTabela();
	}

	private void atualizarTabela() {
		tabelaProfissionais.getColumnModel().getColumn(0).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(0).setPreferredWidth(318);
		tabelaProfissionais.getColumnModel().getColumn(1).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(1).setPreferredWidth(101);
		tabelaProfissionais.getColumnModel().getColumn(2).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(2).setPreferredWidth(98);
		tabelaProfissionais.getColumnModel().getColumn(3).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(3).setMinWidth(0);
		tabelaProfissionais.getColumnModel().getColumn(3).setMaxWidth(0);
		tabelaProfissionais.getColumnModel().getColumn(4).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(4).setMinWidth(0);
		tabelaProfissionais.getColumnModel().getColumn(4).setMaxWidth(0);
		tabelaProfissionais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProfissionais.setDefaultEditor(Object.class, null);
	}

	private void carregarTabela() {
		Profissional profissionalRequisicao = new Profissional();
		profissionalRequisicao.setRequisicao(Solicitacao.LISTAR);

		ArrayList<Profissional> listaProfissionais = new ArrayList<>();
		modelo = createModel();

		try {
			listaProfissionais = (ArrayList<Profissional>) ListarProfissionais
					.getListaProfissionais(profissionalRequisicao);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		listaProfissionais.forEach(profissional -> {
			modelo.addRow(new String[] { profissional.getNomeProfissional(), profissional.getDataNascimento(),
					profissional.getLogin(), profissional.getSenha(), profissional.getIdProfissional().toString() });
		});

		tabelaProfissionais.setModel(modelo);
		atualizarTabela();
	}

	private DefaultTableModel createModel() {
		return (new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Data Nasc", "Login", "", "" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}
}
