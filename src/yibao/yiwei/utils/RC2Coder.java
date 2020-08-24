package yibao.yiwei.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 文件加密工具
 * @author Administrator
 *
 */
public class RC2Coder {
	
	private Key key;//秘钥
	
	private RC2ParameterSpec rc2Spec;//向量参数
	
	public static final String KEY_ALGORITHM = "RC2";//算法名称
	
	public static final String CIPHER_ALGORITHM_CBC = "RC2/CBC/PKCS5Padding";//算法名称/加密模式/填充方式

	/**
	 * 生成密匙
	 * @param str
	 * @throws Exception
	 */
	public RC2Coder(String str) throws Exception {
		getKey(str);
	}

	/**
	 * 根据参数生成key
	 * @param strKey
	 */
	public void getKey(String strKey) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strKey.getBytes());
			byte[] md5HashOfKey = md.digest();
			this.key = new SecretKeySpec(md5HashOfKey, KEY_ALGORITHM);
			this.rc2Spec = new RC2ParameterSpec(0, "12345678" .getBytes());
		} catch (Exception e) {
			throw new RuntimeException("Error: " + e);
		}
	}

	/**
	 * 文件加密
	 * @param file 要加密的文件 如c:/test/srcFile.txt
	 * @param destFile 加密后存放的文件名 如c:/加密后文件.txt
	 */
	public void encrypt(String file, String destFile) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
		cipher.init(Cipher.ENCRYPT_MODE, this.key, this.rc2Spec);
		InputStream is = new FileInputStream(file);
		OutputStream out = new FileOutputStream(destFile);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		try {
			int r;
			byte[] buffer = new byte[1024];
			while ((r = cis.read(buffer)) > 0) {
				out.write(buffer, 0, r);
			}
		} catch (Exception e) {
			System.out.println("Digest Exception");
		} finally {
			cis.close();
			is.close();
			out.close();
		}
	}

	/**
	 * 文件解密
	 * @param file 已加密的文件 如c:/加密后文件.txt * 
	 * @param destFile 解密后存放的文件名 如c:/test/解密后文件.txt
	 */
	public void decrypt(String file, String dest) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
		cipher.init(Cipher.DECRYPT_MODE, this.key, this.rc2Spec);
		InputStream is = new FileInputStream(file);
		OutputStream out = new FileOutputStream(dest);
		CipherOutputStream cos = new CipherOutputStream(out, cipher);
		try {
			int r;
			byte[] buffer = new byte[1024];
			while ((r = is.read(buffer)) >= 0) {
				cos.write(buffer, 0, r);
			}
		} catch (Exception e) {
			
		} finally {
			cos.close();
			out.close();
			is.close();
		}
	}

	public static void main(String[] args) throws Exception {
		RC2Coder ef = new RC2Coder("12345678");
		String srcfile = "D:/TestCipher.txt";
		File fd = new File(srcfile);
		if (!fd.exists() && !fd.isDirectory()) {
			fd.mkdirs();
		}
		//String srcfile = fd + "\\En_药品文档.txt";
		String encfile = "D:/已加密.txt";// + "\\encrypt_已加密.txt";
		String desfile = "D:/已解密.txt";// + "\\deciphering_已解密.txt";
		//ef.encrypt(srcfile, encfile); // 加密
		//System.out.println("encrypt end.");
		ef.decrypt(encfile, desfile); // 解密
		//System.out.println("decrypt end.");
	}
}
