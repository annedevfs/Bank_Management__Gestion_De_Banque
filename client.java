package serveurBanque;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class client {
    private static interfaceServeur banque;
    private static HashMap<String, Double> comptes = new HashMap();
    private static HashMap<String, Double> comptesDest = new HashMap();

    public client() {
    }

    public static void main(String[] args) {
        try {
            banque = (interfaceServeur)Naming.lookup("rmi://localhost/bank_Management__GestionnaireDeCompteBancaire");
            JFrame frame = new JFrame("Client Banque");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(3);
            ImageIcon icone = new ImageIcon("monnaieIcone.png");
            frame.setIconImage(icone.getImage());
            imageArierePlan panel = new imageArierePlan("arrimg.jpg");
            panel.setLayout(new GridBagLayout());
            frame.add(panel);
            dispositionComposants(panel);
            frame.setVisible(true);
        } catch (Exception var4) {
            Exception e = var4;
            e.printStackTrace();
        }

    }

    private static void dispositionComposants(final JPanel panel) {
        GridBagConstraints positionElements = new GridBagConstraints();
        positionElements.insets = new Insets(10, 10, 10, 10);
        positionElements.anchor = 10;
        positionElements.fill = 2;
        positionElements.gridx = 0;
        positionElements.gridy = 0;
        panel.add(new JLabel("ID Compte"), positionElements);
        final JComboBox<String> ajout_ChoixID = new JComboBox();
        positionElements.gridx = 1;
        panel.add(ajout_ChoixID, positionElements);
        positionElements.gridx = 0;
        positionElements.gridy = 1;
        panel.add(new JLabel("Montant"), positionElements);
        final JTextField voirsolde = new JTextField(20);
        positionElements.gridx = 1;
        panel.add(voirsolde, positionElements);
        JButton boutonCreerCompte = new JButton("Créer Compte");
        boutonCreerCompte.setBorder(new arrondirboutons(15));
        positionElements.gridx = 0;
        positionElements.gridy = 2;
        positionElements.gridwidth = 2;
        panel.add(boutonCreerCompte, positionElements);
        boutonCreerCompte.addActionListener(new changerCouleurBoutonClic(boutonCreerCompte, ajout_ChoixID, new JTextField[]{voirsolde}));
        boutonCreerCompte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = JOptionPane.showInputDialog("Entrez l'ID du nouveau compte:");
                    double montantInitial = Double.parseDouble(voirsolde.getText());
                    client.banque.creerCompte(id, montantInitial);
                    if (id != null && !id.isEmpty()) {
                        ajout_ChoixID.addItem(id);
                        JOptionPane.showMessageDialog(panel, "Compte créé avec succès!");
                        client.comptes.put(id, montantInitial);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Aucun ID détecté !");
                    }
                } catch (Exception var5) {
                    Exception ex = var5;
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Erreur lors de la création du compte.");
                }

            }
        });
        JButton boutonconsulterSolde = new JButton("Consulter Solde");
        boutonconsulterSolde.setBorder(new arrondirboutons(15));
        positionElements.gridy = 3;
        panel.add(boutonconsulterSolde, positionElements);
        boutonconsulterSolde.addActionListener(new changerCouleurBoutonClic(boutonconsulterSolde, ajout_ChoixID, new JTextField[0]));
        boutonconsulterSolde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = (String)ajout_ChoixID.getSelectedItem();
                    double solde = client.banque.consulterSolde(id);
                    JOptionPane.showMessageDialog(panel, "Solde: " + solde);
                } catch (Exception var5) {
                    Exception ex = var5;
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Erreur lors de la consultation du solde.");
                }

            }
        });
        positionElements.gridy = 4;
        positionElements.gridwidth = 1;
        panel.add(new JLabel("Montant à ajouter"), positionElements);
        final JTextField montantAjoute = new JTextField(20);
        positionElements.gridx = 1;
        panel.add(montantAjoute, positionElements);
        JButton boutonAjouter = new JButton("Ajouter");
        boutonAjouter.setBorder(new arrondirboutons(15));
        positionElements.gridx = 0;
        positionElements.gridy = 5;
        positionElements.gridwidth = 2;
        panel.add(boutonAjouter, positionElements);
        boutonAjouter.addActionListener(new changerCouleurBoutonClic(boutonAjouter, ajout_ChoixID, new JTextField[]{montantAjoute}));
        boutonAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = (String)ajout_ChoixID.getSelectedItem();
                    double montant = Double.parseDouble(montantAjoute.getText());
                    client.banque.ajouter(id, montant);
                    JOptionPane.showMessageDialog(panel, "Montant ajouté avec succès!");
                } catch (Exception var5) {
                    Exception ex = var5;
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Erreur lors de l'ajout du montant.");
                }

            }
        });
        positionElements.gridy = 6;
        positionElements.gridwidth = 1;
        panel.add(new JLabel("Montant à retirer"), positionElements);
        final JTextField montantRetirer = new JTextField(20);
        positionElements.gridx = 1;
        panel.add(montantRetirer, positionElements);
        JButton boutonRetirer = new JButton("Retirer");
        boutonRetirer.setBorder(new arrondirboutons(15));
        positionElements.gridx = 0;
        positionElements.gridy = 7;
        positionElements.gridwidth = 2;
        panel.add(boutonRetirer, positionElements);
        boutonRetirer.addActionListener(new changerCouleurBoutonClic(boutonRetirer, ajout_ChoixID, new JTextField[]{montantRetirer}));
        boutonRetirer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = (String)ajout_ChoixID.getSelectedItem();
                    double montant = Double.parseDouble(montantRetirer.getText());
                    double solde = client.banque.consulterSolde(id);
                    double soldeRestant = solde - montant;
                    double solde6pour100 = solde * 6.0 / 100.0;
                    if (montant <= solde && soldeRestant >= solde6pour100) {
                        client.banque.retirer(id, montant);
                        JOptionPane.showMessageDialog(panel, "Montant retiré avec succès!");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Erreur: Montant à retirer supérieur au solde.");
                    }
                } catch (Exception var11) {
                    Exception ex = var11;
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Erreur lors du retrait du montant.");
                }

            }
        });
        positionElements.gridy = 8;
        positionElements.gridwidth = 1;
        panel.add(new JLabel("ID Destinataire"), positionElements);
        final JComboBox<String> ajout_ChoixDest = new JComboBox();
        positionElements.gridx = 1;
        panel.add(ajout_ChoixDest, positionElements);
        positionElements.gridx = 0;
        positionElements.gridy = 9;
        panel.add(new JLabel("Montant à transférer"), positionElements);
        final JTextField montantTransferer = new JTextField(20);
        positionElements.gridx = 1;
        panel.add(montantTransferer, positionElements);
        JButton transfererBouton = new JButton("Transférer");
        transfererBouton.setBorder(new arrondirboutons(15));
        positionElements.gridx = 0;
        positionElements.gridy = 10;
        positionElements.gridwidth = 2;
        panel.add(transfererBouton, positionElements);
        transfererBouton.addActionListener(new changerCouleurBoutonClic(transfererBouton, ajout_ChoixID, new JTextField[]{montantTransferer}));
        transfererBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String idDest = JOptionPane.showInputDialog("Entrez l'ID du compte de votre destinataire :");
                    String idSource = (String)ajout_ChoixID.getSelectedItem();
                    double montant = Double.parseDouble(montantTransferer.getText());
                    client.banque.transfererSolde(idSource, idDest, montant);
                    client.comptesDest.put(idDest, montant);
                    ajout_ChoixDest.addItem(idDest);
                    double solde = client.banque.consulterSolde(idSource);
                    double soldeRestant = solde - montant;
                    double solde6pour100 = solde * 6.0 / 100.0;
                    if (idSource.equals(idDest)) {
                        JOptionPane.showMessageDialog(panel, "Erreur: L'ID du compte source et celui du destinataire ne peuvent pas être les mêmes.");
                        return;
                    }

                    if (montant <= solde && soldeRestant >= solde6pour100) {
                        client.banque.retirer(idSource, montant);
                        client.banque.ajouter(idDest, montant);
                        JOptionPane.showMessageDialog(panel, "Montant transféré avec succès!");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Erreur: Montant à transférer supérieur au solde.");
                    }
                } catch (Exception var12) {
                    Exception ex = var12;
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Erreur lors du transfert du montant.");
                }

            }
        });
    }
}