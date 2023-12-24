package club.someoneice.warpbook.item;

import club.someoneice.makpiraaqarvik.core.item.ItemBase;
import club.someoneice.makpiraaqarvik.util.Util;
import club.someoneice.warpbook.client.WarpBookGUI;
import club.someoneice.warpbook.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemWarpBook extends ItemBase {
    public ItemWarpBook() {
        super(Util.properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        super.use(world, player, hand);
        if (hand != InteractionHand.MAIN_HAND) return InteractionResultHolder.pass(player.getItemInHand(hand));
        var item = player.getMainHandItem();
        var offItem = player.getOffhandItem();
        if (offItem.getItem() == ItemInit.WARP_PAGE.asItem()) {
            if (world.isClientSide) return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), true);

            var nbtData = new CompoundTag();
            nbtData.putDouble("posX", player.getX());
            nbtData.putDouble("posY", player.getY());
            nbtData.putDouble("posZ", player.getZ());
            nbtData.putString("posWorld", world.dimension().location().toString());

            var nbt = item.getOrCreateTag();
            for (int i = 0; i < 5; i ++) {
                var name = "posData" + i;
                if (!nbt.contains(name)) {
                    nbt.put(name, nbtData);
                    return InteractionResultHolder.success(item);
                }
            }

            player.sendSystemMessage(Component.translatable("fail.addbook.message.warpbook"));
            return InteractionResultHolder.fail(item);
        }

        if (world.isClientSide) {
            Minecraft.getInstance().pushGuiLayer(new WarpBookGUI(item));
        }

        return InteractionResultHolder.success(item);
    }
}
