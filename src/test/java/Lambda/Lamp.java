package Lambda;

import Lambda.ElectricityConsumer;

public class Lamp implements ElectricityConsumer {

    public void lampOn(){
        System.out.println("Лампа зажглась");
    }

    @Override
    public void electricityOn(Object sender) {
        lampOn();
    }
}
