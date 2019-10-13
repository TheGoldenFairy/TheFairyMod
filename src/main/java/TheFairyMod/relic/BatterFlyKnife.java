package TheFairyMod.relic;

import TheFairyMod.TheFairyMod;
import TheFairyMod.power.BleedPower;
import TheFairyMod.power.BulletPower;
import TheFairyMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static TheFairyMod.TheFairyMod.makeRelicOutlinePath;
import static TheFairyMod.TheFairyMod.makeRelicPath;

public class BatterFlyKnife extends CustomRelic {


    public static final String ID = TheFairyMod.makeID("BatterFlyKnife");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    private static final int BLEED_AMT = 4;
    private static final int BULLET_AMT = 3;

    public BatterFlyKnife() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    public void atBattleStartPreDraw() {
        flash();
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(mo, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new BleedPower(mo, BLEED_AMT)));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BulletPower(AbstractDungeon.player, BULLET_AMT)));
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(SwitchBlade.ID);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
