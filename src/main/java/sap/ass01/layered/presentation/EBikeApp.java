package sap.ass01.layered.presentation;

import sap.ass01.layered.business.*;
import sap.ass01.layered.presentation.dialogs.AddEBikeDialog;
import sap.ass01.layered.presentation.dialogs.AddUserDialog;
import sap.ass01.layered.presentation.dialogs.RideDialog;
import sap.ass01.layered.services.EBikeService;
import sap.ass01.layered.services.RideService;
import sap.ass01.layered.services.ServiceProvider;
import sap.ass01.layered.services.UserService;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.*;


public class EBikeApp extends JFrame implements ActionListener {
   	// NOTE: Observers the RideSimulation
    private VisualiserPanel centralPanel;
    private JButton addUserButton, addEBikeButton, startRideButton;
    private ConcurrentHashMap<String, EBike> bikes;
    private HashMap<String, User> users;
    private HashMap<String, Ride> rides;

	private final ServiceProvider serviceProvider;
    
    private int rideId;
    
    public EBikeApp(final ServiceProvider serviceProvider){
		this.serviceProvider = serviceProvider;
        setupView();
        setupModel();
    }

    
    protected void setupModel() {
        bikes = new ConcurrentHashMap<>();
        users = new HashMap<>();
        rides = new HashMap<>();
        
        rideId = 0;
        this.addUser("u1");
        this.addEBike("b1", new P2d(0,0));
    }

    protected void setupView() {
        setTitle("EBike App");        
        setSize(800,600);
        setResizable(false);
        
        setLayout(new BorderLayout());

		addUserButton = new JButton("Add User");
		addUserButton.addActionListener(this);

		addEBikeButton = new JButton("Add EBike");
		addEBikeButton.addActionListener(this);

		startRideButton = new JButton("Start Ride");
		startRideButton.addActionListener(this);
		
		JPanel topPanel = new JPanel();
		topPanel.add(addUserButton);		
		topPanel.add(addEBikeButton);		
		topPanel.add(startRideButton);		
	    add(topPanel,BorderLayout.NORTH);

        centralPanel = new VisualiserPanel(800,500,this);
	    add(centralPanel,BorderLayout.CENTER);

	    	    		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(-1);
			}
		});
    }

    public void display() {
    	SwingUtilities.invokeLater(() -> {
    		this.setVisible(true);
    	});
    }
        
    public void addEBike(String id, P2d loc) {
    	EBike bike = new EBikeImpl(id);
    	bike.updateLocation(loc);
    	bikes.put(id, bike);
    	log("added new EBike " + bike);
    	centralPanel.refresh();
    }
    
    public EBike getEBike(String id) {
    	return bikes.get(id);
    }

    public void addUser(String id) {
    	User user = new UserImpl(id);
    	user.rechargeCredit(100);
    	users.put(id, user);
    	log("added new User " + user);
    	centralPanel.refresh();
    }

    public void startNewRide(String userId, String bikeId) {
    	rideId++;    	 
    	String idRide = "ride-" + rideId;
    	
    	var b = bikes.get(bikeId);
    	var u = users.get(userId);
    	var ride = new RideImpl(idRide, u, b);
    	b.updateState(EBikeImpl.EBikeState.IN_USE);
    	rides.put(idRide, ride);
    	ride.start(this);
        
        log("started new Ride " + ride);        
    }

    public void endRide(String rideId) {
    	var r = rides.get(rideId);
    	r.end();
    	rides.remove(rideId);
    }
    
    public Enumeration<EBike> getEBikes(){
    	return bikes.elements();
    }
        
    public Collection<User> getUsers(){
    	return users.values();
    }
    
    public void refreshView() {
    	centralPanel.refresh();
    }

    @Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.addEBikeButton) {
	        JDialog d = new AddEBikeDialog(this, this.serviceProvider.geteBikeService());
	        d.setVisible(true);
        } else if (e.getSource() == this.addUserButton) {
		    JDialog d = new AddUserDialog(this, this.serviceProvider.getUserService());
		    d.setVisible(true);
        } else if (e.getSource() == this.startRideButton) {
	        JDialog d = new RideDialog(this, this.serviceProvider.getRideService());
	        d.setVisible(true);
        }
	}

	private void log(String msg) {
		System.out.println("[EBikeApp] " + msg);
	}
    
    public static class VisualiserPanel extends JPanel {
        private long dx;
        private long dy;
        private EBikeApp app;
        
        public VisualiserPanel(int w, int h, EBikeApp app){
            setSize(w,h);
            dx = w/2 - 20;
            dy = h/2 - 20;
            this.app = app;
        }

        public void paint(Graphics g){
    		Graphics2D g2 = (Graphics2D) g;
    		
    		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    		          RenderingHints.VALUE_ANTIALIAS_ON);
    		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
    		          RenderingHints.VALUE_RENDER_QUALITY);
    		g2.clearRect(0,0,this.getWidth(),this.getHeight());
            
    		var it = app.getEBikes().asIterator();
    		while (it.hasNext()) {
    			var b = it.next();
    			var p = b.getLocation();
    			int x0 = (int)(dx+p.x());
		        int y0 = (int)(dy-p.y());
		        g2.drawOval(x0,y0,20,20);
		        g2.drawString(b.getId(), x0, y0 + 35);
		        g2.drawString("(" + (int)p.x() + "," + (int)p.y() + ")", x0, y0+50);
    		} 
    		
    		var it2 = app.getUsers().iterator();
    		var y = 20;
    		while (it2.hasNext()) {
    			var u = it2.next();
    			g2.drawRect(10,y,20,20);
		        g2.drawString(u.getId() + " - credit: " + u.getCredit(), 35, y+15);
		        y += 25;
    		};
            
        }
        
        public void refresh(){
            repaint();
        }
    }

	
	
	public static void main(String[] args) {
		final var serviceProvider = new ServiceProvider();
		var w = new EBikeApp(serviceProvider);
		w.display();
	}
	
}
