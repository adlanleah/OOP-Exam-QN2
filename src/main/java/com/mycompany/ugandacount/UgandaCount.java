
package com.mycompany.ugandacount;

/**
 * Library Management System - Uganda Word Counter
 * This program helps librarians count occurrences of "Uganda" in book descriptions
 * Case-insensitive matching is implemented for accurate counting
 */

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UgandaCount {
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Display program header
            System.out.println("=".repeat(60));
            System.out.println("    LIBRARY MANAGEMENT SYSTEM");
            System.out.println("    Uganda Word Counter for Book Descriptions");
            System.out.println("=".repeat(60));
            
            // Prompt librarian for book description
            System.out.println("Dear Librarian,");
            System.out.println("Please enter the book description below:");
            System.out.println("(Press Enter twice when finished, or type 'END' on a new line)");
            System.out.println("-".repeat(60));
            
            // Read multi-line book description
            StringBuilder bookDescription = new StringBuilder();
            String line;
            
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                
                // Check for end conditions
                if (line.equalsIgnoreCase("END") || 
                    (line.isEmpty() && bookDescription.toString().trim().isEmpty() == false)) {
                    break;
                }
                
                bookDescription.append(line).append(" ");
            }
            
            String description = bookDescription.toString().trim();
            
            // Validate input
            if (description.isEmpty()) {
                System.out.println("  No book description entered. Please try again.");
                return;
            }
            
            // Count occurrences of "Uganda" (case-insensitive)
            int ugandaCount = countUgandaOccurrences(description);
            
            // Display results
            displayResults(description, ugandaCount);
            
        } catch (Exception e) {
            System.err.println(" Error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Counts occurrences of "Uganda" in the given text (case-insensitive)
     * Uses multiple approaches for comprehensive counting
     * 
     * @param text The book description text
     * @return Number of times "Uganda" appears
     */
    private static int countUgandaOccurrences(String text) {
        // Method 1: Using Regular Expression (most accurate for word boundaries)
        Pattern pattern = Pattern.compile("\\bUganda\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        
        return count;
    }
    /**
     * Alternative counting method using string manipulation
     * (Included for educational purposes - shows different approaches)
     * 
     * @param text The book description text
     * @return Number of times "Uganda" appears
     */
    
    /**
     * Displays the results in a formatted manner
     * 
     * @param description The original book description
     * @param count Number of Uganda occurrences
     */
    private static void displayResults(String description, int count) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    ANALYSIS RESULTS");
        System.out.println("=".repeat(60));
        
        // Display book description with character/word count
        System.out.println(" BOOK DESCRIPTION:");
        System.out.println("-".repeat(30));
        System.out.println(description);
        System.out.println("-".repeat(30));
        
        // Display statistics
        String[] words = description.split("\\s+");
        System.out.printf("DESCRIPTION STATISTICS:%n");
        System.out.printf("   Total Characters: %d%n", description.length());
        System.out.printf("   Total Words: %d%n", words.length);
        System.out.printf("   Total Sentences: %d%n", countSentences(description));
        
        // Display Uganda count results
        System.out.println("\n🇺🇬 UGANDA OCCURRENCE ANALYSIS:");
        System.out.printf("   Word 'Uganda' appears: %d time(s)%n", count);
        
        // Provide contextual feedback
        switch (count) {
            case 0 -> System.out.println("    The word 'Uganda' was not found in this description.");
            case 1 -> System.out.println("    The word 'Uganda' appears once in this description.");
            default -> System.out.printf("    The word 'Uganda' appears multiple times (%d) in this description.%n", count);
        }
        
        // Show highlighted text (for verification)
        System.out.println("\nHIGHLIGHTED TEXT:");
        System.out.println("-".repeat(30));
        String highlightedText = highlightUganda(description);
        System.out.println(highlightedText);
        
        // Additional analysis
        if (count > 0) {
            double percentage = ((double) count / words.length) * 100;
            System.out.printf("%nADDITIONAL ANALYSIS:%n");
            System.out.printf("   Uganda mentions represent %.2f%% of total words%n", percentage);
            
            if (percentage > 5) {
                System.out.println("   This book appears to have significant Uganda-related content.");
            } else if (percentage > 1) {
                System.out.println("   This book contains some Uganda-related content.");
            } else {
                System.out.println("   This book mentions Uganda but it's not a primary focus.");
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Thank you for using the Library Management System!");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Highlights occurrences of "Uganda" in the text for visual verification
     * 
     * @param text The original text
     * @return Text with Uganda occurrences highlighted
     */
    private static String highlightUganda(String text) {
        return text.replaceAll("(?i)\\bUganda\\b", "**UGANDA**");
    }
    
    /**
     * Counts the number of sentences in the text
     * 
     * @param text The text to analyze
     * @return Number of sentences
     */
    private static int countSentences(String text) {
        // Count sentences based on sentence-ending punctuation
        String[] sentences = text.split("[.!?]+");
        return sentences.length > 0 ? sentences.length : 1;
    }
}
