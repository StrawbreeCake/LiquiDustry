package liquidustry.content;

import arc.graphics.*;
import arc.struct.Seq;
import mindustry.type.*;
import mindustry.content.*;

public class LDItems {
    public static Item
        gelledArkycite, gelledCryofluid, gelledCyanogen, gelledGallium, gelledHydrogen,
        gelledNeoplasm, gelledNitrogen, gelledOil, gelledOzone, gelledSlag, gelledWater;

    public static Seq<Item> allGels;

    public static Seq<Liquid> gelLiquids;

    public static void load(){
        gelledArkycite = new Item("gelled-arkycite", Color.valueOf("49b247")){{
            flammability = 0.0f;
            explosiveness = 0.0f;
        }};
        gelledCryofluid = new Item("gelled-cryofluid", Color.valueOf("a5c7ff")){{
            flammability = 0.0f;
            explosiveness = 0.0f;
        }};
        gelledCyanogen = new Item("gelled-cyanogen", Color.valueOf("1ce4bf")){{
            explosiveness = 0.4f;
            radioactivity = 0.0f;
            hideDetails = false;
        }};
        gelledGallium = new Item("gelled-gallium", Color.valueOf("a9a9a9")){{
            flammability = 0.0f;
            explosiveness = 0.0f;
        }};
        gelledHydrogen = new Item("gelled-hydrogen", Color.valueOf("7b8fe4")){{
            flammability = 1.0f;
            explosiveness = 1.0f;
        }};
        gelledNeoplasm = new Item("gelled-neoplasm", Color.valueOf("a02020")){{
            flammability = 0.8f;
            explosiveness = 0.0f;
        }};
        gelledNitrogen = new Item("gelled-nitrogen", Color.valueOf("eaeaea")){{
            flammability = 0.0f;
            explosiveness = 0.0f;
        }};
        gelledOil = new Item("gelled-oil", Color.valueOf("191919")){{
            flammability = 1.2f;
            explosiveness = 1.2f;
        }};
        gelledOzone = new Item("gelled-ozone", Color.valueOf("e47bba")){{
            explosiveness = 0.0f;
            radioactivity = 0.0f;
            hideDetails = false;
        }};
        gelledSlag = new Item("gelled-slag", Color.valueOf("e39322")){{
            flammability = 1.2f;
            explosiveness = 1.2f;
        }};
        gelledWater = new Item("gelled-water", Color.valueOf("d7f1f8")){{
            flammability = 0.0f;
            explosiveness = 0.0f;
        }};
        // NOW that they are loaded, put them in a native Arc array (Seq is Iterable!)
        allGels = Seq.with(
            gelledArkycite, gelledCryofluid, gelledCyanogen, gelledGallium, gelledHydrogen,
            gelledNeoplasm, gelledNitrogen, gelledOil, gelledOzone, gelledSlag, gelledWater
        );
        gelLiquids = Seq.with(
            Liquids.arkycite, Liquids.cryofluid, Liquids.cyanogen, Liquids.gallium, Liquids.hydrogen,
            Liquids.neoplasm, Liquids.nitrogen, Liquids.oil, Liquids.ozone, Liquids.slag, Liquids.water
        );
    }
}
