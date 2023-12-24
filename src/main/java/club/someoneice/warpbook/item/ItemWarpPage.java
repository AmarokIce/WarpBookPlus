package club.someoneice.warpbook.item;

import club.someoneice.makpiraaqarvik.core.item.ItemBase;
import club.someoneice.warpbook.Config;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemWarpPage extends ItemBase {
    @Override
    public ItemStack finishUsingItem(ItemStack item, Level world, LivingEntity entity) {
        if (!(entity instanceof Player player)) return item;
        if (Config.usePageShouldExp && player.experienceLevel < 1) return item;

        var nbt = item.getOrCreateTag();
        if (!nbt.contains("posX")) return item;
        final var x = nbt.getDouble("posX");
        final var y = nbt.getDouble("posY");
        final var z = nbt.getDouble("posZ");
        final var level = player.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbt.getString("posWorld"))));
        if (level == null) return item;

        shrinkPage(item);
        if (Config.usePageShouldExp) player.experienceLevel -= 1;
        player.teleportTo(level, x, y, z, RelativeMovement.ALL, player.getYRot(), player.getXRot());

        return item;
    }

    private void shrinkPage(ItemStack item) {
        if (Config.onetimePage) item.shrink(1);
    }
}
