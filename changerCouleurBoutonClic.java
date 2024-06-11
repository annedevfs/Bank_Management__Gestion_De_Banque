package serveurBanque;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class changerCouleurBoutonClic implements ActionListener {
    private JButton bouton;
    private JComboBox<String> choixID;
    private JTextField[] textFields;
    private Color couleurOriginaleback;
    private Color couleurOriginaletexte;

    public changerCouleurBoutonClic(JButton bouton, JComboBox<String> choixID, JTextField... textFields) {
        this.bouton = bouton;
        this.choixID = choixID;
        this.textFields = textFields;
        this.couleurOriginaleback = bouton.getBackground();
        this.couleurOriginaletexte = bouton.getForeground();
    }

    public void actionPerformed(ActionEvent e) {
        boolean allFieldsFilled = true;
        if (this.choixID.getSelectedItem() == null) {
            allFieldsFilled = false;
        }

        JTextField[] var3 = this.textFields;
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            JTextField textField = var3[var5];
            if (textField.getText().trim().isEmpty() || textField.getText() == null) {
                allFieldsFilled = false;
                break;
            }
        }

        if (allFieldsFilled) {
            this.bouton.setBackground(Color.BLUE);
            this.bouton.setForeground(Color.WHITE);
            (new Timer()).schedule(new TimerTask() {
                public void run() {
                    changerCouleurBoutonClic.this.bouton.setBackground(changerCouleurBoutonClic.this.couleurOriginaleback);
                    changerCouleurBoutonClic.this.bouton.setForeground(changerCouleurBoutonClic.this.couleurOriginaletexte);
                }
            }, 1000L);
        } else {
            this.bouton.setBackground(Color.RED);
            this.bouton.setForeground(Color.WHITE);
            (new Timer()).schedule(new TimerTask() {
                public void run() {
                    changerCouleurBoutonClic.this.bouton.setBackground(changerCouleurBoutonClic.this.couleurOriginaleback);
                    changerCouleurBoutonClic.this.bouton.setForeground(changerCouleurBoutonClic.this.couleurOriginaletexte);
                }
            }, 1000L);
        }

    }
}
