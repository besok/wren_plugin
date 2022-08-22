package io.wren.plugin.parser.nodes;

import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.CompositePsiElement;
import com.intellij.psi.tree.IElementType;
import io.wren.plugin.WrenLanguage;
import org.jetbrains.annotations.NotNull;

import static io.wren.plugin.WrenLanguage.*;

/**
 * The node represents an empty node that does not exist in the tree and summoned to stand for the None analog.
 */
public class GhostNode extends WrenNode {
    public GhostNode() {
        super(
                new CompositePsiElement(new IElementType("ghost", INSTANCE)) {
                },
                new IElementType("ghost", INSTANCE)
        );
    }
}
