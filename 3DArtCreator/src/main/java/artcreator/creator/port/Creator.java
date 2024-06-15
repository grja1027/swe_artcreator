package artcreator.creator.port;

import artcreator.domain.Profile;
import artcreator.domain.Template;
import artcreator.domain.Image;

public interface Creator {
	Image loadImage(String path);
	Profile loadProfile(int id);
	Template generateTemplate();
	void saveTemplate(String targetPath);
	boolean confirmTemplateCreation();

	void sysop(String str);

}
