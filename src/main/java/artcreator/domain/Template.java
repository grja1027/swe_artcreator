package artcreator.domain;

import java.awt.image.BufferedImage;

public class Template {

    private BufferedImage templateImage;

    public Template (BufferedImage image) {
        this.templateImage = image;
    }

    public BufferedImage getTemplateImage() {
        return templateImage;
    }

    public void setTemplateImage(BufferedImage templateImage) {
        this.templateImage = templateImage;
    }
}
