package api;

import gameClient.util.Point3D;

/**
 * This class represents a geo location <x,y,z>, aka Point3D.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 *
 */
public class GeoLocation implements geo_location {

    public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    private double x;
    private double y;
    private double z;

    public GeoLocation(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public GeoLocation(double x, double y) {
        this(x,y,0);
    }

    public GeoLocation(String pos){
        String[] a = pos.split(",");
        this.x = Double.parseDouble(a[0]);
        this.y = Double.parseDouble(a[1]);
        this.z = Double.parseDouble(a[2]);
    }

    public GeoLocation(geo_location pos){
        if (pos == null)
            return;
        this.x = pos.x();
        this.y = pos.y();
        this.z = pos.z();
    }

        public double x(){
            return this.x;
        }
        public double y(){
            return this.y;
        }
        public double z(){
            return this.z;
        }
       // TODO: To test this method.
        public double distance(geo_location g){
            double dx = this.x() - g.x();
            double dy = this.y() - g.y();
            double dz = this.z() - g.z();
            double t = (dx*dx+dy*dy+dz*dz);
            return Math.sqrt(t);
        }

    public boolean equals(Object p) {
        if(p==null || !(p instanceof geo_location)) {return false;}
        geo_location p2 = (geo_location) p;
        return ( (x==p2.x()) && (y==p2.y()) && (z==p2.z()) );
    }
    public boolean close2equals(geo_location p2) {
        return ( this.distance(p2) < EPS ); }
    public boolean equalsXY (geo_location p)
    {return p.x() == x && p.y() == y();}
    }