package io.wren.plugin.structure;

import com.intellij.icons.AllIcons;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import io.wren.plugin.parser.nodes.ClassDef;
import io.wren.plugin.parser.nodes.FunctionDef;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static io.wren.plugin.WrenAstUtils.ast;

public class PresentationElement implements ItemPresentation {
    private final PsiElement element;

    public PresentationElement(PsiElement element) {
        this.element = element;
    }

    @Override
    public @Nullable String getPresentableText() {

        if (element instanceof ClassDef) {
            return headText(element, "/classDefinition/id");
        } else if (element instanceof FunctionDef) {
            return headText(element, "/function/id");
        } else {
            return defaultText(element);
        }
    }

    @Override
    public @Nullable Icon getIcon(boolean unused) {

        if (element instanceof ClassDef) {
            return AllIcons.Nodes.Class;
        } else if (element instanceof FunctionDef) {
            return AllIcons.Nodes.Function;
        } else {
            return AllIcons.Actions.Help;
        }
    }

    private String defaultText(PsiElement element) {
        return element.getNode().getElementType().toString();
    }

    private String headText(PsiElement element, String path) {
        return ast(element)
                .path(path)
                .stream()
                .findFirst()
                .map(PsiElement::getText)
                .orElse(defaultText(element));
    }

}
