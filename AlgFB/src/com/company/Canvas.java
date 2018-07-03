package com.company;
import javax.swing.*;
import java.awt.*;

public class Canvas extends  JPanel {
    private VGraph content;
    private static final Color[] compColor = {Color.white,  Color.blue, Color.green, Color.yellow,   Color.magenta,
            Color.cyan,   Color.gray, Color.pink,  Color.darkGray, Color.red};

    //конструктор
    public Canvas() {
        content = null;
        this.setLayout(null);
    }

    public void select(MyGraph data) {
        content.recolor(data.startV - 1, Color.pink);//изменение цвета
        //content.recolor(data.finishV - 1, Color.orange);//изменение цвета
        content.repaint();
    }

    //обновление содержимого (обновление графа)
    public void setContent(MyGraph data) {
        if (content != null) {
            content.setVisible(false);
        }
        content = new VGraph(data);
        this.add(content);
        this.revalidate();//обновление макета экрана
        this.repaint();//обновление графа
    }
}
