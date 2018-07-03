package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//добавить: алгоритм!

public class MyGUIForm extends JFrame{
    private JLabel descLabel;
    private JLabel resLabel;
    private JTextArea graphEdit;
    private JButton buttonLoad;
    private JButton buttonInit;
    private JButton buttonStep;
    private JButton buttonRun;

    private Canvas canvas;

    private InputOutput io;
    private Algorithm solution;
    private MyGraph graph;

    private JPanel rootPanel;

    // ключевое слово super, которое обозначает суперкласс, т.е. класс, производным от которого является текущий класс
    public MyGUIForm() {
        //setBounds(x, y, w, h) - указывает координаты верхней левой вершины окна, а также его ширину и высоту.
        //завершающие настройки
        this.setSize(650,400);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(650,400));
        this.setTitle("Algorithm Ford-Bellman.");
        this.rootPanel = new JPanel();
        rootPanel.setLayout(null);      //абсолютное позиционирование
        rootPanel.setBounds(0,0,650,400);

        this.setBounds(100,100,450,400);
        setContentPane(rootPanel);

        JLabel label = new JLabel();
        label.setText("Graph data: ");
        label.setBounds(12,12,80,12);
        label.setVisible(true);

        //кнопки управления
        this.buttonLoad = new JButton("Load");
        this.buttonInit = new JButton("Init");
        this.buttonStep = new JButton("Step");
        this.buttonRun = new JButton("Run");

        //устанавливаем размеры кнопок
        this.buttonLoad.setBounds(12,300,63,24);
        this.buttonInit.setBounds(80,300,63,24);
        this.buttonStep.setBounds(12,332,63,24);
        this.buttonRun.setBounds(80,332,63,24);

        //поясняющие надписи
        this.descLabel = new JLabel();
        this.descLabel.setBounds(156,300,478,48);
        this.descLabel.setText("Description: ");

        this.resLabel = new JLabel();
        this.resLabel.setBounds(156,340,478,16);
        this.resLabel.setText("Result: -");

        //строка для указания кол-ва вершин
        this.graphEdit = new JTextArea("");
        this.graphEdit.setBounds(12,32,128,14400);
        //this.graphEdit.setText("4 5\n1\n1 2 1\n2 3 2\n1 4 -1\n4 2 1\n3 4 4");//Правильно
         this.graphEdit.setText("8 9\n1\n1 2 -1\n2 3 2\n2 4 3\n3 4 -1\n4 5 -6\n4 6 7\n6 7 7\n7 8 -8\n8 6 9");//Правильно
        // this.graphEdit.setText("8 9\n1\n1 2 -1\n2 3 2\n2 4 3\n3 4 -1\n4 5 -6\n4 6 7\n6 7 7\n7 8 -8\n8 6 -9");//Правильно С циклом отр длины
        //this.graphEdit.setText("8 10\n5\n1 2 1\n2 3 2\n2 4 3\n3 4 4\n4 3 4\n4 5 6\n4 6 7\n6 7 7\n7 8 8\n8 6 9");//Ошибка введено ребро в обе стороны
        //this.graphEdit.setText("8 9\n\n1 2 -1\n2 3 2\n2 4 3\n3 4 -1\n4 5 -6\n4 6 7\n6 7 7\n7 8 -8\n8 6 9");//Ошибка Не указана 1 вершина
        //this.graphEdit.setText("8 10\n1\n1 2 -1\n2 3 2\n2 4 3\n3 4 -1\n4 5 -6\n4 6 7\n6 7 7\n7 8 -8\n8 6 9");//Ошибка Заказано 10 ребер, введено 9
        //this.graphEdit.setText("7 9\n1\n1 2 -1\n2 3 2\n2 4 3\n3 4 -1\n4 5 -6\n4 6 7\n6 7 7\n7 8 -8\n8 6 9");//Ошибка Всего 7 вершин, но введена 8-я
        JScrollPane scroll = new JScrollPane(graphEdit);
        scroll.setBounds(12,32,128,244);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.canvas = new Canvas();
        this.canvas.setBounds(176,12,428,300);
        this.canvas.setVisible(true);

        this.rootPanel.add(this.canvas);

        //устанавливаем видимость всех объектов
        this.buttonLoad.setVisible(true);
        this.buttonInit.setVisible(true);
        this.buttonStep.setVisible(true);
        this.buttonRun.setVisible(true);
        this.descLabel.setVisible(true);
        this.resLabel.setVisible(true);
        scroll.setVisible(true);

        //добавляем объекты на панель
        this.rootPanel.add(this.buttonLoad);
        this.rootPanel.add(this.buttonInit);
        this.rootPanel.add(this.buttonStep);
        this.rootPanel.add(this.buttonRun);
        this.rootPanel.add(label);
        this.rootPanel.add(scroll);
        this.rootPanel.add(this.descLabel);
        this.rootPanel.add(this.resLabel);

        buttonRun.setEnabled(false);
        buttonStep.setEnabled(false);

        rootPanel.setVisible(true);

        io = new InputOutput();

        //открытие файла для чтения
        buttonLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd = new JFileChooser();//диалоговое окно
                int ret = fd.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fd.getSelectedFile();       //получение выбранного файла

                    try(BufferedReader reader =new BufferedReader(new FileReader(file)))
                    {
                        // читаем из файла построчно
                        String line;
                        graphEdit.setText("");
                        while ((line = reader.readLine()) != null) {
                            graphEdit.append(line + "\n");
                        }
                    }
                    catch(IOException ex){
                        resLabel.setText(ex.getMessage());
                    }
                }
            }
        });

        // инициализация графа
        buttonInit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    graph = new MyGraph();
                    graph = io.getData(graph, new BufferedReader(new StringReader(graphEdit.getText()))); //считывание
                    canvas.setContent(graph);//перерисовывание
                    canvas.select(graph);//выделение начальной и конечной вершин
                    //canvas.select(graph);
                    solution = new Algorithm();
                    solution.fordBellman(graph);// НЕ СЮДА!!!!!!!!!!!!!!
                    descLabel.setText("Description: algorithm initialized.");
                    resLabel.setText("Result: -");
                    buttonRun.setEnabled(true);
                    buttonStep.setEnabled(true);
                    buttonLoad.setEnabled(false);
                    buttonInit.setEnabled(false);
                } catch (Exception e) {
                    resLabel.setText("");
                    descLabel.setText("Description: exception! "+e.getClass().getName()+": "+e.getMessage());
                }
            }
        });

        //выполнение алгоритма

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}


