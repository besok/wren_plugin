package io.wren.plugin.highlight;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*;
import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class WrenSyntaxHighlighterKeys {

    public static TextAttributesKey[] ID = new TextAttributesKey[]{createTextAttributesKey("ID", IDENTIFIER)};
    public static TextAttributesKey[] NUMBERS = new TextAttributesKey[]{createTextAttributesKey("NUMBERS", NUMBER)};
    public static TextAttributesKey[] KEYWORDS = new TextAttributesKey[]{createTextAttributesKey("KEYWORD", KEYWORD)};
    public static TextAttributesKey[] SPECIAL_SYMBOLS = new TextAttributesKey[]{createTextAttributesKey("SPECIAL_SYMBOLS", STATIC_FIELD)};
    public static TextAttributesKey[] STRING = new TextAttributesKey[]{createTextAttributesKey("STRING", DefaultLanguageHighlighterColors.STRING)};
    public static TextAttributesKey[] KEY_SECTIONS = new TextAttributesKey[]{createTextAttributesKey("KEY_SECTIONS", METADATA)};
    public static TextAttributesKey[] DOT = new TextAttributesKey[]{createTextAttributesKey("DOT", DefaultLanguageHighlighterColors.DOT)};
    public static TextAttributesKey[] SIGN = new TextAttributesKey[]{createTextAttributesKey("SIGN", OPERATION_SIGN)};
    public static TextAttributesKey[] LABEL = new TextAttributesKey[]{createTextAttributesKey("LABEL", DefaultLanguageHighlighterColors.LABEL)};
    public static TextAttributesKey[] BRACES = new TextAttributesKey[]{createTextAttributesKey("BRACES", DefaultLanguageHighlighterColors.BRACES)};
    public static TextAttributesKey[] BRACKETS = new TextAttributesKey[]{createTextAttributesKey("BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)};
    public static TextAttributesKey[] PARENTHESES = new TextAttributesKey[]{createTextAttributesKey("PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)};

    public static TextAttributesKey[] STATIC = new TextAttributesKey[]{createTextAttributesKey("STATIC", STATIC_FIELD)};

    public static TextAttributesKey[] COMMENTS = new TextAttributesKey[]{
            createTextAttributesKey("BLOCK_COMMENTS", BLOCK_COMMENT),
            createTextAttributesKey("LINE_COMMENTS", LINE_COMMENT)
    };
}
