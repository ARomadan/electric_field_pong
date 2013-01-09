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
  
  void update(float x, float y){
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
  
  void updateCount(){
    count++;
    if(count == 24) hasWon = true;
  }
  
  boolean hasWon(){ return hasWon; }
}
