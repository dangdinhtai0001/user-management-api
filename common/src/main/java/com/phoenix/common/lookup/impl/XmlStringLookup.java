package com.phoenix.common.lookup.impl;

import com.phoenix.common.exceptions.IllegalArgumentExceptions;
import com.phoenix.common.lookup.AbstractStringLookup;
import com.phoenix.common.util.StringUtils;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Looks up keys from an XML document.
 */
public class XmlStringLookup extends AbstractStringLookup {
    /**
     * Defines the singleton for this class.
     */
    static final XmlStringLookup INSTANCE = new XmlStringLookup();

    /**
     * No need to build instances for now.
     */
    private XmlStringLookup() {
        // empty
    }

    /**
     * Looks up the value for the key in the format "DocumentPath:XPath".
     * <p>
     * For example: "com/domain/document.xml:/path/to/node".
     * </p>
     *
     * @param key the key to be looked up, may be null
     * @return The value associated with the key.
     */
    @Override
    public String lookup(final String key) {
        if (key == null) {
            return null;
        }
        final String[] keys = key.split(SPLIT_STR);
        final int keyLen = keys.length;
        if (keyLen != 2) {
            throw IllegalArgumentExceptions.format("Bad XML key format [%s]; expected format is DocumentPath:XPath.",
                    key);
        }
        final String documentPath = keys[0];
        final String xpath = StringUtils.substringAfter(key, SPLIT_CH);
        try (InputStream inputStream = Files.newInputStream(Paths.get(documentPath))) {
            return XPathFactory.newInstance().newXPath().evaluate(xpath, new InputSource(inputStream));
        } catch (final Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up XML document [%s] and XPath [%s].",
                    documentPath, xpath);
        }
    }
}
