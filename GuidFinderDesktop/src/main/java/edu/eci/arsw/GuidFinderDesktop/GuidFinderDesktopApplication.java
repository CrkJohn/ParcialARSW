package edu.eci.arsw.GuidFinderDesktop;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuidFinderDesktopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuidFinderDesktopApplication.class, args);		
		try {
			GuidFinder finder= new GuidFinder();
			finder.keyConstructor();
			System.out.println(finder.countGuidsWithThread(UUID.fromString("b9bd111f-3127-4bc6-b190-9e6e25911991")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
