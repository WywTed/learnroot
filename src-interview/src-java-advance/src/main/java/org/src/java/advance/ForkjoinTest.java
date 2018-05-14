package org.src.java.advance;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkjoinTest {
	
	private static class CountTask extends RecursiveTask<Integer>{
		
		int start, end;
		int THREAD_HOLD = 2;
		
		
		public CountTask(int start, int end) {
			this.end = end;
			this.start = start;
		}

		@Override
		protected Integer compute() {
			int sum = 0;
			boolean canCompute = (end - start) <= THREAD_HOLD;
			if(canCompute) {
				for(int i=start; i<=end; i++) {
					sum += i;
				}
				System.out.println(String.format("线程执行 %s, sum=%s", Thread.currentThread().getName(), sum));
			}else {
				int middle = (end + start) / 2;
				CountTask ctl = new CountTask(start,  middle);
				CountTask ctr = new CountTask(middle + 1, end);
				ctl.fork();
				ctr.fork();
				Integer retl = ctl.join();
				Integer retr = ctr.join();
				sum = retl + retr;
				System.out.println(String.format("线程执行 forkjoin %s, sum=%s", Thread.currentThread().getName(), sum));
			}
			return sum;
		}
	}
	
	public static void main(String[] args) {
		ForkJoinPool fjp = new ForkJoinPool();
		Future<Integer> result = fjp.submit(new CountTask(1, 100));
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
