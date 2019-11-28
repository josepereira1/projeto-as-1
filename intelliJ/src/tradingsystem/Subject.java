package tradingsystem;

import java.util.concurrent.Future;

public interface Subject {
    void registerObserver(Observer observer);
    Future<Void> notifyObservers();
}
