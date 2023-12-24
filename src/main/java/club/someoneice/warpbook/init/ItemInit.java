package club.someoneice.warpbook.init;

import club.someoneice.warpbook.Main;
import club.someoneice.warpbook.item.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MODID);

    public static DeferredItem<Item> ENDER_GEM = ITEMS.register("ender_gem", ItemEnderGem::new);

    public static DeferredItem<Item> WARP_PAGE = ITEMS.register("warp_page", ItemWarpPage::new);
    public static DeferredItem<Item> WARP_BOOK = ITEMS.register("warp_book", ItemWarpBook::new);
    public static DeferredItem<Item> WARP_POTION = ITEMS.register("warp_potion", ItemWarpPotion::new);

    public static DeferredItem<Item> DEATH_BOOK = ITEMS.register("death_book", ItemDeathBook::new);
    public static DeferredItem<Item> DEATH_PAGE = ITEMS.register("death_page", ItemDeathBook.DeathPage::new);

    public static DeferredItem<BlockItem> WARP_TOWER_ITEM = ITEMS.registerSimpleBlockItem("warp_tower_item", () -> BlockInit.WARP_TOWER.get());
}
