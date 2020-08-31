/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.logic;

/**
 *
 * @author ahill
 */
public class LogicExerciseE {

    /**
     * Given the following chart, return the correct color designation 
     * given measured wavelength, frequency and photonic energy. 
     * If it doesn't fall within correct bands, return "Unknown" instead. 
     * If it falls exactly within a band transition, 
     * return a compound color, with the longer wavelength color first. 
     * 
     * 	Color	Wavelength	Frequency	Photon energy
     * 	Violet	380–450 nm	668–789 THz	2.75–3.26 eV
     * 	Blue	450–495 nm	606–668 THz	2.50–2.75 eV
     * 	Green	495–570 nm	526–606 THz	2.17–2.50 eV
     * 	Yellow	570–590 nm	508–526 THz	2.10–2.17 eV
     * 	Orange	590–620 nm	484–508 THz	2.00–2.10 eV
     * 	Red	620–750 nm	400–484 THz	1.65–2.00 eV
     *
     * Ex:
     * whatColor( 575, 510, 2.15 ) ->  "Yellow"
     * whatColor( 449, 670, 3.00 ) ->  "Violet"
     * whatColor( 621, 475, 16.5 ) ->  "Unknown"
     * whatColor( 590, 508, 2.05 ) ->  "Orange"
     * whatColor( 570, 526, 2.17 ) ->  "Yellow-Green"
     * @param waveLengthNM
     * @param frequencyTHZ
     * @param photonicEnergyEV
     * @return String color
     */
    public static String whatColor(int waveLengthNM, int frequencyTHZ, double photonicEnergyEV) {
        throw new UnsupportedOperationException("Code not yet written...!");
    }

}
