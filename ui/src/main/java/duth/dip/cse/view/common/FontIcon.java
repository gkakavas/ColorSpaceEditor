package duth.dip.cse.view.common;

import duth.dip.cse.util.property.Property;
import org.kordamp.ikonli.Ikon;

import java.awt.Color;


public class FontIcon extends org.kordamp.ikonli.swing.FontIcon implements Injectable {

    @Property(name = "fontIcon.background.color")
    public int backgroundColor;

    @Property(name = "fontIcon.size")
    public int size;

    public FontIcon(Ikon ikon) {
        super();
        configure(ikon);
    }

    private void configure(Ikon ikon) {
        injectPropertiesTo(this);
        this.setIkon(ikon);
        this.setIconSize(size);
        this.setIconColor(new Color(backgroundColor));
    }
}