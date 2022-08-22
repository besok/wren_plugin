package io.wren.plugin.parser.nodes;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import io.wren.plugin.WrenLanguageFileType;
import org.jetbrains.annotations.NotNull;

import static io.wren.plugin.WrenLanguage.INSTANCE;


public class WrenFile extends PsiFileBase {
    public WrenFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return WrenLanguageFileType.INSTANCE;
    }

}
