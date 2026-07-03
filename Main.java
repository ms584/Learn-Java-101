import java.util.ArrayList;
import java.util.List;

// Abstract Class Shipment
abstract class Shipment {
    private double weight;

    public Shipment(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    // ให้คลาสลูกรายงานประเภทและเขียนวิธีคำนวณค่าส่ง
    public abstract String getShippingType();
    public abstract double calculatePayment();
}

// สร้างคลาสย่อยเพื่อรองรับแต่ละประเภทการส่ง 
class StandardShipment extends Shipment {
    public StandardShipment(double weight) {
        super(weight);
    }

    @Override
    public String getShippingType() {
        return "Standard";
    }

    @Override
    public double calculatePayment() {
        return getWeight() * 40; // ส่งแบบมาตรฐาน 40 บาท/กก.
    }
}

class ExpressShipment extends Shipment {
    public ExpressShipment(double weight) {
        super(weight);
    }

    @Override
    public String getShippingType() {
        return "Express";
    }

    @Override
    public double calculatePayment() {
        return getWeight() * 100; //  ส่งด่วน 100 บาท/กก.
    }
}

// Company สามารถรองรับ Shipment ได้หลายประเภท
class Company {
    private List<Shipment> shipments;

    public Company() {
        this.shipments = new ArrayList<>();
    }

    public void addShipment(Shipment shipment) {
        shipments.add(shipment);
    }

    public double getTotalPayment() {
        double total = 0;
        for (Shipment s : shipments) {
            total += s.calculatePayment(); // ใช้ Polymorphism ในการดึงค่าส่งของคลาสย่อยอัตโนมัติ
        }
        return total;
    }

    public void display() {
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-12s | %-10s\n", "Shipment", "Type", "Weight (kg)", "Payment");
        System.out.println("--------------------------------------------------------");
        
        int count = 1;
        for (Shipment s : shipments) {
            System.out.printf("S%-9d | %-15s | %-12.1f | %-10.1f\n", 
                              count++, s.getShippingType(), s.getWeight(), s.calculatePayment());
        }
        
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-43s | %-10.1f Baht\n", "Total Payment", getTotalPayment());
        System.out.println("--------------------------------------------------------");
    }
}

public class Main {
    public static void main(String[] args) {
        Company company = new Company();

        company.addShipment(new StandardShipment(5.0)); // S1
        company.addShipment(new ExpressShipment(3.0));  // S2
        company.addShipment(new StandardShipment(8.0)); // S3

        company.display();
    }
}