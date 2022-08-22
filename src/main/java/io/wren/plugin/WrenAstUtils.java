package io.wren.plugin;

import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import io.wren.plugin.parser.nodes.GhostNode;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.xpath.XPath;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.wren.plugin.WrenLanguage.*;

public class WrenAstUtils {

    // tokens from the grammar
    private static List<TokenIElementType> tokens = PSIElementTypeFactory.getTokenIElementTypes(INSTANCE);
    // rules from the grammar
    private static List<RuleIElementType> rules = PSIElementTypeFactory.getRuleIElementTypes(INSTANCE);

    /**
     * Get the token by id like WrenLexer.{token name}
     */
    public static TokenIElementType tt(int idx) {
        return tokens.get(idx);
    }

    /**
     * Get the token by id like WrenParser.{rule name}
     */
    public static RuleIElementType rt(int idx) {
        return rules.get(idx);
    }


    public static WrenElementHelper help(PsiElement element){
        return new WrenElementHelper(element);
    }

    public static class WrenElementHelper {
        private Optional<PsiElement> elemOpt;
        private PsiElement elem;

        public WrenElementHelper(PsiElement element) {
            this.elem = element;
            this.elemOpt =
                    Optional.of(element)
                            .filter(e -> !(e instanceof GhostNode) && !(e instanceof PsiDirectory));
        }


        public Collection<? extends PsiElement> path(String path) {
            return elemOpt
                    .map(e -> XPath.findAll(INSTANCE, e, path))
                    .orElseGet(Collections::emptyList);
        }
    }
}
