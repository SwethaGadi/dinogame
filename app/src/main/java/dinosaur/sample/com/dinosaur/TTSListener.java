package dinosaur.sample.com.dinosaur;

public interface TTSListener {

    public void init();

    public void start(String utteranceID);

    public void end(String utteranceID);
}
