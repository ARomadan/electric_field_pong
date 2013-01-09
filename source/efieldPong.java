import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class efieldPong extends PApplet {

/**
* NAME: Electric Field Pong
* DESCRIPTION: This is exactly like pong except some of the blocks 
               emit electric fields directed radially away from the source, 
               and the ball is a charged particle. When the particle enters 
               the effective radius of an electric field it will feel a force. 
               This force will change the velocity of the particle, thus 
               deflecting the particle from its otherwise straight trajectory.  
* CONTROLS: 
   - mouse click to start
   - press the 'a' key on your keybord to accelate the ball
     (you may want to do this when the particle is moving annoyingly slow)
*/

Particle ball = new Particle();  
Bord bord = new Bord(); 
float x, y;
boolean endGame = false;

public void setup(){
  size(800, 600, P3D);
  noStroke();
 
}

public void draw(){
  background(0.0f);
  lights();
  if(!endGame){
  // the slider  
    pushMatrix();
    fill(250,250);
    translate(mouseX, height - 30, 0);
    box(90, 15, 15);
    popMatrix();
  
  //the ball
    ball.update();
  }
 
 //update the bord 
  directionalLight(250, 250, 250, 0, -1, 0);
  x = ball.getX();
  y = ball.getY();
  bord.update(x, y);
  
  //print win/lose result
  if(bord.hasWon()){ endGame = true; printRes(true); } //true => win, false => lose
  if(ball.hasLost()){ endGame = true; printRes(false); }
}

  
public void printRes(boolean win){
  textFont(loadFont("CenturyGothic-48.vlw"), 48);
  fill(255, 100);
  if(win) text("You Win!", 308, 500);
  else text("You Lose :( " ,308, 500);
}

public void keyPressed(){
  if(key == 'a') ball.accelerate();
}

  
class GlassBall {
  float posX, posY, opacity, shade, ds; 
  boolean isThere, hasField;
  int rad, force, radField;
  
  GlassBall(float x, float y, int rad, int f){
    this.posX = x;
    this.posY = y;
    this.isThere = true;
    this.rad = rad;
    this.force = f;
    if(f > 0){
      this.hasField = true;
      this.opacity = random(255);
      this.shade = random(255);
      this.radField = 10;
      this.ds = f;
    }
    else this.hasField = false;
  }
  
  public void update(float x, float y){
    if(isThere){
      if(dist(x, y, posX, posY) < rad + 10){ //when block is hit
        isThere = false;
        float dx = ball.getDX();
        float dy = ball.getDY();
        if(dx/dy > 1 || dx/dy < -1) ball.changeDY();
        else ball.changeDX();
        if(!hasField)  bord.updateCount();
      }
        
      if(isThere){
        pushMatrix();
        translate(posX, posY, 0);
        fill(250, 150);
        sphere(rad);
        popMatrix();
      }
    }  
    
    if(hasField){
      this.updateField(); 
      float d = dist(x, y, posX, posY);
      if(d < 20){hasField = false; bord.updateCount(); }
      else if(d < radField + 10) ball.applyField(opacity * force, posX, posY, d);
    }      
  }
  
  public void updateField(){
    pushMatrix();
    translate(posX, posY, 0);
    fill(101, random(255), 175, 120);
    sphere(10);
    popMatrix();
    
    if(!isThere){
      if(opacity < 0){
        shade = random(255);
        opacity = random(255);
        radField = 10;
        ds = -ds;
      }
    if(shade < 1 || shade > 254) ds = -ds; 
        
    pushMatrix();
    translate(posX, posY, 0);
    fill(101, shade, 175, opacity);
    sphere(radField);
    popMatrix();
    
    radField += 1;
    opacity -= 3;
    shade += force;
   }
  }  
}

class Bord{
  boolean hasWon;
  int count;
  GlassBall b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, 
            b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24;
  
  Bord(){
    this.count  = 0; //number of blocks hit
    hasWon = false;
    
    this.b1 = new GlassBall(380, 280, 14, 0);
    this.b2 = new GlassBall(340, 280, 17, 2);
    this.b3 = new GlassBall(300, 280, 19, 0);
    this.b4 = new GlassBall(420, 280, 14, 0);
    this.b5 = new GlassBall(460, 280, 17, 2);
    this.b6 = new GlassBall(500, 280, 19, 0);
    
    this.b7 = new GlassBall(500, 240, 18, 0);
    this.b8 = new GlassBall(460, 240, 16, 0);
    this.b9 = new GlassBall(420, 240, 13, 2);
    this.b10 = new GlassBall(380, 240, 13, 2);
    this.b11 = new GlassBall(340, 240, 16, 0);
    this.b12 = new GlassBall(300, 240, 18, 0); 
    
    this.b13 = new GlassBall(300, 200, 18, 0);
    this.b14 = new GlassBall(340, 200, 16, 0);
    this.b15 = new GlassBall(380, 200, 13, 2);
    this.b16 = new GlassBall(420, 200, 13, 2);
    this.b17 = new GlassBall(460, 200, 16, 0);
    this.b18 = new GlassBall(500, 200, 18, 0);
    
    this.b19 = new GlassBall(300, 160, 19, 0);
    this.b20 = new GlassBall(340, 160, 17, 2);
    this.b21 = new GlassBall(380, 160, 14, 0);
    this.b22 = new GlassBall(420, 160, 14, 0);
    this.b23 = new GlassBall(460, 160, 17, 2);
    this.b24 = new GlassBall(500, 160, 19, 0);
       
  }
  
  public void update(float x, float y){
    b1.update(x, y);
    b2.update(x, y);
    b3.update(x, y);
    b4.update(x, y);
    b5.update(x, y);
    b6.update(x, y);
    b7.update(x, y);
    b8.update(x, y);
    b9.update(x, y);
    b10.update(x, y);
    b11.update(x, y);
    b12.update(x, y);
    b13.update(x, y);
    b14.update(x, y);
    b15.update(x, y);
    b16.update(x, y);
    b17.update(x, y);
    b18.update(x, y);
    b19.update(x, y);
    b20.update(x, y);
    b21.update(x, y); 
    b22.update(x, y);
    b23.update(x, y);
    b24.update(x, y);
  }
  
  public void updateCount(){
    count++;
    if(count == 24) hasWon = true;
  }
  
  public boolean hasWon(){ return hasWon; }
}
class Particle {
/** POSITION UPDATE EXPLANATION

 we can find the new position of the particle
    pos2 = + pos1 + (change in position) which is equivalet to
    pos2 = pos1 + (v * dt)  where v = velocity
    pos2 = pos1 + ((p/q) * dt) where p and q are momentum and charge of the particle
 the momentum of the ball is given by
    p2  = p1  + dp
    p2  = p1 + (F * dt)  where F is force on the ball
 In this version pos, momentum, and force are broken down into x and y components
 and I set q = 1, but in later versions/other levels the charge on the 
 ball will play a role.
*/

  float ballX, ballY, dt, px, py, q;  //px and py are x and y components of momentum
  boolean hasStarted, hasLost; 
  
  Particle(){
    this.ballX = mouseX; this.ballY = height - 45;
    this.px = random(-50, 50); this.py = -20;
    this.dt = .1f; this.q = 1;
    this.hasStarted = false;
    this.hasLost = false;
  }
  
  public void update(){
    pushMatrix();
    if(mousePressed) hasStarted = true;
    if(hasStarted){ 
      if(ballX > 780 || ballX < 20) px = -px;
      if(ballY < 20) py = -py;
      else if(ballY > height - 45){
        if((mouseX - 45 > ballX) | (mouseX + 45 < ballX)) hasLost = true;
        else py = -py;
      }        
      ballX += px*dt; ballY += py*dt; 
    }
    else{ ballX = mouseX; ballY = height - 45; }
    translate(ballX, ballY, 0);
    fill(250,250);
    sphere(10);
    popMatrix(); 
  } 
  
  public float getX(){ return ballX; }
  public float getY(){ return ballY; }
  public float getDX(){ return px * dt; }
  public float getDY(){ return py * dt; }
  public void changeDX(){ px = -px; }
  public void changeDY(){ py = -py; }
  public boolean hasLost(){ return hasLost; }
  public void accelerate(){ dt = dt + .1f; }
  
  public void applyField(float magnitude, float fromX, float fromY, float d){
/** FORCE UPDATE EXPLANTION
  - you have uniform force of given magnitude F directed
    radially away from the field emitting sphere
  - the charged particle is some distance d away fromfield ball
  => dx = x component of d  
     dy = y component of d
  => cos(theda) = dx / d 
     sin(theda) = dy / d
  => x component of force: Fx = F * cos(theda) = F (dx / d)
     y component of force: Fy = F * sin(theda) = F (dy / d)

*/
    float dx = abs(ballX - fromX);
    float dy = abs(ballY - fromY);
    float Fx = magnitude * (dx / d);
    float Fy = magnitude * (dy / d);
    
    if(ballX > fromX) px += Fx * dt;
    else px -= Fx * dt;
    
    if(ballY > fromY) py += Fy * dt;
    else py -= Fy * dt;
  }    
}
  

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "efieldPong" });
  }
}
