package PublisherSubscriberExample.src.main;

import modules.Totems;
import options.TotemOptions;

public class MainApp {
    public static void main(String[] args) {
        TotemOptions totemOptions = new TotemOptions();

        @SuppressWarnings("unused")
        Totems totems = new Totems();

        totemOptions.setOption("enabled", "true");
        totemOptions.setOption("Bar height", "35");
    }
}
