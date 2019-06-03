package optional;

import java.util.Optional;

public class Mobile {

    private Optional<DisplayFeatures> optionalDisplayFeatures;
    private long id;
    private String brand;
    private String name;
    private DisplayFeatures displayFeatures;

    public Mobile(long id, String brand, String name, DisplayFeatures displayFeatures) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.displayFeatures = displayFeatures;
    }

    public Mobile(int i, String apple, String iPhone, Optional<DisplayFeatures> displayFeatures) {
        this.id = i;
        this.brand = apple;
        this.name = iPhone;
        this.optionalDisplayFeatures = displayFeatures;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public DisplayFeatures getDisplayFeatures() {
        return displayFeatures;
    }

    public Optional<DisplayFeatures> getOptionalDisplayFeatures() {
        return optionalDisplayFeatures;
    }
}
