import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;


public class pow {
    //Initializing the Scanner
    private static final Scanner scanner = new Scanner(System.in);
    private static int consoleLength;
    static String folderPath;


    //Main class
    public static void main(String[] args) throws Exception {


        File f = new File("./data/target.txt");
        if(f.exists() && !f.isDirectory()) {
            folderPath = "./data/";
        }
        else
        {
            folderPath = "../data/";
        }

        try
        {
            consoleLength = Integer.parseInt(args[0]);
        }catch (Exception e)
        {
            consoleLength = 150;
        }

        //Printing Initial values
        ReadFiles();

        new FileReadWrite();


        int option;
        do {
            //Choosing the function
            System.out.println("\nFollowing are the functionalities in the code, choose anyone:\n" +
                    "1.Generate Target\n" +
                    "2.Generate Solution\n" +
                    "3.Verify\n" +
                    "4.Performance test\n" +
                    "5.Read current data in files\n" +
                    "6.Exit\n" +
                    "Please choose the value in between 1,2,3,4,5 or 6 ");

            int funcSel = promptChoice(6);

            subMain(funcSel);

            System.out.println("\nDo you want to re-run the program?");
            System.out.println("Please choose from the following.\n1. Yes\n2. No");
            option =  promptChoice(2);
        } while (option == 1);

        System.out.println("You chose to terminate the program. Thank you.");
    }

    //Function to read data in all the files by using FileReadWrite class.
    private static void ReadFiles(){
        System.out.println();
        printLine();
        System.out.println("Current Data in all files:");
        System.out.println("input.txt : "+FileReadWrite.ReadFile("input.txt"));
        System.out.println("target.txt : "+FileReadWrite.ReadFile("target.txt"));
        System.out.println("solution.txt : "+FileReadWrite.ReadFile("solution.txt"));
        printLine();
    }

    //Print a line on console
    private static void printLine(){
        for(int i=1;i< consoleLength;i++)
        {
            System.out.print("-");
        }
        System.out.println();
    }


    //Function to get input from the user to select the functions.
    private static int promptChoice(int max) {
        for (; ; ) {
            if (!scanner.hasNextInt()) {
                System.out.println("You must enter a number");
            } else {
                int option = scanner.nextInt();
                if (option >= 1 && option <= max) {
                    return option;
                }
                else{
                    System.out.println("You must enter a number between 1 and "+max);
                }
            }
            scanner.nextLine();
        }
    }


    private static String hashMsg(String msg) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(msg.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(hash);
    }

    private static String getTarget(int d)
    {
        String zeros = new String(new char[d]).replace("\0", "0");
        String ones = new String(new char[(256-d)]).replace("\0", "1");
        return zeros+ones;
    }

    private static boolean binaryStringCompare(String target, String hashedBinary)
    {
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == '1' && hashedBinary.charAt(i) == '0') return true;
            if (hashedBinary.charAt(i) == '1' && target.charAt(i) == '0') return false;
        }
        return true;
    }

    private static String getSolution(String input, String target) throws NoSuchAlgorithmException {
        String solution="";
        boolean pass = false;
        while (!pass){
            solution = new BigInteger(32, new Random()).toString();
            String hashedInput = hashMsg(input+solution);
            String hashedBinary = new BigInteger("1"+hashedInput, 16).toString(2).substring(1);
            pass = binaryStringCompare(target,hashedBinary);
        }
        return solution;
    }
    private static void subMain(int option) throws Exception {
        long duration, endTime, startTime;
        //Calling Generate Target function
        if (option == 1) {
            System.out.println();
            printLine();
            System.out.println("Selected Function is Generate Target.\n Please enter the value of d");
            int d = promptChoice(256);
            startTime = System.nanoTime();
            String target = getTarget(d);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            FileReadWrite.WriteFile("target.txt",target);
            System.out.println("New target text in the file target.txt: " + target);
            System.out.println("The time it took to run Target function  is: " + seconds + " seconds");
            printLine();
            ReadFiles();

        }
        //Calling generate solution function
       else if (option == 2) {
            System.out.println();
            printLine();
            System.out.println("Selected Function is Generate Solution.");
            startTime = System.nanoTime();
            String input = FileReadWrite.ReadFile("input.txt");
            String target = FileReadWrite.ReadFile("target.txt");
            String solution = getSolution(input,target);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            FileReadWrite.WriteFile("solution.txt", solution);
            System.out.println("Solution Text is: " + solution);
            System.out.println("The time it took to generate a solution is: " + seconds + " seconds");
            printLine();
            ReadFiles();
        }

        //Calling verify function
        else if (option == 3) {
            System.out.println();
            printLine();
            //reading and displaying existing files
            System.out.println("Selected Function is Verification.");
            System.out.println();
            String target = FileReadWrite.ReadFile("target.txt");
            System.out.println("Stored target: " + target);
            String input = FileReadWrite.ReadFile("input.txt");
            System.out.println("Stored input: " + input);
            String solution = FileReadWrite.ReadFile("solution.txt");
            System.out.println("Stored solution: " + solution);
            System.out.println();
            //Starting timer
            startTime = System.nanoTime();
            String hashedInput = hashMsg(input+solution);
            String hashedBinary = new BigInteger("1"+hashedInput, 16).toString(2).substring(1);
            System.out.println("solution generated for above target function is: "+binaryStringCompare(target,hashedBinary));
            //Ending timer
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            System.out.println("The time it took to run verification function  is: " + seconds + " seconds");
            printLine();

        }
        //Performance function
        else if (option == 4) {
            System.out.println();
            printLine();//System.out.println("\n******************************************************************************************************************");
            System.out.println("Selected function is performance of POW.");
            String input = FileReadWrite.ReadFile("input.txt");
            System.out.println("Stored input.txt: " + input);
            System.out.println();

            //Generating 6 targets and respective solutions
            for(int d=21; d<=26; d++)
            {
                startTime = System.nanoTime();
                String target = getTarget(d);
                String solution = getSolution(input,target);
                endTime = System.nanoTime();
                duration = (endTime - startTime);
                double seconds = (double) duration / 1000000000.0;
                System.out.println("Generated solution for difficulty "+d+": "+solution);
                System.out.println("The time it took to generate target and solution is: " + seconds + " seconds\n");
            }
            printLine();
        }
        //Read Current Files
        else if (option == 5) {
            ReadFiles();
        }
        //Exit the Program
        else if (option == 6){
            System.out.println("Terminating the program.");
            System.exit(0);
        }
    }
}
