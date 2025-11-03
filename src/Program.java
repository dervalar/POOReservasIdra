
import Views.VistaMenu1;
import static config.ConnectionFactory.getConnection;




public class Program {

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaMenu1 vMenu1 = new VistaMenu1();
                vMenu1.setVisible(true);
                vMenu1.setLocationRelativeTo(null);
            }
        });     
    }
}
