package io.wren.plugin.parser;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import io.wren.gramma.WrenParser;
import io.wren.plugin.WrenLanguage;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

public class WrenParserAdaptor extends ANTLRParserAdaptor {

    public WrenParserAdaptor(WrenParser parser) {
        super(WrenLanguage.INSTANCE, parser);
    }

    @Override
    protected ParseTree parse(Parser parser, IElementType root) {
        return ((WrenParser) parser).script();
    }
}
