package io.wren.plugin;

import com.intellij.lang.Language;
import com.intellij.openapi.util.IconLoader;
import io.wren.gramma.WrenLexer;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;

import javax.swing.*;

public class WrenLanguage extends Language {

    public static Icon ICON = IconLoader.getIcon("/icons/pluginIcon.svg", WrenLanguage.class);
    public static Language INSTANCE = new WrenLanguage();



    protected WrenLanguage() {
        super(("Wren"));
    }
}