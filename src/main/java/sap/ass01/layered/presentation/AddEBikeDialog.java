package sap.ass01.layered.presentation;

import sap.ass01.layered.business.P2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Courteously implemented by ChatGPT 
 * 
 * prompt: 
 * 
 * "Hello ChatGPT. Could you write me a Java class 
 *  implementing a JDialog with title "Adding E-Bike", 
 *  including "OK" and "Cancel" buttons, and some input fields, 
 *  namely: an id input field (with label "E-Bike ID"), 
 *  an x input field (with label "E-Bike location - X coord:") 
 *  and an y input field (with label "E-Bike location - Y coord:"). 
 *  Thanks a lot!"
 * 
 */
public class AddEBikeDialog extends JDialog {

    private JTextField idField;
    private JTextField xCoordField;
    private JTextField yCoordField;
    private JButton okButton;
    private JButton cancelButton;
    private EBikeApp app;
    
    public AddEBikeDialog(EBikeApp owner) {
        super(owner, "Adding E-Bike", true);
        this.app = owner;
        initializeComponents();
        setupLayout();
        addEventHandlers();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initializeComponents() {
        idField = new JTextField(15);
        xCoordField = new JTextField(15);
        yCoordField = new JTextField(15);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
    }

    private void setupLayout() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(new JLabel("E-Bike ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("E-Bike location - X coord:"));
        inputPanel.add(xCoordField);
        inputPanel.add(new JLabel("E-Bike location - Y coord:"));
        inputPanel.add(yCoordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addEventHandlers() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement OK button behavior here
                String id = idField.getText();
                String xCoord = xCoordField.getText();
                String yCoord = yCoordField.getText();
                app.addEBike(id, new P2d(Integer.parseInt(xCoord), Integer.parseInt(yCoord)));
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddEBikeDialog dialog = new AddEBikeDialog(null);
            dialog.setVisible(true);
        });
    }
}
