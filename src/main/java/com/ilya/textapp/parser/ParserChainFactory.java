package com.ilya.textapp.parser;

import com.ilya.textapp.parser.impl.TextParser;

public class ParserChainFactory {

    public static TextParser createChain() {
        DocumentParser documentParser = new DocumentParser();
        BlockParser blockParser = new BlockParser();
        ClauseParser clauseParser = new ClauseParser();
        TokenParser tokenParser = new TokenParser();

        documentParser.setNext(blockParser);
        blockParser.setNext(clauseParser);
        clauseParser.setNext(tokenParser);

        return documentParser;
    }
}