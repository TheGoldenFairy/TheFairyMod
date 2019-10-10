package TheFairyMod.power;

import TheFairyMod.TheFairyMod;
import TheFairyMod.util.CustomTags;
import TheFairyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static TheFairyMod.TheFairyMod.makePowerPath;

public class EfficientAmmoPower extends AbstractPower {

    public static final String POWER_ID = TheFairyMod.makeID("EfficientAmmoPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    private static int PREVIOUS_AMT = 0;

    public EfficientAmmoPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
        updateExistingBulletCards();
    }

    private void updateExistingBulletCards() {
        if (PREVIOUS_AMT < amount) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(CustomTags.REQUIRES) && c.hasTag(CustomTags.BULLET)) {
                    if (c.baseMagicNumber > 0) {
                        c.baseMagicNumber = c.baseMagicNumber - amount;
                    PREVIOUS_AMT = amount;
                    }
                }
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.hasTag(CustomTags.REQUIRES) && c.hasTag(CustomTags.BULLET)) {
                    if (c.baseMagicNumber > 0) {
                        c.baseMagicNumber = c.baseMagicNumber - amount;
                        PREVIOUS_AMT = amount;
                    }
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c.hasTag(CustomTags.REQUIRES) && c.hasTag(CustomTags.BULLET)) {
                    if (c.baseMagicNumber > 0) {
                        c.baseMagicNumber = c.baseMagicNumber - amount;
                        PREVIOUS_AMT = amount;
                    }
                }
            }
            for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                if (c.hasTag(CustomTags.REQUIRES) && c.hasTag(CustomTags.BULLET)) {
                    if (c.baseMagicNumber > 0) {
                        c.baseMagicNumber = c.baseMagicNumber - amount;
                        PREVIOUS_AMT = amount;
                    }
                }
            }
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if(amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
        else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }    }
}
