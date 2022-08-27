package io.wren.plugin;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import io.wren.plugin.parser.nodes.GhostNode;
import io.wren.plugin.parser.nodes.Id;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.intellij.adaptor.xpath.XPath;

import java.util.*;

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

    public static boolean isRule(ASTNode node, int ruleIdx) {
        if (node.getElementType() instanceof RuleIElementType type) {
            return type.getRuleIndex() == ruleIdx;
        } else return false;
    }

    public static WrenElementHelper ast(PsiElement element) {
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

        /**
         * The method pursues to find the first encountered [[IdNode]] instance.
         * The method assumes that the first id encountered at the current level or the levels below will be
         * the unique identifier of the current element.
         * return the first encountered [[IdNode]] instance or none
         */
          public Optional<Id> id(){
              return elemOpt.flatMap(e -> {
                  if(e instanceof Id id) return Optional.of(id);
                  else if(e instanceof ANTLRPsiNode node)
                      return Arrays
                              .stream(node.getChildren())
                              .map(WrenAstUtils::ast)
                              .map(WrenElementHelper::id)
                              .flatMap(Optional::stream)
                              .findFirst();
                  else return Optional.empty();
              });
          }

    }
}
