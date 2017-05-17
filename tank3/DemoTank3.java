package tank3;


import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.io.*;



public class DemoTank3 extends JFrame{

	
	MyPanel mp = null;
	public static void main(String[] args) {
		DemoTank3 mt = new DemoTank3();

	}
	//constructor
	public DemoTank3(){
		mp = new MyPanel();
		
		Thread t = new Thread(mp);
		t.start();
		
		this.add(mp);
		this.setSize(400,300);
		this.setVisible(true);
		
		this.addKeyListener(mp);
	}

}
//Panel of Game
class MyPanel extends JPanel implements KeyListener, Runnable {
	
	Hero hero = null;
	//Enemy tanks
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	//Bombs
	Vector<Bomb> bombs = new Vector<Bomb>();
	
	
	int enSize = 3;
	
	//Declare 3 images
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;
	
	
	public MyPanel(){
		
		hero = new Hero(150, 250);
		hero.setColor(1);
		for(int i = 0; i < enSize; i++){
			//Create the enemy tank
			EnemyTank et = new EnemyTank((i + 1) * 50, 0);
			et.setColor(0);
			et.setDirect(2);
			Thread t = new Thread(et);
			t.start();
			
			Bullets b2 = new Bullets(et.x + 10,et.y + 30,2);
			et.ss.add(b2);
			Thread t2 = new Thread(b2);
			t2.start();
			ets.add(et);
		}
		//solve mytank does not explo
		try{
			image1 = ImageIO.read(new File("bomb_1/gif"));
			image2 = ImageIO.read(new File("bomb_2/gif"));
			image3 = ImageIO.read(new File("bomb_3/gif"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
		//Initialize the image objects
		//NullPointerException -- move the image files to root dir
		/*
		image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}*/
	
	public void paint(Graphics g){
		
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//My tank
		if(hero.isLive){
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		}
		
		
		//Draw the enemy tanks
		for(int i = 0; i < ets.size(); i++){
			
			EnemyTank et = ets.get(i);
			if(et.isLive){
			this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
			//bullets of enemy tanks
			for(int j = 0; j < et.ss.size(); j++){
				Bullets enemyBullets = et.ss.get(j);
				if(enemyBullets.isLive){
					g.draw3DRect(enemyBullets.x, enemyBullets.y, 1, 1, false);
				}else {
					et.ss.remove(enemyBullets);
				}
			}
			
		 }
		}
		
		for(int i = 0; i < hero.ss.size(); i++){
			Bullets myBullets = hero.ss.get(i);
		if(myBullets != null && hero.b.isLive){
			g.draw3DRect(myBullets.x, myBullets.y, 1, 1, false);
		}
		if(myBullets.isLive == false){
			//Delete the shot
			hero.ss.remove(myBullets);//myBullets instead of i
		}
	}
		//Draw the bombs
		for(int i = 0; i < bombs.size(); i++){
			
			Bomb b3 = bombs.get(i);
			if(b3.life > 6){
				g.drawImage(image1, b3.x, b3.y, 30, 30, this);
			}else if(b3.life > 3){
				g.drawImage(image2,b3.x, b3.y, 30, 30, this);
			}else {
				g.drawImage(image3,b3.x, b3.y, 30, 30, this);
			}
			//Decrease the life of bombs
			b3.reduceLife();
			if(b3.life == 0){
				bombs.remove(b3);
			}
		}
}
	public void hitMe(){
		for(int i = 0; i <this.ets.size(); i++){
			EnemyTank et = ets.get(i);
			
			for(int j = 0; j < et.ss.size(); j++){
				Bullets enemyBullets = et.ss.get(j);
				this.hitTank(enemyBullets, hero);
			}
		}
	}
	
	public void hitEnemyTank(){
		
		for(int i = 0; i < hero.ss.size(); i++){
			Bullets myBullet = hero.ss.get(i);
			if(myBullet.isLive){
				for(int j = 0; j <ets.size(); j++){
					EnemyTank et = ets.get(j);
					if(et.isLive){
						this.hitTank(myBullet,et);
						
						}
					}
				}
			}
		}
		
	
	//Target or not
	public void hitTank(Bullets b, Tank et){
		
		//Figure out the direction of tank
		switch(et.direct){
		case 0:
		case 2:
			if(b.x > et.x && b.x < et.x && b.y > et.y && b.y < et.y + 30){
				b.isLive = false;//destroy the bullet
				et.isLive = false;//destroy the enemytank
				//Bomb
				Bomb b2 = new Bomb(et.x, et.y);
				bombs.add(b2);
			}
		case 1:
		case 4:
			if(b.x > et.x && b.x < et.x + 30 && b.y > et.y && b.y < et.y+20){
				b.isLive = false;
				et.isLive = false;
				Bomb b2 = new Bomb(et.x, et.y);
				bombs.add(b2);
			}	
			
		}
	}
	
	public void drawTank(int x, int y, Graphics g, int direct, int type){
		//type
		switch(type){
		case 0: 
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		//direction
		switch(direct){
		case 0:
			
			//g.drawRect(x, y, 5, 30);
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+15, y, 5, 30, false);
			g.fill3DRect(x + 5, y+5, 10, 20, false);
			g.fillOval(x + 5, y + 10, 10, 10);
			g.drawLine(x + 10, y + 15, x + 10, y);
			break;
		case 1:
			g.fill3DRect(x, y,30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			g.fillOval(x + 10, y + 5, 10, 10);
			g.drawLine(x + 15, y + 10, x + 30, y + 10);
			break;
			
		case 2:
			g.fill3DRect(x, y,5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			g.fillOval(x + 5, y + 10, 10, 10);
			g.drawLine(x + 10, y + 15, x + 10, y + 30);
			break;
		case 3:
			g.fill3DRect(x, y,30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			g.fillOval(x + 10, y + 5, 10, 10);
			g.drawLine(x + 15, y + 10, x, y + 10);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode() == KeyEvent.VK_W){
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyCode() == KeyEvent.VK_D){
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(arg0.getKeyCode() == KeyEvent.VK_S){
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(arg0.getKeyCode() == KeyEvent.VK_A){
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		//Bullets shot 
		if(arg0.getKeyCode() == KeyEvent.VK_J){
			//limit the number of bullets
			if(this.hero.ss.size() <= 5){
			this.hero.shotEnemy();
			}
		}
		
		this.repaint();//make the tank moving
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
			
			//Check targeted or not
			for(int i = 0; i < hero.ss.size(); i++){
				Bullets myBullet = hero.ss.get(i);
				//bullet is valid or not
				if(myBullet.isLive){
					//compare the bullet with all tanks
					for(int j = 0; j < ets.size(); j++){
						EnemyTank et = ets.get(j);
						if(et.isLive){
							this.hitTank(myBullet, et);
						}
					}
				}
			}
			this.hitEnemyTank();
			
			this.hitMe();
			
			/* adding bullets
			 * for(int i = 0; i < ets.size(); i++){
			
				
				EnemyTank et = ets.get(i);
				if(et.ss.size() < 5){
					Bullets b = null;
					switch(et.direct){
					case 0:
						b = new Bullets(et.x + 10, et.y, 0);
						et.ss.add(b);
						break;
					case 1:
						b = new Bullets(et.x + 30, et.y + 10, 1);
						et.ss.add(b);
						break;
					case 2:
						b = new Bullets(et.x + 10, et.y + 30, 2);
						et.ss.add(b);
						break;
					case 3:
						b = new Bullets(et.x, et.y + 10, 3);
						et.ss.add(b);
						break;
					}
					Thread t = new Thread(b);
					t.start();
				}  
					}*/
			this.repaint();
		}
		
	}
	
}


