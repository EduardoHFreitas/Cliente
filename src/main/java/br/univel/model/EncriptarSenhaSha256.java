package br.univel.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encriptar a senha do usuario no algoritmo sha256
 *
 * @author Eduardo
 *
 */
public final class EncriptarSenhaSha256 {

	private static final Integer NUMERO_CONTROLE = 1;

	private EncriptarSenhaSha256() {
	}

	/**
	 * Recebe o texto e invoca o metodo responsavel pela criptografia
	 *
	 * @param message
	 * @return
	 */
	public static String hashString(final String message) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(message.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] hashedBytes = messageDigest.digest();
		return convertBytesToHex(hashedBytes);
	}

	private static String convertBytesToHex(final byte[] bytes) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xff & bytes[i]);
			if (hex.length() == NUMERO_CONTROLE){
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
