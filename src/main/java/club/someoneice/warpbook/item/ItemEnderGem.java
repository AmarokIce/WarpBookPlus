package club.someoneice.warpbook.item;

import club.someoneice.makpiraaqarvik.core.item.ItemBase;
import club.someoneice.warpbook.init.ItemInit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ItemEnderGem extends ItemBase {
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        super.use(world, player, hand);
        var item = player.getItemInHand(hand);

        final var x = player.getX();
        final var y = player.getY();
        final var z = player.getZ();
        final var level = player.level().dimension().location().toString();

        if (hand == InteractionHand.MAIN_HAND) {
            var itemOff = player.getOffhandItem();
            if (itemOff.getItem() == Items.PAPER) {
                itemOff.shrink(1);
                item.shrink(1);

                var itemWarpPage = new ItemStack(ItemInit.WARP_PAGE.asItem());

                var nbt = itemWarpPage.getOrCreateTag();
                nbt.putDouble("posX", x);
                nbt.putDouble("posY", y);
                nbt.putDouble("posZ", z);
                nbt.putString("posWorld", level);

                player.addItem(itemWarpPage);

                return InteractionResultHolder.success(item);
            } else if (itemOff.getItem() == ItemInit.WARP_POTION.asItem()) {
                item.shrink(1);

                var nbt = itemOff.getOrCreateTag();
                nbt.putDouble("posX", x);
                nbt.putDouble("posY", y);
                nbt.putDouble("posZ", z);
                nbt.putString("posWorld", level);

                return InteractionResultHolder.success(item);
            }
        }

        var nbt = item.getOrCreateTag();
        nbt.putDouble("posX", x);
        nbt.putDouble("posY", y);
        nbt.putDouble("posZ", z);
        nbt.putString("posWorld", level);

        return InteractionResultHolder.success(item);
    }
}
