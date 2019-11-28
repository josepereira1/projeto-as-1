package tradingsystem;

import java.util.concurrent.Future;

public interface Subject {
    Future<Void> registerObserver(Observer observer);
    Future<Void> notifyObservers(Object arg);
}
