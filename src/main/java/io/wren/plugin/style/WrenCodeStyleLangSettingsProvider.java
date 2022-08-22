package io.wren.plugin.style;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import io.wren.plugin.WrenLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WrenCodeStyleLangSettingsProvider extends LanguageCodeStyleSettingsProvider {

    public static WrenCodeStyleLangSettingsProvider INSTANCE = new WrenCodeStyleLangSettingsProvider();

    @Override
    public @Nullable String getCodeSample(@NotNull SettingsType settingsType) {
        return """
                class Toggle {
                  construct new(startState) {
                    _state = startState
                  }
                                
                  value { _state }
                  activate {
                    _state = !_state
                    return this
                  }
                }
                                
                class NthToggle is Toggle {
                  construct new(startState, maxCounter) {
                    super(startState)
                    _countMax = maxCounter
                    _count = 0
                  }
                                
                  activate {
                    _count = _count + 1
                    if (_count >= _countMax) {
                      super.activate
                      _count = 0
                    }
                                
                    return this
                  }
                }
                                
                var start = System.clock
                var n = 100000
                var val = true
                var toggle = Toggle.new(val)
                                
                for (i in 0...n) {
                  val = toggle.activate.value
                  val = toggle.activate.value
                  val = toggle.activate.value
                  val = toggle.activate.value
                  val = toggle.activate.value
                  val = toggle.activate.value
                  val = toggle.activate.value
                  val = toggle.activate.value
                  val = toggle.activate.value
                  val = toggle.activate.value
                }
                                
                System.print(toggle.value)
                                
                val = true
                var ntoggle = NthToggle.new(val, 3)
                                
                for (i in 0...n) {
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                  val = ntoggle.activate.value
                }
                                
                System.print(ntoggle.value)
                System.print("elapsed: %(System.clock - start)")
                """;
    }

    @Override
    public @NotNull Language getLanguage() {
        return WrenLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType.equals(SettingsType.INDENT_SETTINGS)) {
            consumer.showStandardOptions("INDENT_SIZE", "USE_TAB_CHARACTER", "TAB_SIZE");
        } else if (settingsType.equals(SettingsType.SPACING_SETTINGS)) {
            consumer.showStandardOptions("SPACE_WITHIN_EMPTY_METHOD_CALL_PARENTHESES");
        }
    }

    @Override
    public @Nullable IndentOptionsEditor getIndentOptionsEditor() {
        return new IndentOptionsEditor(INSTANCE);
    }
}
