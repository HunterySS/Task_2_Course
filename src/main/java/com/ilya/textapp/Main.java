package com.ilya.textapp;

import com.ilya.textapp.entity.Clause;
import com.ilya.textapp.entity.DocumentRoot;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.parser.ParserChainFactory;
import com.ilya.textapp.parser.impl.TextParser;
import com.ilya.textapp.service.ClauseSorterService;
import com.ilya.textapp.service.TextAnalyzerService;
import com.ilya.textapp.service.TokenSwapperService;
import com.ilya.textapp.util.FileReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final char TARGET_LETTER = 'e';

    public static void main(String[] args) {
        LOGGER.info("=== Application started ===");

        try {
            String filePath = "data/input.txt";
            String text = FileReaderUtil.readFile(filePath);

            TextParser parser = ParserChainFactory.createChain();
            CompositeComponent document = parser.parse(text);

            if (document instanceof DocumentRoot) {
                DocumentRoot root = (DocumentRoot) document;

                printSeparator();
                System.out.println("=== TEXT STATISTICS ===");
                System.out.println("Total letters in text: " + document.countLetters());
                System.out.println("Total symbols in text: " + document.countSymbols());

                printSeparator();
                System.out.println("=== OPERATION 1: MAX SENTENCES WITH SAME WORDS ===");
                TextAnalyzerService analyzer = new TextAnalyzerService();
                int maxSentences = analyzer.findMaxSentencesWithSameWords(document);
                System.out.println("Result: " + maxSentences);

                printSeparator();
                System.out.println("=== OPERATION 2: SORT CLAUSES BY LETTER '" + TARGET_LETTER + "' ===");
                ClauseSorterService sorter = new ClauseSorterService();
                List<Clause> sortedClauses = sorter.sortClausesByLetterCount(document, TARGET_LETTER);

                System.out.println("Clauses sorted by occurrences of letter '" + TARGET_LETTER + "' (ascending):");
                for (int i = 0; i < sortedClauses.size(); i++) {
                    System.out.println((i + 1) + ". " + sortedClauses.get(i).restore());
                }

                printSeparator();
                System.out.println("=== OPERATION 3: SWAP FIRST AND LAST TOKEN ===");
                System.out.println("BEFORE SWAP:");
                System.out.println(document.restore());

                TokenSwapperService swapper = new TokenSwapperService();
                swapper.swapFirstAndLastToken(document);

                System.out.println("\nAFTER SWAP:");
                System.out.println(document.restore());
            }

            LOGGER.info("=== Application finished successfully ===");

        } catch (TextProcessingException e) {
            LOGGER.error("Application error: {}", e.getMessage(), e);
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printSeparator() {
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
}