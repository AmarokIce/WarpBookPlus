package club.someoneice.warpbook.event;

import club.someoneice.warpbook.Main;
import club.someoneice.warpbook.network.PackageButton;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class CommonEvent {
    @SubscribeEvent
    public void commonEvent(FMLCommonSetupEvent event) {
        Main.addNetworkMessage(PackageButton.class, PackageButton::buff, PackageButton::new, PackageButton::handler);
    }
}
