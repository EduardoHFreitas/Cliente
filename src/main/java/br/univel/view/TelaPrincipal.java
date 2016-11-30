package br.univel.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import br.univel.model.enums.Telas;
import br.univel.view.cliente.DadosCliente;
import br.univel.view.cliente.PainelCliente;
import br.univel.view.profissional.DadosProfissional;
import br.univel.view.profissional.PainelProfissional;

/**
 * Gerenciamento das telas da aplicação
 *
 * @author Eduardo
 *
 */
public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private static JFrame telaPrincipal;
	private JPanel painelMenu = new JPanel();

	final private static JPanel cards = new JPanel(new CardLayout());
	final private JPanel painelInicio = new JPanel();
	final private JPanel painelCliente = new PainelCliente();
	final private JPanel painelProfissional = new PainelProfissional();
	final private JPanel painelDadosCliente = new DadosCliente();
	final private JPanel painelDadosProfissional = new DadosProfissional();

	final private CardLayout principal = new CardLayout();

	public TelaPrincipal() {
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(700, 660);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);

		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\logo.png"));
		painelInicio.add(lblNewLabel);
		painelInicio.setBackground(Color.WHITE);

		cards.add(painelInicio, Telas.INICIAL.toString());
		cards.add(painelCliente, Telas.CLIENTE.toString());
		cards.add(painelProfissional, Telas.PROFISSIONAL.toString());
		cards.add(painelDadosCliente, Telas.DADOS_CLIENTE.toString());
		cards.add(painelDadosProfissional, Telas.DADOS_PROFISSIONAL.toString());

		getContentPane().add(getCards(), BorderLayout.CENTER);

		getContentPane().add(painelMenu, BorderLayout.PAGE_START);
		painelMenu.setLayout(new CardLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		painelMenu.add(menuBar, "name_11931866403246");

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mnitCliente = new JMenuItem("Cliente");
		mnMenu.add(mnitCliente);
		mnitCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cardLayout = (CardLayout) (getCards().getLayout());
				cardLayout.show(getCards(), Telas.CLIENTE.toString());
			}
		});

		JMenuItem mnitProfissional = new JMenuItem("Profissional");
		mnMenu.add(mnitProfissional);
		mnitProfissional.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cardLayout = (CardLayout) (getCards().getLayout());
				cardLayout.show(getCards(), Telas.PROFISSIONAL.toString());
			}
		});

	}

	public static JFrame getTelaPrincipal() {
		if (telaPrincipal == null) {
			telaPrincipal = new TelaPrincipal();
		}
		return telaPrincipal;
	}

	public static JPanel getCards() {
		return cards;
	}

}
