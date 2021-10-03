/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.auth.imp;

import com.phoenix.common.auth.HashingAlgorithm;
import com.phoenix.common.auth.TotpCodeGenerator;
import com.phoenix.common.exceptions.TotpCodeGenerationException;
import org.apache.commons.codec.binary.Base32;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

public class DefaultTotpCodeGenerator implements TotpCodeGenerator {
    // ------------------------------------------------------------
    // -------------------- field
    // ------------------------------------------------------------
    private final HashingAlgorithm algorithm;
    private final int digits;


    // ------------------------------------------------------------
    // -------------------- Constructor
    // ------------------------------------------------------------
    public DefaultTotpCodeGenerator() {
        this(HashingAlgorithm.SHA1, 6);
    }

    public DefaultTotpCodeGenerator(HashingAlgorithm algorithm) {
        this(algorithm, 6);
    }

    public DefaultTotpCodeGenerator(int digits) {
        this(HashingAlgorithm.SHA1, digits);
    }

    public DefaultTotpCodeGenerator(HashingAlgorithm algorithm, int digits) {
        if (algorithm == null) {
            throw new InvalidParameterException("HashingAlgorithm must not be null.");
        }
        if (digits < 1) {
            throw new InvalidParameterException("Number of digits must be higher than 0.");
        }

        this.algorithm = algorithm;
        this.digits = digits;
    }

    // ------------------------------------------------------------
    // -------------------- methods
    // ------------------------------------------------------------
    @Override
    public String generate(String secret, long counter) throws TotpCodeGenerationException {
        try {
            byte[] hash = generateHash(secret, counter);
            return getDigitsFromHash(hash);
        } catch (Exception e) {
            throw new TotpCodeGenerationException("Failed to generate code. See nested exception.", e);
        }
    }

    /**
     * Generate a HMAC-SHA1 hash of the counter number.
     */
    private byte[] generateHash(String key, long counter) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] data = new byte[8];
        long value = counter;

        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }

        // Create a HMAC-SHA1 signing key from the shared key
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(key);
        SecretKeySpec signKey = new SecretKeySpec(decodedKey, algorithm.getHmacAlgorithm());
        Mac mac = Mac.getInstance(algorithm.getHmacAlgorithm());
        mac.init(signKey);

        // Create a hash of the counter value
        return mac.doFinal(data);
    }

    /**
     * Get the n-digit code for a given hash.
     */
    private String getDigitsFromHash(byte[] hash) {
        int offset = hash[hash.length - 1] & 0xF;
        long truncatedHash = 0;

        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }

        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= Math.pow(10, digits);

        //Left pad with 0 s for a n -digit code
        return String.format("%0" + digits + "d", truncatedHash);
    }
}
