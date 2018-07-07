package com.company;

import java.util.ArrayList;

public class UnitTest {
    public MyGraph Graph;
    public Algorithm resulte;

    public void MemoryAllocation() {

        Graph.newWeight = new int[Graph.numV];
        for (int i = 0; i < Graph.numV; ++i) {
            if (i + 1 == Graph.startV) Graph.newWeight[i] = 0;
            else Graph.newWeight[i] = 2000000000;
        }

        Graph.IncidList = new ArrayList[Graph.numV];
        Graph.Weight = new ArrayList[Graph.numV];
        for (int i = 0; i < Graph.numV; ++i) {
            Graph.IncidList[i] = new ArrayList<Integer>();
            Graph.Weight[i] = new ArrayList<Integer>();
        }
    }
    //отрицательные циклы
    public boolean UnitTestMinusCycle1 (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 5;
        Graph.numE = 5;
        Graph.startV = 1;
        MemoryAllocation();

        for (int i = 0; i < Graph.numE - 1; ++i) {
            Graph.IncidList[i].add(i+1);
            Graph.Weight[i].add(-1);
        }
        Graph.IncidList[4].add(0);
        Graph.Weight[4].add(-1);

        resulte.initializete(Graph);
        String res = resulte.fordBellman(Graph);
        if (res.compareTo("Найден цикл отрицательной длины!!!") != 0)
        {
            return false;
        }
        return true;
    }

    public boolean UnitTestMinusCycle2 (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 5;
        Graph.numE = 6;
        Graph.startV = 1;
        MemoryAllocation();

        Graph.IncidList[0].add(1); Graph.Weight[0].add(1);
        Graph.IncidList[1].add(2); Graph.Weight[1].add(1);
        Graph.IncidList[2].add(3); Graph.Weight[2].add(1);
        Graph.IncidList[3].add(4); Graph.Weight[3].add(-1);
        Graph.IncidList[4].add(0); Graph.Weight[4].add(-1);
        Graph.IncidList[0].add(3); Graph.Weight[0].add(-1);

        resulte.initializete(Graph);
        String res = resulte.fordBellman(Graph);
        if (res.compareTo("Найден цикл отрицательной длины!!!") != 0)
        {
            return false;
        }
        return true;
    }

    //циклы
    public boolean UnitTestPlusCycle1 (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 5;
        Graph.numE = 5;
        Graph.startV = 1;
        MemoryAllocation();

        for (int i = 0; i < Graph.numE - 1; ++i) {
            Graph.IncidList[i].add(i+1);
            Graph.Weight[i].add(1);
        }
        Graph.IncidList[4].add(0);
        Graph.Weight[4].add(1);

        resulte.initializete(Graph);
        String res = resulte.fordBellman(Graph);
        for (int i = 0; i < 5; i++) {
            if (Graph.newWeight[i] != i) {
                return false;
            }
        }
        return true;
    }

    public boolean UnitTestPlusCycle2 (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 5;
        Graph.numE = 6;
        Graph.startV = 1;
        MemoryAllocation();

        Graph.IncidList[0].add(1); Graph.Weight[0].add(1);
        Graph.IncidList[1].add(2); Graph.Weight[1].add(1);
        Graph.IncidList[2].add(0); Graph.Weight[2].add(1);
        Graph.IncidList[2].add(4); Graph.Weight[2].add(1);
        Graph.IncidList[4].add(3); Graph.Weight[4].add(1);
        Graph.IncidList[3].add(2); Graph.Weight[3].add(1);

        resulte.initializete(Graph);
        String res = resulte.fordBellman(Graph);
        if (Graph.newWeight[1] == 1 && Graph.newWeight[2] == 2 && Graph.newWeight[3] == 4 && Graph.newWeight[4] == 3) {
            return true;
        }
        else return false;
    }

    //все ребра

