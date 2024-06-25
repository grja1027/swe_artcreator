package artcreator.gui;

import artcreator.creator.port.Creator;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Controller implements ActionListener, Observer {

    private CreatorFrame myView;
    private Creator myModel;
    private Subject subject;

    public Controller(CreatorFrame view, Subject subject, Creator model) {
        this.myView = view;
        this.myModel = model;
        this.subject = subject;
        this.subject.attach(this);
    }

    // opens a dialog to choose the file/image from file system
    public File loadImage() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "bmp");
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null); // Pass null for a simple dialog, or pass a reference to the parent component

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + filePath);
            return selectedFile;
        }
        return null;
    }

    // opens a dialog to choose the location where to save the template
    private String saveTemplate() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the location to save your template");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "jpg".toUpperCase() + " Image", "jpg", "png"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Ensure the file has the correct extension
            if (!filePath.endsWith("." + "jpg")) {
                filePath += "." + "jpg";
            }

            return filePath;
        }

        return null;
    }

    public synchronized void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "SELECT_IMAGE":
                // LoadImage Method
                myModel.loadImage(this.loadImage());
                subject.setState(State.S.IMAGE_LOADED); // Change state to IMAGE_LOADED
                myView.update(subject.getState());
                break;

            case "CONTINUE_TO_LOAD_PROFILE":
                // LoadProfile Method
                myModel.loadProfile(0); // always the current profile is loaded, id = 0
                subject.setState(State.S.PROFILE_LOADED); // Change state to PROFILE_LOADED
                myView.update(State.S.PROFILE_LOADED); // Updates view according to the new State
                break;

            case "GENERATE_TEMPLATE":
                // GenerateTemplate Method
                myModel.generateTemplate();
                subject.setState(State.S.TEMPLATE_GENERATED); // Change state to TEMPLATE_GENERATED
                myView.update(State.S.TEMPLATE_GENERATED); // Updates view according to the new State
                break;

            case "EDIT_PROFILE":
                // Use-case "Profil erstellen", show info message that use-case is not implemented
                myView.showEditProfileInfoMessage();
                break;

            case "SAVE_TEMPLATE":
                // SaveTemplate Method
                myModel.saveTemplate(saveTemplate());
                subject.setState(State.S.TEMPLATE_SAVED); // Change state to TEMPLATE_SAVED
                myView.update(State.S.TEMPLATE_SAVED); // Updates view according to the new State
                break;

            case "FINISH_TEMPLATE_GENERATION":
                //use case not implemented
                break;
        }
    }

    public void update(State newState) {/* modify controller or view if necessary */}
}
	
