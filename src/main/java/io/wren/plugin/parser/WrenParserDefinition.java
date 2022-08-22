package io.wren.plugin.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.wren.gramma.WrenLexer;
import io.wren.gramma.WrenParser;
import io.wren.plugin.parser.nodes.*;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jetbrains.annotations.NotNull;

import static io.wren.gramma.WrenParser.*;
import static io.wren.plugin.WrenLanguage.INSTANCE;
import static org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory.*;
import static org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory.createTokenSet;

public class WrenParserDefinition implements ParserDefinition {
    public WrenParserDefinition() {
        defineLanguageIElementTypes(INSTANCE, WrenParser.tokenNames, WrenParser.ruleNames);
    }

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new ANTLRLexerAdaptor(INSTANCE, new WrenLexer(CharStreams.fromString("")));
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new WrenParserAdaptor(new WrenParser(new CommonTokenStream(new WrenLexer(CharStreams.fromString("")))));
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return new IFileElementType(INSTANCE);
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return createTokenSet(INSTANCE, COMMENT, LINE_COMMENT);
    }

    @Override
    public @NotNull TokenSet getWhitespaceTokens() {
        return createTokenSet(INSTANCE, WS);
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return createTokenSet(INSTANCE, CHAR_LITERAL, STRING_LITERAL, TEXT_BLOCK);
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        IElementType elementType = node.getElementType();
        if (elementType instanceof RuleIElementType) {
            int ruleIndex = ((RuleIElementType) elementType).getRuleIndex();
            return switch (ruleIndex) {
                case RULE_classDefinition -> new ClassDef(node, elementType);
                case RULE_function -> new FunctionDef(node, elementType);
                case RULE_importModule -> new ImportModule(node, elementType);
                case RULE_statement -> new Statement(node, elementType);
                case RULE_block -> new Block(node, elementType);
                default -> new ANTLRPsiNode(node);
            };
        } else {
            return new ANTLRPsiNode(node);
        }
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new WrenFile(viewProvider);
    }
}
