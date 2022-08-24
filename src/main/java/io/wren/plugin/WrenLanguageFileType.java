package io.wren.plugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.util.ui.UIUtil;
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
    public @NotNull String getDefaultExtension() {
        return "wren";
    }

    @Override
    public @Nullable Icon getIcon() {
        if (UIUtil.isUnderDarcula()) return WrenLanguage.ICON_DARK;
        else return WrenLanguage.ICON_LIGHT;
    }
}
