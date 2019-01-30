package no.hiof.fredrivo;

public class Main {


    public static void main(String[] args) {
        int tidssteg;
        int gjennomsnittLanding;
        int gjennomsnittAvta;

        System.out.print("Hvor mange tidssteg skal gjennomføres?: ");
        tidssteg = Integer.parseInt(System.console().readLine());

        System.out.print("Hvor mange gjennomsnittlige landinger?: ");
        gjennomsnittLanding = Integer.parseInt(System.console().readLine());

        System.out.print("Hvor mange gjennomsnittlige avganger?: ");
        gjennomsnittAvta = Integer.parseInt(System.console().readLine());

        simuler(tidssteg,gjennomsnittLanding,gjennomsnittAvta);

    }

    private static void simuler(int tidssteg, int gjennomsnittLanding, int gjennomsnittAvta) {

    }


    public void samletInfo(){

    }
}
