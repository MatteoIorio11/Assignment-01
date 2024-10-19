package sap.ass01.layered.presentation.dialogs;

import sap.ass01.layered.services.observers.InputObserver;
import sap.ass01.layered.services.UserService;
import sap.ass01.layered.services.dto.UserDTO;

import javax.swing.*;
import java.awt.*;

public class AddUserDialog extends AbstractDialog<UserDTO> {

    private JTextField idField, errorField;
    private JButton okButton;
    private JButton cancelButton;

    public AddUserDialog(final JFrame parentFrame, final InputObserver<UserDTO> controller) {
        super(parentFrame, controller, "Adding User");
    }

    @Override
    protected void initializeComponents() {
        idField = new JTextField(15);
        errorField = new JTextField(25);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
    }

    @Override
    protected void setUpLayout() {
        final JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        this.addLabelToPanel(inputPanel, this.idField, "User ID:");

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // JPanel errorPanel = new JPanel();
        // errorPanel.add(errorField);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        // add(errorPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void addEventHandlers() {
        okButton.addActionListener((e) -> {
            controller.notifyUpdateRequested(new UserDTO(this.idField.getText()));
            dispose();
        });

        cancelButton.addActionListener((e) -> { dispose(); });
    }

    public static void main(String[] args) {
        final UserService controller = new UserService();
        SwingUtilities.invokeLater(() -> {
            AddUserDialog dialog = new AddUserDialog(null, controller);
            dialog.setVisible(true);
        });
    }
}
