package io.wren.plugin.style;

import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import io.wren.plugin.WrenLanguage;
import org.jetbrains.annotations.Nullable;

public class WrenSimplePanel extends TabbedLanguageCodeStylePanel {
    protected WrenSimplePanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
        super(WrenLanguage.INSTANCE, currentSettings, settings);
    }
}
