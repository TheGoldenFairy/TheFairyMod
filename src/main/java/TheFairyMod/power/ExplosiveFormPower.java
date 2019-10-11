package TheFairyMod.power;

import TheFairyMod.TheFairyMod;
import TheFairyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static TheFairyMod.TheFairyMod.makePowerPath;

public class ExplosiveFormPower extends AbstractPower {

    public static final String POWER_ID = TheFairyMod.makeID("ExplosiveFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    private static int AMT_THIS_TURN = 0;

    public ExplosiveFormPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = true;
        AMT_THIS_TURN = amount;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (AMT_THIS_TURN > 0 ) {
            if (type == DamageInfo.DamageType.NORMAL) {
                damage *= 2;
            }
        }
        return damage;
    }

    @Override
    public void atStartOfTurn() {
        AMT_THIS_TURN = amount;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK) {
            AMT_THIS_TURN--;
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
