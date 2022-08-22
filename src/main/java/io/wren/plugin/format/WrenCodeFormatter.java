package io.wren.plugin.format;

import com.intellij.formatting.FormattingContext;
import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import org.jetbrains.annotations.NotNull;

public class WrenCodeFormatter implements FormattingModelBuilder {
    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        return FormattingModelBuilder.super.createModel(formattingContext);
    }
}
