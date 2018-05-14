package org.src.design.pattern;


public interface Observerable {

	void registObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObserver();
	
}
