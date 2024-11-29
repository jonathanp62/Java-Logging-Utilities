package net.jmp.util.logging;

/*
 * (#)TestLoggerUtilsForJunit4.java 1.3.0   11/29/2024
 * (#)TestLoggerUtilsForJunit4.java 1.2.0   10/21/2024
 * (#)TestLoggerUtilsForJunit4.java 1.1.0   09/27/2024
 * (#)TestLoggerUtilsForJunit4.java 1.0.0   09/24/2024
 *
 * MIT License
 *
 * Copyright (c) 2024 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import static net.jmp.util.logging.LoggerConstants.*;

import static net.jmp.util.logging.LoggerUtils.*;

import static org.junit.Assert.*;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/// The test class for LoggerUtils.
///
/// @version    1.3.0
/// @since      1.0.0
public final class TestLoggerUtilsForJunit4 {
    @Test
    public void testCatching() {
        try {
            final int i = 1 / 0;
        } catch (final Exception e) {
            final String result = catching(e);

            assertNotNull(result);
            assertTrue(result.startsWith("catching\njava.lang.ArithmeticException: / by zero"));
        }
    }

    @Test
    public void testEntry() {
        final String result = entry();

        assertNotNull(result);
        assertEquals(ENTRY, result);
    }

    @Test
    public void testEntryWith() {
        String result = entryWith(true);

        assertNotNull(result);
        assertEquals("entry with (true)", result);

        result = entryWith(123, true);

        assertNotNull(result);
        assertEquals("entry with (123, true)", result);

        result = entryWith(123, "some string", true);

        assertNotNull(result);
        assertEquals("entry with (123, some string, true)", result);
    }

    @Test
    public void testExit() {
        final String result = exit();

        assertNotNull(result);
        assertEquals(EXIT, result);
    }

    @Test
    public void testExitWith() {
        String result = exitWith(true);

        assertNotNull(result);
        assertEquals("exit with (true)", result);

        result = exitWith("some string");

        assertNotNull(result);
        assertEquals("exit with (some string)", result);

        result = exitWith(123);

        assertNotNull(result);
        assertEquals("exit with (123)", result);
    }

    @Test
    public void testThrowingWithoutLogger() {
        try {
            final int _ = 1 / 0;
        } catch (final Exception e) {
            final String result = throwing(e);

            assertNotNull(result);
            assertTrue(result.startsWith("throwing\njava.lang.ArithmeticException: / by zero"));
        }
    }

    @Test
    public void testThrowingWithLogger() {
        final Logger logger = LoggerFactory.getLogger("test.logger");

        try {
            final int _ = 1 / 0;
        } catch (final Exception e) {
            final Throwable t = throwing(e, logger);

            assertNotNull(t);
            assertEquals(e, t);
        }
    }
}
