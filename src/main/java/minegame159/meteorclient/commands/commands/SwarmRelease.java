package minegame159.meteorclient.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.commands.Command;
import minegame159.meteorclient.modules.ModuleManager;
import minegame159.meteorclient.modules.combat.Swarm;
import net.minecraft.command.CommandSource;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class SwarmRelease extends Command {

    public SwarmRelease() {
        super("s", "(highlight)release(default) - Release your bots.");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(literal("release").executes(context -> {
                    Swarm swarm = ModuleManager.INSTANCE.get(Swarm.class);
                    if (swarm.isActive()) {
                        if (swarm.currentMode.get() == Swarm.Mode.QUEEN && swarm.server != null) {
                            swarm.server.sendMessage("s stop");
                            swarm.server.closeAllClients();
                        }
                    }
                    return SINGLE_SUCCESS;
                })
        );
    }
}
