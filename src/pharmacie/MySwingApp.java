package pharmacie;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class MySwingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exemple de Connexion à la Base de Données");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Connection connection = DatabaseConnection.connect();

        if (connection != null) {
            System.out.println("connected");
           try {
                // Exécutez une requête SQL
                String sql = "SELECT * FROM users";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                // Créez un modèle de tableau pour afficher les résultats
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Colonne1");
                model.addColumn("Colonne2");

                while (rs.next()) {
                    String valeur1 = rs.getString("user_role");
                    String valeur2 = rs.getString("user_name");
                    model.addRow(new Object[]{valeur1, valeur2});
                }

                JTable table = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(table);

                frame.add(scrollPane);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }

        frame.setVisible(true);
    }
}

