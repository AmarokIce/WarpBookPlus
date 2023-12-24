package club.someoneice.warpbook.block;

import club.someoneice.warpbook.Config;
import club.someoneice.warpbook.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BlockWarpTower extends BaseEntityBlock {
    public BlockWarpTower() {
        super(Properties.copy(Blocks.QUARTZ_BLOCK));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!(world.getBlockEntity(pos) instanceof TileWarpTower tile)) return InteractionResult.PASS;
        if (player.getItemInHand(hand).getItem() == ItemInit.WARP_POTION.asItem()) {
            var item = player.getItemInHand(hand);
            var nbt = item.getOrCreateTag();
            if (!nbt.contains("posX")) return InteractionResult.PASS;

            final var x = nbt.getDouble("posX");
            final var y = nbt.getDouble("posY");
            final var z = nbt.getDouble("posZ");

            tile.setPos((int) Math.round(x), (int) Math.round(y), (int) Math.round(z), nbt.getString("posWorld"));
            item.shrink(1);
            return InteractionResult.SUCCESS;
        }

        if (Config.useWarpTowerShouldExp && player.experienceLevel < 1) return InteractionResult.PASS;
        if (!tile.hasPos) return InteractionResult.PASS;

        final var x = tile.posX;
        final var y = tile.posY;
        final var z = tile.posZ;
        final var level = player.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(tile.world)));
        if (level == null) return InteractionResult.FAIL;
        player.teleportTo(level, x, y, z, RelativeMovement.ALL, player.getYRot(), player.getXRot());

        if (Config.useWarpTowerShouldExp) player.experienceLevel -= 1;
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileWarpTower(pos, state);
    }
}
