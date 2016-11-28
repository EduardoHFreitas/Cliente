package br.univel.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.control.VerificarLogin;
import br.univel.model.EncriptarSenhaSha256;
import br.univel.model.dto.Usuario;

/**
 * Tela inicial da aplicação responsavel pelo login do usuario;
 *
 * @author Eduardo
 *
 */
public class Login extends JFrame {
	private static final long serialVersionUID = 7781598968866921968L;

	private static final String FONTE = "Arial";
	private final JTextField tfUsuario;
	private final JPasswordField tfSenha;

	/**
	 * Construtor da tela de login
	 */
	public Login() {
		setBounds(100, 100, 350, 176);
		getContentPane().setFont(new Font(FONTE, Font.PLAIN, 13));
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Login");

		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font(FONTE, Font.PLAIN, 16));
		tfUsuario.setColumns(10);

		tfSenha = new JPasswordField();
		tfSenha.setFont(new Font(FONTE, Font.PLAIN, 16));
		tfSenha.setToolTipText("");

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			/**
			 * Metodo interno para tratamento do evento "Click" do botao
			 */
			public void actionPerformed(final ActionEvent event) {
				if (tfUsuario.getText().isEmpty() || tfSenha.getText().isEmpty()){
					return;
				}
				Usuario usuarioLogin = new Usuario(tfUsuario.getText(),
						new EncriptarSenhaSha256().hashString(tfSenha.getPassword().toString().trim()));

				new VerificarLogin(usuarioLogin);
				new TelaPrincipal();
				dispose();
			}
		});

		btnLogin.setFont(new Font(FONTE, Font.PLAIN, 18));

		JLabel lbUsuario = new JLabel("Usu\u00E1rio :");
		lbUsuario.setFont(new Font(FONTE, Font.BOLD, 18));

		JLabel lbSenha = new JLabel("Senha :");
		lbSenha.setFont(new Font(FONTE, Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(26)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lbUsuario).addComponent(lbSenha))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(tfSenha, GroupLayout.DEFAULT_SIZE, 288,
																Short.MAX_VALUE)
														.addComponent(tfUsuario)))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addContainerGap(243, Short.MAX_VALUE).addComponent(btnLogin,
														GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(22)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbUsuario)
								.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbSenha)
								.addComponent(tfSenha, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnLogin)
						.addContainerGap(92, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * Metodo main da aplicação
	 *
	 * @param args
	 */
	public static void main(final String[] args) {

		EventQueue.invokeLater(new Runnable() {
			/**
			 * Metodo interno para execução da aplicação
			 */
			public void run() {
				Login telaLogin = new Login();
				telaLogin.setLocationRelativeTo(null);
				telaLogin.setVisible(true);
			}
		});
	}
}
