package no.hiof.fredrivo;

import java.util.LinkedList;
import java.util.Queue;
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
//        Initier begge køene til å være tommE
        LinkedList<Fly> tarAv = new LinkedList<>();
        LinkedList<Fly> lander = new LinkedList<>();

//        For hver tidssteg i simuleringen
        for (int i = 0; i < tidssteg; i++ ){
//        Trekk et tilfeldig antall nye fly som kommer for å lande
            int antallLandinger = getPoissonRandom(gjennomsnittLanding);
        }

//        For hvert nytt fly som kommer for å lande
//        Hvis landingskøen er full
//        Avvis det nye flyet (henvis til annen flyplass)
//        ellers
//        Sett det nye flyet sist i landingskøen
//
//        Trekk et tilfeldig antall nye fly som kommer for å ta av
//
//        For hvert nytt fly som kommer for å ta av
//        Hvis avgangskøen er full
//        Avvis det nye flyet (avgang må prøves senere)
//        ellers
//        Sett det nye flyet sist i avgangskøen
//
//        Hvis landingskøen ikke er tom
//        Ta ut første fly i landingskøen og la det få lande
//        ellers hvis avgangskøen ikke er tom
//        Ta ut første fly i avgangskøen og la det få ta av
//        ellers
//        Flyplassen er tom for fly
//
//        Skriv til slutt ut resultater som gj.snittlig ventetid etc.
    }


    public void samletInfo(){

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
