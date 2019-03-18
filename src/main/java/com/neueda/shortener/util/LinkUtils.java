package com.neueda.shortener.util;

import java.util.zip.CRC32;

/**
 * Utils.
 *
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public final class LinkUtils {
    private final static CRC32 crc = new CRC32();

    /**
     * Shortenizes url using CRC32 algorithm. Uses crc.reset() to get the same result for the given original URL every time it's called.
     * @param url original URL
     * @return shortenized slug
     */
    public static String shortenize(String url) {
        crc.reset();
        crc.update(url.getBytes());
        return Long.toHexString(crc.getValue());
    }
}
