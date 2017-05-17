package tank3;

import java.util.Vector;

//Create Tank class as a super class
class Tank{

	
	int x = 0;
	int y = 0;
	int direct = 0;
	int speed = 1;
	int color;
	boolean isLive = true;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Tank(int x, int y){
		this.x = x;
		this.y = y;
	}
}
//Child tank class 
class Hero extends Tank{
	
	//Bullets b1 = null;
	
	Vector<Bullets> ss = new Vector<Bullets>();
	Bullets b = null;
	
	public Hero(int x, int y){
		super(x, y);
		
	}
	
	public void shotEnemy(){
		
		switch(this.direct){
		case 0:
			b = new Bullets(x + 10, y, 0);
			ss.add(b);
			break;
		case 1:
			b = new Bullets(x + 30, y + 10, 1);
			ss.add(b);
			break;
		case 2:
			b = new Bullets(x + 10, y + 30, 2);
			ss.add(b);
			break;
		case 3: 
			b = new Bullets(x, y + 10, 3);
			ss.add(b);
			break;
		}
		
		//Start the thread
		Thread t = new Thread(b);
		t.start();
		
	}
	//move actions
	public void moveUp(){
		y -= speed;
	}
	
	public void moveRight(){
		x += speed;
	}
	
	public void moveLeft(){
		x -= speed;
	}
	public void moveDown(){
		y += speed;
	}
}

//Enemy tank class
class EnemyTank extends Tank implements Runnable{
	
	
	
	// Create a vector to save bullets
	Vector<Bullets> ss = new Vector<Bullets>();

	int times = 0;
	
	
	
	public EnemyTank(int x, int y){
		super(x,y);
	}

	@Override
	public void run() {
		
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch(this.direct){
			case 0:
				for(int i = 0; i < 30; i++){
					
					if( y > 0){
						y -= speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				break;
			case 1:
				for(int i = 0; i < 30; i++){
					if( x < 400){
						x += speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for(int i = 0; i < 30; i++){
					if( y < 300){
						y += speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				break;
			case 3:
				for(int i = 0; i < 30; i++){
					if( x > 0){
						x -= speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				break;
			}
			// Add more bullets to enemyTank
			
			this.times++;
			
			if(times % 2 == 0){
				if(isLive){
					if(ss.size() < 5){
						Bullets b = null;
						
						switch(direct){
						case 0:
							b = new Bullets(x + 10, y, 0);
							ss.add(b);
							break;
						case 1:
							b = new Bullets(x + 30, y + 10, 1);
							ss.add(b);
							break;
						case 2:
							b = new Bullets(x + 10, y + 30, 2);
							ss.add(b);
							break;
						case 3:
							b = new Bullets(x, y + 10, 3);
							ss.add(b);
							break;
						}
						Thread t = new Thread(b);
						t.start();
						
					}
				}
			}
			

			
			//Tank random generates a new direction
			this.direct = (int)(Math.random() * 4);
			
			//Decide the enemy tabks are incorrect
			if(this.isLive == false){
				break;
			}
			
			
		}
	}
	
}

//Bullets class 
class Bullets implements Runnable{
	int x;
	int y;
	int direct;
	boolean isLive = true;
	int speed = 1;
	
	public Bullets(int x, int y, int direct){
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	@Override
	public void run() {
		
		while(true){
			try{
			Thread.sleep(50);
		}catch(Exception ex){
			
		}
			
			switch(direct){
			case 0:
				y -= speed ;
				break;
			case 1:
				x += speed;
				break;
			case 2:
				y += speed;
				break;
			case 3: 
				x -= speed;
				break;
				
			}
			
			//if the bullets out of the panel
			if(x < 0 || x > 400 || y < 0 || y > 300){
				this.isLive = false;
				break;
			}
			
			
		}
		
	}
	
	
}
//Bomb class
class Bomb{
	int x, y;
	int life = 9;
	boolean isLive = true;
	
	public Bomb(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void reduceLife(){
		if(life > 0){
			life--;
		}
		else {
			this.isLive = false;
		}
	}
}
