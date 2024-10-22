package sap.ass01.exagonal.presentation;


import com.sun.source.util.Plugin;
import sap.ass01.exagonal.business.RidePlugin;
import sap.ass01.exagonal.plugin.PluginClassLoader;
import sap.ass01.exagonal.presentation.dialogs.AddEBikeDialog;
import sap.ass01.exagonal.presentation.dialogs.AddUserDialog;
import sap.ass01.exagonal.presentation.dialogs.RideDialog;
import sap.ass01.exagonal.services.PluginService;
import sap.ass01.exagonal.services.ServiceProvider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EBikeAppView extends JFrame implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    private RideVisualisationPanel centralPanel;
    private JButton addUserButton;
    private JButton registerPlugin;
    private JButton addEBikeButton;
    private JButton startRideButton;
    private final ServiceProvider serviceProvider;
    final JPanel topPanel = new JPanel();

    public EBikeAppView(final ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        this.setupView();
    }

    private void setupView() {
        this.setTitle("EBike App");
        this.setSize(800,600);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.addUserButton = new JButton("Add User");
        this.addEBikeButton = new JButton("Add EBike");
        this.startRideButton = new JButton("Start Ride");
        this.registerPlugin = new JButton("Register Plugin");
        this.configureButton(this.addUserButton);
        this.configureButton(this.addEBikeButton);
        this.configureButton(this.startRideButton);
        this.configureButton(this.registerPlugin);
        this.add(topPanel, BorderLayout.NORTH);

        this.centralPanel = new RideVisualisationPanel(WIDTH, HEIGHT, this.serviceProvider);
        this.add(centralPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void configureButton(final JButton btn) {
        btn.addActionListener(this);
        this.topPanel.add(btn);
    }

    public void display() {
        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }

    public void refreshView() {
        this.centralPanel.refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.addEBikeButton) {
            new AddEBikeDialog(this, this.serviceProvider.geteBikeService()).setVisible(true);
        } else if (e.getSource() == this.addUserButton) {
            new AddUserDialog(this, this.serviceProvider.getUserService()).setVisible(true);
        } else if (e.getSource() == this.startRideButton) {
            final RideDialog rd = new RideDialog(this, this.serviceProvider.getRideService());
            rd.attachObserver(this.serviceProvider.getRideService());
            rd.setVisible(true);
        } else if (e.getSource() == this.registerPlugin) {
            this.showPluginSelector();
        }
    }

    private void showPluginSelector() {
        final var fileDialog = new JFileChooser(new File("."));
        if (fileDialog.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        final File selectedFile = fileDialog.getSelectedFile();

        if (!new PluginService(serviceProvider).loadRidePlugin(selectedFile)) {
            return;
        }

        final JButton button = new JButton("Apply Plugin: " + selectedFile.getName());
        button.addActionListener(i -> this.serviceProvider.getRideService().applyEffects(selectedFile.getName()));
        this.topPanel.add(button);
    }
}
