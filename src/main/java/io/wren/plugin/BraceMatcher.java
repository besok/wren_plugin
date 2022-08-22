package io.wren.plugin;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import static io.wren.gramma.WrenLexer.*;
import static io.wren.plugin.WrenAstUtils.tt;

public class BraceMatcher implements PairedBraceMatcher {
    @Override
    public BracePair @NotNull [] getPairs() {
        return new BracePair[]{
                new BracePair(tt(LPAREN), tt(RPAREN), false),
                new BracePair(tt(LBRACK), tt(RBRACK), false),
                new BracePair(tt(LBRACE), tt(RBRACE), false),
        };
    }


    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
