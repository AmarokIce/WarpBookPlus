package club.someoneice.warpbook.item;


import club.someoneice.makpiraaqarvik.core.item.ItemBase;
import club.someoneice.makpiraaqarvik.util.Util;
import club.someoneice.warpbook.Config;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class ItemDeathBook extends ItemBase {
    public ItemDeathBook() {
        super(Util.properties.stacksTo(1));
    }

    public static final class DeathPage extends ItemBase {
        @Override
        public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
            var item = player.getItemInHand(hand);
            if (Config.usePageShouldExp && player.experienceLevel < 1) return InteractionResultHolder.fail(item);

            super.use(world, player, hand);
            if (player.level().isClientSide()) return InteractionResultHolder.sidedSuccess(item, true);

            var nbt = item.getOrCreateTag();
            if (nbt.contains("posX")) {
                final var x = nbt.getDouble("posX");
                final var y = nbt.getDouble("posY");
                final var z = nbt.getDouble("posZ");
                final var worldDeath = player.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbt.getString("posWorld"))));
                if (worldDeath == null) {
                    shrinkPage(item);
                    player.sendSystemMessage(Component.literal("This DeathPage is useless."));
                    return InteractionResultHolder.fail(item);
                }

                player.teleportTo(worldDeath, x, y, z, RelativeMovement.ALL, player.getYRot(), player.getXRot());
                if (Config.usePageShouldExp) player.experienceLevel -= 1;
            } else player.sendSystemMessage(Component.literal("This DeathPage is useless."));
            shrinkPage(item);
            return InteractionResultHolder.success(item);
        }

        private void shrinkPage(ItemStack item) {
            if (Config.onetimePage) item.shrink(1);
        }
    }
}
