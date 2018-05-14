package org.src.java.advance;

/**
 * Hello world!
 *
 */
public class App 
{
    private static volatile App instance;
    
    private App() {}
    
    public static App getInstance() {
    	if(instance != null) return instance;
    	synchronized(App.class){
			if(instance != null) {
				return instance;
			}
			instance = new App();
			return instance;
		}
    }
}
