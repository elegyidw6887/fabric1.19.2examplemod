package org.example.examplefabricmod.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.example.examplefabricmod.util.ModEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements ModEntityDataSaver {
    @Unique
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, @SuppressWarnings("rawtypes") CallbackInfoReturnable info) {
        if (this.persistentData != null) {
            nbt.put("examplefabricmod.data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("examplefabricmod.data", 10)) {
            persistentData = nbt.getCompound("examplefabricmod.data");
        }
    }
}
