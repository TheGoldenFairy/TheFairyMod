package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.util.CustomTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import jdk.nashorn.internal.ir.Block;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class HastyRetreat extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("HastyRetreat");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Skill.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //card Stats
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 2;
    private static final int BLOCK = 14;
    private static final int UPGRADE_PLUS_BLOCK = 5;
    private static final int SECOND_BLOCK = 6;
    private static final int UPGRADE_PLUS_SECOND_BLOCK = 1;
    private static final int DEX_REQ = 1;

    //card Initialize
    public HastyRetreat() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = SECOND_BLOCK;
        fairySecondMagicNumber = fairyBaseSecondMagicNumber = DEX_REQ;
        tags.add(CustomTags.REQUIRES);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        if(p.hasPower(DexterityPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, magicNumber + p.getPower(DexterityPower.POWER_ID).amount));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, DexterityPower.POWER_ID, DEX_REQ));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_PLUS_SECOND_BLOCK);
            initializeDescription();
        }
    }
}
