package dk.manaxi.soundtest;

import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestPlay implements CommandExecutor {
    public TestPlay() {
        Main.getInstance().getCommand("testplay").setExecutor(this::onCommand);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "play");
        jsonObject.addProperty("id", "test");
        MediaProtocol.sendLabyModMessage(player, "sound", jsonObject);
        return true;
    }
}
