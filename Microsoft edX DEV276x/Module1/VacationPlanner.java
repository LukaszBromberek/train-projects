import java.util.*;
import java.math.*;

public class VacationPlanner {
    String name;
    String destination;
    int days;
    int budget;
    String currency;
    double course;
    int timeDifference;
    int area;
    double homeLongitude;
    double homeLatitude;
    double destinationLongitude;
    double destinationLatitude;

    // Class containing all data about one person voyage
    public VacationPlanner() {
        this.name = "";
        this.destination = "";
        this.days = 0;
        this.budget = 0;
        this.currency = "NA";
        this.course = 0.0;
        this.timeDifference= 0;
        this.area=0;
        this.homeLatitude=0.0;
        this.homeLongitude=0.0;
        this.destinationLatitude=0.0;
        this.destinationLongitude=0.0;
    }

    public static void main(String[] args){
        // Initialize scaner and main object
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);
        VacationPlanner vacationPlanner = new VacationPlanner();

        //Greet, and get passanger name
        sysl ("Welcome to Vacation Planer");
        syso ("What is your name? : ");
        vacationPlanner.name = input.nextLine();

        //Get travel destination
        syso("Nice to meet you " + vacationPlanner.name + ", where are you travelling to? ");
        vacationPlanner.destination = input.nextLine();
        syso("Great! " + vacationPlanner.destination + " sounds like a great trip \n*********\n\n");

        //Get days, budget, currency and course
        syso ("How many days are you going to spend travelling? : ");
        vacationPlanner.days = input.nextInt();
        syso ("How much money, in PLN, are you going to spend on your trip? : ");
        vacationPlanner.budget = input.nextInt();
        syso ("What is the three letter currency symbol for your travel destination? : ");
        vacationPlanner.currency = input.next();
        syso ("How many " + vacationPlanner.currency+ " are there in 1 PLN? : ");
        vacationPlanner.course = input.nextDouble();

        //Show trip time and currency data
        syso("\n\nIf you are travelling  for 14 days that is the same as " + vacationPlanner.computeTravelTime() + "\n");
        sysl("If you are going to spend " + vacationPlanner.budget + "PLN" + " that means per day you can spend up to " + vacationPlanner.dailyBudget() + "PLN");
        sysl("Your total budget in " + vacationPlanner.currency + " is " + vacationPlanner.budgetInCurrency() + " " + vacationPlanner.currency + ", which per day is " + vacationPlanner.dailyBudgetInCurrency() + " " + vacationPlanner.currency);
        sysl("************");
        //Time difference
        syso("\n\n What is the time difference, in hours, between your home and your destination? ");
        vacationPlanner.timeDifference = input.nextInt();
        sysl("That means that when it is midnight at home it will be " +vacationPlanner.midnightInDestination()+":00 ain your travel destination and when it is noon at hime it will be " + vacationPlanner.noonInDestination() + ":00");
        sysl("************");

        //Area
        syso("\n\n What is the square area of your destination in km2? ");
        vacationPlanner.area = input.nextInt();
        sysl("In miles2 that is " + vacationPlanner.areaInSquareMiles());
        sysl("************");

        //Get latitude and longitude return travel length
        syso("\n\nWhat is your home longitude (in degrees)? ");
        vacationPlanner.homeLongitude=input.nextDouble();
        syso("What is your home latitude? ");
        vacationPlanner.homeLatitude=input.nextDouble();
        syso("What is your destination longitude? ");
        vacationPlanner.destinationLongitude = input.nextDouble();
        syso("Waht is your destination latitude? ");
        vacationPlanner.destinationLatitude = input.nextDouble();

        syso("\n\nYour travel length will be " + vacationPlanner.travelLength() +"km");
    }

    // Short command for using System.out.println
    public static void sysl(String line){
        System.out.println(line);
    }

    // Short command for using System.out.print
    public static void syso(String output){
        System.out.print(output);
    }

    // Returns days of travel in hours and minutes (as String)
    public String computeTravelTime (){
        return ""+ (this.days*24) + " hours or " + (this.days*24*60) + " minutes";
    }

    // Returns budget divided by days
    public double dailyBudget(){
        return this.budget/this.days;
    }

    // Returns full budget in local currency
    public double budgetInCurrency(){
        return (int)(this.budget*this.course*100)/100.0;
    }

    //Return full budget in local currency divided by number of days
    public double dailyBudgetInCurrency(){
        return (int)(100*this.budgetInCurrency()/this.days)/100.0;
    }

    //Returns time in destination while it's midnight at home
    public int midnightInDestination (){
        return this.timeDifference;
    }

    //Returns time in destination while it's noon at home
    public int noonInDestination (){
        if (this.timeDifference>=12) {
            return this.timeDifference-12;
        }
        else{
            return this.timeDifference+12;
        }
    }

    //Return area of country in mile^2
    public double areaInSquareMiles (){
        return (int)(this.area*0.3861*100)/100.0;
    }

    //Convert longitudes and latitudes from degrees to radians
    private void radianize(){
        this.homeLatitude= Math.toRadians(this.homeLatitude);
        this.destinationLatitude= Math.toRadians(this.destinationLatitude);
        this.homeLongitude= Math.toRadians(this.homeLongitude);
        this.destinationLongitude= Math.toRadians(this.destinationLongitude);
    }

    //Compute travel length in kilometers
    public double travelLength(){
        this.radianize();
        double earthRadius = 6372.8;
        return 2*earthRadius*
                Math.asin
                        (Math.sqrt
                                (
                                        Math.pow(Math.sin((this.destinationLatitude-this.homeLatitude)/2),2) +
                                        Math.cos(this.homeLatitude)*Math.cos(this.destinationLatitude)*
                                        Math.pow(Math.sin((this.destinationLongitude-this.homeLongitude)/2),2)
                                )
                        );
    }
}
