package artcreator.domain;

public class Profile {
    private String format; // Bildformat
    private int colors; // Farbtiefe
    private int resolution; // Auflösung, wieviele Pixel durch eine Bügelperle dargestellt werden
    private int width; // Anzahl Bügelperlen in der Breite, max. 60 Stk.
    private int height; // Anzahl Bügelperlen in der Höhe, max. 60 Stk.

    public Profile(String format, int colors, int resolution, int width, int height) {
        this.format = format;
        this.colors = colors;
        this.resolution = resolution;
        this.width = width;
        this.height = height;
    }

    // Getter und Setter Methoden
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "format='" + format + '\'' +
                ", colors=" + colors +
                ", resolution='" + resolution + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}

