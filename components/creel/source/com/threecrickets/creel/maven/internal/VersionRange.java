/**
 * Copyright 2015-2016 Three Crickets LLC.
 * <p>
 * The contents of this file are subject to the terms of the LGPL version 3.0:
 * http://www.gnu.org/copyleft/lesser.html
 * <p>
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly from Three Crickets
 * at http://threecrickets.com/
 */

package com.threecrickets.creel.maven.internal;

/**
 * @author Tal Liron
 */
public class VersionRange
{
	//
	// Construction
	//

	public VersionRange( String start, String end, boolean includeStart, boolean includeEnd )
	{
		this.start = new Version( start );
		this.end = new Version( end );
		this.includeStart = includeStart;
		this.includeEnd = includeEnd;
	}

	public VersionRange( Version start, Version end, boolean includeStart, boolean includeEnd )
	{
		this.start = start;
		this.end = end;
		this.includeStart = includeStart;
		this.includeEnd = includeEnd;
	}

	//
	// Attributes
	//

	public Version getStart()
	{
		return start;
	}

	public Version getEnd()
	{
		return end;
	}

	public boolean isIncludeStart()
	{
		return includeStart;
	}

	public boolean isIncludeEnd()
	{
		return includeEnd;
	}

	//
	// Operations
	//

	public boolean allows( Version version )
	{
		int compareStart = getStart() != null ? version.compareTo( getStart() ) : 1;
		int compareEnd = getEnd() != null ? getEnd().compareTo( version ) : 1;

		if( isIncludeStart() && isIncludeEnd() )
			return ( compareStart >= 0 ) && ( compareEnd >= 0 );
		else if( isIncludeStart() && !isIncludeEnd() )
			return ( compareStart >= 0 ) && ( compareEnd > 0 );
		else if( !isIncludeStart() && isIncludeEnd() )
			return ( compareStart > 0 ) && ( compareEnd >= 0 );
		else
			return ( compareStart > 0 ) && ( compareEnd > 0 );
	}

	//
	// Object
	//

	@Override
	public String toString()
	{
		return ( isIncludeStart() ? "[" : "(" ) + ( getStart() != null ? getStart() : "" ) + ',' + ( getEnd() != null ? getEnd() : "" ) + ( isIncludeEnd() ? ']' : ')' );
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private final Version start;

	private final Version end;

	private final boolean includeStart;

	private final boolean includeEnd;
}
