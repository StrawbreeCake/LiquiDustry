package liquidustry.content;

// import arc.graphics.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;
import static mindustry.type.ItemStack.*;
import liquidustry.world.blocks.liquid.*;

public class LDBlocks{
    public static Block

    // region liquid - Erekir

    gelationStation, liquidizer, microLiquidizer;

    // endregion

    public static void load(){

        gelationStation = new GelationBlock("gelation-station"){{
            requirements(Category.liquid, with(
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
        }};

        liquidizer = new LiquidizerBlock("liquidizer"){{
            requirements(Category.liquid, with(
                Items.graphite, 60,
                Items.silicon, 60
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
        }};

        microLiquidizer = new LiquidizerBlock("micro-liquidizer"){{
            requirements(Category.liquid, with(
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
        }};
    }
}
