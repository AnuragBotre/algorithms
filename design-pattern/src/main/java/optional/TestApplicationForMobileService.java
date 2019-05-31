package optional;

public class TestApplicationForMobileService {

    public static void main(String[] args) {

        ScreenResolution screenResolution = new ScreenResolution(750,1334);
        DisplayFeatures displayFeatures = new DisplayFeatures("4.7",screenResolution);
        Mobile mobile = new Mobile(21000,"Apple","iphone 4",displayFeatures);

        MobileService mobileService = new MobileService();

        int mobileWidth = mobileService.getMobileWidth(mobile);
        System.out.println("Width :- " + mobileWidth);


        ScreenResolution resolution2 = new ScreenResolution(0,0);
        DisplayFeatures dfeatures2 = new DisplayFeatures("0", resolution2);
        Mobile mobile2 = new Mobile(2015001, "Apple", "iPhone 6s", dfeatures2);
        int mobileWidth2 = mobileService.getMobileWidth(mobile2);
        System.out.println("Apple iPhone 16s Screen Width = " + mobileWidth2);

    }

}
