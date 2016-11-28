package br.univel.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import br.univel.model.dto.ObjetoRetorno;

public class ConexaoServidor<T> {

	public ObjetoRetorno ExecutaComunicacao(T objeto) {
		Socket socket = null;
		OutputStream output = null;
		InputStream input = null;
		ObjetoRetorno retorno = null;
		try {
			socket = new Socket("localhost", 5000);

			output = socket.getOutputStream();

			ObjectOutputStream objOutput = new ObjectOutputStream(output);
			objOutput.writeObject(objeto);
			objOutput.flush();

			input = socket.getInputStream();
			ObjectInputStream objInput = new ObjectInputStream(input);

			return retorno = (ObjetoRetorno) objInput.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
			JOptionPane.showMessageDialog(null,
					"Nao foi possivel conexão com o Servidor" + "\n Verifique se o mesmo esta ativo!");
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
}
