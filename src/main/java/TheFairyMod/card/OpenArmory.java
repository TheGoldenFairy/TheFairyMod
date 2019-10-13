package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.power.BulletPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class OpenArmory extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("OpenArmory");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Skill.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //card Stats
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 2;
    private static final int NEW_COST = 1;
    private static final int BULLET_AMT = 15;


    //card Initialize
    public OpenArmory() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BULLET_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BulletPower(p, magicNumber)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(NEW_COST);
            initializeDescription();
        }
    }
}
