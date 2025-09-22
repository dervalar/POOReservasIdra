
import Views.VistaMenu1;
import static config.ConnectionFactory.getConnection;




public class Program {

    public static void main(String[] args) {
        /*
        try (Connection conn = getConnection()) {
        System.out.println("✅ Conexión exitosa a MySQL!");
    } catch (SQLException e) {
        e.printStackTrace();
    }*/
        
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new VistaMenu1().setVisible(true);
        }
    });   
        
        
        
        
        
        
        
    }
    
}
