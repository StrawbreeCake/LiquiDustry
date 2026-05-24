package liquidustry.world.blocks.liquid;

import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.Vars;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.liquid.*;
import arc.scene.ui.layout.Table;
import liquidustry.content.LDItems;

public class GelationBlock extends LiquidBlock {
    public float flowRate = 120f; // Equivalent flowrate to a standard Liquid Pump
    public float gelLiquidConversion = 10f; // Amount of liquid required to produce one gelled item
    public float craftTime = gelLiquidConversion / flowRate; // Time required to produce one gelled item based on flow rate and gel-liquid conversion

    public GelationBlock (String name) {
        super(name);
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

        config(Item.class, (GelationBuilding tile, Item item) -> {
            tile.selectedOutput = item;
        });

        configClear((GelationBuilding tile) -> {
            tile.selectedOutput = null;
        });
    }

    public class GelationBuilding extends Building {
        public float progress;
        public Item selectedOutput;

        @Override
        public void buildConfiguration(Table table) {
            ItemSelection.buildTable(
                GelationBlock.this,
                table,
                LDItems.allGels, // Displays all items. You can change this to a specific Seq of your gel items!
                () -> selectedOutput,
                item -> configure(item) // Safely routes to config() for multiplayer
            );
        }

        @Override
        public void updateTile() {

            if (liquids.currentAmount() > gelLiquidConversion){ // if has enough liquid to convert to gel
                for (Liquid liquid : LDItems.gelLiquids) { // iterate through all liquids that have gelled item counterparts
                    if (liquids.get(liquid) >= gelLiquidConversion){ // if has enough liquid to convert to gel and
                        String gelName = "liquidustry-gelled-" + liquid.name;
                        Item gel = Vars.content.item(gelName);
                        if (gel != null && items.get(gel) < itemCapacity) { // if has space for more of this gel item
                            progress += edelta();
                            if (progress >= craftTime) {
                                progress = 0f;
                                liquids.remove(liquid, gelLiquidConversion);
                                items.add(gel, 1);
                                break;
                            }
                        }
                    }
                }
            }

            // Attempts to eject items
            if (selectedOutput != null && items.has(selectedOutput)) {
                dump(selectedOutput);
            }
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            return liquids.get(liquid) < liquidCapacity;
        }
    }
}