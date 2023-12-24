package club.someoneice.warpbook.event;

import club.someoneice.warpbook.Main;
import club.someoneice.warpbook.init.ItemInit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public final class PlayerDieEvent {
    @SubscribeEvent
    public void playerDieEvent(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.inventoryMenu.getItems().stream().anyMatch(it -> it.getItem() == ItemInit.DEATH_BOOK.get()))
                addDeathPageToPlayer(player);
        }
    }

    private void addDeathPageToPlayer(Player player) {
        var x = player.getX();
        var y = player.getY();
        var z = player.getZ();
        var world = player.level().dimension().location().toString();

        var item = new ItemStack(ItemInit.DEATH_PAGE.asItem());
        var nbt = item.getOrCreateTag();
        nbt.putDouble("posX", x);
        nbt.putDouble("posY", y);
        nbt.putDouble("posZ", z);
        nbt.putString("posWorld", world);

        player.addItem(item);
    }
}
