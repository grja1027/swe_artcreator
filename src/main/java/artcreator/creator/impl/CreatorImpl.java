package artcreator.creator.impl;

import artcreator.creator.port.Creator;
import artcreator.domain.*;
import artcreator.domain.port.Domain;
import artcreator.gui.Controller;
import artcreator.statemachine.port.StateMachine;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreatorImpl implements Creator {

	private Controller controller;
	private StateMachine stateMachine;
	private Domain domain;
	public Image currentImage = new Image();
	public Profile currentProfile;

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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "bmp");
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

				this.currentImage = image;

				return image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Profile loadProfile(int id) {
		// Load profile from AllProfiles using the provided id
		Profile profile = AllProfiles.getProfile(id);
		if (profile != null) {
			System.out.println("Profile loaded: " + profile);
		} else {
			System.out.println("Profile with ID " + id + " not found.");
		}

		this.currentProfile = profile;
		return profile;
	}

	@Override
	public Template generateTemplate() {
		Image image = this.currentImage;
		Profile profile = AllProfiles.getProfile(0);

		int tileSize = profile.getResolution();
		int maxWidth = profile.getWidth();
		int maxHeight = profile.getHeight();
		int numColors = profile.getColors();

		BufferedImage bufferedImage = image.getBufferedImage();
		BufferedImage mosaicImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

		for (int y = 0; y < bufferedImage.getHeight(); y += tileSize) {
			for (int x = 0; x < bufferedImage.getWidth(); x += tileSize) {
				int averageColor = getAverageColor(bufferedImage, x, y, tileSize);
				int quantizedColor = quantizeColor(averageColor, numColors);
				fillArea(mosaicImage, x, y, tileSize, quantizedColor);
			}
		}

		return new Template(mosaicImage);
	}

	private int getAverageColor(BufferedImage image, int startX, int startY, int size) {
		int endX = Math.min(startX + size, image.getWidth());
		int endY = Math.min(startY + size, image.getHeight());

		int sumRed = 0, sumGreen = 0, sumBlue = 0, count = 0;

		for (int y = startY; y < endY; y++) {
			for (int x = startX; x < endX; x++) {
				int rgb = image.getRGB(x, y);
				sumRed += (rgb >> 16) & 0xFF;
				sumGreen += (rgb >> 8) & 0xFF;
				sumBlue += rgb & 0xFF;
				count++;
			}
		}

		if (count == 0) {
			return 0; // oder eine andere Standardfarbe
		}

		int avgRed = sumRed / count;
		int avgGreen = sumGreen / count;
		int avgBlue = sumBlue / count;

		return (avgRed << 16) | (avgGreen << 8) | avgBlue;
	}

	private int quantizeColor(int rgb, int numColors) {
		int levels = (int) Math.cbrt(numColors); // Wurzel 3 von numColors für gleichmäßige Aufteilung in R, G, B
		int mask = 0xFF / (levels - 1); // Maske für die Quantisierung

		int red = ((rgb >> 16) & 0xFF) / mask * mask;
		int green = ((rgb >> 8) & 0xFF) / mask * mask;
		int blue = (rgb & 0xFF) / mask * mask;

		return (red << 16) | (green << 8) | blue;
	}

	private void fillArea(BufferedImage image, int startX, int startY, int size, int color) {
		int endX = Math.min(startX + size, image.getWidth());
		int endY = Math.min(startY + size, image.getHeight());

		for (int y = startY; y < endY; y++) {
			for (int x = startX; x < endX; x++) {
				image.setRGB(x, y, color);
			}
		}
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
