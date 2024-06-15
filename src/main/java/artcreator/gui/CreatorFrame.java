package artcreator.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import artcreator.creator.CreatorFactory;
import artcreator.creator.port.Creator;
import artcreator.domain.Image;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;
import artcreator.statemachine.port.State.S;

public class CreatorFrame extends JFrame implements Observer {

	private Creator creator = CreatorFactory.FACTORY.creator();
	private Subject subject = StateMachineFactory.FACTORY.subject();
	private Controller controller;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 500;

	private JButton btn = new JButton("Select Image");
	private JButton continueBtn = new JButton("Continue");
	private JPanel panel = new JPanel();
	private JLabel imageLabel = new JLabel();
	private JLabel formatLabel = new JLabel();

	public CreatorFrame() {
		super("ArtCreator");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.subject.attach(this);
		this.controller = new Controller(this, subject, creator);

		/* build view */
		this.btn.addActionListener(this.controller);
		this.panel.add(this.btn);
		this.getContentPane().add(panel, BorderLayout.NORTH);
		this.getContentPane().add(imageLabel, BorderLayout.CENTER);
		this.getContentPane().add(formatLabel, BorderLayout.SOUTH);
	}

	public void update(State newState) {
		if (newState == S.LOAD_IMAGE) {
			// Logic to update the view when IMAGE_LOADED state is reached
		}
		if (newState == S.IMAGE_LOADED) {
			this.displayImage(this.controller.currentImage);
		}
		if (newState == S.PROFILE_LOADED) {
			// Logic to update the view when IMAGE_LOADED state is reached
		}
		if (newState == S.TEMPLATE_GENERATED) {
			// Logic to update the view when IMAGE_LOADED state is reached
		}
		if (newState == S.TEMPLATE_SAVED) {
			// Logic to update the view when IMAGE_LOADED state is reached
		}
	}

	public void displayImage(Image image) {
		BufferedImage bufferedImage = image.getBufferedImage();
		BufferedImage scaledImage = new BufferedImage(600, 400, bufferedImage.getType());

		Graphics2D g2d = scaledImage.createGraphics();
		g2d.drawImage(bufferedImage, 0, 0, 600, 400, null);
		g2d.dispose();

		// Button Adjustments
		this.panel.add(this.continueBtn);
		this.btn.setText("Change Image");

		ImageIcon imageIcon = new ImageIcon(scaledImage);
		imageLabel.setIcon(imageIcon);

		// Label to show current format
		String format = "3:2"; // Has to be dynamic, not hard-coded
		String formatText = String.format("Format laut Profil: %s", format);
		formatLabel.setText(formatText);
		formatLabel.setHorizontalAlignment(JLabel.LEFT);
		formatLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

		this.revalidate();
		this.repaint();
	}
}
