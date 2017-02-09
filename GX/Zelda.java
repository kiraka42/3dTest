import javafx.application.*;

import javafx.beans.property.*;
import javafx.scene.*; 

import javafx.scene.transform.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.scene.input.*;
import java.lang.*; 

import javafx.event.*;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.*;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;


public class Zelda extends Application
{
        String [] sprites = {"b1.gif","mapping01.jpg","test.png","housse2.png","housse3.png","mojo.jpg","arbre.png"};
        
        String [] character = {"b1.gif","b2.gif","b3.gif","b4.gif","d1.gif","d2.gif","d3.gif","d4.gif",
        "l1.gif","l2.gif","l3.gif","l4.gif","h1.gif","h2.gif","h3.gif","h4.gif","ed1.gif","ed2.gif","ed3.gif","ed4.gif"};
        //16->19
        Group [] parents = new Group[100] ; 
        int depX=0;
        int depY=0;
        
        int moveH=0;
        int moveR=4;
        int moveL=8;
        int moveB=12;
        
        
        int camX=depX;
        int camY=depY;
       
        int directionSens=0;
        
        PerspectiveCamera sceneP = new PerspectiveCamera(false);
        final Group root=new Group();
        
        public void start(Stage stage)
        {
                stage.setTitle("3d Game");
            /*    final AudioClip song =
                new AudioClip(Zelda.class.getResource("m.wav").toString()); */
                final AudioClip walk =
                new AudioClip(Zelda.class.getResource("a.wav").toString());
              /*  final AudioClip lula =
                new AudioClip(Zelda.class.getResource("lula.wav").toString());*/
                
                Image [] images = new Image[100];
                PhongMaterial [] phongs = new PhongMaterial[100];
                for(int i=0;i<character.length;i++)
                {
                        images[i]= new Image(Zelda.class.getResource(character[i]).toExternalForm());
                        phongs[i] = new PhongMaterial();
                        phongs[i].setDiffuseMap(images[i]);
                }
               
               
                
                
                final Box sprite=new Box(50,50,3);
                sprite.setMaterial(phongs[0]);
                parents[0]=new Group(sprite);   
                parents[0].setTranslateX(depX);
                parents[0].setTranslateY(350);
                parents[0].setTranslateZ(depY);
                
                
                
                
                images[90]= new Image(Zelda.class.getResource(sprites[1]).toExternalForm());
                phongs[90] = new PhongMaterial();
                phongs[90].setDiffuseMap(images[90]);
              //  final Box earth=new Box(3200,20,2000);
                final Box earth=new Box(3200,20,7000);
                earth.setMaterial(phongs[90]);
                parents[1]=new Group(earth);   
              /*  parents[1].setTranslateX(400);
                parents[1].setTranslateY(400);
                parents[1].setTranslateZ(600);*/
                parents[1].setTranslateX(0);
                parents[1].setTranslateY(400);
                parents[1].setTranslateZ(0);
               
                //Housse test
                images[91]= new Image(Zelda.class.getResource(sprites[2]).toExternalForm());
                phongs[91] = new PhongMaterial();
                phongs[91].setDiffuseMap(images[91]);
                final Box tile=new Box(250,250,100);
                tile.setMaterial(phongs[91]);
                parents[2]=new Group(tile);   
                parents[2].setTranslateX(300);
                parents[2].setTranslateY(260);
                parents[2].setTranslateZ(200);
                  
                  
                images[92]= new Image(Zelda.class.getResource(sprites[3]).toExternalForm());
                phongs[92] = new PhongMaterial();
                phongs[92].setDiffuseMap(images[92]);
                final Box tile2=new Box(350,250,100);
                tile2.setMaterial(phongs[92]);
                parents[3]=new Group(tile2);   
                parents[3].setTranslateX(900);
                parents[3].setTranslateY(260);
                parents[3].setTranslateZ(450);  
                  
                
                
              /*  final Sphere boule = new Sphere(350);
                phongs[95] = new PhongMaterial(Color.color(0.2, 0.8, 0.2));
                boule.setMaterial(phongs[95]);
                parents[6]=new Group(boule);   
                parents[6].setTranslateX(-500);
                parents[6].setTranslateY(-200);
                parents[6].setTranslateZ(250);*/
                
                
                
               
                images[93]= new Image(Zelda.class.getResource(sprites[5]).toExternalForm());
                phongs[93] = new PhongMaterial();
                phongs[93].setDiffuseMap(images[93]);
                final Cylinder tile3=new Cylinder(200,400);
                
               //  final Box tile3=new Box(200,400,4);
                tile3.setMaterial(phongs[93]);
                parents[4]=new Group(tile3);   
                parents[4].setTranslateX(-500);
                parents[4].setTranslateY(200);
                parents[4].setTranslateZ(250); 
                  
                tile3.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) { 
                         System.out.println("ho ho  je suis arbre mojo ! "); 
                         mojo = new Thread(new Mojo());
                         mojo.start();
                        // song.stop();
                        // lula.play();
                           
                    }
                });
                  
                images[94]= new Image(Zelda.class.getResource(sprites[6]).toExternalForm());
                phongs[94] = new PhongMaterial();
                phongs[94].setDiffuseMap(images[94]);
                final Box tile4=new Box(200,300,1);
                tile4.setMaterial(phongs[94]);
                parents[5]=new Group(tile4);   
                parents[5].setTranslateX(100);
                parents[5].setTranslateY(290);
                parents[5].setTranslateZ(50); 
                
                
               
                final Box tile5=new Box(200,300,1);
                tile5.setMaterial(phongs[94]);
                parents[6]=new Group(tile5);   
                parents[6].setTranslateX(200);
                parents[6].setTranslateY(290);
                parents[6].setTranslateZ(450);
                 
                 
                
                
                root.getChildren().addAll(parents[0],parents[1],parents[2],parents[3],parents[4],parents[5],parents[6]);
                final Scene scene=new Scene(root,900,700,true);
                
                scene.setFill(Color.color(0.1, 0.6, 0.8, 1.0));
               
                sceneP.setTranslateX(0);
                sceneP.setTranslateY(-200);
                sceneP.setTranslateZ(0);
           
                scene.setCamera(sceneP);
                root.getChildren().addAll(sceneP);
                stage.setScene(scene);
                stage.show();
                
               // song.play();
                
                
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
                {
                    public void handle(KeyEvent ke) 
                    {
                       
                        if (KeyCode.SPACE.equals(ke.getCode())) {
                                    if (stage.isFullScreen()) {
                                        stage.setFullScreen(false);
                                    } else {
                                        stage.setFullScreen(true);
                                    }
                         }
                         if (KeyCode.DOWN.equals(ke.getCode())) 
                         {
                               if(directionSens==0){
                                        depY-=15;
                                        parents[0].setTranslateZ(depY);
                                        camY=depY+10;
                                        sceneP.setTranslateZ(depY+10);
                               }else if(directionSens==1 || directionSens == -3){
                                        depX-=15;
                                        parents[0].setTranslateX(depX);
                                        camX=depX-350;
                                        sceneP.setTranslateX(depX-350);
                               }else if(directionSens==2 || directionSens == -2){
                                        depY+=15;
                                        parents[0].setTranslateZ(depY);
                                        camY=depY-10;
                                        sceneP.setTranslateZ(depY-10);
                                        
                               }else if(directionSens==3 || directionSens == -1){
                                        depX+=15;
                                        parents[0].setTranslateX(depX);
                                        camX=depX-350;
                                        sceneP.setTranslateX(depX-350);
                               }
                                sprite.setMaterial(phongs[moveH]);
                                moveH++;
                                if(moveH>3)moveH=0;
                         }
                         if (KeyCode.UP.equals(ke.getCode())) 
                         {
                                if(directionSens==0){
                                        depY+=15;
                                        parents[0].setTranslateZ(depY);
                                        camY=depY-10;
                                        sceneP.setTranslateZ(depY-10);
                                }else if(directionSens == 1 || directionSens==-3){
                                        depX+=15;
                                        parents[0].setTranslateX(depX);
                                        camX=depX-350;
                                        sceneP.setTranslateX(depX-350);
                                }else if(directionSens==2 || directionSens==-2){
                                        depY-=15;
                                        parents[0].setTranslateZ(depY);
                                        camY=depY+10;
                                        sceneP.setTranslateZ(depY+10);
                                
                                }else if(directionSens==3 || directionSens==-1){
                                        depX-=15;
                                        parents[0].setTranslateX(depX);
                                        camX=depX-350;
                                        sceneP.setTranslateX(depX-350);
                                }
                                sprite.setMaterial(phongs[moveB]);
                                moveB++;
                                if(moveB>15)moveB=12;
                         
                         }
                          if (KeyCode.LEFT.equals(ke.getCode())) 
                         {
                               if(directionSens==0){
                                        depX-=15;
                                        parents[0].setTranslateX(depX);
                                        camX=depX-350;
                                        sceneP.setTranslateX(depX-350);
                                }else if(directionSens==1 || directionSens==-3){
                                        depY+=15;
                                        parents[0].setTranslateZ(depY);
                                        camY=depY-10;
                                        sceneP.setTranslateZ(depY-10);
                                }else if(directionSens==2|| directionSens==-2){
                                        depX+=15;
                                        parents[0].setTranslateX(depX);
                                        camX=depX-350;
                                        sceneP.setTranslateX(depX-350);
                                }else if(directionSens==3 || directionSens==-1){
                                        depY-=15;
                                        parents[0].setTranslateZ(depY);
                                        camY=depY+10;
                                        sceneP.setTranslateZ(depY+10);
                                }
                                sprite.setMaterial(phongs[moveL]);
                                moveL++;
                                if(moveL>11)moveL=8;
                         
                         }
                          if (KeyCode.RIGHT.equals(ke.getCode())) 
                         {
                                if(directionSens==0){
                                        depX+=15;
                                        parents[0].setTranslateX(depX);
                                        camX=depX-350;
                                        sceneP.setTranslateX(depX-350);
                                }else if(directionSens==1 || directionSens==-3){
                                        depY-=15;
                                        parents[0].setTranslateZ(depY);
                                        camY=depY+10;
                                        sceneP.setTranslateZ(depY+10);
                                }else if(directionSens==2|| directionSens==-2){
                                        depX-=15;
                                        parents[0].setTranslateX(depX);
                                        camX=depX-350;
                                        sceneP.setTranslateX(depX-350);
                                }else if(directionSens==3 || directionSens==-1){
                                        depY+=15;
                                        parents[0].setTranslateZ(depY);
                                        camY=depY-10;
                                        sceneP.setTranslateZ(depY-10);
                                
                                }
                                sprite.setMaterial(phongs[moveR]);
                                moveR++;
                                if(moveR>7) moveR=4;
                         }
                         if(KeyCode.SPACE.equals(ke.getCode())){
                                int anime=16;
                                
                         }
                         /*
                         if (KeyCode.A.equals(ke.getCode())) 
                         {
                               walk.play(1000.0); 
                                directionSens++;
                              // Rotate rz = new Rotate(10.0,depX,350,depY, Rotate.Y_AXIS);
                               //  parents[1].getTransforms().add(rz);
                                 //parents[2].getTransforms().add(rz);
                               // sceneP.getTransforms().add(rz);
                               turn=new Thread(new Turn(0));
                               turn.start();
                         }
                          if (KeyCode.Z.equals(ke.getCode())) 
                         {
                              
                               walk.play(1000.0); 
                               Rotate rz = new Rotate(10.0,depX,350,depY, Rotate.Y_AXIS);
                               //  parents[1].getTransforms().add(rz);
                                 //parents[2].getTransforms().add(rz);
                               // sceneP.getTransforms().add(rz);
                               turn=new Thread(new Turn(1));
                               turn.start();
                               
                         }*/
                         if(KeyCode.Q.equals(ke.getCode())){
                          walk.play(1000.0);
                                  
                                  directionSens++;
                                  if(directionSens == 4) directionSens = 0;
                                  final Rotate rotate = new Rotate(0, Rotate.Y_AXIS);
                                    
                                    rotate.setPivotX(depX-camX);
                                    rotate.setPivotY(360);
                                    rotate.setPivotZ(depY-camY);
                                    animation = new Timeline(
                                            new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                                            new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90))
                                    );
                                    animation.setCycleCount(1);
                                    sceneP.getTransforms().add(rotate);
                                    animation.play();
                                    turn=new Thread(new Turn(0));
                                    turn.start(); 
            
                         }
                         
                          if(KeyCode.D.equals(ke.getCode())){
                                 walk.play(1000.0);
                                 
                                  directionSens--;
                                  if(directionSens == -4) directionSens=0;
                                  final Rotate rotate = new Rotate(0, Rotate.Y_AXIS);
                                    
                                    rotate.setPivotX(depX-camX);
                                    rotate.setPivotY(360);
                                    rotate.setPivotZ(depY-camY);
                                    animation = new Timeline(
                                            new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                                            new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), -90))
                                    );
                                    animation.setCycleCount(1);
                                    sceneP.getTransforms().add(rotate);
                                    animation.play();
                                    turn=new Thread(new Turn(1));
                                    turn.start();
                         }
                        
                         System.out.println(depX+"  "+depY+"  ");
                    }
               });
        }
       
        Timeline animation;
        public static void main(String args[])
        {
           launch(args);
        }
        
        Thread turn;
        Thread mojo;
        int dist = 400;
        
        
        class Mojo implements Runnable
        {
                //throw the animation 
                public void run()
                {
                final Rotate rotate = new Rotate(0, Rotate.Y_AXIS);
                                    
                                    rotate.setPivotX(depX-camX);
                                    rotate.setPivotY(360);
                                    rotate.setPivotZ(depY-camY);
                                    animation = new Timeline(
                                            new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                                            new KeyFrame(Duration.seconds(2), new KeyValue(rotate.angleProperty(), 360))
                                    );
                                    animation.setCycleCount(1);
                                    sceneP.getTransforms().add(rotate);
                                    animation.play();
                }
        }
        
        class Turn implements Runnable
        {
                int direction;
                public Turn(int direction)
                {       
                        this.direction=direction;
                }
                public void run()
                {
                         dist=-100;
                         System.out.println(depX+"  "+depY);
                         Rotate ro=null;
                         Rotate rz = new Rotate(10.0, Rotate.Y_AXIS);
                         Rotate rz2 = new Rotate(-10.0, Rotate.Y_AXIS);
                       /*  rz.setPivotX(depX);
                         rz.setPivotY(0);
                         rz.setPivotZ(depY);*/
                        if(direction==0)
                        {
                                for(int i=0; i < 9;i++)
                                {
                                      
                                        parents[0].getTransforms().add(rz);
                                        parents[5].getTransforms().add(rz);
                                        parents[6].getTransforms().add(rz);
                                       // sceneP.setTranslateZ(1800);                                     
                                        try{
                                        Thread.sleep(100);}catch(Exception exp){}
                                } 
                               
                                    
                        }else
                        {
                                for(int i=0; i < 9;i++)
                                {
                                       
                                        parents[0].getTransforms().add(rz2);
                                        parents[5].getTransforms().add(rz2);
                                        parents[6].getTransforms().add(rz2);
                                        try{
                                        Thread.sleep(100);}catch(Exception exp){}
                                }   
                        }
                        //dist = 700;
                }
        }

}
