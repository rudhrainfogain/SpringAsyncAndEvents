package com.infogain.training.publisher;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.infogain.training.AsyncTrigger;
import com.infogain.training.model.AnotherCustomEvent;
import com.infogain.training.model.CustomEvent;

@Component
public class AsyncCallerPlusEventPublisher {
	@Autowired
	AsyncTrigger asyncTriggerObject;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	String param;

	/*
	 * Triggering Method With Return Type Void
	 */
	public void rightWayToCall(String arg) {
		System.out.println("Calling From rightWayToCall Thread " + Thread.currentThread().getName());
		param = arg;
		asyncTriggerObject.send();
	}

	/*
	 * Triggering Method With Return Type
	 */
	public void rightWayWithReturn(String args) throws InterruptedException, ExecutionException {
		System.out.println("Calling From rightWayWithReturn Thread " + Thread.currentThread().getName());
		// delay of 1s for demonstration purposes
		Thread.sleep(1000L);
		Future<String> future = asyncTriggerObject.sendWithReturn(args);
		System.out.println("*****************Exiting**************");
		asyncTriggerObject.fetchFuture(future);
	}
	
	/*
	 * Triggering Method Exception
	 */
	public void rightWayWithException(String args) throws Exception {
		System.out.println("Calling From rightWayWithException Thread " + Thread.currentThread().getName());
		asyncTriggerObject.sendWithException(args);
	}

	public void wrongWayToCall() {
		System.out.println("Calling From wrongWayToCall Thread " + Thread.currentThread().getName());
		this.send();
	}

	public void wrongWayToCall2() {
		System.out.println("Calling From wrongWayToCall2 Thread " + Thread.currentThread().getName());
		AsyncTrigger asyncTriggerObject = new AsyncTrigger();
		asyncTriggerObject.send();
	}

	@Async
	public void send() {
		System.out.println("Trigger in the same Main Thread :: " + Thread.currentThread().getName());
	}
	
	public void publishEvent(final String name, final int code) {
	    publisher.publishEvent(new CustomEvent(this, name, code));
	}
	
	public void publishAnotherEvent(final String name, final int code) {
	    publisher.publishEvent(new AnotherCustomEvent(this, name, code));
	}
}