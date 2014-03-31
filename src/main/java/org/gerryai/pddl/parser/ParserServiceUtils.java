package org.gerryai.pddl.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.gerryai.pddl.parser.antlr.PDDL31Lexer;
import org.gerryai.pddl.parser.antlr.PDDL31Parser;
import org.gerryai.pddl.parser.logic.LogicStackHandler;

import java.io.IOException;
import java.io.InputStream;

/**
 * Helper functions for handling parser input, etc.
 */
public class ParserServiceUtils {

    /**
     * Create an {@link ANTLRInputStream} from a file.
     * @param inputStream the InputStream to read
     * @return the ANTLR input stream
     * @throws java.io.IOException if there was a problem reading the input stream
     */
    public CharStream createInputStream(final InputStream inputStream) throws IOException {
        return new ANTLRInputStream(inputStream);
    }

    /**
     * Create a {@link TokenStream} from a {@link Lexer}.
     * @param lexer the lexer to use
     * @return the token stream
     */
    public TokenStream createTokenStream(final Lexer lexer) {
        return new CommonTokenStream(lexer);
    }

    /**
     * Create a Lexer from the given input stream.
     * @param inputStream the input stream to process
     * @return the lexer
     */
    public Lexer createLexer(final CharStream inputStream) {
        return new PDDL31Lexer(inputStream);
    }

    /**
     * Create a Parser from the given token stream.
     * @param tokenStream the toek stream to process
     * @return the parser
     */
    public PDDL31Parser createParser(final TokenStream tokenStream) {
        return new PDDL31Parser(tokenStream);
    }

    /**
     * Get the domain context from the given parser.
     * @param parser the parser to use
     * @return the domain context
     */
    public PDDL31Parser.DomainContext getDomainContext(final PDDL31Parser parser) {
        return parser.domain();
    }

    /**
     * Create a parse tree walker.
     * This is primarily to facilitate unit testing.
     * @return a new parse tree walker
     */
    public ParseTreeWalker createParseTreeWalker() {
        return new ParseTreeWalker();
    }

    /**
     * Create the listener for extracting the {@link org.gerryai.pddl.model.Domain} from the parser.
     * @return the listener to apply when walking the parse tree
     */
    public ExtractDomainListener createExtractDomainListener() {
        return new ExtractDomainListener(new LogicStackHandler());
    }
}
