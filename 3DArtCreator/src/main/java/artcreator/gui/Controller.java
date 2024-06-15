package artcreator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.CompletableFuture;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import artcreator.creator.port.Creator;
import artcreator.domain.Image;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;

public class Controller implements ActionListener, Observer {

	  private CreatorFrame myView;
	  private Creator myModel;
	  private Subject subject;

	  public Image currentImage;


	  public Controller(CreatorFrame view, Subject subject, Creator model) {
	    this.myView = view;
	    this.myModel = model;
	    this.subject = subject;
	    this.subject.attach(this); 
	  }


	public synchronized void actionPerformed(ActionEvent e) {
		// LoadImage Method
		this.currentImage = myModel.loadImage(null);

		if (currentImage != null) {
			System.out.println("Image loaded successfully.");
			subject.setState(State.S.IMAGE_LOADED); // Change state to IMAGE_LOADED
			myView.update(State.S.IMAGE_LOADED); // Updates view regarding to the new State
		} else {
			System.out.println("No image selected or failed to load the image.");
		}
	}
	  
	  public void update(State newState) {/* modify controller or view if necessary */}
	}
	
