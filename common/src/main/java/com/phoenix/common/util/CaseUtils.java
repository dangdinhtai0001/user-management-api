package com.phoenix.common.util;

import java.util.HashSet;
import java.util.Set;


public class CaseUtils {
    /**
     *
     * <h1> Convert chuỗi kí tự sang dạng CamelCase</h1>
     *
     * Chuyển đổi tất cả các từ được phân tách bằng dấu phân cách trong một Chuỗi thành camelCase, nghĩa là mỗi từ được
     * tạo thành từ một ký tự hoa đầu tiên và sau đó là một loạt các ký tự viết thường.
     * <p>
     * Các delimiters đại diện cho một tập hợp các ký tự được hiểu để phân tách các từ. Ký tự không phải delimiters đầu
     * tiên sau mỗi delimiters sẽ được viết hoa.
     * <p>
     * Ký tự đầu tiên của chuỗi có thể được viết hoa hoặc không, tùy thuộc vào giá trị của biến capitalizeFirstLetter
     * <p>
     * Nếu đầu vào là {@code null} thì sẽ return {@code null}
     * <p>
     * Nếu đầu vào chỉ bao gồm toàn delimiter thì sẽ trả về {@code ""}
     * <p>
     * Ví dụ:
     *
     * <pre>
     * CaseUtils.toCamelCase(null, false)                                 = null
     * CaseUtils.toCamelCase("", false, *)                                = ""
     * CaseUtils.toCamelCase(*, false, null)                              = *
     * CaseUtils.toCamelCase(*, true, new char[0])                        = *
     * CaseUtils.toCamelCase("To.Camel.Case", false, new char[]{'.'})     = "toCamelCase"
     * CaseUtils.toCamelCase(" to @ Camel case", true, new char[]{'@'})   = "ToCamelCase"
     * CaseUtils.toCamelCase(" @to @ Camel case", false, new char[]{'@'}) = "toCamelCase"
     * CaseUtils.toCamelCase(" @", false, new char[]{'@'})                = ""
     * </pre>
     *
     * @param str                   the String to be converted to camelCase, may be null
     * @param capitalizeFirstLetter boolean that determines if the first character of first word should be title case.
     * @param delimiters            set of characters to determine capitalization, null and/or empty array means whitespace
     * @return camelCase of String, {@code null} if null String input
     */
    public static String toCamelCase(String str, final boolean capitalizeFirstLetter, final char... delimiters) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        str = str.toLowerCase();
        final int strLen = str.length();
        final int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        final Set<Integer> delimiterSet = toDelimiterSet(delimiters);
        boolean capitalizeNext = capitalizeFirstLetter;
        for (int index = 0; index < strLen; ) {
            final int codePoint = str.codePointAt(index);

            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = outOffset != 0;
                index += Character.charCount(codePoint);
            } else if (capitalizeNext || outOffset == 0 && capitalizeFirstLetter) {
                final int titleCaseCodePoint = Character.toTitleCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
            } else {
                newCodePoints[outOffset++] = codePoint;
                index += Character.charCount(codePoint);
            }
        }

        return new String(newCodePoints, 0, outOffset);
    }

    /**
     * Converts an array of delimiters to a hash set of code points. Code point of space(32) is added
     * as the default value. The generated hash set provides O(1) lookup time.
     *
     * @param delimiters set of characters to determine capitalization, null means whitespace
     * @return Set<Integer>
     */
    private static Set<Integer> toDelimiterSet(final char[] delimiters) {
        final Set<Integer> delimiterHashSet = new HashSet<>();
        delimiterHashSet.add(Character.codePointAt(new char[]{' '}, 0));
        if (ArrayUtils.isEmpty(delimiters)) {
            return delimiterHashSet;
        }

        for (int index = 0; index < delimiters.length; index++) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }
        return delimiterHashSet;
    }


    /**
     * {@code CaseUtils} instance chỉ nên được dùng dưới dạng gọi các hàm static như
     * {@code CaseUtils.toCamelCase("foo bar", true, new char[]{'-'});}.
     *
     * <p> Hàm dựng này chỉ tạo ra để dùng cho những trường hợp cần phải khởi tại bean để sử dụng</p>
     */
    public CaseUtils() {
    }
}
