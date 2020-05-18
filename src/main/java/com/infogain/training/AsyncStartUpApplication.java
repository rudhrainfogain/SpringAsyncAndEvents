package com.infogain.training;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.infogain.training.publisher.AsyncCallerPlusEventPublisher;

@SpringBootApplication
public class AsyncStartUpApplication implements CommandLineRunner {

	@Autowired
	AsyncCallerPlusEventPublisher caller;

	public static void main(String[] args) {
		SpringApplication.run(AsyncStartUpApplication.class, args);
		System.out.println("*******main*******");
	}

	@Override
	public void run(String... args) throws Exception {
		caller.rightWayToCall("1");
		Thread.sleep(1000);
		System.out.println("**************");
		Thread.sleep(1000);
		caller.wrongWayToCall();
		Thread.sleep(1000);
		System.out.println("**************");
		
		Thread.sleep(1000); caller.wrongWayToCall2();
		  System.out.println("**************"); 
		  caller.rightWayWithReturn("return");
		  System.out.println("**************"); Thread.sleep(1000);
		  caller.rightWayWithException("exception");
		 
		System.out.println("**************");
		//caller.publishEvent("event-1", 1);
		System.out.println("**************");
		//caller.publishAnotherEvent("Anotherevent-1", 1);
		System.out.println("**************");
		//caller.publishEvent("event-2", 2);

	}

	@Bean(name = "asyncExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(3);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("AsynchThread-");
		executor.initialize();
		return executor;
	}

}