    public boolean UnitTestAllEdges (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 5;
        Graph.numE = 10;
        Graph.startV = 1;
        MemoryAllocation();

        Graph.IncidList[0].add(1); Graph.Weight[0].add(1);
        Graph.IncidList[1].add(2); Graph.Weight[1].add(1);
        Graph.IncidList[2].add(3); Graph.Weight[2].add(1);
        Graph.IncidList[3].add(4); Graph.Weight[3].add(1);
        Graph.IncidList[4].add(0); Graph.Weight[4].add(1);

        Graph.IncidList[0].add(2); Graph.Weight[0].add(-1);
        Graph.IncidList[0].add(3); Graph.Weight[0].add(-1);
        Graph.IncidList[4].add(1); Graph.Weight[4].add(1);
        Graph.IncidList[4].add(2); Graph.Weight[4].add(1);
        Graph.IncidList[1].add(3); Graph.Weight[1].add(1);

        resulte.initializete(Graph);
        String res = resulte.fordBellman(Graph);
        if (Graph.newWeight[1] == 1 && Graph.newWeight[2] == -1 && Graph.newWeight[3] == -1 && Graph.newWeight[4] == 0) {
            return true;
        }
        else return false;
    }

    //нет циклов, ребра входящие

    public boolean UnitTestNoCycle1 (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 5;
        Graph.numE = 4;
        Graph.startV = 1;
        MemoryAllocation();

        Graph.IncidList[1].add(0); Graph.Weight[1].add(1);
        Graph.IncidList[2].add(0); Graph.Weight[2].add(1);
        Graph.IncidList[3].add(0); Graph.Weight[3].add(1);
        Graph.IncidList[4].add(0); Graph.Weight[4].add(1);

        int inf = 2000000000;
        resulte.initializete(Graph);
        String res = resulte.fordBellman(Graph);
        if (Graph.newWeight[1] == inf && Graph.newWeight[2] == inf &&
                Graph.newWeight[3] == inf && Graph.newWeight[4] == inf) {
            return true;
        }
        else return false;
    }

    public boolean UnitTestNoCycle2 (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 5;
        Graph.numE = 0;
        Graph.startV = 1;
        MemoryAllocation();

        int inf = 2000000000;
        resulte.initializete(Graph);
        String res = resulte.fordBellman(Graph);
        if (Graph.newWeight[1] == inf && Graph.newWeight[2] == inf &&
                Graph.newWeight[3] == inf && Graph.newWeight[4] == inf) {
            return true;
        }
        else return false;
    }

    //нет циклов, ребра выходящие
    public boolean UnitTestNoCycle3 (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 5;
        Graph.numE = 4;
        Graph.startV = 1;
        MemoryAllocation();

        Graph.IncidList[0].add(1); Graph.Weight[0].add(1);
        Graph.IncidList[0].add(2); Graph.Weight[0].add(1);
        Graph.IncidList[0].add(3); Graph.Weight[0].add(1);
        Graph.IncidList[0].add(4); Graph.Weight[0].add(1);

        resulte.initializete(Graph);
        String res = resulte.fordBellman(Graph);
        if (Graph.newWeight[1] == 1 && Graph.newWeight[2] == 1 &&
            Graph.newWeight[3] == 1 && Graph.newWeight[4] == 1) {
            return true;
        }
        else return false;
    }

    public boolean UnitTestLargeGraphs (){
        Graph = new MyGraph();
        resulte = new Algorithm();

        Graph.numV = 1000;
        Graph.numE = 1000;
        Graph.startV = 1;
        MemoryAllocation();

        for(int j=0; j<10; j++) {
            for (int i = 0; i < Graph.numE - 1; ++i) {
                Graph.IncidList[i].add(i+1);
                Graph.Weight[i].add(i%(10+j));
            }
            Graph.IncidList[999].add(0);
            Graph.Weight[999].add(Graph.numE%(10+j));

            resulte.initializete(Graph);
            String res = resulte.fordBellman(Graph);
            for (int i = 0; i < Graph.numV; i++) {
                if (Graph.newWeight[i] != i%(10+j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
