package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.power.BulletPower;
import TheFairyMod.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class ScarredL extends AbstractFairyCard {
    //card Info
    public static final String ID = TheFairyMod.makeID("ScarredL");
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
    private static final int COST = 2;
    private static final int DAMAGE = 13;
    private static final int BLOCK = 13;
    private static final int UPGRADE_PLUS = 4;
    private static final int BULLET_AMT = 2;

    //card Initialize
    public ScarredL() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = BULLET_AMT;
        tags.add(CustomTags.BULLET);
        tags.add(CustomTags.REQUIRES);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        if(!AbstractDungeon.player.hasPower(BulletPower.POWER_ID)) {
            return false;
        }

        if(AbstractDungeon.player.hasPower(BulletPower.POWER_ID) && AbstractDungeon.player.getPower(BulletPower.POWER_ID).amount < magicNumber) {
            return false;
        }
        return true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS);
            upgradeBlock(UPGRADE_PLUS);
            initializeDescription();
        }
    }
}
