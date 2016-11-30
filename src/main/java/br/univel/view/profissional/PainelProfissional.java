package br.univel.view.profissional;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.univel.control.ListarProfissionais;
import br.univel.model.ProfissionalAlterar;
import br.univel.model.ProfissionalAlterar;
import br.univel.model.dto.Profissional;
import br.univel.model.dto.Profissional;
import br.univel.model.enums.Solicitacao;
import br.univel.model.enums.Telas;
import br.univel.view.TelaPrincipal;

public class PainelProfissional extends JPanel {
	private JTable tabelaProfissionais;
	private DefaultTableModel modelo;

	public PainelProfissional() {
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

		modelo = (new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Data Nasc", "Login", "" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tabelaProfissionais = new JTable();
		tabelaProfissionais.setModel(modelo);
		tabelaProfissionais.getColumnModel().getColumn(0).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(0).setPreferredWidth(318);
		tabelaProfissionais.getColumnModel().getColumn(1).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(1).setPreferredWidth(101);
		tabelaProfissionais.getColumnModel().getColumn(2).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(2).setPreferredWidth(98);
		tabelaProfissionais.getColumnModel().getColumn(3).setResizable(false);
		tabelaProfissionais.getColumnModel().getColumn(3).setMinWidth(0);
		tabelaProfissionais.getColumnModel().getColumn(3).setMaxWidth(0);
		tabelaProfissionais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProfissionais.setDefaultEditor(Object.class, null);

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
				if (tabelaProfissionais.getSelectedRow() > 0) {
					Profissional profissionalAuxiliar = new Profissional();
					profissionalAuxiliar
							.setNomeProfissional((String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 0));
					profissionalAuxiliar
							.setDataNascimento((String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 1));
					profissionalAuxiliar
							.setLogin((String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 2));
					profissionalAuxiliar.setIdProfissional(
							Integer.parseInt((String) tabelaProfissionais.getValueAt(tabelaProfissionais.getSelectedRow(), 3)));
					profissionalAuxiliar.setRequisicao(Solicitacao.ALTERAR);
					ProfissionalAlterar.getInstancia().setProfissional(profissionalAuxiliar);
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

	private void carregarTabela() {
		ArrayList<Profissional> listaProfissionais = new ArrayList<>();
		Profissional profissional = new Profissional();
		profissional.setRequisicao(Solicitacao.LISTAR);

		listaProfissionais = ListarProfissionais.getListaProfissionais(profissional);

		listaProfissionais.forEach(cliente -> {
			modelo.addRow(new String[] { cliente.getNomeProfissional(), cliente.getDataNascimento(),
					cliente.getLogin(), cliente.getIdProfissional().toString() });
		});

		tabelaProfissionais.setModel(modelo);
	}
}
