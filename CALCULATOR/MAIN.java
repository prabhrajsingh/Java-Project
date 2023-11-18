package CALCULATOR;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MAIN 
{
    MAIN() 
    {
        System.out.println(" PROJECT CONSTRUCTED ");
    }
    public static void clearScreen() //clear screen function
    {   
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    public static void gotoxy(int x, int y) // for moving the cursor point for inserting the text
    {
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df",escCode, x, y));
    }
    public void display() 
    {
        Scanner input = new Scanner(System.in);
        gotoxy(10,80);System.out.println(" O P E N I N G . . . . . . . . ");
        input.nextLine();
    }
    public void exit() 
    {
        Scanner input = new Scanner(System.in);
        gotoxy(30,80);System.out.println(" . . . . . . . . C L O S I N G ");
        input.nextLine();
    }
    public static void main(String[] args)
    {
        clearScreen();
        MAIN m = new MAIN(); //calling the object
        gotoxy(15,85);  System.out.print("L O A D I N G  "); 
        for(int i=99; i<=109; i+=2)
        {
            gotoxy(15,i);  System.out.print("."); 
            try 
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
        clearScreen();
        int choice=0;
        Scanner sc = new Scanner(System.in);
        do
        {    
            gotoxy(8,65); System.out.println("< < < < < < < < <     O P E R A T I O N     > > > > > > > ");
            gotoxy(11,85); System.out.println("1. D I S P L A Y : ");
            gotoxy(13,85); System.out.println("2. C A L C U L A T I O N S : ");
            gotoxy(15,85); System.out.println("3. E X I T : ");
            int option = sc.nextInt();  

            if (option==1)
            {
                clearScreen();
                m.display();
                clearScreen();
            }
            if (option==2)
            {
                clearScreen();
                new CalculatorBox();
            }
            else if (option==3)
            {
                clearScreen();
                m.exit();
            }
            choice = option;
        }while(choice!=3);   
    }
}


