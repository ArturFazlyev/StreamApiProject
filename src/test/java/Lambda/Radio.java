package Lambda;

import Lambda.ElectricityConsumer;

public class Radio implements ElectricityConsumer {

    public void playMusic(){
        System.out.println("Lambda.Radio plays");
    }

    @Override
    public void electricityOn(Object sender) {
        playMusic();
    }
}
