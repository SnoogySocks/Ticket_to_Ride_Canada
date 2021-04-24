package util;

/**
 * The Observer pattern revolves around observers and observable objects.
 * When an observable object emits an event, the update() methods of its observers is called.
 *
 * I find this to be quite useful when trying to decouple classes (remove dependencies)
 * by eliminating the need for individual classes to "know" about each other. Instead we
 * depend on a signal/emit system that any Observer can subscribe to.
 *
 * Thanks for attending my TED talk :)
 * @author Nathan
 */
public interface Observer {
    public void update(Observable obj, EventType event);
}
