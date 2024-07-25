package com.batchTondeuse.tondeuse.model;

public class Tondeuse {
    int x, y;
    char orientation;

    public Tondeuse(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    // Méthode pour faire pivoter la tondeuse
    public void pivoter(char direction) {
        // Utilisation d'un string pour les orientations selon la direction des aiguilles de montre
        String orientations = "NESW";
        int indexActuel = orientations.indexOf(orientation);
        if (direction == 'G') {
            orientation = orientations.charAt((indexActuel + 3) % 4);
        } else if (direction == 'D') {
            orientation = orientations.charAt((indexActuel + 1) % 4);
        }
    }

    // Méthode pour faire avancer la tondeuse
    public void avancer(int maxX, int maxY) {
        switch (orientation) {
            case 'N': if (y < maxY) y++; break;
            case 'E': if (x < maxX) x++; break;
            case 'S': if (y > 0) y--; break;
            case 'W': if (x > 0) x--; break;
        }
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}