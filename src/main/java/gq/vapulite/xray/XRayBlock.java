package gq.vapulite.xray;

import net.minecraft.util.BlockPos;

public class XRayBlock {
    private final BlockPos blockPos;
    private final XRayData xRayData;

    public XRayBlock(BlockPos blockPos, XRayData xRayData) {
        this.blockPos = blockPos;
        this.xRayData = xRayData;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public XRayData getxRayData() {
        return this.xRayData;
    }
}

