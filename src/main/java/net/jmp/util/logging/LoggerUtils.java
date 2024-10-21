package net.jmp.util.logging;

/*
 * (#)LoggerUtils.java  1.2.0   10/20/2024
 * (#)LoggerUtils.java  1.1.0   09/27/2024
 * (#)LoggerUtils.java  1.0.0   09/24/2024
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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static net.jmp.util.logging.LoggerConstants.*;

import org.slf4j.Logger;

/// Logger utilities to assist in creating
/// trace entry and exit records.
///
/// @version    1.2.0
/// @since      1.0.0
public final class LoggerUtils {
    /// The default constructor.
    private LoggerUtils() {
        super();
    }

    /// An enumeration of stack trace handlers
    ///
    /// @since  1.1.0
    private enum StackTraceHandlerType {
        /// When catching a throwable
        CATCHING,

        /// When throwing a throwable
        THROWING;
    }

    /// Format a catching message.
    ///
    /// @param  throwable   java.lang.Throwable
    /// @return             java.lang.String
    /// @since              1.1.0
    public static String catching(final Throwable throwable) {
        return catchingOrThrowing(StackTraceHandlerType.CATCHING, throwable);
    }

    /// Format a trace entry message.
    ///
    /// @return java.lang.String
    public static String entry() {
        return ENTRY;
    }

    /// Format a trace entry message with arguments.
    ///
    /// @param  objArray    java.lang.Object[]
    /// @return             java.lang.String
    public static String entryWith(final Object... objArray) {
        final StringBuilder sb = new StringBuilder();

        sb.append(ENTRY).append(" with (");

        for (int i = 0; i < objArray.length; i++) {
            sb.append(objArray[i]);

            if (i != objArray.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    /// Format a trace exit message.
    ///
    /// @return java.lang.String
    public static String exit() {
        return EXIT;
    }

    /// Format a trace exit message with an argument.
    ///
    /// @param  object  java.lang.Object
    /// @return         java.lang.String
    public static String exitWith(final Object object) {
        return EXIT + " with (" + object + ")";
    }

    /// Format a throwing message.
    ///
    /// @param  throwable   java.lang.Throwable
    /// @return             java.lang.String
    /// @since              1.1.0
    public static String throwing(final Throwable throwable) {
        return catchingOrThrowing(StackTraceHandlerType.THROWING, throwable);
    }

    /// Format a throwing message and rethrow the throwable.
    ///
    /// @param  throwable   java.lang.Throwable
    /// @param  logger      org.slf4j.Logger
    /// @return             java.lang.Throwable
    /// @since              scratch
    public static Throwable throwing(final Throwable throwable, final Logger logger) {
        if (logger.isErrorEnabled()) {
            logger.error(catchingOrThrowing(StackTraceHandlerType.THROWING, throwable));
        }

        return throwable;
    }

    /// Build and return a string for either
    /// the catching or the throwing methods.
    ///
    /// @param  type        net.jmp.util.logging.LoggerUtils.StackTraceHandlerType
    /// @param  throwable   java.lang.Throwable
    /// @return             java.lang.String
    /// @since              1.1.0
    private static String catchingOrThrowing(final StackTraceHandlerType type,
                                             final Throwable throwable) {
        final StringBuilder sb = new StringBuilder();

        String stackTrace = null;

        try (final StringWriter sw = new StringWriter()) {
            throwable.printStackTrace(new PrintWriter(sw));

            stackTrace = sw.toString();
        } catch (final IOException ioe) {
            ioe.printStackTrace(System.err);
        }

        if (stackTrace != null) {
            switch (type) {
                case StackTraceHandlerType.CATCHING -> sb.append(CATCHING);
                case StackTraceHandlerType.THROWING -> sb.append(THROWING);
                default -> throw new IllegalStateException("Stack trace handler type is unrecognized");
            }

            sb.append('\n').append(stackTrace);

            return sb.toString();
        } else {
            return "";
        }
    }
}
