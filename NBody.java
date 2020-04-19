

public class NBody {

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename =   args[2];
        
        double radius  = readRadius(filename);
        Body[] bodies  = readBodies(filename);
        int bodycount = bodies.length;
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < bodycount; i++){
            bodies[i].draw();
        }

        
        for(double time = 0; time <= T; time += dt){
            double[] xForces = new double[bodycount];
            double[] yForces = new double[bodycount];
            for(int i = 0 ; i < bodycount; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int j = 0; j < bodycount; j++){
                bodies[j].update(dt,xForces[j] , yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int h = 0; h <bodycount; h++){
                bodies[h].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodycount; i++) {
                  StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                                bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                                bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
                                }
    }

    public static double readRadius(String file){
            In input = new In(file);
            int N = input.readInt();
            double radius = input.readDouble();
            return radius ;


    }

    public static Body[] readBodies(String file){
        
        In input = new In(file);
        int N = input.readInt();
        Body[] bodies = new Body[N];
        double radius = input.readDouble(); 
        for (int i = 0 ; i < N ; i++){
            double xpos = input.readDouble();
            double ypos = input.readDouble();
            double xvel = input.readDouble();
            double yvel = input.readDouble();
            double mass = input.readDouble();
            String imgfile = input.readString();
            bodies[i] = new Body(xpos, ypos, xvel, yvel, mass, imgfile);



        }
        return bodies;


    }

}