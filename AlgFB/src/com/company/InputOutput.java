package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InputOutput {
    private PrintWriter cout;

    //ввод данных с читателя cin
    public MyGraph getData(MyGraph Graph, BufferedReader cin) throws IOException {//передаем ссылку на граф, в который надо считать данные,
        cout = new PrintWriter(System.out);
        Graph = new MyGraph();

        StringTokenizer tokenizer = new StringTokenizer(cin.readLine());
        Graph.numV = Integer.parseInt(tokenizer.nextToken()); //считываем количество вершин графа
        Graph.numE = Integer.parseInt(tokenizer.nextToken()); //считываем количество ребер графа

        tokenizer = new StringTokenizer(cin.readLine());
        Graph.startV = Integer.parseInt(tokenizer.nextToken()); //считываем начальную вершину
       // Graph.finishV = Integer.parseInt(tokenizer.nextToken()); //считываем конечную вершину

        Graph.newWeight = new int[Graph.numV];
        for (int i = 0; i < Graph.numV; ++i) {
            if (i + 1 == Graph.startV) Graph.newWeight[i] = 0;
            else Graph.newWeight[i] = 2000000000;
        }

        //инициализация списка инцидентности графа
        Graph.IncidList = new ArrayList[Graph.numV];
        Graph.Weight = new ArrayList[Graph.numV];
        for (int i = 0; i < Graph.numV; ++i) {
            Graph.IncidList[i] = new ArrayList<Integer>();
            Graph.Weight[i] = new ArrayList<Integer>();
        }
        //считываем граф, заданный списком ребер
        for (int i = 0; i < Graph.numE; ++i) {
            tokenizer = new StringTokenizer(cin.readLine());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            int wt = Integer.parseInt(tokenizer.nextToken());
            v--;
            w--;
            Graph.IncidList[v].add(w);
            for (int j=0; j < Graph.IncidList[w].size(); j++) {//Убираем двунаправленные ребра
                if (Graph.IncidList[w].get(j) == v) return Graph;
            }
            Graph.Weight[v].add(wt);
        }
        return Graph;
    }

    //вывод результата!!!!   передаем в метод кратчайший путь - данные, которые надо вывести
}
