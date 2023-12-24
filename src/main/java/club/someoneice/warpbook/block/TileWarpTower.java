package club.someoneice.warpbook.block;

import club.someoneice.makpiraaqarvik.api.TileNbt;
import club.someoneice.makpiraaqarvik.tile.TileBase;
import club.someoneice.warpbook.init.TileInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public final class TileWarpTower extends TileBase {
    @TileNbt(name = "hasPos") public boolean hasPos = false;
    @TileNbt(name = "posX") public int posX = 0;
    @TileNbt(name = "posY") public int posY = 0;
    @TileNbt(name = "posZ") public int posZ = 0;
    @TileNbt(name = "world") public String world = "overworld";

    public TileWarpTower(BlockPos pos, BlockState state) {
        super(TileInit.TILE_WARP_TOWER.get(), pos, state);
    }

    public void setPos(int x, int y, int z, String world) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.world = world;

        this.hasPos = true;
    }

    @Override
    public void writeToNbt(CompoundTag nbt) {}

    @Override
    public void readFromNbt(CompoundTag nbt) {}
}
