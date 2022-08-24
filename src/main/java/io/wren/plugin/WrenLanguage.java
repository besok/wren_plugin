package io.wren.plugin;

import com.intellij.lang.Language;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class WrenLanguage extends Language {

    public static Icon ICON_LIGHT = IconLoader.getIcon("/icons/pluginIcon.svg", WrenLanguage.class);
    public static Icon ICON_DARK = IconLoader.getIcon("/icons/pluginIcon_dark.svg", WrenLanguage.class);
    public static Language INSTANCE = new WrenLanguage();



    protected WrenLanguage() {
        super(("Wren"));
    }
}