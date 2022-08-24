package io.wren.plugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.util.PsiTreeUtil;
import io.wren.gramma.WrenParser;
import io.wren.plugin.parser.nodes.Block;
import io.wren.plugin.parser.nodes.ClassDef;
import io.wren.plugin.parser.nodes.FunctionDef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.stream.Stream;

import static io.wren.gramma.WrenParser.*;
import static io.wren.plugin.WrenAstUtils.*;

public class BaseWrenFoldingBuilder extends FoldingBuilderEx implements DumbAware {
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {

        return Stream.of(
                        findBy(root, Block.class),
                        findBy(root, ClassDef.class),
                        findBy(root, FunctionDef.class))
                .flatMap(Collection::stream)
                .filter(e -> e.getTextLength() > 0)
                .map(this::createFolding)
                .toArray(FoldingDescriptor[]::new);
    }

    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
        if (isRule(node, RULE_block)) return "{..}";
        else if (isRule(node, RULE_classDefinition))
            return help(node.getPsi()).id().map(LeafElement::getText).orElse(node.getElementType().toString()) + " {..}";
        else if (isRule(node, RULE_function))
            return node.getFirstChildNode().getText() + " (..) {..}";
        else return node.getElementType().toString();
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }


    private @NotNull <T extends PsiElement> Collection<T> findBy(PsiElement root, Class<T> clazz) {
        return PsiTreeUtil.findChildrenOfType(root, clazz);
    }

    private FoldingDescriptor createFolding(PsiElement node) {
        return new FoldingDescriptor(node.getNode(), node.getTextRange());
    }


}
