package TheFairyMod.patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(
        clz = CardGroup.class,
        method = "refreshHandLayout"
)

@SuppressWarnings("unused")
public class applyPowersFixPatch {
    private static final Logger logger = LogManager.getLogger(applyPowersFixPatch.class.getName());

    @SpireInsertPatch( locator = Locator.class)

    public static void thisIsOurActualPatchMethod() {
        AbstractDungeon.onModifyPower();
    }


    private static class Locator extends SpireInsertLocator { // Hey welcome to our SpireInsertLocator class!
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getCurrRoom");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
