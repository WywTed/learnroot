package org.src.java.advance;

/**
 * stackoverflowerror
 * @author huhu
 *
 */
public class SofeTest {
	
	static int deep = 0;

	public static void cycle() {
		while(true) {
			deep = deep + 1;
			System.out.println("stack deeper: " + deep);
			cycle();
		}
	}
	
	private static ThreadLocal<String> tl = new ThreadLocal<>();
	
	private static InheritableThreadLocal<InheritableData> inheritableThreadLocal = new InheritableThreadLocal<>();
	
	public static void main(String[] args) {
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		tg.activeCount();
		tl.set("I'm main thread");
		InheritableData data = new InheritableData(new Integer(500), "testmain");
		inheritableThreadLocal.set(data);
		Thread t = new Thread(new CycleuseTest());
		t.start();
		try {
			Thread.sleep(2000);
			data.setCount(new Integer(1000));
			data.setName(new String("testmain1"));
			System.out.println("main thread: " + data);
			Thread.sleep(3000);
			InheritableData bak = inheritableThreadLocal.get();
			System.out.println("main thread: " + bak);
			inheritableThreadLocal.set(data);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static class CycleuseTest implements Runnable{

		@Override
		public void run() {
//			String currentThreadValue = tl.get();
//			System.out.println(currentThreadValue);
//			tl.remove();
			InheritableData data = inheritableThreadLocal.get();
			System.out.println("sub thread: " + data);
			try {
				Thread.sleep(3000);
				System.out.println("sub thread: " + data);
				data = inheritableThreadLocal.get();
				System.out.println("sub thread: " + data);
				inheritableThreadLocal.remove();
				Thread.sleep(3000);
				System.out.println("sub thread: " + data);
				data = inheritableThreadLocal.get();
				System.out.println("sub thread: " + data);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static class InheritableData{
		Integer count;
		String name;
		
		public InheritableData(Integer count, String name) {
			this.count = count;
			this.name = name;
		}

		@Override
		public String toString() {
			return "InheritableData [count=" + count + ", name=" + name + "]";
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}


