package com.batchTondeuse.tondeuse.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TondeuseTest {

    private Tondeuse tondeuse;

    @BeforeEach
    public void setUp() {
        // Initialize the tondeuse at position (0, 0) facing North
        tondeuse = new Tondeuse(0, 0, 'N');
    }

    @Test
    public void testPivoterGauche() {
        // Rotate left (counter-clockwise)
        tondeuse.pivoter('G');
        assertEquals('W', tondeuse.orientation);

        tondeuse.pivoter('G');
        assertEquals('S', tondeuse.orientation);

        tondeuse.pivoter('G');
        assertEquals('E', tondeuse.orientation);

        tondeuse.pivoter('G');
        assertEquals('N', tondeuse.orientation);
    }

    @Test
    public void testPivoterDroite() {
        // Rotate right (clockwise)
        tondeuse.pivoter('D');
        assertEquals('E', tondeuse.orientation);

        tondeuse.pivoter('D');
        assertEquals('S', tondeuse.orientation);

        tondeuse.pivoter('D');
        assertEquals('W', tondeuse.orientation);

        tondeuse.pivoter('D');
        assertEquals('N', tondeuse.orientation);
    }

    @Test
    public void testAvancer() {
        // Move forward in the direction the tondeuse is facing
        tondeuse.avancer(5, 5);
        assertEquals(0, tondeuse.x);
        assertEquals(1, tondeuse.y);

        tondeuse.pivoter('D'); // Face East
        tondeuse.avancer(5, 5);
        assertEquals(1, tondeuse.x);
        assertEquals(1, tondeuse.y);

        tondeuse.pivoter('D'); // Face South
        tondeuse.avancer(5, 5);
        assertEquals(1, tondeuse.x);
        assertEquals(0, tondeuse.y);

        tondeuse.pivoter('D'); // Face West
        tondeuse.avancer(5, 5);
        assertEquals(0, tondeuse.x);
        assertEquals(0, tondeuse.y);
    }

    @Test
    public void testAvancerLimite() {
        // Move forward but do not exceed the limits
        tondeuse = new Tondeuse(5, 5, 'N');
        tondeuse.avancer(5, 5);
        assertEquals(5, tondeuse.x);
        assertEquals(5, tondeuse.y);

        tondeuse.pivoter('D'); // Face East
        tondeuse.avancer(5, 5);
        assertEquals(5, tondeuse.x);
        assertEquals(5, tondeuse.y);

        tondeuse.pivoter('D'); // Face South
        tondeuse.avancer(5, 5);
        assertEquals(5, tondeuse.x);
        assertEquals(4, tondeuse.y);

        tondeuse.pivoter('D'); // Face West
        tondeuse = new Tondeuse(0, 0, 'W');
        tondeuse.avancer(5, 5);
        assertEquals(0, tondeuse.x);
        assertEquals(0, tondeuse.y);
    }

    @Test
    public void testToString() {
        // Check the string representation of the tondeuse
        assertEquals("0 0 N", tondeuse.toString());

        tondeuse.pivoter('D');
        assertEquals("0 0 E", tondeuse.toString());

        tondeuse.avancer(5, 5);
        assertEquals("1 0 E", tondeuse.toString());
    }
}