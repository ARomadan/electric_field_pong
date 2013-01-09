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
  
  void update(float x, float y){
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
  
  void updateField(){
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

