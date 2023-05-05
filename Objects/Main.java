public class Main {
    public static void main(String[] args) {
        TV tv1 = new TV();
        tv1.turnOn();
        tv1.setChannel(30);
        tv1.setVolume(30);

        TV tv2 = new TV();
        tv2.turnOn();
        tv2.channelUp();
        tv2.channelUp();
        tv2.volumeUp();

        System.out.println("TV1 Diagnostics");
        System.out.println("Channel: " + tv1.channel);
        System.out.println("Volume: " + tv1.volumeLevel);
        
        System.out.println("TV2 Diagnostics");
        System.out.println("Channel: " + tv2.channel);
        System.out.println("Volume: " + tv2.volumeLevel);


        Account[] accounts = new Account[10];
        for (int i = 0; i < 10; i++) {
            accounts[i] = new Account(i, 100);
        }
    }
}