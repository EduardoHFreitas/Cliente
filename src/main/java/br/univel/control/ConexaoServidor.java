package br.univel.control;

import java.io.IOException;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

import javax.swing.JOptionPane;

/**
 * Executa uma conexção com o servidor
 *
 * @author Eduardo
 *
 */
public class ConexaoServidor {

	/**
	 * Escrita e leitura no socket
	 *
	 * @param objeto
	 * @return
	 * @throws IOException
	 */
	public Object executaComunicacao(final Object objeto) throws IOException {
		Socket socket = null;
		OutputStream output = null;
		InputStream input = null;

		try {
			Objects.requireNonNull(objeto, "Objeto nao pode ser nulo!");
			socket = new Socket("localhost", 5000);

			output = socket.getOutputStream();

			ObjectOutputStream objOutput = new ObjectOutputStream(output);
			objOutput.writeObject(objeto);
			objOutput.flush();

			input = socket.getInputStream();
			ObjectInputStream objInput = new ObjectInputStream(input);

			return (Object) objInput.readObject();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Nao foi possivel conexão com o Servidor" + "\n Verifique se o mesmo esta ativo!");
			throw new RuntimeException(e);
		} finally {
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
			}
			if (socket != null) {
				socket.close();
			}
		}
	}
}
