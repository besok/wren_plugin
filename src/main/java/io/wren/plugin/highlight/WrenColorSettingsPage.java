package io.wren.plugin.highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import io.wren.plugin.WrenLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class WrenColorSettingsPage implements ColorSettingsPage {
    @Override
    public @Nullable Icon getIcon() {
        return WrenLanguage.ICON;
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter() {
        return new WrenSyntaxHighlighter();
    }

    @Override
    public @NonNls @NotNull String getDemoText() {
        return """
                // Ported from the Python version.
                                
                class Tree {
                  construct new(item, depth) {
                    _item = item
                    if (depth > 0) {
                      var item2 = item + item
                      depth = depth - 1
                      _left = Tree.new(item2 - 1, depth)
                      _right = Tree.new(item2, depth)
                    }
                  }
                                
                  check {
                    if (_left == null) {
                      return _item
                    }
                                
                    return _item + _left.check - _right.check
                  }
                }
                                
                var minDepth = 4
                var maxDepth = 12
                var stretchDepth = maxDepth + 1
                                
                var start = System.clock
                                
                System.print("stretch tree of depth %(stretchDepth) check: " +
                    "%(Tree.new(0, stretchDepth).check)")
                for (i in 1...1000) System.gc()
                                
                var longLivedTree = Tree.new(0, maxDepth)
                                
                // iterations = 2 ** maxDepth
                var iterations = 1
                for (d in 0...maxDepth) {
                  iterations = iterations * 2
                }
                                
                var depth = minDepth
                while (depth < stretchDepth) {
                  var check = 0
                  for (i in 1..iterations) {
                    check = check + Tree.new(i, depth).check + Tree.new(-i, depth).check
                  }
                                
                  System.print("%(iterations * 2) trees of depth %(depth) check: %(check)")
                  for (i in 1...1000) System.gc()
                                
                  iterations = iterations / 4
                  depth = depth + 2
                }
                                
                System.print(
                    "long lived tree of depth %(maxDepth) check: %(longLivedTree.check)")
                for (i in 1...1000) System.gc()
                                
                System.print("elapsed: %(System.clock - start)")
                """;
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return new AttributesDescriptor[]{
                new AttributesDescriptor("Identifier", WrenSyntaxHighlighterKeys.ID[0]),
                new AttributesDescriptor("Key sections", WrenSyntaxHighlighterKeys.KEY_SECTIONS[0]),
                new AttributesDescriptor("Keywords", WrenSyntaxHighlighterKeys.KEYWORDS[0]),
                new AttributesDescriptor("Numbers", WrenSyntaxHighlighterKeys.NUMBERS[0]),
                new AttributesDescriptor("String literal", WrenSyntaxHighlighterKeys.STRING[0]),
                new AttributesDescriptor("Dot", WrenSyntaxHighlighterKeys.DOT[0]),
                new AttributesDescriptor("Block comments", WrenSyntaxHighlighterKeys.COMMENTS[0]),
                new AttributesDescriptor("Line comments", WrenSyntaxHighlighterKeys.COMMENTS[1]),
                new AttributesDescriptor("Braces", WrenSyntaxHighlighterKeys.BRACES[0]),
                new AttributesDescriptor("Brackets", WrenSyntaxHighlighterKeys.BRACKETS[0]),
                new AttributesDescriptor("Parentheses", WrenSyntaxHighlighterKeys.PARENTHESES[0]),
                new AttributesDescriptor("Label", WrenSyntaxHighlighterKeys.LABEL[0])
        };
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Override
    public @NotNull String getDisplayName() {
        return "Wren";
    }
}
