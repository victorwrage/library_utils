/*
 * Dollar, http://bitbucket.org/dfa/dollar
 * (c) 2010, 2011 Davide Angelocola <davide.angelocola@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bitbucket.dollar;

/**
 * Contains static method for checking preconditions which throw exceptions if
 * conditions are not met.
 * 
 */
public class Preconditions {

    /**
     * Throws an {@link IllegalArgumentException} if condition is false.
     * 
     * @param condition
     *            Boolean expression which should resolve as true.
     * @param fmt
     *            Message to use in exception using
     *            {@link String#format(String, Object...)}.
     * @param args
     *            Arguments for message to use in exception using
     *            {@link String#format(String, Object...)}.
     * @throws IllegalArgumentException
     *             if condition is false.
     */
    public static void require(boolean condition, String fmt, Object... args) {
        if (!condition) {
            throw new IllegalArgumentException(String.format(fmt, args));
        }
    }

    /**
     * Throws an {@link IllegalArgumentException} if ref is null.
     * 
     * @param reference
     *            Object reference which should not be null.
     * @return reference
     * @throws IllegalArgumentException
     *             if reference is null.
     */
    public static <T> T requireNotNull(T reference) {
        if (reference == null) {
            throw new IllegalArgumentException();
        }
        return reference;
    }

    /**
     * Throws an {@link IllegalArgumentException} if ref is null.
     * 
     * @param reference
     *            Object reference which should not be null.
     * @param fmt
     *            Message to use in exception using
     *            {@link String#format(String, Object...)}.
     * @param args
     *            Arguments for message to use in exception using
     *            {@link String#format(String, Object...)}.
     * @return reference
     * @throws IllegalArgumentException
     *             if reference is null.
     */
    public static <T> T requireNotNull(T reference, String fmt, Object... args) {
        if (null == reference) {
            throw new IllegalArgumentException(String.format(fmt, args));
        }
        return reference;
    }
}
