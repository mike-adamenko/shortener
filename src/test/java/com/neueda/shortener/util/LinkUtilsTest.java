package com.neueda.shortener.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *@author Mihail Adamenko
 */
public class LinkUtilsTest {

    @Test
    public void shortenizeShouldReturnTheSameResult2Times(){
        String expectedResult = "331e5b6b";
        assertEquals(expectedResult, LinkUtils.shortenize("https://www.google.com"));
        assertEquals(expectedResult, LinkUtils.shortenize("https://www.google.com"));
    }

}