package com.batchTondeuse.tondeuse.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TondeuseProcessorTest {

    private TondeuseProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new TondeuseProcessor();
    }

    @Test
    void testProcessFirstLine() throws Exception {
        String input = "5 5";
        String result = processor.process(input);
        assertNull(result);
    }

    @Test
    void testProcessPositionLine() throws Exception {
        // First, process the pelouse dimensions
        processor.process("5 5");

        String input = "1 2 N";
        String result = processor.process(input);
        assertNull(result);
    }

    @Test
    void testProcessInstructionLine() throws Exception {
        // Set up the pelouse and initial position
        processor.process("5 5");
        processor.process("1 2 N");

        String input = "GAGAGAGAA";
        String result = processor.process(input);
        assertEquals("1 3 N", result);
    }

    @Test
    void testIsPremiereLigne() {
        assertTrue(processor.isPremiereLigne("5 5"));
        assertFalse(processor.isPremiereLigne("1 2 N"));
        assertFalse(processor.isPremiereLigne("GAGAGAGAA"));
    }

    @Test
    void testFullSequence() throws Exception {
        assertNull(processor.process("5 5"));
        assertNull(processor.process("1 2 N"));
        assertEquals("1 3 N", processor.process("GAGAGAGAA"));
        assertNull(processor.process("3 3 E"));
        assertEquals("5 1 E", processor.process("AADAADADDA"));
    }

    @Test
    void testInvalidInput() {
        assertThrows(NumberFormatException.class, () -> processor.process("A B"));
    }
}