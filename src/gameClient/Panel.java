package gameClient;

import api.*;
import gameClient.util.Point3D;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class Panel extends JPanel {

    private Arena arena;
    private gameClient.util.Range2Range _w2f;

    public Panel(Arena arena){
        super();
        this.arena = arena;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
        drawPokemons(g);
       // drawAgents(g);
    }

    private void drawGraph(Graphics g) {
        directed_weighted_graph graph = arena.getGraph();
        Iterator<node_data> it1 = graph.getV().iterator();
        while(it1.hasNext()) {
            node_data n = it1.next();
            g.setColor(Color.blue);
            drawNode(n,5,g);
            Iterator<edge_data> it2 = graph.getE(n.getKey()).iterator();
            while(it2.hasNext()) {
                edge_data e = it2.next();
                g.setColor(Color.blue);
                drawEdge(e, g);
            }
        }
    }

    private void drawNode(node_data n, int r, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
        g.drawString(""+n.getKey(), (int)fp.x(), (int)fp.y()-4*r);
    }

    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = arena.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
        //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
    }

    private void drawPokemons(Graphics g) {
        List<Pokemon> pokemons = arena.getPokemons();
        if(pokemons!=null) {
            Iterator<Pokemon> it = pokemons.iterator();
            while(it.hasNext()) {
                Pokemon pok = it.next();
                geo_location c = pok.getPos();
                int r = 10;
                g.setColor(Color.green);
                if(pok.getType() < 0) {
                    g.setColor(Color.orange);
                }
                if(c!=null) {
                    geo_location ge = this._w2f.world2frame(c);
                    g.fillOval((int)ge.x()-r, (int)ge.y()-r, 2*r, 2*r);
                    //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);

                }
            }
        }
    }

   /* private void drawAgents(Graphics graphics) {
        List<Agent> agents = arena.getAgents();
        graphics.setColor(Color.red);
        int i = 0;
        while(agents != null && i < agents.size()) {
            geo_location c = agents.get(i).getPos();
            int r=8;
            i++;
            if(c!=null) {
                geo_location ge = this.world2frame(c);
                graphics.fillOval((int)ge.x()-r, (int)ge.y()-r, 2*r, 2*r);
            }
        }
    }*/
}
