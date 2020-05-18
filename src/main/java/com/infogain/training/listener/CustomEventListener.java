package com.infogain.training.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.infogain.training.model.AnotherCustomEvent;
import com.infogain.training.model.CustomEvent;

@Component
public class CustomEventListener{

	// @Async
	// @TransactionalEventListener
	// (condition = "#event.code == 1")
	//({ CustomEvent.class })
	@Async
	@EventListener({ AnotherCustomEvent.class })
	void handleCustom(Object event) {
		System.out.println("Event got triggered for ::  " + event);
		System.out.println("Triggered in a New Thread :: " + Thread.currentThread().getName());
	}

	@EventListener(condition = "#myEvent.code == 2")
	public AnotherCustomEvent handleContextEvent(CustomEvent myEvent) {
		System.out.println("event received: " + myEvent.getName());
		System.out.println("Triggered in a New Thread :: " + Thread.currentThread().getName());
		System.out.println("************************");
		return new AnotherCustomEvent(this, myEvent.getName(), myEvent.getCode());
	}

	@EventListener
	public void handleContextEvent(AnotherCustomEvent myEvent) {
		System.out.println("event received: " + myEvent.getName());
		System.out.println("Triggered in a New Thread :: " + Thread.currentThread().getName());
	}


}
