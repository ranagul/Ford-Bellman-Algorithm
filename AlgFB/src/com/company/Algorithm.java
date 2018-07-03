package com.company;

class Enge {
    public int eFirst;
    public int eSecond;
    public int w;
}

public class Algorithm {
    //Здесь должен быть алгоритм :)

    public void fordBellman(MyGraph graph)
    {
        //boolean x = false;
        Enge listE[] = new Enge [graph.numE];
        int ch = 0;
        for (int i=0; i<graph.numV; i++)
            for (int j=0; j<graph.IncidList[i].size(); j++) {
                listE[ch] = new Enge();
                listE[ch].eFirst = i;
                listE[ch].eSecond = graph.IncidList[i].get(j);
                listE[ch].w = graph.Weight[i].get(j);
                ch++;
            }

        boolean minusCycle = false;
        for (int v = 0; v < graph.numV - 1; ++v) { // Перебираем веса
            for (int i = 0; i < graph.numE; ++i) {// Перебираем ребра
                if (graph.newWeight[listE[i].eSecond] > graph.newWeight[listE[i].eFirst] + listE[i].w) {
                    graph.newWeight[listE[i].eSecond] = graph.newWeight[listE[i].eFirst] + listE[i].w; // Обновляем расстояние
                }
            }
        }

        for (int i = 0; i < graph.numE; ++i) {// Перебираем ребра
            if (graph.newWeight[listE[i].eSecond] > graph.newWeight[listE[i].eFirst] + listE[i].w) {
                minusCycle = true; // Найден цикл отрицательной длины
            }
        }

        if (minusCycle == true) System.out.println("Найден цикл отрицательной длины!!!");
        else {
            for (int i = 0; i < graph.numV; i++) {
                System.out.println("[" + (i + 1) + "] - " + graph.newWeight[i]);
            }
        }
    }
}
