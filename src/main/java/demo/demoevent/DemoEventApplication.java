package demo.demoevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

/**
 * Enable the Async...
 * 
 * @author Johnson Fu
 *
 */
@SpringBootApplication
@Slf4j
@EnableAsync
public class DemoEventApplication implements CommandLineRunner {
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public static void main(String[] args) {
		SpringApplication.run(DemoEventApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("start...");
		
		CustomEvent customEvent1 = new CustomEvent(this, "embedded data");
		
		applicationEventPublisher.publishEvent(customEvent1);
		
		
		CustomEvent customEvent2 = new CustomEvent(this, "secret data");
		
		applicationEventPublisher.publishEvent(customEvent2);
		
		log.info("end...");
		
	}
	
	/**
	 * Event Handler and Async
	 * 
	 * @param e
	 */
	@Async
	@EventListener
	public void handleCustomEvent(CustomEvent e) {
		
		log.info("handleCustomEvent: start handle: {}", e.getData());
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		log.info("handleCustomEvent: end handle");

	}
	

}
