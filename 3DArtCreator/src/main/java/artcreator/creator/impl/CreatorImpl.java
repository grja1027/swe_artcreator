package artcreator.creator.impl;

import artcreator.creator.port.Creator;
import artcreator.domain.Image;
import artcreator.domain.Profile;
import artcreator.domain.Template;
import artcreator.domain.port.Domain;
import artcreator.statemachine.port.StateMachine;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreatorImpl implements Creator {

	private StateMachine stateMachine;
	private Domain domain;
	public CreatorImpl(StateMachine stateMachine, Domain domain) {
		this.stateMachine = stateMachine;
		this.domain = domain;
	}

	@Override
	public void sysop(String str) {
		System.out.println(str);
		
	}

	@Override
	public Image loadImage(String path) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select an Image");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
		fileChooser.addChoosableFileFilter(filter);

		int returnValue = fileChooser.showOpenDialog(null); // Pass null for a simple dialog, or pass a reference to the parent component

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();
			System.out.println("Selected file: " + filePath);

			try {
				BufferedImage bufferedImage = ImageIO.read(selectedFile);
				Image image = new Image();
				image.setBufferedImage(bufferedImage);
				return image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Profile loadProfile(int ID) {
		// Implementation for processing the image
		return new Profile();
	}

	@Override
	public Template generateTemplate() {
		// Implementation for generating a template from the chosen image and its profile
		return new Template();
	}

	@Override
	public void saveTemplate(String targetPath) {
		// Implementation for saving the generated template
	}

	@Override
	public boolean confirmTemplateCreation() {
		// Implementation for confirming the template creation
		return true;
	}

}
