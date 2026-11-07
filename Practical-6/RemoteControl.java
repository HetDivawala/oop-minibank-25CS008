interface Switchable
{
    public abstract void on();
    public abstract void off();
    public abstract boolean isOn();

    public default void toggle()
    {
        if(isOn()){
            off();
        }
        else{
            on();
        }
    }
}

class Fan implements Switchable
{
    private boolean state;

    public void on(){ state = true; System.out.println("Fan ON"); }
    public void off(){ state = false; System.out.println("Fan OFF"); }
    public boolean isOn(){ return state; }
}

class Light implements Switchable
{
    private boolean state;

    public void on(){ state = true; System.out.println("Light ON"); }
    public void off(){ state = false; System.out.println("Light OFF"); }
    public boolean isOn(){ return state; }
}

interface SwitchRule
{
    boolean mayTurnOn(Switchable device, int hour);
}

public class RemoteControl {
    public static void main(String[] args){

        Switchable[] devices = {new Fan(), new Light(), new Fan()};

        for(int i=0; i<2; i++){
            System.out.println("Toggle round " + (i+1));
            for(Switchable d : devices){
                d.toggle();
            }
        }

        // anonymous class
        SwitchRule daytime = new SwitchRule(){
            @Override
            public boolean mayTurnOn(Switchable device, int hour){
                return hour >= 6 && hour <= 22;
            }
        };

        // same rule as a lambda
        SwitchRule daytimeLambda = (device, hour) -> hour >= 6 && hour <= 22;

        int[] hours = {3, 6, 14, 22, 23};

        for(int h : hours){
            System.out.println("Hour " + h + " -> anonymous: " + daytime.mayTurnOn(devices[0], h) + " | lambda: " + daytimeLambda.mayTurnOn(devices[0], h));
        }
    }
}