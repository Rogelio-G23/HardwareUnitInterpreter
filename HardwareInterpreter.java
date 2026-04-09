import java.util.Scanner;
public class HardwareInterpreter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Repository repo = new Repository();
        
        
        int choice;
        do{
        System.out.println("\n-------------------------------------------------------");
        System.out.println("HARDWARE INVENTORY");
        System.out.println("[1] ADD HARDWARE");
        System.out.println("[2] SHOW ALL HARDWARE STORED");
        System.out.println("[0] EXIT");
        System.out.print("Select: ");
        choice = scan.nextInt();
        
        switch(choice){
            case 1 -> repo.addHardwareList();
            case 2 -> repo.printAllHardware();
            case 0 -> System.out.println("Closing.");
            default -> System.out.println("Invalid Choice.");
        }
        }while(choice != 0);
        
    
        scan.close();
    }
}