package club.someoneice.warpbook;

import club.someoneice.warpbook.init.BlockInit;
import club.someoneice.warpbook.init.ItemInit;
import club.someoneice.warpbook.init.TileInit;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.simple.MessageFunctions;
import net.neoforged.neoforge.network.simple.SimpleChannel;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "warpbook";

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public static <T> void addNetworkMessage(Class<T> messageType, MessageFunctions.MessageEncoder<T> encoder, MessageFunctions.MessageDecoder<T> decoder, MessageFunctions.MessageConsumer<T> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    public Main() {
        final var bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        TileInit.TILES.register(bus);
    }
}
