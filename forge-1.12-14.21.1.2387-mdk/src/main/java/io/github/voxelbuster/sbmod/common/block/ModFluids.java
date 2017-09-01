package io.github.voxelbuster.sbmod.common.block;

import io.github.voxelbuster.sbmod.common.StarboundMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = StarboundMod.MODID)
public class ModFluids {

    public static final Fluid erichiusfuel = new Fluid("erichiusfuel",
            new ResourceLocation(StarboundMod.MODID, "fuelstill"), new ResourceLocation(StarboundMod.MODID, "fuelflowing"))
            .setDensity(2000).setLuminosity(10).setViscosity(100);

    public static final Fluid oil = new Fluid("oil",
            new ResourceLocation(StarboundMod.MODID, "oilstill"), new ResourceLocation(StarboundMod.MODID, "oilflowing"))
            .setDensity(1200).setLuminosity(0).setViscosity(400);

    public static final Fluid healingwater = new Fluid("healingwater",
            new ResourceLocation(StarboundMod.MODID, "healingwaterstill"), new ResourceLocation(StarboundMod.MODID, "healingwaterflowing"))
            .setDensity(1600).setLuminosity(10).setViscosity(20);

    public static BlockFluidClassic fuelblock;
    public static BlockFluidClassic oilblock;
    public static BlockFluidClassic hwaterblock;

    public static void registerFluids() {
        FluidRegistry.registerFluid(erichiusfuel);
        FluidRegistry.addBucketForFluid(erichiusfuel);
        FluidRegistry.registerFluid(oil);
        FluidRegistry.addBucketForFluid(oil);
        FluidRegistry.registerFluid(healingwater);
        FluidRegistry.addBucketForFluid(healingwater);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        fuelblock = (BlockFluidClassic) new BlockFluidClassic(erichiusfuel, Material.WATER).setRegistryName(StarboundMod.MODID, "erichiusfuel").setUnlocalizedName("erichiusfuel")
                .setCreativeTab(StarboundMod.creativeTab);
        oilblock = (BlockFluidClassic) new BlockFluidClassic(oil, Material.WATER).setRegistryName(StarboundMod.MODID, "oil").setUnlocalizedName("oil")
                .setCreativeTab(StarboundMod.creativeTab);
        hwaterblock = (BlockFluidClassic) new BlockFluidClassic(healingwater, Material.WATER).setRegistryName(StarboundMod.MODID, "healingwater").setUnlocalizedName("healingwater")
                .setCreativeTab(StarboundMod.creativeTab);

        registry.register(fuelblock);
        registry.register(oilblock);
        registry.register(hwaterblock);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ItemBlock fuelitem = new ItemBlock(fuelblock);
        fuelitem.setRegistryName(StarboundMod.MODID, "erichiusfuel");
        ItemBlock oilitem = new ItemBlock(oilblock);
        oilitem.setRegistryName(StarboundMod.MODID, "oil");
        ItemBlock hwateritem = new ItemBlock(hwaterblock);
        hwateritem.setRegistryName(StarboundMod.MODID, "healingwater");

        registry.register(fuelitem);
        registry.register(oilitem);
        registry.register(hwateritem);

        renderFluids();
    }

    @SideOnly(Side.CLIENT)
    public static void renderFluids() {
        ModelLoader.setCustomStateMapper(fuelblock, new StateMap.Builder().ignore(BlockFluidClassic.LEVEL).build());
        ModelLoader.setCustomStateMapper(oilblock, new StateMap.Builder().ignore(BlockFluidClassic.LEVEL).build());
        ModelLoader.setCustomStateMapper(hwaterblock, new StateMap.Builder().ignore(BlockFluidClassic.LEVEL).build());
    }
}