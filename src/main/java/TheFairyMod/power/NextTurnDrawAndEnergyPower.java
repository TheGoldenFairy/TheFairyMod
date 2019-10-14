package TheFairyMod.power;

import TheFairyMod.TheFairyMod;
import TheFairyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static TheFairyMod.TheFairyMod.makePowerPath;

public class NextTurnDrawAndEnergyPower extends AbstractPower {

    private static final String POWER_ID = TheFairyMod.makeID("NextTurnDrawAndEnergyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    private int CARD_DRAW_AMT = 0;
    private int ENERGY_AMT = 0;

    public NextTurnDrawAndEnergyPower(final AbstractCreature owner, final int amount, final int CARD_DRAW, final int ENERGY) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = true;
        CARD_DRAW_AMT += CARD_DRAW;
        ENERGY_AMT += ENERGY;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, CARD_DRAW_AMT));
        AbstractDungeon.actionManager.addToTop(new GainEnergyAction(ENERGY_AMT));
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        CARD_DRAW_AMT = 0;
        ENERGY_AMT = 0;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + CARD_DRAW_AMT + DESCRIPTIONS[1] + ENERGY_AMT + DESCRIPTIONS[2];
    }
}
