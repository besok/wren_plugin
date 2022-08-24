package io.wren.plugin.highlight;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import io.wren.gramma.WrenLexer;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.v4.runtime.CharStreams;
import org.jetbrains.annotations.NotNull;

import static io.wren.gramma.WrenLexer.*;
import static io.wren.plugin.WrenLanguage.INSTANCE;

public class WrenSyntaxHighlighter extends SyntaxHighlighterBase {

    static TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ANTLRLexerAdaptor(INSTANCE, new WrenLexer(CharStreams.fromString("")));
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {

        if (tokenType instanceof TokenIElementType) {
            return switch (((TokenIElementType) tokenType).getANTLRTokenType()) {
                case IDENTIFIER -> WrenSyntaxHighlighterKeys.ID;
                case DOT -> WrenSyntaxHighlighterKeys.DOT;
                case STRING_LITERAL, TEXT_BLOCK -> WrenSyntaxHighlighterKeys.STRING;
                case LBRACE, RBRACE -> WrenSyntaxHighlighterKeys.BRACES;
                case LBRACK, RBRACK -> WrenSyntaxHighlighterKeys.BRACKETS;
                case LPAREN, RPAREN -> WrenSyntaxHighlighterKeys.PARENTHESES;
                case COMMENT , LINE_COMMENT -> WrenSyntaxHighlighterKeys.COMMENTS;
                case DECIMAL_LITERAL, HEX_LITERAL, FLOAT_LITERAL, HEX_FLOAT_LITERAL ->
                        WrenSyntaxHighlighterKeys.NUMBERS;
                case AS, BREAK_T, CLASS_T, CONTINUE_T, ELSE_T, FALSE_T, STATIC_T, VAR_T, IMPORT_T, IN, IS, CONSTRUCT, NULL_T, RETURN_T, TRUE_T, WHILE_T, FOR_T, IF_T, FOREIGN_T ->
                        WrenSyntaxHighlighterKeys.KEYWORDS;
                default -> EMPTY_KEYS;
            };
        } else return EMPTY_KEYS;
    }
}

