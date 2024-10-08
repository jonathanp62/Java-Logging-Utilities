package net.jmp.util.logging;

/*
 * (#)LoggerConstants.java  1.1.0   09/27/2024
 * (#)LoggerConstants.java  1.0.0   09/24/2024
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

/// Constants defined for use by the logging utility.
///
/// @version    1.1.0
/// @since      1.0.0
final class LoggerConstants {
    /// The text for a catching message.
    ///
    /// @since  1.1.0
    static final String CATCHING = "catching";

    /// The text for a trace entry message.
    static final String ENTRY = "entry";

    /// The text for a trace exit message.
    static final String EXIT = "exit";

    /// The text for a throwing message.
    ///
    /// @since  1.1.0
    static final String THROWING = "throwing";

    /// The default constructor.
    private LoggerConstants() {
        super();
    }
}
