package api;

/**
 * This class represents a geo location <x,y,z>, aka Point3D.
 *
 * @authors Liel.Vaknin & Renana.Levy.
 *
 */
public class GeoLocation implements geo_location {

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
            double distance;
            double partOneOfCalculation, partTwoOfCalculation, partThreeOfCalculation;
            partOneOfCalculation = Math.pow(g.x() - this.x, 2);
            partTwoOfCalculation = Math.pow(g.y() - this.y, 2);
            partThreeOfCalculation = Math.pow(g.z() - this.z, 2);
            distance = Math.pow(partOneOfCalculation + partTwoOfCalculation + partThreeOfCalculation, 0.5);
            return distance;
        }
    }