package gq.vapulite.eventapi.events.callables;

import gq.vapulite.eventapi.events.Event;
import gq.vapulite.eventapi.events.Cancellable;

/**
 * Abstract example implementation of the Cancellable interface.
 *
 * @author DarkMagician6
 * @since August 27, 2013
 */
public abstract class EventCancellable implements Event, Cancellable {
	public static int a = 1;
	public boolean cancelled;

	protected EventCancellable() {
	}

	/**
	 * @see Cancellable.isCancelled
	 */
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * @see Cancellable.setCancelled
	 */
	@Override
	public void setCancelled(boolean state) {
		cancelled = state;
	}

}
