import java.util.Random;

public class Egg{
  //Properties
  private double thresholdF;
  private int chances;

  //Instantaneous Properties (for while its falling)
  private double speed;
  private double height;

  Random randomizer = new Random();

  //Methods

  //Get Methods
  int get_chances(){
    return chances;
  }

  //Constructor
  Egg(int c){
    chances = c;
    thresholdF = randomizer.nextDouble() * 50.0;
  }

  boolean drop(double startingHeight, double gravityStrength){
    if(chances < 1){
      System.out.println("You cannot drop a broken egg. Please restart to try again");
      return true;
    }
    else{
      height = startingHeight;
      speed = 0.0;
      double g = gravityStrength;
      double t = 0.001; //Seconds - step size for the loop
      boolean[] toReport = {true, true, true};

      while(height > (speed * t)){
        speed += g * t; //Accellerate
        height -= speed * t; //Fall

        double rheight = Math.round(height * 1000d) / 1000d;
        double rspeed = Math.round(speed * 1000d) / 1000d;

        //Report to user at 3/4, 1/2, and 1/4 height
        if(height < (startingHeight * 3/4) && toReport[0]){
          toReport[0] = false;
          System.out.println("\nThe egg is falling. Currently, it's height is: " + rheight + "m. And it's speed is " + rspeed + "m/s.");
        }

        if(height < (startingHeight * 1/2) && toReport[1]){
          toReport[1] = false;
          System.out.println("\nThe egg is falling. Currently, it's height is: " + rheight + "m. And it's speed is " + rspeed + "m/s.");
        }

        if(height < (startingHeight * 1/4) && toReport[2]){
          toReport[2] = false;
          System.out.println("\nThe egg is falling. Currently, it's height is: " + rheight + "m. And it's speed is " + rspeed + "m/s.");
        }
      }
      return smash(speed, 0.01);
    }
  }

  boolean smash(double finalSpeed, double crashDistance){
    double k = 0.5 * 0.06 * finalSpeed * finalSpeed;
    double f = k / crashDistance;
    if(f > thresholdF){
      //Egg breaks
      System.out.println("The egg broke");
      chances -= 1;
      return true;
    }
    else{
      //Egg survives
      System.out.println("The egg survived");
      return false;
    }
  }
}