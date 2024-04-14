package me.cablemp5.classbattle;

import me.cablemp5.classbattle.Commands.ArenaCommand;
import me.cablemp5.classbattle.Commands.ClassSelectCommand;
import me.cablemp5.classbattle.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClassBattle extends JavaPlugin {

    public static final String PLUGIN_NAME = (ChatColor.LIGHT_PURPLE + "[ClassBattle] " + ChatColor.RESET);


    private ClassSelectCommand classSelectCommand;

    private ArenaCommand arenaCommand;

    private ClassSelectListener classSelectListener;

    private ActivateAbilityListener activateAbilityListener;

    private TargetListener targetListener;

    private TrapListener trapListener;

    private BlockListener blockListener;

    private DamageListener damageListener;

    private MoveListener moveListener;

    private SpawnListener spawnListener;

    private DeathListener deathListener;

    @Override
    public void onEnable() {
        // Plugin startup logic
        //gitghub test

        this.classSelectCommand = new ClassSelectCommand(this);
        this.arenaCommand = new ArenaCommand(this);
        this.classSelectListener = new ClassSelectListener(this);
        this.activateAbilityListener = new ActivateAbilityListener(this);
        this.targetListener = new TargetListener(this);
        this.trapListener = new TrapListener(this);
        this.blockListener = new BlockListener(this);
        this.damageListener = new DamageListener(this);
        this.moveListener = new MoveListener(this);
        this.spawnListener = new SpawnListener(this);
        this.deathListener = new DeathListener(this);


        Bukkit.getLogger().info("Plugin enabled!");

        getCommand("select").setExecutor(classSelectCommand);
        getCommand("arena").setExecutor(arenaCommand);


        getServer().getPluginManager().registerEvents(classSelectListener, this);
        getServer().getPluginManager().registerEvents(activateAbilityListener, this);
        getServer().getPluginManager().registerEvents(targetListener, this);
        getServer().getPluginManager().registerEvents(trapListener, this);
        getServer().getPluginManager().registerEvents(blockListener, this);
        getServer().getPluginManager().registerEvents(damageListener, this);
        getServer().getPluginManager().registerEvents(moveListener, this);
        getServer().getPluginManager().registerEvents(spawnListener,this);
        getServer().getPluginManager().registerEvents(deathListener,this);





    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ClassSelectCommand getClassSelectCommand() {
        return classSelectCommand;
    }

    public ClassSelectListener getClassSelectListener() {
        return classSelectListener;
    }

    public ActivateAbilityListener getActivateAbilityListener() {
        return activateAbilityListener;
    }

    public TargetListener getTargetListener() {
        return targetListener;
    }

    public ArenaCommand getArenaCommand() {
        return arenaCommand;
    }

    public TrapListener getTrapListener() {
        return trapListener;
    }

    public BlockListener getBlockListener() {
        return blockListener;
    }

    public DamageListener getDamageListener() {
        return damageListener;
    }

    public MoveListener getMoveListener() {
        return moveListener;
    }

    public SpawnListener getSpawnListener() {
        return spawnListener;


    }

    public DeathListener getDeathListener() {
        return deathListener;
    }
}
