package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.power.BulletPower;
import TheFairyMod.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class MagnaPistal extends AbstractFairyCard {
    //card Info
    public static final String ID = TheFairyMod.makeID("MagnaPistal");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Attack.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    //card Stats
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int CARD_DRAW_AMT = 1;
    private static final int BULLET_AMT = 2;
    private static final int ADDITIONAL_DMG = 6;

    //card Initialize
    public MagnaPistal() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = BULLET_AMT;
        fairySecondMagicNumber = fairyBaseSecondMagicNumber = ADDITIONAL_DMG;
        tags.add(CustomTags.BULLET);
        tags.add(CustomTags.REQUIRES);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Normal Action
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        //Bullet Required Action
        if(AbstractDungeon.player.hasPower(BulletPower.POWER_ID) && AbstractDungeon.player.getPower(BulletPower.POWER_ID).amount >= magicNumber) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, fairySecondMagicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, BulletPower.POWER_ID, magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, CARD_DRAW_AMT));
        }

        //Normal Action
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, CARD_DRAW_AMT));
    }

    //For Calculating the Damage
    @Override
    public void applyPowers() {
        int originalDamage = baseDamage;
        baseDamage = fairyBaseSecondMagicNumber;
        super.applyPowers();
        fairySecondMagicNumber = damage;
        isFairySecondMagicNumberModified = isDamageModified;
        baseDamage = originalDamage;
        super.applyPowers();
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int originalDamage = baseDamage;
        baseDamage = fairyBaseSecondMagicNumber;
        super.calculateCardDamage(mo);
        fairySecondMagicNumber = damage;
        isFairySecondMagicNumberModified = isDamageModified;
        baseDamage = originalDamage;
        super.calculateCardDamage(mo);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
