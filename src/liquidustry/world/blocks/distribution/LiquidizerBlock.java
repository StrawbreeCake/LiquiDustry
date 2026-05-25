package liquidustry.world.blocks.distribution;

import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.Vars;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.liquid.*;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.layout.Table;
import arc.util.io.Reads;
import arc.util.io.Writes;
import liquidustry.content.LDItems;

public class LiquidizerBlock extends LiquidBlock {
    public TextureRegion bottomRegion;
    public float flowRate = 120f; // Equivalent flowrate to a standard Liquid Pump
    public float gelLiquidConversion = 10f; // Amount of liquid required to produce one gelled item
    public float craftTime = gelLiquidConversion / flowRate; // Time required to produce one gelled item based on flow rate and gel-liquid conversion

    private static Liquid resolveBaseLiquid(String gelName) {
        if (gelName == null) return null;
        String[] parts = gelName.split("-");
        String liquidName = parts[parts.length - 1];
        return Vars.content.liquid(liquidName);
    }

    public LiquidizerBlock(String name) {
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

        config(Liquid.class, (LiquidizerBuilding tile, Liquid liquid) -> {
            tile.selectedOutput = liquid;
        });

        configClear((LiquidizerBuilding tile) -> {
            tile.selectedOutput = null;
        });
    }

    @Override
    public void load() {
        super.load();
        bottomRegion = Core.atlas.find(name + "-bottom");
    }

    public class LiquidizerBuilding extends Building {
        public float progress;
        public Liquid selectedOutput;

        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(progress);
            write.s(selectedOutput == null ? -1 : selectedOutput.id); // if no liquid is selected, save -1. Otherwise, save the liquid's ID.
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            progress = read.f();
            selectedOutput = Vars.content.liquid(read.s()); // Read the Liquid ID and restore the Liquid from the registry.
        }

        @Override
        public void buildConfiguration(Table table) {
            ItemSelection.buildTable(
                LiquidizerBlock.this,
                table,
                LDItems.gelLiquids, // Displays all items. You can change this to a specific Seq of your gel items!
                () -> selectedOutput,
                liquid -> configure(liquid) // Safely routes to config() for multiplayer
            );
        }

        @Override
        public void draw() {
            if (selectedOutput != null) {
                Draw.color(selectedOutput.color);
            } else {
                Draw.color(Color.valueOf("2f2d39"));
            }
            Draw.rect(bottomRegion, x, y);
            Draw.color();
            super.draw();
        }

        @Override
        public void updateTile() {

            if (items.total() > 0) { // if has any items, check for items and convert them to liquids
                for (Item item : LDItems.allGels) { // cycle through all gel items
                    if (items.has(item, 1)) { // if has at least 1 of the gel item
                        Liquid baseLiquid = resolveBaseLiquid(item.name); // get the corresponding base liquid for the gel item
                        if (baseLiquid != null && liquids.get(baseLiquid) < liquidCapacity) { // make liquid check null-safe and check for liquid capacity
                            progress += edelta();
                            if (progress >= craftTime) {
                                progress = 0f;
                                items.remove(item, 1);
                                liquids.add(baseLiquid, gelLiquidConversion);
                                break; // exit loop
                            }
                        }
                    }
                }
            }

            // Attempts to eject liquids
            if (selectedOutput != null && liquids.get(selectedOutput) > 0) {
                dumpLiquid(selectedOutput);
            }
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            for (Item gelItem : LDItems.allGels) { // accept any gel items
                if (item == gelItem && items.get(gelItem) < getMaximumAccepted(gelItem)) {
                    return true;
                }
            }
            return false;
        }
    }
}