package util;

/**
 * @author Nathan
 */
public interface Observer {
    public void update(Observable obj, EventType event);
}
