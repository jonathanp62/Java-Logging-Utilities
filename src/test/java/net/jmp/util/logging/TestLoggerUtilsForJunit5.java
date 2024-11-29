package net.jmp.util.logging;

/*
 * (#)TestLoggerUtilsForJunit5.java 1.3.0   11/29/2024
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

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/// The test class for LoggerUtils.
///
/// @version    1.3.0
/// @since      1.3.0
@DisplayName("JUnit 5 tests for LoggerUtils")
final class TestLoggerUtilsForJunit5 {
    @DisplayName("Test catching")
    @Test
    void testCatching() {
        try {
            final int i = 1 / 0;
        } catch (final Exception e) {
            final String result = catching(e);

            assertAll(
                    () -> assertThat(result).isNotNull(),
                    () -> assertThat(result).startsWith("catching\njava.lang.ArithmeticException: / by zero")
            );
        }
    }

    @DisplayName("Test entry")
    @Test
    void testEntry() {
        final String result = entry();

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(ENTRY)
        );
    }

    @DisplayName("Test entry with")
    @Test
    void testEntryWith() {
        final String result1 = entryWith(true);

        assertAll(
                () -> assertThat(result1).isNotNull(),
                () -> assertThat(result1).isEqualTo("entry with (true)")
        );

        final String result2 = entryWith(123, true);

        assertAll(
                () -> assertThat(result2).isNotNull(),
                () -> assertThat(result2).isEqualTo("entry with (123, true)")
        );

        final String result3 = entryWith(123, "some string", true);

        assertAll(
                () -> assertThat(result3).isNotNull(),
                () -> assertThat(result3).isEqualTo("entry with (123, some string, true)")
        );
    }

    @DisplayName("Test exit")
    @Test
    void testExit() {
        final String result = exit();

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(EXIT)
        );
    }

    @DisplayName("Test exit with")
    @Test
    void testExitWith() {
        final String result1 = exitWith(true);

        assertAll(
                () -> assertThat(result1).isNotNull(),
                () -> assertThat(result1).isEqualTo("exit with (true)")
        );

        final String result2 = exitWith("some string");

        assertAll(
                () -> assertThat(result2).isNotNull(),
                () -> assertThat(result2).isEqualTo("exit with (some string)")
        );

        final String result3 = exitWith(123);

        assertAll(
                () -> assertThat(result3).isNotNull(),
                () -> assertThat(result3).isEqualTo("exit with (123)")
        );
    }

    @DisplayName("Test throwing without logger")
    @Test
    void testThrowingWithoutLogger() {
        try {
            final int _ = 1 / 0;
        } catch (final Exception e) {
            final String result = throwing(e);

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertTrue(result.startsWith("throwing\njava.lang.ArithmeticException: / by zero"))
            );
        }
    }

    @DisplayName("Test throwing with logger")
    @Test
    void testThrowingWithLogger() {
        final Logger logger = LoggerFactory.getLogger("test.logger");

        try {
            final int _ = 1 / 0;
        } catch (final Exception e) {
            final Throwable t = throwing(e, logger);

            assertAll(
                    () -> assertNotNull(t),
                    () -> assertEquals(e, t)
            );
        }
    }
}
