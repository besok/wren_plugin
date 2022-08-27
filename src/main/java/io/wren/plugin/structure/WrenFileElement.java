package io.wren.plugin.structure;

import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

import static io.wren.plugin.WrenAstUtils.ast;

public class WrenFileElement extends BaseStructureViewElem {

    private PsiFile psiFile;

    public WrenFileElement(PsiFile element) {
        super(element);
        psiFile = element;
    }

    // todo create hierarchy when it comes to add the deep structure (implement the method get children)
    @Override
    public TreeElement @NotNull [] getChildren() {

        TreeElement[] classes = ast(psiFile)
                .path("/script/fileAtom/classDefinition")
                .stream()
                .map(BaseStructureViewElem::new)
                .toArray(TreeElement[]::new);

        TreeElement[] fns = ast(psiFile)
                .path("/script/fileAtom/function")
                .stream()
                .map(BaseStructureViewElem::new)
                .toArray(TreeElement[]::new);

        return Stream.concat(Stream.of(classes), Stream.of(fns)).toArray(TreeElement[]::new);
    }
}
