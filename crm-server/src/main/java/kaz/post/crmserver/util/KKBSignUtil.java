package kaz.post.crmserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.Serializable;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;

/**
 * Created by user on 26.11.2015.
 */
public class KKBSignUtil implements Serializable {
	private static final Logger log = LoggerFactory.getLogger(KKBSignUtil.class);
	public String keystoretype = new String("JKS");
	public String signalgorythm = new String("SHA1withRSA");
	public boolean invert = true;
	public boolean debug = false;
	public String debughash = new String("SHA");

	public KKBSignUtil() {
	}

	public synchronized String build64(String var1, String var2, String var3) {
		try {
			FileInputStream var4 = new FileInputStream(var1);
			byte[] var5 = new byte[var4.available()];
			var4.read(var5);
			String var6 = new String(var5);
			String var7 = this.value(var6, "keystore");
			String var8 = this.value(var6, "alias");
			String var9 = this.value(var6, "keypass");
			String var10 = this.value(var6, "storepass");
			String var11 = this.value(var6, "template");
			String var12 = this.value(var6, "certificate");
			String var13 = this.value(var6, "merchant_id");
			String var14 = this.value(var6, "currency");
			String var15 = this.value(var6, "merchant_name");
			FileInputStream var16 = new FileInputStream(var11);
			byte[] var17 = new byte[var16.available()];
			var16.read(var17);
			String var18 = new String(var17);
			var18 = this.replace(var18, "%order_id%", var3);
			var18 = this.replace(var18, "%amount%", var2);
			var18 = this.replace(var18, "%amount%", var2);
			var18 = this.replace(var18, "%certificate%", var12);
			var18 = this.replace(var18, "%merchant_id%", var13);
			var18 = this.replace(var18, "%currency%", var14);
			var18 = this.replace(var18, "%merchant_name%", var15);
			String var19 = this.sign64(var18, var7, var8, var9, var10);
			var18 = var18 + "<merchant_sign type=\"RSA\">" + var19 + "</merchant_sign>";
			var18 = "<document>" + var18 + "</document>";
			new Base64Util();
			byte[] var21 = var18.getBytes();
			char[] var22 = Base64Util.encode(var21);
			return new String(var22);
		} catch (Exception var23) {
			return new String("");
		}
	}

	public synchronized String sign64(String var1, String var2, String var3, String var4, String var5) {
		try {
			new Base64Util();
			byte[] var7 = var1.getBytes();
			char[] var8 = var4.toCharArray();
			char[] var9 = var5.toCharArray();
			if(this.debug) {
				MessageDigest var10 = MessageDigest.getInstance(this.debughash);
				byte[] var11 = var10.digest(var7);
				char[] var12 = Base64Util.encode(var11);
				log.debug(this.debughash + " Hash:");
				log.debug(new String(var12));
			}

			KeyStore var19 = KeyStore.getInstance(this.keystoretype);
			FileInputStream var20 = new FileInputStream(var2);
			var19.load(var20, var9);
			Signature var21 = Signature.getInstance(this.signalgorythm);
			PrivateKey var13 = (PrivateKey)var19.getKey(var3, var8);
			var21.initSign(var13);
			var21.update(var7);
			byte[] var14 = var21.sign();
			if(this.invert) {
				int var15 = 0;

				for(int var16 = var14.length; var15 < var16 / 2; ++var15) {
					byte var17 = var14[var15];
					var14[var15] = var14[var16 - var15 - 1];
					var14[var16 - var15 - 1] = var17;
				}
			}

			char[] var22 = Base64Util.encode(var14);
			return new String(var22);
		} catch (Exception var18) {
			return new String("");
		}
	}

	public synchronized boolean verify(String var1, String var2, String var3, String var4, String var5) {
		try {
			new Base64Util();
			byte[] var7 = var1.getBytes();
			byte[] var8 = Base64Util.decode(var2.toCharArray());
			char[] var9 = var5.toCharArray();
			KeyStore var10 = KeyStore.getInstance(this.keystoretype);
			FileInputStream var11 = new FileInputStream(var3);
			var10.load(var11, var9);
			Signature var12 = Signature.getInstance(this.signalgorythm);
			var12.initVerify(var10.getCertificate(var4));
			var12.update(var7);
			if(this.invert) {
				int var13 = 0;

				for(int var14 = var8.length; var13 < var14 / 2; ++var13) {
					byte var15 = var8[var13];
					var8[var13] = var8[var14 - var13 - 1];
					var8[var14 - var13 - 1] = var15;
				}
			}

			boolean var17 = var12.verify(var8);
			return var17;
		} catch (Exception var16) {
			return false;
		}
	}

	public String getKeystoretype() {
		return this.keystoretype;
	}

	public void setKeystoretype(String var1) {
		this.keystoretype = var1;
	}

	public String getSignalgorythm() {
		return this.signalgorythm;
	}

	public void setSignalgorythm(String var1) {
		this.signalgorythm = var1;
	}

	public boolean getInvert() {
		return this.invert;
	}

	public void setInvert(boolean var1) {
		this.invert = var1;
	}

	public boolean getDebug() {
		return this.debug;
	}

	public void setDebug(boolean var1) {
		this.debug = var1;
	}

	public String getDebughash() {
		return this.debughash;
	}

	public void setDebughash(String var1) {
		this.debughash = var1;
	}

	private String value(String var1, String var2) {
		int var3 = var1.indexOf(var2);
		var3 = var1.indexOf(34, var3) + 1;
		int var4 = var1.indexOf(34, var3);
		return var1.substring(var3, var4);
	}

	private String replace(String var1, String var2, String var3) {
		int var4 = var1.indexOf(var2);
		int var5 = var4 + var2.length();
		return var1.substring(0, var4) + var3 + var1.substring(var5);
	}
}
