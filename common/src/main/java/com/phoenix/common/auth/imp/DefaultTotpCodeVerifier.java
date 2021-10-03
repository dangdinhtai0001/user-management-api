/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.auth.imp;

import com.phoenix.common.auth.TotpCodeGenerator;
import com.phoenix.common.auth.TotpCodeVerifier;
import com.phoenix.common.exceptions.TotpCodeGenerationException;
import com.phoenix.common.time.TimeProvider;

public class DefaultTotpCodeVerifier implements TotpCodeVerifier {
    // ------------------------------------------------------------
    // -------------------- field
    // ------------------------------------------------------------
    private final TotpCodeGenerator codeGenerator;
    private final TimeProvider timeProvider;
    private int timePeriod = 30;
    private int allowedTimePeriodDiscrepancy = 1;

    // ------------------------------------------------------------
    // -------------------- Constructor
    // ------------------------------------------------------------
    public DefaultTotpCodeVerifier(TotpCodeGenerator codeGenerator, TimeProvider timeProvider) {
        this.codeGenerator = codeGenerator;
        this.timeProvider = timeProvider;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void setAllowedTimePeriodDiscrepancy(int allowedTimePeriodDiscrepancy) {
        this.allowedTimePeriodDiscrepancy = allowedTimePeriodDiscrepancy;
    }

    // ------------------------------------------------------------
    // -------------------- methods
    // ------------------------------------------------------------
    @Override
    public boolean isValidCode(String secret, String code) {
        // Get the current number of seconds since the epoch and
        // calculate the number of time periods passed.
        // The java.lang.Math.floorDiv() is a built-in math function in java which returns the largest
        // (closest to positive infinity) int value that is less than or equal to the algebraic quotient.
        // As floorDiv() is static, so object creation is not required.
        long currentBucket = Math.floorDiv(timeProvider.getTime(), timePeriod);

        // Calculate and compare the codes for all the "valid" time periods,
        // even if we get an early match, to avoid timing attacks
        boolean success = false;
        for (int i = -allowedTimePeriodDiscrepancy; i <= allowedTimePeriodDiscrepancy; i++) {
            success = checkCode(secret, currentBucket + i, code) || success;
        }

        return success;
    }

    /**
     * Check if a code matches for a given secret and counter.
     */
    private boolean checkCode(String secret, long counter, String code) {
        try {
            String actualCode = codeGenerator.generate(secret, counter);
            return timeSafeStringComparison(actualCode, code);
        } catch (TotpCodeGenerationException e) {
            return false;
        }
    }

    /**
     * Compare two strings for equality without leaking timing information.
     */
    private boolean timeSafeStringComparison(String a, String b) {
        byte[] aBytes = a.getBytes();
        byte[] bBytes = b.getBytes();

        if (aBytes.length != bBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < aBytes.length; i++) {
            result |= aBytes[i] ^ bBytes[i];
        }

        return result == 0;
    }
}
