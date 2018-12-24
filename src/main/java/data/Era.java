package data;

/**
 * Created by bruce on 7/11/2018.
 */
public class Era {
    private int eraId;
    private String eraName;
    private String eraBackground;
    private String eraMusic;
    // private int cost;

    public Era(String eraNAme, String eraBackground, String eraMusic) {
        this.eraName = eraNAme;
        this.eraBackground = eraBackground;
        this.eraMusic = eraMusic;
    }

    public int getEraId() {
        return eraId;
    }

    public String getEraName() {
        return eraName;
    }

    public String getEraBackground() {
        return eraBackground;
    }

    public String getEraMusic() {
        return eraMusic;
    }
}
