package com.plop.cubeplus.common.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.plop.cubeplus.CubePlus;
import com.plop.cubeplus.common.blockentity.cpNewRoofEntity;
import com.plop.cubeplus.common.setup.ModModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;

public class cpNewRoofRenderer implements BlockEntityRenderer<cpNewRoofEntity>
{
    public static final ResourceLocation TEXTURE1 = new ResourceLocation(CubePlus.MOD_ID, "entity/new_roof");
    public static final ResourceLocation TEXTURE2 = new ResourceLocation(CubePlus.MOD_ID, "entity/new_roof2");

    public static final Material MATERIAL1 = new Material(TextureAtlas.LOCATION_BLOCKS, TEXTURE1);
    public static final Material MATERIAL2 = new Material(TextureAtlas.LOCATION_BLOCKS, TEXTURE2);

    private final ModelPart new_roof;
    private final BlockEntityRenderDispatcher renderer;

    public cpNewRoofRenderer(BlockEntityRendererProvider.Context context)
    {
        this.renderer = context.getBlockEntityRenderDispatcher();
        this.new_roof = context.bakeLayer(ModModelLayers.NEW_ROOF);
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("new_roof", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F,
                        new CubeDeformation(0.0F))
                .texOffs(0, 15)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 1.0F, 8.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void render(cpNewRoofEntity entity, float p_112308_, PoseStack stack, MultiBufferSource buffer, int p_112311_, int p_112312_)
    {
        VertexConsumer vertexconsumer = MATERIAL1.buffer(buffer, RenderType::entitySolid);
        stack.pushPose();
        stack.translate(0.5f, 1.5f, 0.5f);
        stack.mulPose(Vector3f.XP.rotationDegrees(180));
        this.new_roof.render(stack, vertexconsumer, p_112311_, p_112312_);
        stack.popPose();
    }
}
