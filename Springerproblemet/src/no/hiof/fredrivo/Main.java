package no.hiof.fredrivo;

import java.util.Random;
import java.util.Scanner;

public class Main {

        // Verdiene som kan lagres på brettett
        private static int FRI = 1, STENGT = 0, BRUKT = 2, VEI = 3;

        // Størrelse på sjakkbrettet
        private static int n;
        private static int brett[][];
        private static int antallFlytt = 0;
        private static int besøkteRuter[][];

        public static boolean finnVei(int i, int j)
        {
            // Leter rekursivt etter en vei gjennom labyrinten fra rute
            // (i,j) til rute (n-1,n-1).
            //
            // Returnerer true hvis vi fant en slik vei, false ellers

            // Bunn i rekursjonen: Ferdig hvis springeren har besøkt alle ruter.
            if (antallFlytt == n*n-1){
                brett[i][j] = VEI;
                besøkteRuter[i][j] = ++antallFlytt;
                return true;
            }

            // Markerer at rute (i,j) nå er oppsøkt
            brett[i][j] = BRUKT;
            besøkteRuter[i][j] = ++antallFlytt;
            // I SPRINGERPROBLEMET må vi her i tillegg lagre både antall
            // flytt som er gjort frem til nå, og hvike flytt som er gjort
            // for å komme hit.


            // For å løse SPRINGERPROBLEMET, må koden her utvides til å
            // håndtere alle de åtte mulige lovlige stegene som en
            // springer kan ta fra rute (i,j)

            // OPP OG VENSTRE
            if (i > 1 && j > 0 && brett[i-2][j-1] == FRI)
            {
                // Prøver å finne vei videre rekursivt fra høyre naborute
                if (finnVei(i-2,j-1))
                {
                    // Her vet vi at det ble funnet en vei gjennom
                    // labyrinten fra rute (i,j). Merker av at (i,j)
                    // ligger på denne veien og stopper deretter videre
                    // leting etter vei ved å returnere true

                    brett[i][j] = VEI;
                    return true;
                }
            }

            // OPP OG HØYRE
            if (i > 1 && j < n-1 && brett[i-2][j+1] == FRI)
            {
                if (finnVei(i-2,j+1))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // HØYRE OG OPP
            if (i > 0 && j < n-2 && brett[i-1][j+2] == FRI)
            {
                if (finnVei(i-1,j+2))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // HØYRE OG NED
            if (i < n-1 && j < n-2 && brett[i+1][j+2] == FRI)
            {
                if (finnVei(i+1,j+2))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // NED OG HØYRE
            if (i < n-2 && j < n-1 && brett[i+2][j+1] == FRI)
            {
                if (finnVei(i+2,j+1))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // NED OG VENSTRE
            if (i < n-2 && j > 0 && brett[i+2][j-1] == FRI)
            {
                if (finnVei(i+2,j-1))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // VENSTRE OG NED
            if (i < n-1 && j > 1 && brett[i+1][j-2] == FRI)
            {
                if (finnVei(i+1,j-2))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // VENSTRE OG OPP
            if (i > 0 && j > 1 && brett[i-1][j-2] == FRI)
            {
                if (finnVei(i-1,j-2))
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

            System.out.println("Hvor skal springeren begynne på ett brett som er " + n + " x " + n + "?");
            System.out.println("x?:");
            int startX = scanner.nextInt();
            System.out.println("y?:");
            int startY = scanner.nextInt();

            // Oppretter 2D-tabell som lagrer sjakkbrettet
            brett = new int[n][n];
            besøkteRuter = new int[n][n];

            //setter alle ruter fri
            for (int i = 0; i<n; i++){
                for (int j = 0; j<n; j++){
                    brett[i][j] = FRI;
                }
            }

            // Leter etter vei fra øvre venstre hjørne

            boolean funnetVei = finnVei(startX, startY);

            // Skriver ut sjakkbrett
            System.out.println();
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                        System.out.print(besøkteRuter[i][j] + "\t");
                }
                System.out.println();
            }

            System.out.println();
            if (!funnetVei)
                System.out.println("Ikke mulig for springeren å besøke alle ruter ved satt startposisjon og/eller brettstørrelse.");

        }

}
