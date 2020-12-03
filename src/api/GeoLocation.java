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