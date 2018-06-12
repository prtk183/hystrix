/*package com.sample.consumer;

import java.time.ZonedDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.HystrixCommandMetrics.HealthCounts;


import rx.Observable;

@SpringBootApplication
@EnableCircuitBreaker
public class ConsumerApplication {

	 static class IdGeneratingCommand extends HystrixObservableCommand<String> {
	        private final ConsumerApplication app;
	 
	        public IdGeneratingCommand(HystrixObservableCommand.Setter setter, ConsumerApplication app) {
	            super(setter);
	            this.app = app;
	        }
	 
	        @Override
	        protected Observable<String> construct() {
	            return Observable.create(observer -> {
	                try {
	                    if (!observer.isUnsubscribed()) {
	                        observer.onNext(app.generateId());
	                        observer.onCompleted();
	                    }
	                } catch (Exception e) {
	                    observer.onError(e);
	                }
	            });
	        }
	    };
	 
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
		
		ConsumerApplication app = new ConsumerApplication();
		 
	        HystrixObservableCommand.Setter setter = HystrixObservableCommand.Setter
	                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("unstableAppCmdGroup"))
	                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true)
	                        .withCircuitBreakerErrorThresholdPercentage(50)
	                        .withCircuitBreakerSleepWindowInMilliseconds(1000).withCircuitBreakerRequestVolumeThreshold(1));
	 
	        for (int i = 0; i < 10; i++) {
	            CountDownLatch l = new CountDownLatch(1);
	            IdGeneratingCommand cmd = new IdGeneratingCommand(setter, app);
	            final HealthCounts healthCounts = cmd.getMetrics().getHealthCounts();
	            System.out.printf("circuit-breaker state is open: %s, %d errors of %d requests\n",
	                    cmd.isCircuitBreakerOpen(), healthCounts.getErrorCount(), healthCounts.getTotalRequests());
	            Observable<String> observable = cmd.observe();
	            observable.subscribe(s -> {
	                System.out.printf("HystrixExample: id '%s' received at '%s'\n", s, ZonedDateTime.now());
	            } , t -> {
	                System.err.printf("HystrixExample: error %s, circuit-breaker state is open: %s\n", t,
	                        cmd.isCircuitBreakerOpen());
	            } , () -> {
	                l.countDown();
	            });
	            try {
					l.await(4, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	public String generateId() {
		// TODO Auto-generated method stub
		return null;
	}
	}

*/