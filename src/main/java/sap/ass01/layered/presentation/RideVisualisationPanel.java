package sap.ass01.layered.presentation;

import sap.ass01.layered.services.EBikeService;
import sap.ass01.layered.services.ServiceProvider;
import sap.ass01.layered.services.UserService;

import javax.swing.*;
import java.awt.*;

public class RideVisualisationPanel extends JPanel {
        private final long dx;
        private final long dy;
		private final EBikeService eBikeService;
		private final UserService userService;

        public RideVisualisationPanel(int w, int h, final ServiceProvider serviceProvider){
            setSize(w,h);
            dx = w/2 - 20;
            dy = h/2 - 20;
			this.eBikeService = serviceProvider.geteBikeService();
			this.userService = serviceProvider.getUserService();
        }

        public void paint(final Graphics g) {
			final Graphics2D g2 = (Graphics2D) g;

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2.clearRect(0, 0, this.getWidth(), this.getHeight());

			for (final var b: this.eBikeService.getAll()) {
				var p = b.getLocation();
				int x0 = (int) (dx + p.x());
				int y0 = (int) (dy - p.y());
				g2.drawOval(x0, y0, 20, 20);
				g2.drawString(b.getId(), x0, y0 + 35);
				g2.drawString("(" + (int) p.x() + "," + (int) p.y() + ")", x0, y0 + 50);
			}

			var y = 20;
			for (final var u: this.userService.getAll()) {
				g2.drawRect(10, y, 20, 20);
				g2.drawString(u.getId() + " - credit: " + u.getCredit(), 35, y + 15);
				y += 25;
			}
        }

        public void refresh(){
            repaint();
        }
    }
