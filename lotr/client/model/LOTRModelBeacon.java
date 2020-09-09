package lotr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LOTRModelBeacon extends ModelBase {
    private ModelRenderer base;
    private ModelRenderer[][] logs = new ModelRenderer[3][4];

    public LOTRModelBeacon() {
        this.base = new ModelRenderer(this, 0, 0);
        this.base.addBox(-8.0f, -8.0f, -2.0f, 16, 16, 4);
        this.base.setRotationPoint(0.0f, 22.0f, 0.0f);
        this.base.rotateAngleX = 1.5707964f;
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.logs[i][j] = new ModelRenderer(this, 30, 15);
                this.logs[i][j].addBox(-1.5f, 0.0f, -7.0f, 3, 3, 14);
                this.logs[i][j].setRotationPoint(i != 1 ? -6.0f + j * 4.0f : 0.0f, 17.0f - i * 3.0f, i == 1 ? -6.0f + j * 4.0f : 0.0f);
                if(i != 1) continue;
                this.logs[i][j].rotateAngleY = 1.5707964f;
            }
        }
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.base.render(f5);
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.logs[i][j].render(f5);
            }
        }
    }
}
