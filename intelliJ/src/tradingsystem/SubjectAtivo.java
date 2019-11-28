package tradingsystem;

public interface SubjectAtivo {
    void registerObserver(Observer observer);
    void notifyObservers(Object arg);
}
