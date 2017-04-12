/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Class to assist with password hashing. Very heavily based off of the top
 * answer from the following:
 *
 * https://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash
 *
 * But modified to be more useful for our purposes.
 *
 * @author Aaron Usher
 */
public class HashHelper {

    /**
     * SecureRandom to generate random values in a secure way. It doesn't make
     * them as fast as a regular random, but it isn't nearly as predictable.
     */
    public static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * This is how many times the hash function will run, I think. More takes
     * longer, but is more secure.
     */
    public static final int ITERATIONS = 10000;

    /**
     * This is how long the hash ends up being.
     */
    public static final int KEY_LENGTH = 64;

    /**
     * Don't instantiate one of these; literally everything in this class is
     * static.
     */
    private HashHelper() {
    }

    /**
     * Generates a salt for a password.
     *
     * @return The salt.
     */
    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        SECURE_RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * Generates salt as a string.
     *
     * @return
     */
    public static String generateStringSalt() {
        return Base64.encode(generateSalt());
    }

    /**
     * Hashes a password with a given salt.
     *
     * @param password
     * @param salt
     * @return
     */
    public static String hashPassword(char[] password, byte[] salt) {
        byte[] hash = null;
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH * 8);
        //Don't forget to scrub the password array by writing over it.
        //for (int i = 0; i < password.length; i++) {
        //    password[i] = 'x';
        //}
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = secretKeyFactory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            //This shouldn't happen, nor should it be a checked exception.
        } catch (InvalidKeySpecException ex) {
            //This also shouldn't happen.
        } finally {
            spec.clearPassword();
        }
        return Base64.encode(hash);
    }

    /**
     * Hashes password with a string salt.
     *
     * @param password
     * @param salt
     * @return
     */
    public static String hashPassword(String password, String salt) {
        byte[] byteSalt = Base64.decode(salt);
        return hashPassword(password, byteSalt);
    }

    /**
     * For safety, passwords should be kept in character arrays that are easily
     * destroyed. I don't see that happening, especially on a web project, so
     * here is an overload to make hashing easier.
     *
     * @param password
     * @param salt
     * @return
     */
    public static String hashPassword(String password, byte[] salt) {
        return hashPassword(password.toCharArray(), salt);

    }

    /**
     * Overload for checkPassword that takes the ingredients to make a hash
     * along with the expected hash, because that could be useful.
     *
     * @param password
     * @param salt
     * @param expectedHash
     * @return
     */
    public static boolean checkPassword(char[] password, byte[] salt, String expectedHash) {
        String givenHash = hashPassword(password, salt);
        //for (int i = 0; i < password.length; i++) {
        //    password[i] = 'x';
        //}
        return checkPassword(givenHash, expectedHash);

    }

    /**
     * Compares one password hash to another, because that's surprisingly
     * annoying to do sometimes.
     *
     * We switched to encoded hashes as strings instead of byte arrays, so this
     * isn't as useful as it once was.
     *
     * @param givenHash
     * @param expectedHash
     * @return
     */
    public static boolean checkPassword(String givenHash, String expectedHash) {
        return givenHash.equals(expectedHash);
    }
}
