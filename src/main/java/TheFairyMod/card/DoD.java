package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.power.NextTurnDrawAndEnergyPower;
import TheFairyMod.util.CustomTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class DoD extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("DoD");
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
    private static final int COST = 1;
    private static final int NEW_COST = 0;
    private static final int BLOCK = 6;
    private static final int BLOCK_REQ = 4;
    private static final int CARD_DRAW_AMT = 2;
    private static final int ENERGY_AMT = 1;
    private static final int POWER_AMT = 1;

    //card Initialize
    public DoD() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber =  CARD_DRAW_AMT;
        fairySecondMagicNumber = fairyBaseSecondMagicNumber = BLOCK_REQ;
        tags.add(CustomTags.REQUIRES);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Requires Block Action
        if (AbstractDungeon.player.currentBlock < fairySecondMagicNumber) {
            AbstractDungeon.actionManager.addToBottom(new LoseBlockAction(p, p, fairySecondMagicNumber));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NextTurnDrawAndEnergyPower(p, POWER_AMT, magicNumber, ENERGY_AMT)));
        }

        //Normal Action
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
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
