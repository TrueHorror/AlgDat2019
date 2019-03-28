package no.hiof.fredrivo.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.text.Text;

import no.hiof.fredrivo.Main;
import no.hiof.fredrivo.model.Sorteringer;

import java.util.Arrays;
import java.util.Random;

public class SorteringController {

    @FXML
    private TextArea usortertVisningArea,
                     sortertVisningTextArea;

    @FXML
    private TextField antallTallaaSortereTextField;

    @FXML
    private Text timerText,
                 konstantText;

    @FXML
    private Button insertionSortBtn,
                   quicksortBtn,
                   mergeSortBtn,
                   radixsortBtn;

    private long timeMillis;

    private Random r = new Random();


    private int n;
    private final static int MAX_INSETR_N = 100000;
    private final static int MAX_QUICK_AND_MERGE_N = 1000000;
    private final static int MAX_RADIX_N = 1000000;

    private double C;

    private final String INSERTION = "insertion";
    private final String QUICK = "quick";
    private final String MERGE = "merge";
    private final String RADIX = "radix";

    @FXML
    public void initialize(){
        usortertVisningArea.setEditable(false);
        usortertVisningArea.setWrapText(true);

        sortertVisningTextArea.setEditable(false);
        sortertVisningTextArea.setWrapText(true);


        insertionSortBtn.setOnAction(event -> {
            n = Integer.parseInt(antallTallaaSortereTextField.getText());

            executeSorting(n*n, MAX_INSETR_N, INSERTION);

        });

        quicksortBtn.setOnAction(event -> {
            n = Integer.parseInt(antallTallaaSortereTextField.getText());

            executeSorting(n*Math.log(n), MAX_QUICK_AND_MERGE_N, QUICK);
        });

        mergeSortBtn.setOnAction(event -> {
            n = Integer.parseInt(antallTallaaSortereTextField.getText());

            executeSorting(n*Math.log(n), MAX_QUICK_AND_MERGE_N, MERGE);

        });

        radixsortBtn.setOnAction(event -> {
            n = Integer.parseInt(antallTallaaSortereTextField.getText());

            executeSorting(n*String.valueOf(n).length(), MAX_RADIX_N, RADIX);
        });

    }

    private int[] makeArray(int n){

        int A[] = new int[n];

        for (int i = 0; i < n ; i++) {

            A[i] = r.nextInt(2*n);
        }

        return A;
    }

    private int[] updateUsortertVisning(){

        int array[] = makeArray(n);
        usortertVisningArea.setText(Arrays.toString(array));

        return array;
    }

    private void visWarning(){

        Main.visWarningVindu();

    }

    private double beregnKonstant(double workload) {
        return timeMillis / workload;
    }

    private void executeSorting(double workload, int max_n, String method) {

        if(n > max_n){
            visWarning();
        }
        else {
            int array[] = updateUsortertVisning();

            //Starte sorteringsmetode
            timeMillis = System.currentTimeMillis();

            switch (method){
                case "insertion":
                    Sorteringer.insertionSort(array);
                    break;

                case "quick":
                    Sorteringer.quickSort(array, 0, n-1);
                    break;

                case "merge":
                    Sorteringer.mergeSort(array, 0, n-1);
                    break;

                case "radix":
                    Sorteringer.radixSort(array, String.valueOf(n*2).length());
                    break;


            }

            timeMillis = System.currentTimeMillis() - timeMillis;

            timerText.setText(String.valueOf(timeMillis) + " ms");

            sortertVisningTextArea.setText(Arrays.toString(array));
            C = beregnKonstant(workload);
            konstantText.setText(Double.toString(C));
        }

    }

}
