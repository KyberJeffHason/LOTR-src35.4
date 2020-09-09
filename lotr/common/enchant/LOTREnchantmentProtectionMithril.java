package lotr.common.enchant;

import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtectionMithril extends LOTREnchantmentProtectionSpecial {
    public LOTREnchantmentProtectionMithril(String s) {
        super(s, 1);
        this.setValueModifier(2.0f);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted("lotr.enchant.protectMithril.desc", this.formatAdditiveInt(this.calcIntProtection()));
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        if(super.canApply(itemstack, considering)) {
            Item item = itemstack.getItem();
            return item instanceof ItemArmor && ((ItemArmor) item).getArmorMaterial() == LOTRMaterial.MITHRIL.toArmorMaterial();
        }
        return false;
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        ItemStack weapon;
        Entity attacker = source.getEntity();
        Entity entity = source.getSourceOfDamage();
        if(attacker instanceof EntityLivingBase && attacker == entity && (weapon = ((EntityLivingBase) attacker).getHeldItem()) != null) {
            ItemStack weaponBase = weapon.copy();
            LOTREnchantmentHelper.clearEnchants(weaponBase);
            float range = LOTRWeaponStats.getMeleeReachFactor(weaponBase);
            if(range >= 1.3f) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected int calcIntProtection() {
        return 4;
    }
}
