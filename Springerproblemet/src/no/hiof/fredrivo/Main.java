package no.hiof.fredrivo;

import java.util.Scanner;

public class Main {

        // Verdiene som kan lagres på brettett
        private static int FRI = 1, BRUKT = 2, VEI = 3;

        // Størrelse på sjakkbrettet
        private static int n;
        private static int brett[][];
        private static int antallFlytt = 0;
        private static int besøkteRuter[][];

        public static boolean finnVei(int i, int j)
        {
            // Leter rekursivt etter mulige steg springeren kan ta
            //  fra rute (i,j) til rute (n-1,n-1).

            // Bunn i rekursjonen: Ferdig hvis springeren har besøkt alle ruter.
            if (antallFlytt == n*n-1){
                brett[i][j] = VEI;
                besøkteRuter[i][j] = ++antallFlytt;
                return true;
            }

            // Markerer at rute (i,j) nå er oppsøkt
            brett[i][j] = BRUKT;
            besøkteRuter[i][j] = ++antallFlytt;

            //Mulige trekk for springeren:
            // 2 OPP OG 1 VENSTRE
            if (i > 1 && j > 0 && brett[i-2][j-1] == FRI)
            {
                if (finnVei(i-2,j-1))
                {

                    brett[i][j] = VEI;
                    return true;
                }
            }

            // 2 OPP OG 1 HØYRE
            if (i > 1 && j < n-1 && brett[i-2][j+1] == FRI)
            {
                if (finnVei(i-2,j+1))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // 2 HØYRE OG 1 OPP
            if (i > 0 && j < n-2 && brett[i-1][j+2] == FRI)
            {
                if (finnVei(i-1,j+2))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // 2 HØYRE OG 1 NED
            if (i < n-1 && j < n-2 && brett[i+1][j+2] == FRI)
            {
                if (finnVei(i+1,j+2))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // 2 NED OG 1 HØYRE
            if (i < n-2 && j < n-1 && brett[i+2][j+1] == FRI)
            {
                if (finnVei(i+2,j+1))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // 2 NED OG 1 VENSTRE
            if (i < n-2 && j > 0 && brett[i+2][j-1] == FRI)
            {
                if (finnVei(i+2,j-1))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // 2 VENSTRE OG 1 NED
            if (i < n-1 && j > 1 && brett[i+1][j-2] == FRI)
            {
                if (finnVei(i+1,j-2))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            // 2 VENSTRE OG 1 OPP
            if (i > 0 && j > 1 && brett[i-1][j-2] == FRI)
            {
                if (finnVei(i-1,j-2))
                {
                    brett[i][j] = VEI;
                    return true;
                }
            }

            antallFlytt--;
            brett[i][j] = FRI;
            besøkteRuter[i][j] = 0;

            return false;

        }

        public static void main(String[] args)
        {
            Scanner scanner = new Scanner(System.in);

            // Leser størrelsen på brettet
            // Ved n > 6 går det veldig sakte grunnet finnVei metoden kjører brute-force.
            System.out.print("størrelse på sjakkbrettet?: ");
            n = scanner.nextInt();

            //Leser start posisjon på x og y (i,j)
            System.out.println("Hvor skal springeren begynne på ett brett som er " + n + " x " + n + "?");
            System.out.print("x?: ");
            int startX = scanner.nextInt();
            System.out.print("y?: ");
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

            // Starter metode for springerproblemet
            // Setter springeren på startposisjon - 1 for å holde seg inne i Arrayen.
            boolean funnetVei = finnVei(startX-1, startY-1);

            // Skriver ut sjakkbrett
            System.out.println();
            skrivUtBesøkteRuter();

            System.out.println();
            if (!funnetVei)
                System.out.println("Ikke mulig for springeren å besøke alle ruter ved satt startposisjon og/eller brettstørrelse.");

        }

        private static void skrivUtBesøkteRuter(){
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    System.out.print(besøkteRuter[i][j] + "\t");
                }
                System.out.println();
            }
        }

}
