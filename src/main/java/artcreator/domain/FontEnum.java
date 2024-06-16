package artcreator.domain;

import java.awt.Font;

public enum FontEnum {
    BOLD("Arial", Font.BOLD, 14),
    REGULAR("Arial", Font.PLAIN, 12);

    private final String fontName;
    private final int fontStyle;
    private final int fontSize;
    private final Font font;

    FontEnum(String fontName, int fontStyle, int fontSize) {
        this.fontName = fontName;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        this.font = new Font(this.fontName, this.fontStyle, this.fontSize);
    }

    public Font getFont() {
        return this.font;
    }

    public Font getFont(int size) {
        return new Font(this.fontName, this.fontStyle, size);
    }
}
