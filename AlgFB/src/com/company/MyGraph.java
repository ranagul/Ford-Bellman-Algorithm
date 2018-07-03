package com.company;
import java.util.ArrayList;

public class MyGraph {
    public ArrayList<Integer> IncidList[];  //список инцидентности графа (список из пары вершин (1,2), где напрвление ребра 1 -> 2)
    public ArrayList<Integer> Weight[];     //список весов графа (в каком порядке добавляются пары в списке инцид, в таком и добавляются веса)
    public int newWeight[];
    public int numV;    //кол-во вершин
    public int numE;    //кол-во ребер
    public int startV;  //начальная вершина
}
