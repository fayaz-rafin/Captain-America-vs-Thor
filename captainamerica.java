import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class captainamerica extends Applet implements KeyListener, Runnable {
 
//**************//images//*********************// 
  
//CAPTAIN AMERICA 
Image capbase,capbaseL,  left, right, up, up2, down,down2, shield, hammer, capthrow,  currentImage, offscreen;
//THOR
Image thorbaseL, thorbaseR, thorL, thorR, thorthrowL, thorthrowR, thorImage;
//cap Infinity war
Image cap2basel, cap2baseR, cap2base, cap2up, cap2up2, cap2down, cap2down2, cap2L, cap2R, cap2Shield;
//misc
Image health1, health2, health3, health4, health5;
//background
Image background;
//******************//Characters//************************//

//cap
int capX = 500, capY = 425, capH = 92, capW = 74;

//cap 2
int cap2X, cap2Y, cap2H, cap2W;

//thor
int thorX = 788, thorY = 415, thorH = 92, thorW = 74;

//cap's shield
int shieldX = 490, shieldY = 420, shieldH = 48, shieldW = 48;

//thor's hammer
int hammerX = 790, hammerY = 420, hammerH = 30, hammerW = 30;

//applet height
int appletHeight = 1440, appletWidth = 900;


//misc
int health1x = 0, health1y = 30;
int health2x = 0, health2y = 60;
int health3x = 0, health3y = 90;
int health4x = 0, health4y = 120;
int health5x = 0, health5y = 150;

int lives = 100;
int hit = 0;

boolean jump;
boolean disc;
boolean mjolnir;

int jumpcount,shieldcount, hammercount;

int capDirection;

Graphics bufferGraphics; 

 //**************************************************************************
  public void init(){
    
    capDirection = 1; // capitan is facing right
    
    
    //cap's movements
    left = this.getImage(this.getDocumentBase(), "capL.gif");
    up = this.getImage(this.getDocumentBase(), "Image141.png");
    right = this.getImage(this.getDocumentBase(), "capR.gif");
    down = this.getImage(this.getDocumentBase(), "Image143.png");
    capbase = this.getImage(this.getDocumentBase(), "Image145.png");
    capbaseL = this.getImage(this.getDocumentBase(), "Image145 copy.png");
    capthrow = this.getImage(this.getDocumentBase(), "Image15.png");
    shield = this.getImage(this.getDocumentBase(), "Image122.png");
    
    //thor's movements
    hammer = this.getImage(this.getDocumentBase(), "hammer.gif");
    thorbaseL = this.getImage(this.getDocumentBase(), "thorbaseL.png");
    thorbaseR = this.getImage(this.getDocumentBase(), "thorbaseR.png");
    thorL = this.getImage(this.getDocumentBase(), "thorL.png");
    thorR = this.getImage(this.getDocumentBase(), "thorR.png");
    thorthrowL = this.getImage(this.getDocumentBase(), "thorthrowL.png");
    thorthrowR = this.getImage(this.getDocumentBase(), "thorthrowR.png");
    
    //health
    health1 = this.getImage(this.getDocumentBase(), "hearts.png");
    health2 = this.getImage(this.getDocumentBase(), "hearts.png");
    health3 = this.getImage(this.getDocumentBase(), "hearts.png");
    health4 = this.getImage(this.getDocumentBase(), "hearts.png");
    health5 = this.getImage(this.getDocumentBase(), "hearts.png");
    
    //background
    background = this.getImage(this.getDocumentBase(), "Background 1.png");
    
    
    addKeyListener(this);
    
    
    
    jump = false;
    disc = false; 
    mjolnir = false; //the hammer is not being thrown
    shieldcount = 10;
    hammercount = 10;
    
    if (capDirection == 1){
      currentImage = capbase;
    }
    else if (capDirection == 0){
      currentImage = capbaseL;
    }
    
    thorImage = thorbaseL;
    
    offscreen = createImage(appletWidth, appletHeight); 
    
    bufferGraphics = offscreen.getGraphics(); 
  }
  //**************************************************************************
  public void paint (Graphics g){

    
    bufferGraphics.clearRect(0,0,appletWidth, appletHeight); 
    bufferGraphics.drawImage(background,0,0,this);
    
    
    bufferGraphics.drawImage(thorImage, thorX, thorY, this);
    
    bufferGraphics.drawImage(currentImage, capX, capY, this);
    
    //health
    bufferGraphics.drawImage(health1, health1x, health1y, this);
    bufferGraphics.drawImage(health2, health2x, health2y, this);
    bufferGraphics.drawImage(health3, health3x, health3y, this);
    bufferGraphics.drawImage(health4, health4x, health4y, this);
    bufferGraphics.drawImage(health5, health5x, health5y, this);
    
   
    
    
    if (disc==true)
    bufferGraphics.drawImage(shield, shieldX,shieldY,this);
    
    
    if (mjolnir == true)
    bufferGraphics.drawImage(hammer, hammerX, hammerY, this);
    
    
    g.drawImage(offscreen,0,0,this);
  }
  //**************************************************************************
  public void update(Graphics g)  {
    paint(g);
  }
  //**************************************************************************
  
  public void keyTyped(KeyEvent e){}
  
  //**************************************************************************
  public void keyPressed(KeyEvent e){
    
    
   // if the chracter is already jumping do nothing
    if ((jump == true) || (disc == true))
      return;
    
    int key = e.getKeyCode();
    
   
  
  
  //right
  if (key == 39){
    capX = capX + 20;
    capDirection = 1;
     
      currentImage = right;
          shieldX = capX;
          
    }
  
  
  //left
  if (key == 37){
    capX = capX - 20;
    shieldX = capX;
    capDirection = 0;
    
      currentImage = left;
        
    }
  
  
  //-------------------Actions-------------------//
  
  //crouch before jump
  if (key == 38){
    
    capY= 400;
  currentImage = down;
  }
  //crouch
  else  if (key == 40){
    
    currentImage = down;
  }
  //throw shield if the x key is pressed
  else if ((key == 88) && (capDirection==1)){
    disc = true;
    shieldY = 375;
    currentImage = capthrow;
    
  }
  else if ((key == 88) && (capDirection==0)){
    disc = true;
    shieldY = 375;
    currentImage = capbaseL;
    
  }
  
 
  
  
  
  
  }

//**************************************************************************
  public void keyReleased(KeyEvent e){
    
    
    // if the chracter is already jumping do nothing
    if ((jump == true) || (disc == true))
      return;
    
    int key = e.getKeyCode();
  
    //******************movement*******************//
        
    //up
    
    if (key == 38) { //the key up is pressed
      jump = true;
      jumpcount = 10;
      currentImage = up;
    }
    
    if ((key == 88) && (capDirection==1)){//to throw the shield when the "x" key is pressed
      disc = true;
      shieldcount = 10;
      shieldX = capX;
      currentImage = capthrow;
    }
    if ((key == 88) && (capDirection==0)){//to throw the shield when the "x" key is pressed
      disc = true;
      shieldcount = 10;
      shieldX = capX;
      currentImage = capbaseL;
    }
   
        
     
    
   
    
    repaint();
   e.consume();
 
  }
  //**************************************************************************
  
  public void start(){
    Thread animete = new Thread(this);
      animete.start();
  }
  
//**************************************************************************\
  
  //*****************RUN METHOD**************//
  public void run(){
    while (true){
      
      //thor's movements
      int distance = thorX - capX;
      if ( (distance >=75) && (distance <= 400))  {
        thorX = thorX - 2;
        hammerX = hammerX - 2;
        
        thorImage = thorL;
      }
      
      if ( (distance >=-400) && (distance <= -100)) {
        thorX = thorX + 2;
        hammerX = hammerX + 2;
        thorImage = thorR;
      }

        
      
      
      
      
      //for jumping
      if (jump == true){
          
          jumpcount--;
          
          if (jumpcount >= 0)
            capY = capY - 15;
          else{
            capY = capY + 15;
          }
          
         if (jumpcount < -9){
            jump = false;
            jumpcount = 10;
             
             currentImage=capbase;
         }
          
      
       
      }
      /*System.out.println("DIrection " + capDirection);*/
      //shield
      if ((disc == true) && (capDirection == 1)){
         
          shieldcount--;
          
          if (shieldcount >= 0)
            shieldX = shieldX + 15;
          else{
            shieldX = shieldX - 15;
          }
          
         if (shieldcount < -9){
            disc = false;
            shieldcount = 10;
            
             
             currentImage=capbase;
         }
         
      }
      
      
      else if ((disc == true) && (capDirection == 0)){
         
          shieldcount--;
          
          if (shieldcount >= 0)
            shieldX = shieldX - 15;
          else{
            shieldX = shieldX + 15;
          }
          
         if (shieldcount < -9){
            disc = false;
            shieldcount = 10;
            
             
             currentImage=capbaseL;
         }
         
      }
      
      
      
      
         
      
      
      
      
      
      
      //action for hammer
      if (mjolnir == false) {
        int x;
        x = RandomNumber.get(1,50);
        /*System.out.println("X =" + x);*/
        if (x == 10)
          mjolnir = true;
          hammercount = 10;
      } 
      
      
      //hammer
     
      if (mjolnir == true) {
        
        
         
          hammercount--;
          
          distance = capX - thorX;
          
          if (distance < 0) {
            //* used for throwing hammer when xthor is on the right of cap
            if (hammercount >= 0) 
              
              hammerX = hammerX - 15;
            else{
              hammerX = hammerX + 15;
            }
          
            if (hammercount < - 9){
              mjolnir = false;
              hammercount = 10;
            
             
             thorImage=thorbaseL;
            }
          }
          else {
             //* used for throwing hammer when xthor is on the right of cap
            System.out.println("Thor is on the left - throwin the hammer");
            if (hammercount >= 0) 
              hammerX = hammerX + 25;
            else{
              hammerX = hammerX - 25;
            }
          
            if (hammercount < - 9){
              mjolnir = false;
              hammercount = 10;
            
             
             thorImage=thorbaseR;
            }
          }
            
            
            
//********************************************************************************************
           
          //***********// COLLISION //*************//
          
          
          //hit with shield
          if(collision(shieldX, shieldY, shieldH, shieldW, thorX, thorY, thorH, thorW) && (disc==true)) {
              hit++;
              System.out.println("Hit " + hit);
              if(hit == 50){
                thorX = 12000;
                hammerX = 12000;
              }
          } 
          
          //cap takes damage
          if(collision (hammerX, hammerY, hammerH, hammerW, capX, capY, capH, capW) && (mjolnir==true)) {
              lives = lives -1;
              
              System.out.println("Cap got hit " + lives);
              
              if(lives == 80){
                health5x = 9000;
              }
              if(lives == 60){
                health4x = 9000;
              }
              if(lives == 40){
                health3x = 9000;
              }
              if(lives == 20){
                health2x = 9000;
              }
              if(lives == 0){
                health1x = 9000;
                capX = 9000;
                shieldX = 9000;
                return;
              }
          } 
          
          
          
          
          
        
    }
      
      
    
    
    if (lives == 0)
  repaint();
      try{Thread.sleep(20);}
      catch(InterruptedException e){}
  }
}
  
//*********************************************************************************************
   private boolean collision (int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2 ) {
   

   for (int x = x1; x <= x1+width1; x++) 
     for (int y = y1; y <= y1+height1; y++) {
           
       if ((x >= x2) && (x <= x2 + width2) && (y >= y2) && (y <= y2 + height2))
         return true;
  }
    
    return false;
}
}

  
    