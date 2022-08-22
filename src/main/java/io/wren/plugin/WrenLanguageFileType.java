package io.wren.plugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class WrenLanguageFileType extends LanguageFileType {
    public static final WrenLanguageFileType INSTANCE = new WrenLanguageFileType();

    protected WrenLanguageFileType() {
        super(WrenLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Wren";
    }

    @Override
    public @NotNull String getDescription() {
        return "Wren language";
    }

    @Override
    public  @NotNull String getDefaultExtension() {
        return "wren";
    }

    @Override
    public @Nullable Icon getIcon() {
        return WrenLanguage.ICON;
    }
}
