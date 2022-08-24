package io.wren.plugin.parser;

import com.intellij.lang.DefaultASTFactoryImpl;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import io.wren.gramma.WrenParser;
import io.wren.plugin.parser.nodes.Id;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;

import static io.wren.plugin.WrenAstUtils.tt;

public class WrenAstFactory extends DefaultASTFactoryImpl {
    @Override
    public @NotNull LeafElement createLeaf(@NotNull IElementType type, @NotNull CharSequence text) {
        if(type instanceof TokenIElementType tpe){
            if(tt(WrenParser.IDENTIFIER) == tpe)
                return new Id(type,text);
        }
        return super.createLeaf(type, text);
    }
}
