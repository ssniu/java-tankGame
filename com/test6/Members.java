package com.test6;

//Create Tank class as a super class
class Tank{

	
	int x = 0;
	int y = 0;
	int direct = 0;
	int speed = 1;
	int color;
	
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
	
	public Hero(int x, int y){
		super(x, y);
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

class EnemyTank extends Tank{
	
	public EnemyTank(int x, int y){
		super(x,y);
	}
	
}

