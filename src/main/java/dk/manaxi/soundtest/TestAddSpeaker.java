package dk.manaxi.soundtest;

import com.google.gson.JsonObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestAddSpeaker implements CommandExecutor {
    public TestAddSpeaker() {
        Main.getInstance().getCommand("testaddspeaker").setExecutor(this::onCommand);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length != 1) {
            commandSender.sendMessage("nope");
            return true;
        }
        Player player = (Player) commandSender;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "addspeaker");
        jsonObject.addProperty("speakerId", strings[0]);
        JsonObject locationObject = new JsonObject();
        locationObject.addProperty("x", 0f);
        locationObject.addProperty("y", 100f);
        locationObject.addProperty("z", 0f);
        jsonObject.add("location", locationObject);
        jsonObject.addProperty("relative", false);
        MediaProtocol.sendLabyModMessage(player, "sound", jsonObject);
        return true;
    }
}
