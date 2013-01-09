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
    this.dt = .1; this.q = 1;
    this.hasStarted = false;
    this.hasLost = false;
  }
  
  void update(){
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
  
  float getX(){ return ballX; }
  float getY(){ return ballY; }
  float getDX(){ return px * dt; }
  float getDY(){ return py * dt; }
  void changeDX(){ px = -px; }
  void changeDY(){ py = -py; }
  boolean hasLost(){ return hasLost; }
  void accelerate(){ dt = dt + .1; }
  
  void applyField(float magnitude, float fromX, float fromY, float d){
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
  
