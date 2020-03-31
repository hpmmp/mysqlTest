package com.cqut.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.SecureRandom;

/**
 * @author ChenTengfei
 * @date 2019/12/23 16:07
 **/
public class DESUtil {
    private static final String DES = "DESede";
    public static final String DES_KEY = "bonc_dp_beaf_md_dg_&n*2b_2u";

    public DESUtil() {
    }

    public static byte[] encrypt(byte[] src, byte[] key) throws RuntimeException {
        try {
            SecureRandom sr = new SecureRandom();
            DESedeKeySpec dks = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(1, securekey, sr);
            return cipher.doFinal(src);
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws RuntimeException {
        try {
            SecureRandom sr = new SecureRandom();
            DESedeKeySpec dks = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(2, securekey, sr);
            return cipher.doFinal(src);
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public static final String decrypt(String data) {
        return data == null ? null : new String(decrypt(hex2byte(data.getBytes()), "bonc_dp_beaf_md_dg_&n*2b_2u".getBytes()));
    }

    public static final String decrypt(String data, String key) {
        return data != null && key != null ? new String(decrypt(hex2byte(data.getBytes()), key.getBytes())) : null;
    }

    public static final String encrypt(String data) {
        if (data != null) {
            try {
                return byte2hex(encrypt(data.getBytes(), "bonc_dp_beaf_md_dg_&n*2b_2u".getBytes()));
            } catch (Exception var2) {
                throw new RuntimeException(var2);
            }
        } else {
            return null;
        }
    }

    public static final String encrypt(String data, String key) {
        if (data != null && key != null) {
            try {
                return byte2hex(encrypt(data.getBytes(), key.getBytes()));
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        } else {
            return null;
        }
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();

        for(int n = 0; b != null && n < b.length; ++n) {
            String stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs.append('0');
            }

            hs.append(stmp);
        }

        return hs.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if (b.length % 2 != 0) {
            throw new IllegalArgumentException();
        } else {
            byte[] b2 = new byte[b.length / 2];

            for(int n = 0; n < b.length; n += 2) {
                String item = new String(b, n, 2);
                b2[n / 2] = (byte)Integer.parseInt(item, 16);
            }

            return b2;
        }
    }
}