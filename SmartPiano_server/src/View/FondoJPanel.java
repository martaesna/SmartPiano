package View;

import javax.swing.*;
import java.awt.*;

public class FondoJPanel extends JPanel {
        private Image fondo;
        private int altura;
        private int amplada;

        public void paintComponent(Graphics graphics) {
            altura = this.getSize().height;
            amplada = this.getSize().width;
            if (this.fondo !=  null) {
                graphics.drawImage(this.fondo,0,0, amplada, altura, null);
            }
            super.paintComponent(graphics);
        }
        public void introdueixFoto(String imagePath){
            this.setOpaque(false);
            this.fondo = new ImageIcon(imagePath).getImage();
            repaint();
        }
}
