package edu.eci.arsw.GuidFinderAPI.service;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.GuidFinderAPI.model.UUID;

@Service
public class UUIDServices {
	
	Hashtable<Integer,UUID> uuids = new Hashtable<>();
	
	public UUIDServices() {
		 UUID ud = new UUID();
		 ud.setCount(0);
		 ud.setFecha("2019-02-21T05:10:00");
		 ud.setGuid("d0692660-c39a-4d73-9496-d9df0c4ebdf3");
		 uuids.put(1,ud);
	}
	
	
	public UUID getUUID(String id) {
		if(uuids.containsKey(id)) {
			return uuids.get(id);
		}
		return null;
	}
}
