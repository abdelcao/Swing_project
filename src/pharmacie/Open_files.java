package pharmacie;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Open_files {
    public static void openbyid(String id) {
        try {
            if (new File("C:\\Users\\abdelhamid\\Desktop\\Facture\\"+id).exists()) {
                try {
                    String filePath = "C:\\Users\\abdelhamid\\Desktop\\Facture\\" + id;
                    Process p = Runtime.getRuntime().exec(new String[] {
                            "rundll32", "url.dll", "FileProtocolHandler", filePath
                    });
                } catch (IOException e) {
                    e.printStackTrace(); 
                }
            } else {
                JOptionPane.showMessageDialog(null, "this file does not exists");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
