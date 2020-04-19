public class Body {
    public static double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
              double yV, double m, String img){
                  xxPos = xP;
                  yyPos = yP;
                  xxVel = xV;
                  yyVel = yV;
                  mass = m;
                  imgFileName = img;
              }
    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;    
    }          

    public double calcDistance(Body b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double r = Math.sqrt((dx*dx + dy*dy));
        return r; 
    }

    public double calcForceExertedBy(Body b){
    
        double r = this.calcDistance(b);
        // calculates the force by the equation f = (G*m1*m2 )/ r^2 
        double numerator = G * b.mass * this.mass;
        double result = numerator / (r * r);
        return result;

    }    

    public double calcForceExertedByX(Body b){
        double F = calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        double r = calcDistance(b);
        double result  = (F * dx) / r ; 
        return result;
    }

    public double calcForceExertedByY(Body b){
       
        double F = calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        double r = calcDistance(b);
        double result  = (F * dy) / r ; 
        return result;
    }


    public double calcNetForceExertedByX(Body[] b){
        double xForce = 0;
        
        for(int i=0; i < b.length; i++){
            if (this.equals(b[i])){
                continue;
            }
            xForce += calcForceExertedByX(b[i]);
        }

        return xForce;

    }
 
    public double calcNetForceExertedByY(Body[] b){
        double yForce = 0;
        
        for(int i=0; i < b.length; i++){
            if (this.equals(b[i])){
                continue;
            }
            yForce += calcForceExertedByY(b[i]);
        }

        return yForce;

    }    


    public void update(double dt, double fX, double fY){
        // acceleration calculation in x and y components 
        double Ax = fX / this.mass;
        double Ay = fY / this.mass;
        // new velcoity calculation (calculated with the accelaration and time interval provided)
        this.xxVel += dt * Ax;
        this.yyVel += dt * Ay;
        // new position calculation
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw(){
        String img = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, img);
    }


}