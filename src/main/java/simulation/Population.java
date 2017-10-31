package simulation;

import people.Gender;
import people.Person;
import people.Policyholder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Population {

    ArrayList<Person> population = new ArrayList<Person>();

    public Population(ArrayList<Person> prev) {
        ArrayList<Person> newPOP = new ArrayList<Person>();
        newPOP.addAll(prev);
        population = newPOP;
    }

    public Population(String pth) {
        int count = 0;
        int pid = 0;
        int age = 0;
        Gender g = Gender.Male;
        int face = 0;
        int term = 0;
        int cd = 0;
        int prem = 0;
        int cltPrem = 0;
        LocalDate dob;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(pth)));
            String line = "";
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                try {
                    String[] row = line.split(",");
                    pid = Integer.parseInt(row[0]);
                    age = Integer.parseInt(row[1]);
                    g = g.parse(row[2]);
                    dob = LocalDate.parse(row[3]);
                    face = Integer.parseInt(row[4]);
                    term = Integer.parseInt(row[5]);
                    cd = Integer.parseInt(row[6]);
                    prem = Integer.parseInt(row[7]);
                    cltPrem = Integer.parseInt(row[8]);

                    Policyholder p = new Policyholder(g, age, pid, dob, cltPrem, prem, face, cd, term);
                    population.add(p);
                    count++;
                } catch (Exception e) {
                    System.out.println("Could not parse the person record at " + count);
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println("Count not fine the input file at " + pth);
            f.printStackTrace();
            System.exit(1);
        } catch (IOException io) {
            System.out.println("Could not skip the header row");
            io.printStackTrace();
            System.exit(1);
        }
    }

    public Population updatePopulation(LocalDate dt) {
        ArrayList<Person> newPeeps = new ArrayList<Person>();
        Population updatedPop;
        Iterator popCyc = this.population.iterator();
        while (popCyc.hasNext()) {
            Policyholder pol = (Policyholder) popCyc.next();
            newPeeps.add(pol.updatePerson(dt));
        }
        updatedPop = new Population(newPeeps);
        return updatedPop;

    }

    @Override
    public String toString() {
        String str = "";
        Iterator popCyc = this.population.iterator();
        while (popCyc.hasNext()) {
            Policyholder ph = (Policyholder) popCyc.next();
            str = str.concat(ph.toString()) + "|";
        }
        return str;
    }
}
