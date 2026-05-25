package liquidustry.content;

// import arc.graphics.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;
import static mindustry.type.ItemStack.*;

import liquidustry.world.blocks.distribution.GelationBlock;
import liquidustry.world.blocks.distribution.LiquidizerBlock;
import mindustry.world.blocks.liquid.*;

public class LDBlocks{
    public static Block

    // region distribution - Erekir

    gelationStation, liquidizer;

    // endregion

    public static void load(){

        gelationStation = new GelationBlock("gelation-station"){{
            requirements(Category.distribution, with(
                Items.graphite, 120,
                Items.silicon, 100,
                Items.surgeAlloy, 20
            ));
            health = 200;
            size = 3;
            update = true;
            solid = true;
            hasItems = true;
            hasLiquids = true;
            sync = true;
            destructible = true;
            itemCapacity = 20;
            liquidCapacity = 200f;
            consumePower(0.5f);
            hasPower = true;
            configurable = true;
            saveConfig = true;
        }};

        liquidizer = new LiquidizerBlock("liquidizer"){{
            requirements(Category.distribution, with(
                Items.graphite, 20,
                Items.silicon, 30
            ));
            health = 200;
            size = 1;
            update = true;
            solid = true;
            hasItems = true;
            hasLiquids = true;
            sync = true;
            destructible = true;
            itemCapacity = 10;
            liquidCapacity = 100f;
            consumePower(0.5f);
            hasPower = true;
            configurable = true;
            saveConfig = true;
        }};
    }
}
