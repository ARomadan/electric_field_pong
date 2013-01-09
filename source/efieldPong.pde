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

void setup(){
  size(800, 600, P3D);
  noStroke();
 
}

void draw(){
  background(0.0);
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

  
void printRes(boolean win){
  textFont(loadFont("CenturyGothic-48.vlw"), 48);
  fill(255, 100);
  if(win) text("You Win!", 308, 500);
  else text("You Lose :( " ,308, 500);
}

void keyPressed(){
  if(key == 'a') ball.accelerate();
}

  
