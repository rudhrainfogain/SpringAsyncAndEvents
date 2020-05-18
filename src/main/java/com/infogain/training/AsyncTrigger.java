package com.infogain.training;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncTrigger {

	/*
	 * Methods With Void Return Type
	 */
	@Async("asyncExecutor")
	public void send() {
		System.out.println("Trigger in a New Thread :: " + Thread.currentThread().getName());
	}

	/*
	 * Methods With Return Type
	 */
	@Async
	public Future<String> sendWithReturn(String args) throws InterruptedException {
		System.out.println("Trigger in a New Thread :: " + Thread.currentThread().getName());
		Thread.sleep(5000L);
		System.out.println("**************Exiting Async method****************");
		return new AsyncResult<String>(args);
	}

	/*
	 * Methods Throwing Exception
	 */
	@Async
	public void sendWithException(String args) throws Exception {
		throw new Exception("Server not found :: orginated from Thread :: " + Thread.currentThread().getName());
	}

	@Async
	public void fetchFuture(Future<String> future) throws InterruptedException, ExecutionException {
		while(!future.isDone()) {
			System.out.println(future.get());
		}
		
	}
}
