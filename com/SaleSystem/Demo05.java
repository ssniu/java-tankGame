package com.SaleSystem;

public class Demo05 {

	public static void main(String[] args) {
		
		TicketWindow t1 = new TicketWindow();
		//TicketWindow t2 = new TicketWindow();
		//TicketWindow t3 = new TicketWindow();
		
		Thread t01 = new Thread(t1);
		Thread t02 = new Thread(t1);
		Thread t03 = new Thread(t1);
		
		t01.start();
		t02.start();
		t03.start();
		
	}

}

//Ticket station
class TicketWindow implements Runnable{
	private int num = 2000;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		while(true){
			
			synchronized(this) {
			//Check it has tickets or not
			//Thread.currentThread().getName() -- get current thread name
			if(num > 0){
				System.out.println("Sale " + num);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				num--;
			}else {
				break;
			}
		}
		
	}
	}	
	
}
