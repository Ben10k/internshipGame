package lt.taurosevicius.game.client;

import javax.swing.*;
import java.awt.event.*;

public class connectionDetails extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private String host;
    private int port;

    @SuppressWarnings("Convert2Lambda")
    public connectionDetails() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Error connecting to server");
        setName("Enter correct server details");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setVisible(true);
    }

    private void onOK() {
        try {
            host = textField1.getText();
            port = Integer.parseInt(textField2.getText());
        } catch (NumberFormatException ignored) {
        }
//        setVisible(false);
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        System.exit(0);
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }


}
