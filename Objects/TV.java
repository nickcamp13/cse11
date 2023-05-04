public class TV {
    // Data Fields
    int channel = 1;        // default channel is 1
    int volumeLevel = 1;    // default volume is 1
    boolean on = false;     // TV is off

    // Constructors
    public TV() {}      // public = other classes have access

    public void turnOn() {
        on = true;
    }

    public void turnOff() {
        on = false;
    }

    public void setChannel(int newChannel) {
        if (on && newChannel >= 1 && newChannel <= 120) {
            channel = newChannel;
        }
    }

    public void setVolume(int newVolume) {
        if (on && newVolume >= 0 && newVolume <= 100) {
            volumeLevel = newVolume;
        }
    }

    public void channelUp() {
        if (on && channel < 120) {
            channel++;
        }
    }

    public void channelDown() {
        if (on && channel > 1) {
            channel--;
        }
    }

    public void volumeUp() {
        if (on && volumeLevel < 100) {
            volumeLevel++;
        }
    }

    public void volumeDown() {
        if (on && volumeLevel > 0) {
            volumeLevel--;
        }
    }
}