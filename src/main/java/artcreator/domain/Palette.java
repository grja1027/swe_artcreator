package artcreator.domain;

import java.awt.Color;
import java.util.List;
import java.util.Arrays;

public enum Palette {
    PALETTE_64(new Color[]{
            new Color(255, 0, 0), new Color(0, 255, 0), new Color(0, 0, 255),
            new Color(255, 255, 0), new Color(0, 255, 255), new Color(255, 0, 255),
            new Color(192, 192, 192), new Color(128, 128, 128), new Color(128, 0, 0),
            new Color(128, 128, 0), new Color(0, 128, 0), new Color(128, 0, 128),
            new Color(0, 128, 128), new Color(0, 0, 128), new Color(255, 165, 0),
            new Color(255, 69, 0), new Color(255, 105, 180), new Color(255, 20, 147),
            new Color(255, 255, 224), new Color(255, 250, 205), new Color(255, 239, 213),
            new Color(255, 228, 181), new Color(255, 218, 185), new Color(255, 222, 173),
            new Color(255, 228, 225), new Color(255, 240, 245), new Color(255, 245, 238),
            new Color(240, 248, 255), new Color(248, 248, 255), new Color(245, 245, 245),
            new Color(255, 250, 250), new Color(240, 255, 240), new Color(255, 255, 240),
            new Color(240, 255, 255), new Color(245, 255, 250), new Color(240, 230, 140),
            new Color(230, 230, 250), new Color(255, 228, 196), new Color(250, 235, 215),
            new Color(255, 235, 205), new Color(255, 248, 220), new Color(255, 228, 181),
            new Color(255, 222, 173), new Color(255, 218, 185), new Color(250, 240, 230),
            new Color(255, 240, 245), new Color(255, 228, 225), new Color(240, 248, 255),
            new Color(248, 248, 255), new Color(245, 245, 245), new Color(255, 250, 250),
            new Color(240, 255, 240), new Color(255, 255, 240), new Color(240, 255, 255),
            new Color(245, 255, 250), new Color(255, 20, 147), new Color(255, 105, 180),
            new Color(255, 69, 0), new Color(255, 165, 0)
    }),
    PALETTE_32(new Color[]{
            new Color(255, 0, 0), new Color(0, 255, 0), new Color(0, 0, 255),
            new Color(255, 255, 0), new Color(0, 255, 255), new Color(255, 0, 255),
            new Color(192, 192, 192), new Color(128, 128, 128), new Color(128, 0, 0),
            new Color(128, 128, 0), new Color(0, 128, 0), new Color(128, 0, 128),
            new Color(0, 128, 128), new Color(0, 0, 128), new Color(255, 165, 0),
            new Color(255, 69, 0), new Color(255, 105, 180), new Color(255, 20, 147),
            new Color(255, 255, 224), new Color(255, 250, 205), new Color(255, 239, 213),
            new Color(255, 228, 181), new Color(255, 218, 185), new Color(255, 222, 173),
            new Color(255, 228, 225), new Color(255, 240, 245), new Color(255, 245, 238),
            new Color(240, 248, 255), new Color(248, 248, 255), new Color(245, 245, 245),
            new Color(255, 250, 250), new Color(240, 255, 240)
    }),
    PALETTE_16(new Color[]{
            new Color(255, 0, 0), new Color(7, 95, 68), new Color(0, 0, 255),
            new Color(255, 255, 0), new Color(0, 255, 255), new Color(255, 0, 255),
            new Color(255, 255, 255), new Color(113, 74, 17), new Color(154, 145, 154),
            new Color(67, 67, 63), new Color(0, 0, 0), new Color(135, 7, 135),
            new Color(0, 133, 227), new Color(230, 204, 105), new Color(112, 218, 77),
            new Color(255, 115, 0)
    });

    private final Color[] colors;

    Palette(Color[] colors) {
        this.colors = colors;
    }

    public Color[] getColors() {
        return colors;
    }
}
