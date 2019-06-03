package optional;

import java.util.Optional;

public class MobileService {

    public int getMobileWidth(Mobile mobile) {

        //without optional
        if (mobile != null) {
            DisplayFeatures displayFeatures = mobile.getDisplayFeatures();
            if(displayFeatures != null){
                ScreenResolution resolution = displayFeatures.getResolution();
                if(resolution != null){
                    return resolution.getWidth();
                }
            }
        }
        return 0;
    }

    public int getMobileWidthWithOptional(Optional<Mobile> mobile) {
        return mobile
                .flatMap(mobile1 -> mobile1.getOptionalDisplayFeatures())
                .flatMap(displayFeatures -> displayFeatures.getOptionalScreenResolution())
                .map(screenResolution -> screenResolution.getWidth()).orElse(0);
    }
}
