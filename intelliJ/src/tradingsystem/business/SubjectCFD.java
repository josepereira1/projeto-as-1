package tradingsystem.business;

import java.util.concurrent.Future;

public interface SubjectCFD {
    Future<Void> registerObserver(Observer observer);
    Future<Void> notifyObservers(Object arg);
}
