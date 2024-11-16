package Vistas;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear instancia del LoginPanel y mostrarla
            LoginPanel loginPanel = new LoginPanel();
            loginPanel.setVisible(true);
        });
    }
}
