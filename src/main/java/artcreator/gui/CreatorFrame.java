package artcreator.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Time;

import artcreator.creator.CreatorFactory;
import artcreator.creator.port.Creator;
import artcreator.domain.*;
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
    private JButton editProfileBtn = new JButton("Edit Profile");
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JLabel imageLabel = new JLabel();
    private JLabel templateLabel = new JLabel();
    private JLabel formatLabel = new JLabel();
    private JLabel infoLabel = new JLabel();
    private JPanel profilePanel = new JPanel(new GridBagLayout());

    public CreatorFrame() {
        super("ArtCreator");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.subject.attach(this);
        this.controller = new Controller(this, subject, creator);

        /* build view */
        this.btn.setActionCommand("SELECT_IMAGE");
        this.continueBtn.setActionCommand("CONTINUE_TO_LOAD_PROFILE");
        this.editProfileBtn.setActionCommand("EDIT_PROFILE");
        this.btn.addActionListener(this.controller);
        this.continueBtn.addActionListener(this.controller);
        this.editProfileBtn.addActionListener(this.controller);
        this.topPanel.add(this.btn);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);

        this.infoLabel = new JLabel("This button is not implemented.");
        this.infoLabel.setForeground(Color.RED);
        this.infoLabel.setVisible(false); // Initially hidden

        // Style the continue & generate template button
        this.continueBtn.setForeground(new Color(0, 133, 227));
    }

    // Updates the View according to state
    public void update(State newState) {
        if (newState == S.LOAD_IMAGE) {
            // Logic to update the view when IMAGE_LOADED state is reached
        }
        if (newState == S.IMAGE_LOADED) {
            this.displayImage(this.controller.currentImage);
        }
        if (newState == S.PROFILE_LOADED) {
            this.displayProfile(this.controller.currentProfile);
        }
        if (newState == S.TEMPLATE_GENERATED) {
            this.displayTemplate(this.controller.currentTemplate);
        }
        if (newState == S.TEMPLATE_SAVED) {
            // Logic to update the view when IMAGE_LOADED state is reached
        }
    }

    public void displayImage(Image image) {
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        this.bottomPanel.add(formatLabel); // add formatlabel to GUI
        this.bottomPanel.add(this.continueBtn); // add continue-button to GUI
        this.getContentPane().add(imageLabel, BorderLayout.CENTER); // add imageview to GUI

        BufferedImage bufferedImage = image.getBufferedImage();
        BufferedImage scaledImage = new BufferedImage(600, 300, bufferedImage.getType()); //ToDo: müsste aus Profil geladen werden

        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(bufferedImage, 0, 0, 600, 300, null);
        g2d.dispose();

        // Button Adjustments
        this.btn.setText("Change Image");

        ImageIcon imageIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(imageIcon);

        // Label to show current format
        String format = AllProfiles.getProfile(0).getFormat();
        String formatText = String.format("Format according to profile: %s", format);
        formatLabel.setText(formatText);
        formatLabel.setHorizontalAlignment(JLabel.LEFT);
        formatLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

        this.revalidate();
        this.repaint();
    }

    public void showEditProfileInfoMessage() {
        infoLabel.setVisible(true);
    }

    public void hideEditProfileInfoMessage() {
        infoLabel.setVisible(false);
    }

    public void displayProfile(Profile profile) {
        this.getContentPane().add(profilePanel, BorderLayout.CENTER); // add profileview to GUI
        this.continueBtn.setActionCommand("GENERATE_TEMPLATE");
        this.continueBtn.setText("Generate Template"); // button text changes from "continue" to "generate template"
        this.btn.setVisible(false); // set "Change image" button invisible
        this.imageLabel.setVisible(false); // set Image invisible
        this.formatLabel.setVisible(false); // set format-label invisible

        this.bottomPanel.removeAll(); // reset the layout of bottom panel the restructure the buttons
        this.bottomPanel.add(editProfileBtn); // add Button to edit profile
        this.bottomPanel.add(continueBtn);

        profilePanel.removeAll(); // Clear any previous data

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 10, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel headerText = new JLabel("Please check the following profile which is used to create the template:");
        headerText.setFont(FontEnum.BOLD.getFont());
        profilePanel.add(headerText, gbc);

        // Create and add labels for each profile attribute
        gbc.gridy++;
        JLabel formatLabel = new JLabel("Image format: " + profile.getFormat());
        profilePanel.add(formatLabel, gbc);

        gbc.gridy++;
        JLabel colorsLabel = new JLabel("Color depth: " + profile.getColors() + " colors");
        profilePanel.add(colorsLabel, gbc);

        gbc.gridy++;
        JLabel resolutionLabel = new JLabel("Resolution: " + profile.getResolution() + " pixels represented by one ironing bead");
        profilePanel.add(resolutionLabel, gbc);

        gbc.gridy++;
        JLabel widthLabel = new JLabel("Image width: " + profile.getWidth() + " ironing beads");
        profilePanel.add(widthLabel, gbc);

        gbc.gridy++;
        JLabel heightLabel = new JLabel("Image height: " + profile.getHeight() + " ironing beads");
        profilePanel.add(heightLabel, gbc);

        profilePanel.revalidate();
        profilePanel.repaint();
    }

    public void displayTemplate(Template template) {
        this.getContentPane().add(templateLabel, BorderLayout.CENTER);
        this.profilePanel.setVisible(false);

        BufferedImage bufferedImage = template.getTemplateImage();
        BufferedImage scaledImage = new BufferedImage(600, 300, bufferedImage.getType()); //ToDo: müsste aus Profil geladen werden

        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(bufferedImage, 0, 0, 600, 300, null);
        g2d.dispose();

        ImageIcon imageIcon = new ImageIcon(scaledImage);
        templateLabel.setIcon(imageIcon);

        this.revalidate();
        this.repaint();
    }
}

