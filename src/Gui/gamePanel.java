package Gui;

import api.*;
import gameClient.*;
import gameClient.util.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * This class uses for drawing the window of the game on the frame.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 */
public class gamePanel extends JPanel {

    private static final int WP = 100;

    private JLabel level;
    private JLabel time;
    private JLabel score;
    private JLabel moves;

    private int w;
    private int h;

    private Image background;

    private Arena arena;
    private gameClient.util.Range2Range _w2f;

    /**
     * Constructor
     *
     * @param arena represents the game arena.
     */
    public gamePanel(Arena arena) {
        super();
        this.setLayout(null);

        background = new ImageIcon("./resources/GameBackground.png").getImage();

        level = new JLabel("level: "+arena.getLevel());
        this.add(level);
        time = new JLabel("time: "+(arena.gatGame().timeToEnd()/1000));
        this.add(time);
        int grade = jsonToObject.score(arena.gatGame().toString());
        score = new JLabel("score: "+grade);
        this.add(score);
        int moving = jsonToObject.moves(arena.gatGame().toString());
        moves = new JLabel("moves: "+moving);
        this.add(moves);

        this.arena = arena;
    }

    /**
     * Updates the window which displays game arena.
     *
     * @param score represents the score on the game.
     */
    protected void update(int score){
//        int grade = jsonToObject.score(arena.gatGame().toString());
        this.score.setText("score: "+score);
        this.repaint();
    }

    /**
     * Updates the frame relative to the screen size.
     */
    private void updateFrame() {
        Range rx = new Range(30,w-30);
        Range ry = new Range(h-30,60);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = arena.getGraphAlgo().getGraph();
        _w2f = Arena.w2f(g,frame);
    }

    /**
     * Draws on the window.
     * Overrides paintComponent method.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        w = this.getWidth();
        h = this.getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0,0, w, h, null);
        updateFrame();
        g.setColor(Color.BLACK);
        drawGraph(g);
        drawPokemons(g);
        drawAgents(g);
        Info();
    }

    /**
     * Draws the graph on the window.
     *
     * @param g
     */
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

    /**
     * Draws a node on the window.
     *
     * @param n represents the given node needed to be drawn on the window.
     * @param r represents a given range between the geo location of a given node and the location of the node in the drawing.
     * @param g
     */
    private void drawNode(node_data n, int r, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.fillOval((int)fp.x()-r, (int)fp.y()-r-2, 2*r+3, 2*r+3);
        g.drawString(""+n.getKey(), (int)fp.x()-r+1, (int)fp.y()-2*r);
    }

    /**
     * Draws an edge on the window.
     *
     * @param e represents the given edge needed to be drawn on the window.
     * @param g
     */
    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = arena.getGraphAlgo().getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
        //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
    }

    /**
     * Draws all the pokemons on the window.
     *
     * @param g
     */
    private void drawPokemons(Graphics g) {
        List<CL_Pokemon> pokemons = arena.getPokemons();
        if(pokemons!=null) {
            Iterator<CL_Pokemon> it = pokemons.iterator();
            while(it.hasNext()) {
                CL_Pokemon pok = it.next();
                geo_location c = pok.getPos();
                int r = 10;
                g.setColor(new Color(246, 243, 243, 255));
                if(pok.getType() < 0) {
                    g.setColor(Color.red);
                }
                if(c!=null) {
                    geo_location ge = this._w2f.world2frame(c);
                    ImageIcon poky = new ImageIcon("./resources/Pokemon.png");
                    g.drawImage(poky.getImage(), (int)ge.x()-r-5, (int)ge.y()-r-5, 3*r, 3*r, null);
//                    g.fillOval((int)ge.x()-r, (int)ge.y()-r, 2*r, 2*r);
                    g.drawString(""+pok.getValue(), (int)ge.x()-r+1, (int)ge.y()-r-3);
                }
            }
        }
    }

    /**
     * Draws all the agents on the window.
     *
     * @param g
     */
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
                ImageIcon poky = new ImageIcon("./resources/Agent.png");
                g.drawImage(poky.getImage(), (int)ge.x()-2*r, (int)ge.y()-4*r, 5*r, 6*r, null);
//                g.fillOval((int)ge.x()-r, (int)ge.y()-r, 2*r, 2*r);
//                g.drawString(""+agents.get(i).getValue(), (int)ge.x(), (int)ge.y()-2*r);
            }
        }
    }

    /**
     * Draws information about the game on the window - level, time to end, score and moves.
     */
    private void Info(){

        level.setText("level: "+arena.getLevel());
        level.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        level.setBounds(w-3*WP, 2, 200, 50);

        time.setText("time: "+(arena.gatGame().timeToEnd()/1000));
        time.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        time.setBounds(w-2*WP, 2, 200, 50);

//        int grade = jsonToObject.score(arena.gatGame().toString());
        score.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        score.setBounds(WP-5, 2, 200, 50);

        int moving = jsonToObject.moves(arena.gatGame().toString());
        moves.setText("moves: "+moving);
        moves.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        moves.setBounds(2*WP, 2, 200, 50);
    }
}
