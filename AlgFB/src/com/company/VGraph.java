package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.lang.Math;

//объект, превращающий абстрактный граф в его плоскостное представление
public class VGraph extends JPanel {
    private ArrayList<HashMap<String,Object>> vertices; //список вершин
    private ArrayList<HashMap<String,Object>> edges;    //список ребер

    //для рисования графа
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (HashMap<String,Object> e: edges) {
            g2d.draw((Line2D) e.get("component"));//рисует линии
            Coord c1 = (Coord) e.get("posFrom");
            Coord c2 = (Coord) e.get("posTo");
            Coord c3 = (Coord) e.get("posN");

            double angle = Math.atan2(c2.y - c1.y, c2.x-c1.x);
            double x1 = -10;
            double x2 = -10;
            double y1 = -10;
            double y2 = 10;

            double fx1 = x1*Math.cos(angle) - y1*Math.sin(angle);
            double fx2 = x2*Math.cos(angle) - y2*Math.sin(angle);
            double fy1 = x1*Math.sin(angle) + y1*Math.cos(angle);
            double fy2 = x2*Math.sin(angle) + y2*Math.cos(angle);

            fx1 += c3.x;
            fx2 += c3.x;
            fy1 += c3.y;
            fy2 += c3.y;

            g2d.drawLine(c3.x , c3.y , (int) fx1, (int) fy1);//рисуют стрелки
            g2d.drawLine(c3.x, c3.y, (int) fx2, (int) fy2);

            if (c3.x >= 214) {
                if (c3.y <= 150) g2d.drawString((String) e.get("name"), c3.x + 5, c3.y - 5);
                else g2d.drawString((String) e.get("name"), c3.x + 15, c3.y + 10);
            }
            else {
                if (c3.y <= 150) g2d.drawString((String) e.get("name"), c3.x - 5, c3.y - 5);
                else g2d.drawString((String) e.get("name"), c3.x - 15, c3.y + 15);
            }
        }

            //Работа с цветом линии/фигуры
        // Запоминаем исходный цвет;
        Color oldColor = g.getColor();
        //рисование кружочков для вершин графа
        for (HashMap<String, Object> vertice : vertices) {       //проходим по всем вершинам
            Coord vertC = (Coord) vertice.get("pos");    //координата вершины

            //если надо закрасить кружочки:
            Color newColor = (Color) vertice.get("color");
            // Устанавливаем новый цвет;
            g.setColor(newColor);//цвет для
            g.fillOval(vertC.x - 19, vertC.y - 10, 30, 30);//покраска

            // Восстанавливаем исходный цвет;
            g.setColor(oldColor);//цвет для контура
            g.drawOval(vertC.x - 19, vertC.y - 10, 30, 30);//контур

            g.drawString((String) vertice.get("name"), vertC.x - 10, vertC.y + 10);//вносим название в кружок
            if (vertC.x >= 214) {
                if(vertC.y <= 150) g.drawString((String) vertice.get("weight"), vertC.x + 15, vertC.y);//веса
                else g.drawString((String) vertice.get("weight"), vertC.x + 15, vertC.y + 20);
            }
            else {
                if(vertC.y <= 150) g.drawString((String) vertice.get("weight"), vertC.x - 40, vertC.y);
                else g.drawString((String) vertice.get("weight"), vertC.x - 40, vertC.y + 20);
            }
        }
    }

    //добавление вершины в граф
    private void addVertex(String name, String weight) {
        HashMap<String, Object> vertex = new HashMap<>(5);
        vertex.put("name", name);
        vertex.put("color",Color.white);
        vertex.put("selected",false);
        vertex.put("pos", new Coord(0,0));
        vertex.put("component", new JLabel());
        vertex.put("weight", weight);
        vertices.add(vertex);
    }

    //добавление ребра в граф
    private void addEdge(String fromName, String toName, String name) {
        HashMap<String, Object> edge = new HashMap<>(5);
        edge.put("from", fromName);
        edge.put("to", toName);
        edge.put("name", name);
        edge.put("posFrom", new Coord(0,0));
        edge.put("posTo", new Coord(0,0));
        edge.put("posN", new Coord(0,0));
        edge.put("component", new Line2D.Double(0,0,0,0));
        edges.add(edge);
    }

    //возвращает объект вершины по указанному имени
    private HashMap<String,Object> vertexLookup(String name) {
        for (HashMap<String, Object> cur : vertices) if (cur.get("name").equals(name)) return cur;
        return null;
    }

    //расположение графа на плоскости
    private void reposition() {
        for (int i = 0; i<vertices.size(); ++i) {
            HashMap<String,Object> cur = vertices.get(i);
            Coord place = new Coord(214+(int)(130*Math.cos(6.28/vertices.size()*i)),150+(int)(130*Math.sin(6.28/vertices.size()*i)));
            cur.replace("pos", place);
            JLabel lbl = (JLabel) cur.get("component");
            lbl.setText((String) cur.get("name"));
            lbl.setBounds(place.x-10,place.y-10,30,30);
            lbl.setVisible(true);
            lbl.setBackground((Color) cur.get("color"));
            this.add(lbl);
        }

        for (HashMap<String, Object> cur : edges) {
            HashMap<String, Object> v = vertexLookup((String) cur.get("from"));
            Coord fromC = new Coord((Coord) v.get("pos"));
            v = vertexLookup((String) cur.get("to"));
            Coord toC = new Coord((Coord) v.get("pos"));
            Coord posN = new Coord((Coord) v.get("pos"));

            fromC.x -= 5;
            fromC.y += 5;
            toC.x -= 5;
            toC.y += 5;

            posN.x = (fromC.x + toC.x) / 2;
            posN.y = (fromC.y + toC.y) / 2;

            Line2D line = (Line2D) cur.get("component");
            line.setLine(fromC.x, fromC.y, toC.x, toC.y);
            //System.out.println(fromC.x+"  "+ fromC.y+"  "+ toC.x+"  "+ toC.y);
            cur.replace("posFrom", fromC);
            cur.replace("posTo", toC);
            cur.replace("posN", posN);

        }
    }

    void recolor(int id, Color c) {
        vertices.get(id).replace("color",c);
    }

    VGraph(MyGraph original) {
        this.setBounds(0,0,428, 300);
        this.setLayout(null);

        vertices = new ArrayList<>(original.numV);
        edges = new ArrayList<>(original.numE);
        String s;
        for (int i = 1; i<=original.numV; ++i) {
            if (original.newWeight[i-1] == 2000000000) s = new String("inf");
            else s = new String("0");
            this.addVertex("v" + String.valueOf(i),
                            "["+ s +"]");
        }

        for (int i = 0; i<original.numV; ++i) {
            for (int j = 0; j<original.IncidList[i].size(); ++j) {
                this.addEdge("v"+String.valueOf(i+1),
                            "v"+String.valueOf(original.IncidList[i].get(j)+1),
                            ""+String.valueOf(original.Weight[i].get(j)));
            }
        }

        reposition();

        this.revalidate();//обновление
        this.repaint();
    }
}
