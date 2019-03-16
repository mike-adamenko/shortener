package com.neueda.shortener.util;

import java.util.zip.CRC32;

/**
 *
 */
public final class LinkUtils {
    private static CRC32 crc = new CRC32();

    public static String shortenize(String url) {
        crc.reset();
        crc.update(url.getBytes());
        return Long.toHexString(crc.getValue());
    }
}
