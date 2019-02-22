package edu.eci.arsw.GuidFinderDesktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class GuidFinder  implements NativeKeyListener{

	private static UUID[] guids;
	private static final int particiones = 4;
	private ThreadJohn tr[];
	private long act = System.nanoTime();
	public static Object monitor = new Object();
	

	public GuidFinder() throws Exception {
		getGuids();
	}
	
	public void keyConstructor() {
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(this);
		LogManager.getLogManager().reset();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
	}

	public static UUID[] getGuids() throws Exception {
		if (guids == null) {
			System.out.println("es nulo");
			FileInputStream fi;
			fi = new FileInputStream(new File("guids.eci"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			guids = (UUID[]) oi.readObject();
			oi.close();
			fi.close();
		}
		
	
		return guids;
	}

	

	public int countGuids(UUID guidToFind) {

		int count = 0;
		for (UUID uuid : guids) {
			System.out.println(uuid.toString());
			if (uuid.equals(guidToFind)) {
				count++;
			}
		}
		return count;
	}

	public int countGuidsWithThread(UUID guidToFind) {
		tr = new ThreadJohn[particiones];
		int count = 0, lim = 0;
		int div = guids.length / particiones;
		int red = guids.length % particiones;
		for (int i = 0; i < 4; i++) {
			tr[i] = new ThreadJohn(lim, lim + div + ((i + 1 == particiones) ? red : 0), guidToFind, guids);
			lim = lim + div + ((i + 1 == particiones) ? red : 0);
			tr[i].start();
		}

		
		long sec = 1000000000;
		while (true) {			
			if(System.nanoTime() - act >= 1 * sec){
				System.out.println("Domir");
				for (ThreadJohn threadJohn : tr) {
					synchronized (monitor) {
						monitor.notifyAll();
					}
				}		
				act = System.nanoTime();
			}	
			System.out.println("Aqui");
			if (!endProgram())break;
		}
		

		for (ThreadJohn threadJohn : tr) {
			count += threadJohn.getCount();
		}
		return count;
	}

	public boolean endProgram() {
		boolean vivos = false;
		int conti = 0;
		for (ThreadJohn threadJohnuuid : tr) {
			if (threadJohnuuid.isAlive()) {
				System.out.println("Vivo "  + conti);
				vivos = true;
			}
			conti++;
		}
		return vivos;
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Dormia");
		for (ThreadJohn threadJohn : tr) {
			threadJohn.setIsWait(false);
			
		}
		act = System.nanoTime();
		
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
