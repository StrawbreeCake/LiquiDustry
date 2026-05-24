package liquidustry;

import arc.*;
import liquidustry.content.*;
import liquidustry.graphics.*;
// import liquidustry.ui.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class LiquiDustry extends Mod{
    public LiquiDustry(){
        // Load all assets once they're added into Vars.tree
        Events.on(FileTreeInitEvent.class, e -> app.post(() -> {
            if(!headless){
                LDShaders.init();
            }
        }));
    }

    // @Override
    // public void init(){
    //     if(!headless){
    //         LDDialogs.init();
    //     }
    // }

    @Override
    public void loadContent(){
        LDItems.load();
        LDBlocks.load();
        LDTechTree.load();
    }
}
