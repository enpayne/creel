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

package com.threecrickets.creel.ant.internal;

import java.util.HashMap;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DynamicAttribute;

/**
 * An Ant tag that supports any arbitrary attribute.
 * 
 * @author Tal Liron
 */
public class DynamicType extends HashMap<String, String> implements DynamicAttribute
{
	//
	// DynamicAttribute
	//

	public void setDynamicAttribute( String name, String value ) throws BuildException
	{
		put( name, value );
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private static final long serialVersionUID = 1L;
}
