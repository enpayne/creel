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

package com.threecrickets.creel.event;

/**
 * Handles events.
 * 
 * @author Tal Liron
 */
public interface EventHandler
{
	/**
	 * Handle the event. Note that events may arrive from several threads at
	 * once, so they must be handled in a thread-safe manner.
	 * 
	 * @param event
	 *        The event
	 * @return True if the event was swallowed
	 */
	public boolean handleEvent( Event event );
}
