package club.someoneice.warpbook.init;

import club.someoneice.warpbook.Main;
import club.someoneice.warpbook.block.BlockWarpTower;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockInit {
    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Main.MODID);

    public static DeferredBlock<Block> WARP_TOWER = BLOCKS.register("warp_tower", BlockWarpTower::new);
}
