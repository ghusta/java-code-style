package fr.husta.tool.ide.formatting;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * From Hibernate Tools (<code>org.hibernate.tool.ide.formatting.JavaFormatter</code>).
 */
public class JavaFormatter {

    private CodeFormatter codeFormatter;

    public JavaFormatter() {
        this(null);
    }

    public JavaFormatter(Map<Object, Object> settings) {
        if (settings == null) {
            // if no settings run with jdk 5 as default
            settings = new HashMap<>();
            settings.put(JavaCore.COMPILER_SOURCE, "1.5");
            settings.put(JavaCore.COMPILER_COMPLIANCE, "1.5");
            settings.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, "1.5");
        }

        this.codeFormatter = ToolFactory.createCodeFormatter(settings);
    }

    /**
     * Throws exception if not possible to read or write the file.
     * Returns true if formatting went ok; returns false if the formatting could not finish because of errors in the input.
     *
     * <p>
     *     <b>Note</b> : very similar to {@link org.eclipse.jdt.core.formatter.CodeFormatterApplication#formatFile(File, CodeFormatter)}.
     * </p>
     * @param file
     * @return
     */
    public boolean formatFile(File file) throws ExporterException {
        IDocument doc = new Document();
        try {
            String contents = new String(org.eclipse.jdt.internal.compiler.util.Util.getFileCharContent(file, null));
            doc.set(contents);
            TextEdit edit = codeFormatter.format(CodeFormatter.K_COMPILATION_UNIT, contents, 0, contents.length(), 0, null);
            if (edit != null) {
                edit.apply(doc);
            } else {
                return false; // most likely syntax error
            }

            // write the file
            try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
                out.write(doc.get());
                out.flush();
            }
            return true;
        } catch (IOException | BadLocationException e) {
            throw new ExporterException("Could not format " + file, e);
        }
    }

}
