package sap.ass01.exagonal.presentation;

import sap.ass01.exagonal.services.dto.RideDTO;
import sap.ass01.exagonal.services.observers.StopSimulationObserver;
import sap.ass01.exagonal.services.observers.StopSimulationObserverSource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RideSimulationControlPanel extends JFrame implements StopSimulationObserverSource {

    private final List<StopSimulationObserver> observers = new ArrayList<>();

    public RideSimulationControlPanel(final RideDTO ride) {
        super("Ride simulation for "  + ride.getId());
    	setSize(400, 200);

        JButton stopButton = new JButton("Stop Riding");
    	
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(new JLabel("Rider name: " + ride.userId()));
        inputPanel.add(new JLabel("Riding e-bike: " + ride.bikeId()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(stopButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        stopButton.addActionListener((e) -> {
            this.observers.forEach(o -> o.notifyStopSimulation(ride.getId().orElseThrow(() -> new IllegalStateException("ride does not have a valid id"))));
            dispose();
        });
    }
    
    public void display() {
    	SwingUtilities.invokeLater(() -> {
    		this.setVisible(true);
    	});
    }

    @Override
    public void addObserver(final StopSimulationObserver observer) {
        this.observers.add(observer);
    }
}
