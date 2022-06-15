import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    //Welcome the user and describe the scenario. 

    System.out.println("Welcome to the Egg Drop!\n\nYou have 3 eggs which all have the same (random) strength.\nYour job is to find out how high you can drop the eggs from without them breaking.");

    Egg userEggs = new Egg(3);
    double maxHeight = 0d;
    Scanner INPUT = new Scanner(System.in);

    while(userEggs.get_chances() > 0){
      System.out.println("\nYou have " + userEggs.get_chances() + " eggs left.");
      System.out.println("Your highest drop so far was: " + maxHeight);
      
      //Ask the user for a drop height. 
      System.out.println("How high (in metres) do you want to drop your egg from?");
      double dropHeight = INPUT.nextDouble();
      INPUT.nextLine(); //Purge buffer. Squash bug.

      //Drop egg. Face consequences.
      boolean broken = userEggs.drop(dropHeight, 9.81);

      if(!broken && dropHeight > maxHeight){
        maxHeight = dropHeight;
      }
    }
    System.out.println("Great work, your heighest drop was " + maxHeight);
  }
}