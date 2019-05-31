package optional;

import java.util.Optional;

public class DisplayFeatures {

    private Optional<ScreenResolution> optionalScreenResolution;
    private String size; // In inches
    private ScreenResolution resolution;

    public DisplayFeatures(String size, ScreenResolution resolution) {
        this.size = size;
        this.resolution = resolution;
    }

    public DisplayFeatures(String size, Optional<ScreenResolution> optionalScreenResolution) {
        this.size = size;
        this.optionalScreenResolution = optionalScreenResolution;
    }

    public String getSize() {
        return size;
    }

    public ScreenResolution getResolution() {
        return resolution;
    }

    public Optional<ScreenResolution> getOptionalScreenResolution() {
        return optionalScreenResolution;
    }
}
