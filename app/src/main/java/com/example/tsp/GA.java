package com.example.tsp;



import javax.net.ssl.HttpsURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GA {

    int popSize;
    double cr; //crossover probability
    double pm; //mutation probability

    ArrayList<TSP.Tour> population;
    ArrayList<TSP.Tour> offspring;

    public GA(int popSize, double cr, double pm) {
        this.popSize = popSize;
        this.cr = cr;
        this.pm = pm;
    }

    public TSP.Tour execute(TSP problem, ArrayList<Integer> indexes) {
        population = new ArrayList<>();
        offspring = new ArrayList<>();

        TSP.Tour best = null;

        for (int i = 0; i < popSize; i++) {

            TSP.Tour newTour = problem.generateTour(indexes);
            problem.evaluate(newTour);
            population.add(newTour);
            if(i==0){
                best = newTour.clone();

            }else{
                if(newTour.distance<best.distance){
                    best = newTour.clone();
                }
            }
        }

        while (problem.getNumberOfEvaluations() < problem.getMaxEvaluations()) {

            //elitizem - poišči najboljšega in ga dodaj v offspring in obvezno uporabi clone()
            while (offspring.size() < popSize) {
                TSP.Tour parent1 = tournamentSelection();
                TSP.Tour parent2 = tournamentSelection();


                //TODO preveri, da starša nista enaka
                if (parent1.path != parent2.path) {
                    if (RandomUtils.nextDouble() < cr) {
                        TSP.Tour[] children = pmx(parent1, parent2);
                        offspring.add(children[0]);
                        if (offspring.size() < popSize)
                            offspring.add(children[1]);
                    } else {
                        offspring.add(parent1.clone());
                        if (offspring.size() < popSize)
                            offspring.add(parent2.clone());
                    }
                }
            }

            for (TSP.Tour off : offspring) {

                if (RandomUtils.nextDouble() < pm) {
                    swapMutation(off);
                }
            }

            //TODO ovrednoti populacijo in shrani najboljšega (best)
            //TSP.Tour bestPath = offspring.get(0); // naredi globalno pa updataj
            for (TSP.Tour off : offspring) {

                problem.evaluate(off);

                if(off.distance<best.distance){
                    best = off.clone();

                }
            }
            //implementacijo lahko naredimo bolj učinkovito tako, da overdnotimo samo tiste, ki so se spremenili (mutirani in križani potomci)

            population = new ArrayList<>(offspring);
            offspring.clear();
        }
        return best;
    }

    private void swapMutation(TSP.Tour off) {
        //izvedi mutacijo
        int rand_int1 = RandomUtils.nextInt(0, off.path.length);
        int rand_int2 = RandomUtils.nextInt(0, off.path.length);

        while (rand_int1 == rand_int2) {
            rand_int2 = RandomUtils.nextInt(0, off.path.length);
        }

        TSP.City temp1 = off.getPath().clone()[rand_int1];
        TSP.City temp2 = off.getPath().clone()[rand_int2];

        off.setCity(rand_int1, temp2);
        off.setCity(rand_int2, temp1);
    }

    private TSP.Tour[] pmx(TSP.Tour parent1, TSP.Tour parent2) {
        //izvedi pmx križanje, da ustvariš dva potomca

        TSP.Tour potomec1 = parent1.clone();
        TSP.Tour potomec2 = parent2.clone();

        int len = RandomUtils.nextInt(parent1.dimension-2);
        int pos = RandomUtils.nextInt(parent1.dimension-len)+1;

        TSP.City temp;
        int swapPos;
        for(int i = pos; i < len+pos; i++) {
            swapPos = parent1.path[parent2.path[i].index].index;
            temp = potomec1.path[i];
            potomec1.setCity(i, potomec1.path[swapPos]);
            potomec1.setCity(swapPos, temp);

            swapPos = parent2.path[i].index;
            temp = potomec2.path[i];
            potomec2.setCity(i, potomec2.path[swapPos]);
            potomec2.setCity(swapPos, temp);
        }

        return new TSP.Tour[]{potomec1, potomec2};
    }

    private TSP.Tour tournamentSelection() {
        // naključno izberi dva RAZLIČNA posameznika in vrni boljšega

        int rand_int1 = RandomUtils.nextInt(0, population.size());
        int rand_int2 = RandomUtils.nextInt(0, population.size());

        while (rand_int1 == rand_int2) {
            rand_int2 = RandomUtils.nextInt(0, population.size());
        }

        if (population.get(rand_int1).distance < population.get(rand_int2).distance) {
            return population.get(rand_int1);
        }
        else {
            return population.get(rand_int2);
        }
    }
}
