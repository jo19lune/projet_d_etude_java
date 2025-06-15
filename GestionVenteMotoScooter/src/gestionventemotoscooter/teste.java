
package gestionventemotoscooter;

import java.awt.print.PrinterJob;
import javax.print.PrintService;

public class teste {
    public static void main(String args[]) {
        PrintService[] printServices = PrinterJob.lookupPrintServices();
        if (printServices.length == 0) {
            System.out.println("Aucun service d'impression trouvé !");
        } else {
            for (PrintService service : printServices) {
                System.out.println("Service d'impression disponible : " + service.getName());
            }
        }

    }
}
