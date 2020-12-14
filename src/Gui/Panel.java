package Gui;

import api.*;
import gameClient.*;
import gameClient.util.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class Panel extends JPanel {

    private JLabel level;
    private JLabel time;
    private JLabel score;
    private JLabel moves;

    private Image background;

    private Arena arena;
    private gameClient.util.Range2Range _w2f;

//    public Panel(){
//        System.out.println("enter");
//    }

//    public void foo(){
//        System.out.println("foo");
//    }

//    @Override
//    protected void paintComponent(Graphics g){
//        super.paintComponent(g);
//        System.out.println("paint");
//        g.setColor(Color.BLACK);
//        g.fillOval(100, 150, 20, 20);
//        System.out.println(this.getHeight());
//    }

    public Panel(Arena arena) {
        this.setLayout(null);
        background = new ImageIcon("./resources/backgroundGame.png").getImage();
//        Dimension size = new Dimension(background.getWidth(null), background.getHeight(null));
//        setPreferredSize(size);
//        setMinimumSize(size);
//        setMaximumSize(size);
//        setSize(size);
//        setLayout(null);
        this.arena = arena;
        newInfo();
//        this.repaint();
//        paintComponent();
        updateFrame();
    }

    public void update(){
        this.repaint();
    }

    private void updateFrame() {
        Range rx = new Range(50,this.getWidth()+1300);
        Range ry = new Range(this.getHeight()+670,80);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = arena.getGraphAlgo().getGraph();
        _w2f = Arena.w2f(g,frame);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawImage("./resources/backgroundGame.png");
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.BLUE);
        drawGraph(g);
        drawPokemons(g);
        drawAgents(g);
        info();
    }

    private void info() {
        time.repaint();
        time = new JLabel("time: "+arena.gatGame().timeToEnd());
    }

    private void drawGraph(Graphics g) {
        directed_weighted_graph graph = arena.getGraphAlgo().getGraph();
        Iterator<node_data> it1 = graph.getV().iterator();
        while(it1.hasNext()) {
            node_data n = it1.next();
            drawNode(n,5,g);
            Iterator<edge_data> it2 = graph.getE(n.getKey()).iterator();
            while(it2.hasNext()) {
                edge_data e = it2.next();
                drawEdge(e, g);
            }
        }
    }

    private void drawNode(node_data n, int r, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.fillOval((int)fp.x()-r, (int)fp.y()-r-2, 2*r+3, 2*r+3);
        g.drawString(""+n.getKey(), (int)fp.x()-r+1, (int)fp.y()-2*r);
    }

    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = arena.getGraphAlgo().getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g.drawLine((int)s0.x()-1, (int)s0.y(), (int)d0.x(), (int)d0.y());
        //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
    }

    private void drawPokemons(Graphics g) {
        List<CL_Pokemon> pokemons = arena.getPokemons();
        if(pokemons!=null) {
            Iterator<CL_Pokemon> it = pokemons.iterator();
            while(it.hasNext()) {
                CL_Pokemon pok = it.next();
                geo_location c = pok.getPos();
                int r = 10;
                g.setColor(Color.green);
                if(pok.getType() < 0) {
                    g.setColor(Color.red);
                }
                if(c!=null) {
                    geo_location ge = this._w2f.world2frame(c);
                    ImageIcon poky = new ImageIcon("./resources/pokemon.png");
                    g.drawImage(poky.getImage(), (int)ge.x()-r-5, (int)ge.y()-r-5, 3*r, 3*r, null);
//                    g.fillOval((int)ge.x()-r, (int)ge.y()-r, 2*r, 2*r);
                    g.drawString(""+pok.getValue(), (int)ge.x()-r+1, (int)ge.y()-r-3);
//                    	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);

                }
            }
        }
    }

    private void drawAgents(Graphics g) {
        List<CL_Agent> agents = arena.getAgents();
        g.setColor(Color.DARK_GRAY);
        int i = 0;
        while(agents != null && i < agents.size()) {
            geo_location c = agents.get(i).getPos();
            int r=8;
            i++;
            if(c!=null) {
                geo_location ge = this._w2f.world2frame(c);
                ImageIcon poky = new ImageIcon("./resources/agent2.png");
                g.drawImage(poky.getImage(), (int)ge.x()-2*r, (int)ge.y()-2*r-5, 5*r, 6*r, null);
//                g.fillOval((int)ge.x()-r, (int)ge.y()-r, 2*r, 2*r);
//                g.drawString(""+agents.get(i).getValue(), (int)ge.x(), (int)ge.y()-2*r);
            }
        }
    }

    private void newInfo(){
        level = new JLabel("level: "+arena.getLevel());
        this.add(level);
        level.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        level.setBounds(1100, 2, 200, 50);

        time = new JLabel("time: "+arena.gatGame().timeToEnd());
        this.add(time);
        time.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        time.setBounds(1200, 2, 200, 50);

        int grade = jsonToObject.score(arena.gatGame().toString());
        score = new JLabel("score: "+grade);
        this.add(score);
        score.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        score.setBounds(50, 2, 200, 50);

        int moving = jsonToObject.moves(arena.gatGame().toString());
        moves = new JLabel("moves: "+moving);
        this.add(moves);
        moves.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        moves.setBounds(150, 2, 200, 50);
    }
}
