package no.hiof.fredrivo;

import java.util.Random;
import java.util.Scanner;

public class Main {

        // Verdiene som kan lagres på brettett
        private static int FRI = 1, STENGT = 0, BRUKT = 2, VEI = 3;

        // Størrelse på sjakkbrettet
        private static int n;
        private static int brett[][];

        public static boolean finnVei(int i, int j)
        {
            // Leter rekursivt etter en vei gjennom labyrinten fra rute
            // (i,j) til rute (n-1,n-1).
            //
            // Returnerer true hvis vi fant en slik vei, false ellers

            // Bunn i rekursjonen: Ferdig hvis vi er i nedre hÃ¸yre hjÃ¸rne
            if (i == n-1 && j == n-1)
            // SPRINGERPROBLEMET mÃ¥ her ha et annet stoppkriterium
            {
                // Markerer at siste rute i labyrinten ligger pÃ¥ veien som
                // er funnet, og returnerer true
                brett[i][j] = VEI;
                return true;
            }

            // Markerer at rute (i,j) nÃ¥ er oppsÃ¸kt
            brett[i][j] = BRUKT;
            // I SPRINGERPROBLEMET mÃ¥ vi her i tillegg lagre bÃ¥de antall
            // flytt som er gjort frem til nÃ¥, og hvike flytt som er gjort
            // for Ã¥ komme hit.

            // PrÃ¸ver alle fire mulige lovlige veier videre fra rute(i,j):
            // HÃ˜YRE, NED, VENSTRE, OPP

            // For Ã¥ lÃ¸se SPRINGERPROBLEMET, mÃ¥ koden her utvides til Ã¥
            // hÃ¥ndtere alle de Ã¥tte mulige lovlige stegene som en
            // springer kan ta fra rute (i,j)

            // HÃ˜YRE
            // Sjekker om det er lovlig Ã¥ gÃ¥ til hÃ¸yre
            if (j < n-1 && brett[i][j+1] == FRI)
            {
                // PrÃ¸ver Ã¥ finne vei videre rekursivt fra hÃ¸yre naborute
                if (finnVei(i,j+1))
                {
                    // Her vet vi at det ble funnet en vei gjennom
                    // labyrinten fra rute (i,j). Merker av at (i,j)
                    // ligger pÃ¥ denne veien og stopper deretter videre
                    // leting etter vei ved Ã¥ returnere true

                    brett[i][j] = VEI;
                    return true;
                }
            }

            // NED
            if (i < n-1 && brett[i+1][j] == FRI)
            {
                if (finnVei(i+1,j))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // VENSTRE
            if (j > 0 && brett[i][j-1] == FRI)
            {
                if (finnVei(i,j-1))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // OPP
            if (i > 0 && brett[i-1][j] == FRI)
            {
                if (finnVei(i-1,j))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // Hvis vi kommer hit i koden, fantes det ingen vei gjennom
            // labyrinten fra rute (i,j), returnerer false

            return false;

            // I SPRINGERPROBLEMET må dette siste tilfellet, der vi ikke
            // finner noen lovlig løsning med start i rute (i,j),
            // behandles litt anderledes. I labyrinten er det ingen vits i
            // å gå tilbake til en rute der vi har vært før. I
            // springerproblemet er det ikke slik, der må vi nå markere at
            // ruten er blitt ledig igjen, slik at den kan brukes på nytt
            // i senere forsøk på å bygge ut en løsning. Øvrig
            // datastruktur som brukes til å lagre løsningen må også
            // oppdateres slik at dette steget som ikke ledet til løsning
            // blir fjernet.
        }

        public static void main(String[] args)
        {
            Scanner scanner = new Scanner(System.in);

            // Leser størrelsen på brettet
            System.out.print("størrelse på sjakkbrettet?: ");
            n = scanner.nextInt();

            // Oppretter 2D-tabell som lagrer sjakkbrettet
            brett = new int[n][n];


            // Leter etter vei fra øvre venstre hjørne

            boolean funnetVei = finnVei(0, 0);

            // Skriver ut labyrinten (og evt. funnet vei)
            System.out.println();
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (brett[i][j] == VEI)
                        System.out.print('*');
                    else
                        System.out.print(brett[i][j]);
                }
                System.out.println("");
            }

            System.out.println("");
            if (!funnetVei)
                System.out.println("Fant ingen vei gjennom labyrinten");

        }

}
