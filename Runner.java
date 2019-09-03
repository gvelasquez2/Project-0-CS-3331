// Gilbert Velasquez Program 0
// 9/3/2019
// CS 3331
// This Program is intended for a Coach to be able to compute where a runner is at a given time on an in-and-out track.
// To solve this I created a velocity vs time graph based on the given stats of each runner. Using some simple kinematics 
// I determined the time it takes for the runner to accelerate to top speed. This time frame is essential for my solution, 
// since we can use this time to break our solution down into check points based one time. The first checkpoint is located 
// at the point where the runner reaches top speed. The next is located at the exact point the runner will need to declerate 
// to turn around, the next checkpoint is located at the exact moment the runner turns around and starts accelerating from 
// zero. The last checkpoint is located at the exact moment the runner reaches top speed again. Since the runner accelerates 
// and decelerates at the same velocity it takes the exact same amount of time, we can use this to calculate our missing 
// time, aka the point in which the runner is running at top speed. Since we now know exactly how long the runner spend at 
// each velocity. We can use our distance formula to calculate how far our runner has ran based on the given time, to pick 
// which velocity we are using, we simpliy just need to look at our velocity vs time chart and determine what percent of 
// the runner spent at each velocity. Then we can compute the given distance based on time. I decided to make each 
// Check-Point (CP) and TimeStamp an object of the class to simplfy the process. When we create a new runner, the computer
// will calculate all these numbers based on the equations I gave it. 

class Runner{

    public String name; // Name of runner 
    public float topSpeed; // Top speed of Runner 
    public float acceleration; // Acceleration/Deceleration of Runner 
    float distanceCP1; // Distance traveled after reaching Top Speed
    float distanceCP2; // Distance traveled right before deceleration begins 
    float distanceCP3; // Distance traveled right before runner turs around
    float distanceCP4; // Distance traveled right as runner has accelerated to top speed
    float topSpeedP1; // Portion of race towards midpoint where runner is running at top speed
    float timeStamp1; // Time to accelerate 
    float timeStamp2; // Time to reach deceleration 
    float timeStamp3; // Time to turn around 
    float timeStamp4; // To to reach Acceleration towards finish 

    Runner(String n, float ts, float a){ // runner contructor 
        
        name = n; // name of runner 
        topSpeed = ts; // top speed of runner 
        acceleration = a; // acceleration of runner
        distanceCP1 = ((topSpeed * topSpeed)/(2*acceleration)); // from d = (v^2 - u^2)/2a
        distanceCP2 = (300 - distanceCP1);
        distanceCP3 = 300; // half way point 
        distanceCP4 = (300 + distanceCP1);
        topSpeedP1 = (300 - (2*distanceCP1));
        timeStamp1 = (ts/a); // from t = (v-u)/a
        timeStamp2 = ((topSpeedP1/ts) + timeStamp1);
        timeStamp3 = (timeStamp2 + timeStamp1);
        timeStamp4 = (timeStamp3 + timeStamp1);
    }
    
    public static void main(String[] args){ // main 
    
        Runner R1 = new Runner ("Nelly",30.0f,8.0f); // Instance for each runner 
        Runner R2 = new Runner ("Steve",8.8f,3.0f);
        Runner R3 = new Runner ("Usain",41.0f,11.0f);
        System.out.println(""); // Spacing 
        System.out.println("Runner         Max Speed(f/s)          Acceleration(f/s/s)"); // Given Data Table 
        System.out.println(R1.name + "          " + R1.topSpeed + "                    " + R1.acceleration);
        System.out.println(R2.name + "          " + R2.topSpeed + "                     " + R2.acceleration);
        System.out.println(R3.name + "          " + R3.topSpeed + "                    " + R3.acceleration);
        System.out.println(""); // Spacing 
        System.out.println(""); // Spacing 
        System.out.printf("%-15s%-15s%-15s%-15s\n","Time",R1.name,R2.name,R3.name);
        
        
        for(float i = 0.00f; i <= 75.00f; i +=5.00f){ // This loop prints out distance for each runner every 5 seconds
            System.out.printf("%-15s%-15s%-15s%-15s\n",i,distanceAtTime(R1,i),distanceAtTime(R2,i),distanceAtTime(R3,i));
        }
        System.out.println(""); // Spacing
    }
    
    // This method uses computes distance at time. For the assignment every 5 seconds. The logic is simple. It takes time as // an input and determines where it lies on our velocity vs time graph. After it pin points where the given time is. It // calculates which velocity to use to plug into our distance formula. My trick is that since i've broken it down into // check points I can simply add the last checkpoint's distance to the reaming time's distance. To do this I take the 
    // difference of the input time and the checkpoint's timestamp and plug that time into the formula. To get the remaing // distance.
    
    public static float distanceAtTime(Runner r, float time){ 
        float distance = 0;
        if(time < r.timeStamp1){
            distance = r.acceleration * time * time;
            return distance;
        }
        if((time >= r.timeStamp1) && (time < r.timeStamp2)){
            distance = r.distanceCP1 + (r.topSpeed * (time - r.timeStamp1));
            return distance;
        }
        if((time >= r.timeStamp2) && (time < r.timeStamp3)){
            distance = r.distanceCP2 + (r.acceleration * (time - r.timeStamp2) * (time - r.timeStamp2));
            return distance; 
        }
        if((time >= r.timeStamp3) && (time < r.timeStamp4)){
            distance = r.distanceCP3 + (r.acceleration * (time - r.timeStamp3) * (time - r.timeStamp3));
            return distance;
        }
        if(time >= r.timeStamp4){
            distance = r.distanceCP4 + (r.topSpeed * (time - r.timeStamp4));
                if(distance > 600){
                    return 600;
                }
            return distance;
        }
        return distance;    
    } 
}
                
                    
