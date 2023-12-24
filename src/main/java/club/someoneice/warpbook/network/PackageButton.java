package club.someoneice.warpbook.network;

import club.someoneice.warpbook.Config;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.NetworkEvent;
public class PackageButton {
    private final int buttonID;

    public PackageButton(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
    }


    public PackageButton(int buttonID) {
        this.buttonID = buttonID;
    }

    public static void buff(PackageButton message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
    }

    public static void handler(PackageButton message, NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            Player player = context.getSender();
            int buttonID = message.buttonID;
            handleButtonAction(player, buttonID);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player player, int buttonID) {
        ItemStack item = player.getMainHandItem();
        String name = "posData" + buttonID;
        var nbt = item.getOrCreateTag();
        if (!nbt.contains(name)) return;
        if (Config.useBookShouldExp && player.experienceLevel < 1) return;

        var nbtData = nbt.getCompound(name);
        var x = nbtData.getDouble("posX");
        var y = nbtData.getDouble("posY");
        var z = nbtData.getDouble("posZ");
        var level = player.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbtData.getString("posWorld"))));
        if (level == null) return;

        if (Config.useBookShouldExp) player.experienceLevel -= 1;
        player.teleportTo(level, x, y, z, RelativeMovement.ALL, player.getYRot(), player.getXRot());
    }

}
