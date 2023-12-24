package club.someoneice.warpbook.item;

import club.someoneice.makpiraaqarvik.core.item.ItemBase;
import club.someoneice.makpiraaqarvik.util.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ItemWarpPotion extends ItemBase {
    public ItemWarpPotion() {
        super(Util.properties.stacksTo(1));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack item, Level world, LivingEntity entity) {
        super.finishUsingItem(item, world, entity);
        if (!(entity instanceof Player player)) return item;
        var nbt = item.getOrCreateTag();
        if (!nbt.contains("posX")) return item;
        final var x = nbt.getDouble("posX");
        final var y = nbt.getDouble("posY");
        final var z = nbt.getDouble("posZ");
        final var worldDeath = player.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbt.getString("posWorld"))));
        if (worldDeath == null) return item;

        player.teleportTo(worldDeath, x, y, z, RelativeMovement.ALL, player.getYRot(), player.getXRot());

        return item;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack item) {
        return UseAnim.DRINK;
    }
}
