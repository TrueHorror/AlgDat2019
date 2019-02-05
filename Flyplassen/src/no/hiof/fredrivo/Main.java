package no.hiof.fredrivo;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        int tidssteg;
        float gjennomsnittLanding;
        float gjennomsnittAvta;

        Scanner scanner = new Scanner(System.in);


        // leser inn verdier fra brukerinput
        System.out.print("Hvor mange tidssteg skal gjennomføres?: ");
        tidssteg = scanner.nextInt();

        System.out.print("Hvor mange gjennomsnittlige landinger?: ");
        gjennomsnittLanding = scanner.nextFloat();

        System.out.print("Hvor mange gjennomsnittlige avganger?: ");
        gjennomsnittAvta = scanner.nextFloat();

        simuler(tidssteg,gjennomsnittLanding,gjennomsnittAvta);

    }

    private static void simuler(int tidssteg, float gjennomsnittLanding, float gjennomsnittAvta) {
        //Initier begge køene til å være tommE
        LinkedList<Fly> avgangskø = new LinkedList<>();
        LinkedList<Fly> landingskø = new LinkedList<>();

        //Variabler for antall fly i systemet
        int flyteller = 0;
        int landinger = 0;
        int avganger = 0;
        int avvist = 0;
        int landingsKlar = 0;
        int avgangsKlar = 0;
        int totalVentetid = 0;


        //For hver tidssteg i simuleringen
        for (int i = 1; i <= tidssteg; i++ ){

            //Trekk et tilfeldig antall nye fly som kommer for å lande
            int antallLandinger = getPoissonRandom(gjennomsnittLanding);
            System.out.println("Tidssteg " + i + ": ");

            //For hvert nytt fly som kommer for å lande
            for (int j = 0; j <= antallLandinger; j++){

                //Hvis landingskøen er full
                //Avvis det nye flyet (henvis til annen flyplass)
                if (landingskø.size() == 10){
                    System.out.println("Landingskøen er full, land en annen plass takk.");
                    avvist++;
                }

                //ellers
                //Sett det nye flyet sist i landingskøen
                else {
                    flyteller++;
                    landingskø.add(new Fly(flyteller,i));
                    System.out.println("Fly " + flyteller + " har annkommet landingskøen.");
                    landingsKlar++;
                }
            }

            //Trekk et tilfeldig antall nye fly som kommer for å ta av
            int antallAvganger = getPoissonRandom(gjennomsnittAvta);

            //For hvert nytt fly som kommer for å ta av
            for (int k = 0; k <= antallAvganger; k++){

                //Hvis avgangskøen er full
                //Avvis det nye flyet (avgang må prøves senere)
                if (avgangskø.size() == 10){
                    System.out.println("Avgangskøen er full, venligst kom tilbake når den ikke er det.");
                    avvist++;
                }

                //ellers
                //Sett det nye flyet sist i avgangskøen
                else {
                    flyteller++;
                    avgangskø.add(new Fly(flyteller,i));
                    System.out.println("Fly " + flyteller + " har annkommet avgangskøen.");
                    avgangsKlar++;

                }

            }



            //Hvis landingskøen ikke er tom
            //Ta ut første fly i landingskøen og la det få lande
            if (landingskø.size() != 0){
                System.out.println("Fly " + landingskø.getFirst().getId() + " har landet.");
                totalVentetid += (i - landingskø.getFirst().getInngangSteg());
                landingskø.removeFirst();
                landinger++;

            }

            //ellers hvis avgangskøen ikke er tom
            //Ta ut første fly i avgangskøen og la det få ta av
            else if (avgangskø.size() != 0){
                System.out.println("Fly " + avgangskø.getFirst().getId() + " tar av:");
                totalVentetid += (i - landingskø.getFirst().getInngangSteg());
                avgangskø.removeFirst();
                avganger++;
            }

            //ellers
            //Flyplassen er tom for fly
            else {
                System.out.println("Flyplassen er tom for fly.");
            }






        }

        //Skriv til slutt ut resultater som gj.snittlig ventetid etc.
        samletInfo(flyteller, landinger, avganger, avvist, landingsKlar, avgangsKlar, totalVentetid, tidssteg);


    }




    public static void samletInfo(int flyteller, int landinger, int avganger, int avvist, int landingsKlar, int avgangsKlar, int totalVentetid, int tidssteg){
        float gjennomsnittligVentetid = totalVentetid/tidssteg;
        System.out.println("\n Generell informasjon etter simulering: \n" +
                "Total antall fly i systemet: " + flyteller + "\n" +
                "Total antall landinger: " + landinger + "\n" +
                "Total antall avganger: " + avganger + "\n" +
                "Antall avviste fly: " + avvist + "\n" +
                "Antall fly klare til landing: " + landingsKlar + "\n" +
                "Antall fly klare til avgang: " + avgangsKlar + "\n" +
                "Gjennomsnittlig ventetid: " + gjennomsnittligVentetid);

    }

    private static int getPoissonRandom(double mean)
    {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do
        {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
