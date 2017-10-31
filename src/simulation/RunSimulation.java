package simulation;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class RunSimulation {

    static ArrayList<String> simOut = new ArrayList<String>();
    static LocalDate simSD;
    static LocalDate simED;

    public static Population updatePopulation(Population pop, LocalDate dt) {
        // will call the update person function on the full population
        Population up = pop.updatePopulation(dt);
        return up;
    }

    public static void runSimulation(Population pop, LocalDate sd, LocalDate ed) {
        Population currentPop = pop;
        int t = getSimLength(sd, ed);

        for (int j = 1; j <= t; j++) {
            currentPop = updatePopulation(currentPop, sd.plusMonths(j));
            simOut.add("time " + j + ": " + currentPop.toString());
        }
    }

    private static int getSimLength(LocalDate sd, LocalDate ed) {
        int years = (ed.getYear() - sd.getYear());
        int moAdj = ed.getMonthValue() - sd.getMonthValue();
        return years * 12 + moAdj;
    }

    private static void printOutput(String outPth, ArrayList outData) {
        try {
            FileWriter fw = new FileWriter(outPth, true);
            PrintWriter op = new PrintWriter(fw);
            Iterator runPop = outData.iterator();
            while (runPop.hasNext()) {
                op.println((String) runPop.next());
            }
            op.close();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException i) {
            i.printStackTrace();
            System.exit(1);
        }

    }

    public static void main(String[] args) {
        simSD = LocalDate.parse(args[0]); //note this needs to be like 2007-12-31
        simED = LocalDate.parse(args[1]); //note this needs to be like 2007-12-31
        Population initPop = new Population(args[2]);
        runSimulation(initPop, simSD, simED);
        printOutput(args[3], simOut);
    }
}
