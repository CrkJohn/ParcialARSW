package edu.eci.arsw.GuidFinderDesktop;

import java.util.UUID;

public class ThreadJohn extends Thread {

	private int inferior;

	private int count;
	private int superior;
	private UUID path;
	private UUID[] guids;
	private boolean isWait;


	public ThreadJohn(int inf, int sup, UUID guidToFind2, UUID[] guids) {
		this.inferior = inf;
		this.superior = sup;
		this.count = 0;
		this.path = guidToFind2;
		this.guids = guids;
		isWait = true;
	}

	public int getCount() {
		return count;
	}

	public void setIsWait(boolean isWait) {
		this.isWait = isWait;
	}

	public boolean getIsWait() {
		return this.isWait;
	}

	@Override
	public void run() {
		for (int i = inferior; i < superior; i++) {
			if (isWait) {
				if (guids[i].equals(path)) {
					count++;
				}

			} else {
				synchronized (GuidFinder.monitor) {
					 try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}


			}
			isWait =  true;
		}
	}

}
