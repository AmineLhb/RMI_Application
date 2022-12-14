package GestionUtilisateurs;

import GestionUtilisateurs.exceptions.CompteAlreadyExist;
import GestionUtilisateurs.exceptions.CompteNoExistException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;

import static java.awt.Font.SANS_SERIF;
import static javax.swing.JOptionPane.showMessageDialog;


public class MyForm extends Component {
    public MyForm(IGestionUtilisateur b) {
        JFrame f = new JFrame("GESTION D'UTILISATEURS ");
        JLabel titre = new JLabel("GESTION D'UTILISATEURS");
        JLabel author0 = new JLabel("Mohamed Amine LECHHEB  ");
        JLabel author1 = new JLabel("Haron BELGHIT  ");
        Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 29);
        titre.setFont(f2);
        titre.setBounds(220, 20, 400, 30);
        author1.setBounds(500, 285, 400, 80);
        author0.setBounds(500, 300, 400, 80);

        JButton b1 = new JButton("Créer un compte");
        b1.setBounds(500, 80, 180, 30);

        JButton b2 = new JButton("Update un compte");
        b2.setBounds(500, 120, 180, 30);

        JButton b3 = new JButton("Retirer un compte");
        b3.setBounds(500, 160, 180, 30);

        JButton b4 = new JButton("Consulter un compte");
        b4.setBounds(500, 200, 180, 30);

        JButton b5 = new JButton("Ouvrir Chat");
        b5.setBounds(500, 250, 180, 30);

        JLabel idlbl = new JLabel("#ID :");
        idlbl.setBounds(20, 80, 50, 30);

        JLabel nmbl = new JLabel("Name:");
        nmbl.setBounds(20, 120, 50, 30);

        JLabel passlbl = new JLabel("Password:");
        passlbl.setBounds(20, 160, 50, 30);

        JTextField IDField = new JTextField();
        JTextField NameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        IDField.setBounds(120, 80, 200, 30);
        NameField.setBounds(120, 120, 200, 30);
        passwordField.setBounds(120, 160, 200, 30);

        JTextArea area = new JTextArea();
        area.setBounds(120, 220, 200, 100);

        f.add(titre);
        f.add(author0);
        f.add(author1);

        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);

        f.add(IDField);
        f.add(NameField);
        f.add(passwordField);
        f.add(area);

        f.add(idlbl);
        f.add(nmbl);
        f.add(passlbl);

        f.setSize(850, 400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width / 2 - f.getSize().width / 2, dim.height / 2 - f.getSize().height / 2);
        f.setResizable(false);
        f.setLayout(null);
        f.setVisible(true);

        // CREATE USER
        b1.addActionListener(e -> {
            if (!IDField.getText().equals("") && !NameField.getText().equals("") && !passwordField.getText().equals("")) {
                try {

                    b.creerCompte(new Compte(IDField.getText(), NameField.getText(), passwordField.getText()));
                    showMessageDialog(f, "Le compte est bien créé...");

                    String m = b.consulter(IDField.getText());
                    area.setText(IDField.getText() + " == > " + m + "\n");

                    System.out.println("User added successfully!");

                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                } catch (CompteAlreadyExist compteAlreadyExist) {
                    showMessageDialog(f, compteAlreadyExist.getMessage());
                } catch (CompteNoExistException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                showMessageDialog(f, "Vous devez remplir tous les champs.");
            }
        });

        // UPDATE USER
        b2.addActionListener(e -> {
            if (!IDField.getText().equals("") && !NameField.getText().equals("") && !passwordField.getText().equals("")) {
                try {

                    b.modifierCompte(Integer.parseInt(IDField.getText()), NameField.getText(), passwordField.getText());
                    showMessageDialog(f, "Le compte est bien modifié...");

                    String m = b.consulter(IDField.getText());
                    area.setText(IDField.getText() + " == > " + m + "\n");

                    System.out.println("User modified successfully!");

                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                } catch (CompteAlreadyExist compteAlreadyExist) {
                    showMessageDialog(f, compteAlreadyExist.getMessage());
                } catch (CompteNoExistException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                showMessageDialog(f, "Vous devez remplir tous les champs.");
            }
        });

        // DELETE USER
        b3.addActionListener(e -> {
            if (!IDField.getText().equals("")) {
                try {
                    b.retirer(IDField.getText());
                    UserDAO.REMOVE_USER(Integer.parseInt(IDField.getText()));
                    showMessageDialog(f, "L'utilisateur a été supprimé...");
                    System.out.println(b.count());
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                } catch (CompteNoExistException compteNoExistException) {
                    showMessageDialog(f, compteNoExistException.getMessage());
                }
            } else {
                showMessageDialog(f, "Vous devez remplir tous les champs.");
            }
        });

        // READ USER
        b4.addActionListener(e -> {
            if (!IDField.getText().equals("")) {
                try {
                    String m = b.consulter(IDField.getText());
                    if (m.equals("")) {
                        showMessageDialog(f, "Le compte n'existe pas...");
                    } else
                        area.setText(IDField.getText() + " == > " + UserDAO.getCompteNamebyID(Integer.parseInt(IDField.getText())) + "\n");
                } catch (RemoteException remoteException) {
                    showMessageDialog(f, "Problème de connexion...");
                } catch (CompteNoExistException compteNoExistException) {
                    showMessageDialog(f, compteNoExistException.getMessage());
                }
            } else {
                showMessageDialog(f, "Vous devez remplir le ID.");
            }

        });

        //ouvrir chat
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!IDField.getText().equals("")) {
                    try {
                        String m = b.consulter(IDField.getText());
                        if (m =="") {
                            showMessageDialog(f, "Le compte n'existe pas...");
                        } else
                            area.setText(IDField.getText() + " == > " + m + "\n");
                        Method main = null;
                        try {
                            main = Class.forName("ChatRMI.server.ChatServer").getMethod("main", String[].class);
                        } catch (NoSuchMethodException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        // Invoke the main method
                        try {
                            main.invoke(null, (Object) new String[] { "arg1", "arg2", "arg3" });
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            throw new RuntimeException(ex);
                        }
                        Method main2 = null;
                        try {
                            main2 = Class.forName("ChatRMI.client.ClientRMIGUI").getMethod("main", String[].class);
                        } catch (NoSuchMethodException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        // Invoke the main method
                        try {
                            main2.invoke(null, (Object) new String[] { "arg1", "arg2", "arg3" });
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (RemoteException remoteException) {
                        showMessageDialog(f, "Problème de connexion...");
                    } catch (CompteNoExistException compteNoExistException) {
                        showMessageDialog(f, compteNoExistException.getMessage());
                    }
                } else {
                    showMessageDialog(f, "Vous devez remplir le ID.");
                }

            }
        });

    }

}
