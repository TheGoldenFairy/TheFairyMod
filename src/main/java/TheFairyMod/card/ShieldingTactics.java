package TheFairyMod.card;

import TheFairyMod.TheFairyMod;
import TheFairyMod.character.TheGunner;
import TheFairyMod.util.CustomTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import static TheFairyMod.TheFairyMod.makeCardPath;

public class ShieldingTactics extends AbstractFairyCard {

    //card Info
    public static final String ID = TheFairyMod.makeID("ShieldingTactics");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = makeCardPath("Skill.png");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //card Stats
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheGunner.Enums.COLOR_BROWN;

    //card Number
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int BLOCK_PLUS = 3;
    private static final int BLOCK_NEXT_TURN = 5;
    private static final int BLOCK_NEXT_TURN_UPGRADE = 2;
    private static final int BLOCK_REQ = 4;

    //card Initialize
    public ShieldingTactics() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = BLOCK_REQ;
        fairySecondMagicNumber = fairyBaseSecondMagicNumber = BLOCK_NEXT_TURN;
        tags.add(CustomTags.REQUIRES);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Requires Block Action
        if (AbstractDungeon.player.currentBlock >= magicNumber) {
            AbstractDungeon.actionManager.addToBottom(new LoseBlockAction(p, p, magicNumber));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, fairySecondMagicNumber)));
        }

        //Normal Action
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_PLUS);
            upgradeFairySecondMagicNumber(BLOCK_NEXT_TURN_UPGRADE);
            initializeDescription();
        }
    }
}
