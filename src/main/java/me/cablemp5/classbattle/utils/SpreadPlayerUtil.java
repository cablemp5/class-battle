package me.cablemp5.classbattle.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.block.BlockCanBuildEvent;

import java.util.Random;

public class SpreadPlayerUtil {

    public static final Random random = new Random();

    public static Location generateLocationWithinRadius(Location location, int diameter) {

        int diameterShrunk = (int) (diameter * 0.95);

        double x = (random.nextInt(diameterShrunk)-(diameterShrunk/2)) + location.getX();
        double z = (random.nextInt(diameterShrunk)-(diameterShrunk/2)) + location.getZ();
        double y = location.getWorld().getHighestBlockYAt((int)x,(int)z);

        return new Location(location.getWorld(), x,y+1,z);

    }

}
