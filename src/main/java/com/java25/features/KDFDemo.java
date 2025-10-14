package com.java25.features;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * KDF - Key Derivation Function API
 * Sometimes we don't want to use raw password or week key directly for encryption
 * So KDF makes the key material/password safe for cryptography
 *
 */
public class KDFDemo {
     void main() throws NoSuchAlgorithmException, InvalidKeySpecException {

         // 1 Password provided by user
         char[] pwd = "javatech".toCharArray();

         // 2 generate a random salt for extra security
         byte[] salt = new byte[16];
         new SecureRandom().nextBytes(salt);

         //3 PBKDF2 parameters: password, salt, iterations, key length
         PBEKeySpec spec = new PBEKeySpec(pwd, salt, 65536, 256); //65536 times hashing is done, 256 is desired length in bits.

         //4 Create the key factory and generate the desired key
         SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");//PBKDF2WithHmacSHA256 is a standard secure KDF algorithm.
         SecretKey derivedKey = factory.generateSecret(spec);

         //5 Show results
         System.out.println("Derived key (hex): "+ bytesToHex(derivedKey.getEncoded()));
         System.out.println("Salt used (hex): "+ bytesToHex(salt));

     }

    //Helper method to convert bytes to hex string
    private String bytesToHex(byte[] bytes) {
         StringBuilder sb = new StringBuilder();
         for (byte b: bytes){
             sb.append(String.format("%02x", b));
         }
         return sb.toString();
    }
}
