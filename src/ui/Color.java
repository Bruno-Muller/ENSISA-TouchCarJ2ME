package ui;

/**
 *
 * @author bruno
 */

public class Color {

    public static final Color DARK_GRAY = new Color(0x404040);
    public static final Color GRAY = new Color(0x808080);
    public static final Color BLACK = new Color(0x000000);
    public static final Color GREEN = new Color(0x00FF00);
    public static final Color CYAN = new Color(0x00FFFF);
    public static final Color RED = new Color(0xFF0000);
    public static final Color ORANGE = new Color(0xFFC800);
    public static final Color YELLOW = new Color(0xFFFF00);

    private int color;

    Color(int color) {
        this.color = color;
    }

    public int value() {
        return this.color;
    }

}
