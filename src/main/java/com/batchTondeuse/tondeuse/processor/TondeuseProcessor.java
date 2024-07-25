package com.batchTondeuse.tondeuse.processor;

import com.batchTondeuse.tondeuse.model.Tondeuse;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component
public class TondeuseProcessor implements ItemProcessor<String, String> {
    private int largeurPelouse, hauteurPelouse;
    private Tondeuse tondeuseActuelle;
    private boolean isLignePosition = true;

    @Override
    public String process(String ligne) throws Exception {
        System.out.println("Here IM Processor!!!!!!!!!!!!!!!!!");


        boolean isPremiereLigne = isPremiereLigne(ligne);

        if (isPremiereLigne) {
            // Traitement de la première ligne (dimensions de la pelouse)
            String[] dimensions = ligne.split(" ");
            largeurPelouse = Integer.parseInt(dimensions[0]);
            hauteurPelouse = Integer.parseInt(dimensions[1]);
            isPremiereLigne = false;
            return null;
        }

        if (isLignePosition) {
            // Traitement de la ligne de position initiale de la tondeuse
            String[] position = ligne.split(" ");
            tondeuseActuelle = new Tondeuse(
                    Integer.parseInt(position[0]),
                    Integer.parseInt(position[1]),
                    position[2].charAt(0)
            );
            isLignePosition = false;
            return null;
        } else {
            // Traitement de la ligne d'instructions entre avancer 'A' ou pivoter avec G ou D
            for (char instruction : ligne.toCharArray()) {
                if (instruction == 'A') {
                    tondeuseActuelle.avancer(largeurPelouse, hauteurPelouse);
                } else {
                    tondeuseActuelle.pivoter(instruction);
                }
            }
            isLignePosition = true;
            return tondeuseActuelle.toString();
        }
    }

    public boolean isPremiereLigne(String input) {

        String pattern = "^\\d+\\s\\d+$";
        return Pattern.matches(pattern, input);
    }
}