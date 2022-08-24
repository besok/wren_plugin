package io.wren.plugin.parser.nodes;

import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import io.wren.gramma.WrenParser;
import io.wren.plugin.parser.WrenParserDefinition;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.antlr.intellij.adaptor.psi.Trees;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.wren.plugin.WrenAstUtils.rt;
import static io.wren.plugin.WrenLanguage.INSTANCE;

public class Id extends ANTLRPsiLeafNode implements PsiNamedElement {
    private IElementType tpe;
    private String text;

    public Id(IElementType type, CharSequence text) {
        super(type, text);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        PsiElement findElem = Trees.createLeafFromText(getProject(), INSTANCE, this, name, rt(WrenParser.RULE_id));
        return Optional
                .ofNullable(findElem)
                .map(this::replace)
                .orElse(this);

    }
}



