package com.neueda.shortener.util;

import java.util.zip.CRC32;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public final class LinkUtils {
    private final static CRC32 crc = new CRC32();

    public static String shortenize(String url) {
        crc.reset();
        crc.update(url.getBytes());
        return Long.toHexString(crc.getValue());
    }
}
