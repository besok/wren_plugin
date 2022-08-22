package io.wren.plugin.style;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class WrenCodeStyleSettings extends CustomCodeStyleSettings {
    protected WrenCodeStyleSettings(CodeStyleSettings container) {
        super("Wren", container);
    }
}
