package serveurBanque;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class imageArierePlan extends JPanel {
    private Image image;

    public imageArierePlan(String filePath) {
        try {
            this.image = (new ImageIcon(filePath)).getImage();
        } catch (Exception var3) {
            Exception e = var3;
            e.printStackTrace();
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
        }

    }
}
