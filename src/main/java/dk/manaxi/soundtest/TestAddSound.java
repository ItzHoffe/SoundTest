package dk.manaxi.soundtest;

import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class TestAddSound implements CommandExecutor {
    public TestAddSound() {
        Main.getInstance().getCommand("testsound").setExecutor(this::onCommand);
    }
    private File[] files = null;
    private int index = 0;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        index = 0;
        File file = new File(Main.getInstance().getDataFolder(), "nvr/test.ogg");
        Player player = (Player) commandSender;
        byte[] bytes = getBytesFromFile(file);
        for (byte[] splitChunk : splitChunks(bytes, 100000)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", "addsound");
            String compressBase64 = Base64.encodeBase64String(splitChunk);
            jsonObject.addProperty("data", compressBase64);
            jsonObject.addProperty("id", file.getName());
            //player.sendMessage(jsonObject.toString());
            MediaProtocol.sendLabyModMessage(player, "sound", jsonObject);
        }
        return true;
    }

    private byte[][] splitChunks(byte[] source, int CHUNK_SIZE)
    {
        byte[][] ret = new byte[(int)Math.ceil(source.length / (double)CHUNK_SIZE)][];
        int start = 0;
        for(int i = 0; i < ret.length; i++) {
            if(start + CHUNK_SIZE > source.length) {
                ret[i] = new byte[source.length-start];
                System.arraycopy(source, start, ret[i], 0, source.length - start);
            }
            else {
                ret[i] = new byte[CHUNK_SIZE];
                System.arraycopy(source, start, ret[i], 0, CHUNK_SIZE);
            }
            start += CHUNK_SIZE ;
        }
        return ret;
    }

    private static byte[] getBytesFromFile(File file) {
        try {
            File oggFile = file;
            byte[] bytes = new byte[(int) oggFile.length()];
            FileInputStream fis = new FileInputStream(oggFile);
            fis.read(bytes);
            fis.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
