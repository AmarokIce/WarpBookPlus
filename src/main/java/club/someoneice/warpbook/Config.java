package club.someoneice.warpbook;

import club.someoneice.makpiraaqarvik.api.IPineappleConfig;
import club.someoneice.makpiraaqarvik.config.ConfigBean;

public class Config extends ConfigBean implements IPineappleConfig {
    public Config() {
        super(Main.MODID);
    }

    public static boolean onetimePage = true;
    public static boolean usePageShouldExp = true;
    public static boolean useBookShouldExp = true;
    public static boolean useWarpTowerShouldExp = false;

    public void init() {
        this.addNote("If the Warp Pages is one-time, return true.");
        onetimePage = this.getBoolean("onetimePage", onetimePage);
        this.addEnter();

        this.addNote("If player use the Warp Pages should exp, return true.");
        usePageShouldExp = this.getBoolean("UsePageShouldEXP", usePageShouldExp);
        this.addEnter();

        this.addNote("If player use the Warp Books should exp, return true.");
        useBookShouldExp = this.getBoolean("useBookShouldExp", useBookShouldExp);
        this.addEnter();

        this.addNote("If player use the Warp Tower should exp, return true.");
        useWarpTowerShouldExp = this.getBoolean("useWarpTowerShouldExp", useWarpTowerShouldExp);
        this.addEnter();

        this.build();
    }
}
