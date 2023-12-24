package club.someoneice.warpbook.init;

import club.someoneice.warpbook.Main;
import club.someoneice.warpbook.block.TileWarpTower;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TileInit {
    public static DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Main.MODID);

    public static DeferredHolder<BlockEntityType<?>, ? extends BlockEntityType<?>> TILE_WARP_TOWER = TILES.register("warp_tower_tile", () -> BlockEntityType.Builder.of( TileWarpTower::new, BlockInit.WARP_TOWER.get()).build(null));
}
