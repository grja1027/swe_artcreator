package artcreator.creator.port;

import artcreator.domain.Profile;
import artcreator.domain.Template;
import artcreator.domain.Image;

import java.io.File;

public interface Creator {
	void loadImage(File path);
	void loadProfile(int id);
	void generateTemplate();
	void saveTemplate(String targetPath);

	Image getCurrentImage();
	Profile getCurrentProfile();
	Template getCurrentTemplate();
	boolean getTemplateSaved();
	void sysop(String str);

}
