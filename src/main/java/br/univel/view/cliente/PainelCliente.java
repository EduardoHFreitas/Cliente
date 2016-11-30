package br.univel.view.cliente;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.univel.control.EnviarCliente;
import br.univel.control.ListarClientes;
import br.univel.model.ClienteAlterar;
import br.univel.model.dto.Cliente;
import br.univel.model.enums.Solicitacao;
import br.univel.model.enums.Telas;
import br.univel.view.TelaPrincipal;

public class PainelCliente extends JPanel {
	private JTable tabelaClientes;
	private DefaultTableModel modelo;
	private ArrayList<Cliente> listaClientes = new ArrayList<>();

	public PainelCliente() {
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

		modelo = (new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Data Nasc", "CPF", "RG", ""}) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		tabelaClientes = new JTable();
		tabelaClientes.setModel(modelo);
		atualizarTabela();
		tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaClientes.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(tabelaClientes);

		JButton btnAdicionar = new JButton("+");
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAdicionar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdicionar.gridx = 3;
		gbc_btnAdicionar.gridy = 3;
		add(btnAdicionar, gbc_btnAdicionar);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteAlterar.getInstancia().setCliente(null);
				CardLayout cardLayout = (CardLayout) (TelaPrincipal.getCards().getLayout());
				cardLayout.show(TelaPrincipal.getCards(), Telas.DADOS_CLIENTE.toString());
			}
		});

		JButton btnRemover = new JButton("-");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabelaClientes.getSelectedRow() >= 0) {
					Cliente clienteAuxiliar = new Cliente();
					clienteAuxiliar
							.setNomeCliente((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 0));
					clienteAuxiliar
							.setDataNascimento((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 1));
					clienteAuxiliar
							.setNumeroCPF((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 2));
					clienteAuxiliar.setNumeroRG((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 3));
					clienteAuxiliar.setIdCliente(
							Integer.parseInt((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 4)));
					clienteAuxiliar.setRequisicao(Solicitacao.EXCLUIR);
					new EnviarCliente().enviar(clienteAuxiliar);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum CLIENTE selecionado!");
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
				if (tabelaClientes.getSelectedRow() >= 0) {
					Cliente clienteAuxiliar = new Cliente();
					clienteAuxiliar
							.setNomeCliente((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 0));
					clienteAuxiliar
							.setDataNascimento((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 1));
					clienteAuxiliar
							.setNumeroCPF((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 2));
					clienteAuxiliar.setNumeroRG((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 3));
					clienteAuxiliar.setIdCliente(
							Integer.parseInt((String) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 4)));
					clienteAuxiliar.setRequisicao(Solicitacao.ALTERAR);
					ClienteAlterar.getInstancia().setCliente(clienteAuxiliar);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum CLIENTE selecionado!");
					return;
				}
				CardLayout cardLayout = (CardLayout) (TelaPrincipal.getCards().getLayout());
				cardLayout.show(TelaPrincipal.getCards(), Telas.DADOS_CLIENTE.toString());
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
		tabelaClientes.getColumnModel().getColumn(0).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(0).setPreferredWidth(318);
		tabelaClientes.getColumnModel().getColumn(1).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(1).setPreferredWidth(101);
		tabelaClientes.getColumnModel().getColumn(2).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(2).setPreferredWidth(98);
		tabelaClientes.getColumnModel().getColumn(3).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(3).setPreferredWidth(107);
		tabelaClientes.getColumnModel().getColumn(4).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(4).setMinWidth(0);
		tabelaClientes.getColumnModel().getColumn(4).setMaxWidth(0);
	}

	private void carregarTabela() {
		Cliente clienteRequisicao = new Cliente();
		clienteRequisicao.setRequisicao(Solicitacao.LISTAR);

		listaClientes = new ArrayList<>();
		modelo = (new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Data Nasc", "CPF", "RG", "" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		listaClientes = ListarClientes.getListaClientes(clienteRequisicao);

		listaClientes.forEach(cliente -> {
			modelo.addRow(new String[] { cliente.getNomeCliente(), cliente.getDataNascimento().toString(),
					cliente.getNumeroCPF(), cliente.getNumeroRG(), cliente.getIdCliente().toString() });
		});
		tabelaClientes.setModel(modelo);
		atualizarTabela();
	}
}
