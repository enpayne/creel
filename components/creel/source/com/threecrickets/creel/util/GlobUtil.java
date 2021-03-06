/**
 * Copyright 2015-2017 Three Crickets LLC.
 * <p>
 * The contents of this file are subject to the terms of the LGPL version 3.0:
 * http://www.gnu.org/copyleft/lesser.html
 * <p>
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly from Three Crickets
 * at http://threecrickets.com/
 */

package com.threecrickets.creel.util;

import java.util.regex.Pattern;

/**
 * Glob utilities.
 * 
 * @author Tal Liron
 */
public abstract class GlobUtil
{
	/**
	 * Tests if a string matches a glob pattern.
	 * <p>
	 * The pattern may contain any number of '*' or '?' wildcards. Escape '*' or
	 * '?' using a preceding '\'.
	 * 
	 * @param string
	 *        The string to test
	 * @param pattern
	 *        The pattern
	 * @return True if the string matches the pattern
	 */
	public static boolean matches( String string, String pattern )
	{
		if( ( pattern == null ) || pattern.isEmpty() || pattern.equals( "*" ) )
			// Match everything
			return true;

		String regex = toRegEx( pattern );
		return Pattern.matches( regex, string );
	}

	/**
	 * Transforms a glob pattern into a compiled regular expression.
	 * <p>
	 * The pattern may contain any number of '*' or '?' wildcards. Escape '*' or
	 * '?' using a preceding '\'.
	 * 
	 * @param pattern
	 *        The pattern
	 * @return The compiled regular expression.
	 */
	public static Pattern compile( String pattern )
	{
		return Pattern.compile( toRegEx( pattern ) );
	}

	/**
	 * Transforms a glob pattern into a regular expression pattern.
	 * <p>
	 * The pattern may contain any number of '*' or '?' wildcards. Escape '*' or
	 * '?' using a preceding '\'.
	 * 
	 * @param pattern
	 *        The pattern
	 * @return The regular expression
	 */
	public static String toRegEx( String pattern )
	{
		StringBuilder regex = new StringBuilder();

		int length = pattern.length();
		char lastC = 0;
		int lastI = 0;
		for( int i = 0; i < length; i++ )
		{
			char c = pattern.charAt( i );

			if( ( ( c == '*' ) || ( c == '?' ) ) && ( lastC != '\\' ) )
			{
				regex.append( Pattern.quote( pattern.substring( lastI, i ) ) );
				regex.append( "[\\s\\S]" );
				if( c == '*' )
					regex.append( '*' );
				lastI = i + 1;
			}

			lastC = c;
		}

		if( lastI < length )
			regex.append( Pattern.quote( pattern.substring( lastI ) ) );

		regex.insert( 0, '^' );
		regex.append( '$' );

		return regex.toString();
	}

	/**
	 * Checks if the pattern has wildcards.
	 * 
	 * @param pattern
	 *        The pattern
	 * @return True if the pattern has wildcards
	 */
	public static boolean hasWildcards( String pattern )
	{
		int length = pattern.length();
		char lastC = 0;
		for( int i = 0; i < length; i++ )
		{
			char c = pattern.charAt( i );
			if( ( ( c == '*' ) || ( c == '?' ) ) && ( lastC != '\\' ) )
				return true;
			lastC = c;
		}

		return false;
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private GlobUtil()
	{
	}
}
