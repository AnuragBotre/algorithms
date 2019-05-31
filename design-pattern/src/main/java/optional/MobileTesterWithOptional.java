package optional;

import java.util.Optional;

public class MobileTesterWithOptional {

    public static void main(String[] args) {
        ScreenResolution resolution = new ScreenResolution(750,1334);
        Optional<ScreenResolution> optionalScreenResolution = Optional.of(resolution);
        DisplayFeatures displayFeatures = new DisplayFeatures("7", optionalScreenResolution);
        Mobile mobile = new Mobile(123,"Apple","iPhone",Optional.of(displayFeatures));

        Optional<Mobile> optionalMobile = Optional.of(mobile);

        MobileService mobileService = new MobileService();
        int mobileWidth = mobileService.getMobileWidthWithOptional(optionalMobile);

        System.out.println(mobileWidth);

        Optional<ScreenResolution> nullableScreenResolution = Optional.ofNullable(null);
        DisplayFeatures displayFeatures1 = new DisplayFeatures("7", nullableScreenResolution);
        Mobile mobile1 = new Mobile(123,"Apple","iPhone",Optional.of(displayFeatures1));

        Optional<Mobile> optionalMobile1 = Optional.of(mobile1);

        mobileWidth = mobileService.getMobileWidthWithOptional(optionalMobile1);

        System.out.println(mobileWidth);

    }

}
