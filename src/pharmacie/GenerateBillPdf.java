package pharmacie;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.*;
import javax.swing.JOptionPane;

public class GenerateBillPdf {

    public static void createBillPdf() {
        Document doc = new Document();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Connexion à la base de données
            con = DatabaseConnection.connect();
            ps = con.prepareStatement("SELECT * FROM bills ORDER BY bill_id DESC LIMIT 1");
            rs = ps.executeQuery();

            if (rs.next()) {
                int billId = rs.getInt("bill_id"); // Récupérer le bill_id
                String billDate = rs.getString("bill_date");
                int totalPaid = rs.getInt("total_paid");
                int userId = rs.getInt("user_id");

                // Utiliser bill_id comme nom de fichier PDF
                String pdfFileName = billId + ".pdf";

                PdfWriter.getInstance(doc,
                        new FileOutputStream("C:\\Users\\Amine Aabid\\Desktop\\Facture" + pdfFileName)); // Créer le PDF
                doc.open(); // Ouvrir le document

                // Ajouter le contenu au PDF
                Paragraph header = new Paragraph("Pharmacy Management System");
                header.setAlignment(Paragraph.ALIGN_CENTER);
                doc.add(header);

                Paragraph stars = new Paragraph(
                        "*************************************************************************");
                doc.add(stars);

                Paragraph details = new Paragraph(String.format(
                        "Bill ID: %d\nBill Date: %s\nTotal Paid: %d DH\nUser ID: %d",
                        billId, billDate, totalPaid, userId));
                doc.add(details);

                doc.close(); // Fermer le document
                JOptionPane.showMessageDialog(null, "PDF created successfully: " + pdfFileName);
                Open_files.openbyid(pdfFileName);
            } else {
                JOptionPane.showMessageDialog(null, "No bills found in the database.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating PDF: " + e.getMessage());
        } finally {
            // Assurer que les ressources de la base de données sont fermées
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing database resources: " + e.getMessage());
            }
        }
    }
}
