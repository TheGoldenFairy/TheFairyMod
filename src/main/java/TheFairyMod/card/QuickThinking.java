package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class QuickThinking extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("QuickThinking");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Skill.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //card Stats
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 1;
    private static final int BLOCK_REQ = 4;
    private static final int WEAK_AMT = 2;
    private static final int VUL_AMT = 1;
    private static final int UPGRADE_PLUS = 1;

    //card Initialize
    public QuickThinking() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK_REQ;
        magicNumber = baseMagicNumber = WEAK_AMT;
        fairySecondMagicNumber = fairyBaseSecondMagicNumber = VUL_AMT;
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
       if(AbstractDungeon.player.currentBlock < magicNumber) {
           return false;
       }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.loseBlock(BLOCK_REQ);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, true)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, fairySecondMagicNumber, true)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS);
            upgradeFairySecondMagicNumber(UPGRADE_PLUS);
            initializeDescription();
        }
    }
}
