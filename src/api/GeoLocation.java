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

    /**
     * Constructor.
     *
     * @param x
     * @param y
     * @param z
     */
    public GeoLocation(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public GeoLocation(double x, double y) {
        this(x,y,0);
    }

    /**
     * Constructor - gets a String, splits it and initializes x, y and z.
     *
     * @param pos
     */
    public GeoLocation(String pos){
        String[] a = pos.split(",");
        this.x = Double.parseDouble(a[0]);
        this.y = Double.parseDouble(a[1]);
        this.z = Double.parseDouble(a[2]);
    }

    /**
     * Copy constructor - Performs a deep copy of a given geo location.
     *
     * @param pos
     */
    public GeoLocation(geo_location pos){
        if (pos == null)
            return;
        this.x = pos.x();
        this.y = pos.y();
        this.z = pos.z();
    }

    /**
     * Returns x.
     *
     * @return x.
     */
    @Override
    public double x(){
        return this.x;
    }

    /**
     * Returns y.
     *
     * @return y.
     */
    @Override
    public double y(){
        return this.y;
    }

    /**
     * Returns z.
     *
     * @return z.
     */
    @Override
    public double z(){
        return this.z;
    }

    /**
     * Calculates the distance between this geo location and a given one.
     *
     * @param g represents a given geo location.
     */
    // TODO: To test this method.
    @Override
    public double distance(geo_location g){
        double dx = this.x() - g.x();
        double dy = this.y() - g.y();
        double dz = this.z() - g.z();
        double t = (dx*dx+dy*dy+dz*dz);
        return Math.sqrt(t);
//            double distance;
//            double partOneOfCalculation, partTwoOfCalculation, partThreeOfCalculation;
//            partOneOfCalculation = Math.pow(g.x() - this.x, 2);
//            partTwoOfCalculation = Math.pow(g.y() - this.y, 2);
//            partThreeOfCalculation = Math.pow(g.z() - this.z, 2);
//            distance = Math.pow(partOneOfCalculation + partTwoOfCalculation + partThreeOfCalculation, 0.5);
//            return distance;
    }

    /**
     * Equals method.
     *
     * @param p represents a given object.
     * @return true if this object and a given object are equals, false if not.
     */
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