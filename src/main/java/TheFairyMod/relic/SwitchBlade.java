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

public class SwitchBlade extends CustomRelic {

    public static final String ID = TheFairyMod.makeID("SwitchBlade");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    private static final int BLEED_AMT = 2;
    private static final int BULLET_AMT = 1;

    public SwitchBlade() {
        super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    public void atBattleStartPreDraw() {
        flash();
        AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(m, this));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new BleedPower(m, BLEED_AMT)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BulletPower(AbstractDungeon.player, BULLET_AMT)));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
