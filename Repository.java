import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {
    private Connection connection;
    
    private Connection getConnection(){
        return connection;
    }

    public Repository() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/Rogelio/Documents/NetBeansProjects/HardwareInterpreter/src/HardwareInterpreterDB.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to database!");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite driver not found.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private Hardware createHardware(String brand, int spec, String type) {
        
        switch (type.toUpperCase()) {
            case "LAPTOP":
                Hardware laptop = new Laptop.Builder()
                        .setBrand(brand).setSpec(spec).setType(type).build();
                return laptop;
                
            case "PHONE":
                Hardware phone = new Phone.Builder()
                        .setBrand(brand).setSpec(spec).setType(type).build();
                return phone;
                
            default:
                Hardware hardware = new Hardware.Builder()
                        .setBrand(brand).setSpec(spec).setType(type).build();
                return hardware;
        }
    }

    public void printAllHardware() {
        String sql = "SELECT id, Brand, Spec, Type FROM tbl_hardware_logic";
        int laptop16 = 0;
        int laptop32 = 0;
        int phone50 = 0;
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("============================== HARDWARE MASTERLIST ==============================");
            while (rs.next()) {
                int id       = rs.getInt("id");
                String brand = rs.getString("Brand");
                int spec     = rs.getInt("Spec");
                String type  = rs.getString("Type");

                Hardware hw = createHardware(brand, spec, type);
                
                if (hw instanceof Laptop) {
                    if (hw.getSpec() == 16){
                        laptop16++;
                    }else if (hw.getSpec() == 32){
                        laptop32++;
                    }
                } else if (hw instanceof Phone) {
                    if (hw.getSpec() == 50) phone50++;
                }
                
               System.out.println("ID: " + id +
                   "\t | Brand: " + hw.getBrand() +
                   "\t | Spec: " + hw.getSpec() +
                   "\t | Type: " + hw.getType() +
                   "\t | Unit: " + hw.unit());
            
            }
            System.out.println("==================================================================================");
            System.out.println("\n\n=============================== HARDWARE INVENTORY ===============================");
            System.out.println("TOTAL 16GB Laptop: " + laptop16);
            System.out.println("TOTAL 32GB Laptop: " + laptop32);
            System.out.println("TOTAL 50MP Phone:" + phone50);
            System.out.println("==================================================================================");

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }
    
    public void addHardware(Hardware detail) {
    try {
            // Standard — no room number
            String sql = "INSERT INTO tbl_hardware_logic (Brand, Spec, Type) VALUES (?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, detail.getBrand());
            stmt.setInt(2, detail.getSpec());
            stmt.setString(3, detail.getType());
            stmt.executeUpdate();
            stmt.close();
            
            System.out.println("Hardware added successfully!");
    } catch (SQLException e) {
        System.out.println("Error inserting: " + e.getMessage());
    }
}
    
    public void addHardwareList(){
        Scanner scan = new Scanner(System.in);
        Repository repo = new Repository();
        
        System.out.println("\nADD HARDWARE:");
        System.out.print("Brand: ");
        String brand = scan.nextLine();
        System.out.print("Spec: ");
        int spec = scan.nextInt();
        
        scan.nextLine();
        System.out.print("Type: ");
        String type = scan.nextLine();
        
        Hardware detail = new Hardware.Builder()
                .setBrand(brand)
                .setSpec(spec)
                .setType(type)
                .build();
        
                repo.addHardware(detail);
    }
    
   
    
    
}