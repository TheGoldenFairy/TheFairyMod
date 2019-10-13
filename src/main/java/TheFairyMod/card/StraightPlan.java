package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.power.BleedPower;

import TheFairyMod.power.BulletPower;
import TheFairyMod.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class StraightPlan extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("StraightPlan");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Skill.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //card Stats
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 1;
    private static final int BLEED_AND_VUL_AMT = 2;
    private static final int UPGRADE_PLUS = 1;
    private static final int BULLET_AMT = 1;
    private static final int BLEED_ADDITIONAL_AMT = 2;

    //card Initialize
    public StraightPlan() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BULLET_AMT;
        fairySecondMagicNumber = fairyBaseSecondMagicNumber = BLEED_AND_VUL_AMT;
        tags.add(CustomTags.REQUIRES);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Normal Action
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BleedPower(m, fairyBaseSecondMagicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, fairySecondMagicNumber, true)));

        //Bullet Required Action
        if(AbstractDungeon.player.hasPower(BulletPower.POWER_ID) && AbstractDungeon.player.getPower(BulletPower.POWER_ID).amount >= magicNumber) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BleedPower(m, BLEED_ADDITIONAL_AMT)));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, BulletPower.POWER_ID, magicNumber));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeFairySecondMagicNumber(UPGRADE_PLUS);
            initializeDescription();
        }
    }
}
