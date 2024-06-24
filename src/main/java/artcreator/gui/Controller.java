package artcreator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.CompletableFuture;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import artcreator.creator.port.Creator;
import artcreator.domain.Image;
import artcreator.domain.Profile;
import artcreator.domain.Template;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;

public class Controller implements ActionListener, Observer {

    private CreatorFrame myView;
    private Creator myModel;
    private Subject subject;

    public Image currentImage;
    public Profile currentProfile;
    public Template currentTemplate;


    public Controller(CreatorFrame view, Subject subject, Creator model) {
        this.myView = view;
        this.myModel = model;
        this.subject = subject;
        this.subject.attach(this);
    }

    public synchronized void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "SELECT_IMAGE":
                // LoadImage Method
                this.currentImage = myModel.loadImage(null);

                if (currentImage != null) {
                    System.out.println("Image loaded successfully.");
                    subject.setState(State.S.IMAGE_LOADED); // Change state to IMAGE_LOADED
                    myView.update(subject.getState()); // Updates view according to the new State
                } else {
                    System.out.println("No image selected or failed to load the image.");
                }
                break;

            case "CONTINUE_TO_LOAD_PROFILE":
                // LoadProfile Method
                this.currentProfile = myModel.loadProfile(0); // always the current profile is loaded, id = 0

                if (currentProfile != null) {
                    System.out.println("Profile loaded successfully.");
                    subject.setState(State.S.PROFILE_LOADED); // Change state to PROFILE_LOADED
                    myView.update(State.S.PROFILE_LOADED); // Updates view according to the new State
                } else {
                    System.out.println("No profile selected or failed to load the profile.");
                }
                break;

            case "GENERATE_TEMPLATE":
                // GenerateTemplate Method
                this.currentTemplate = myModel.generateTemplate();
                if (currentTemplate != null) {
                    System.out.println("Template generated successfully.");
                    subject.setState(State.S.TEMPLATE_GENERATED); // Change state to TEMPLATE_GENERATED
                    myView.update(State.S.TEMPLATE_GENERATED); // Updates view according to the new State
                } else {
                    System.out.println("Failed to generate template.");
                }
                break;

            case "EDIT_PROFILE":
                // Use-case "Profil erstellen", show info message that use-case is not implemented
                myView.showEditProfileInfoMessage();

                break;
        }
    }

    public void update(State newState) {/* modify controller or view if necessary */}
}
	
