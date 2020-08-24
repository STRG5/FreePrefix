package de.strg5.freeprefix.command;

import de.strg5.freeprefix.FreePrefix;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FreePrefixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 0) {
            this.help(commandSender);
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (commandSender.hasPermission("freeprefix.reload") || commandSender.isOp()) {
                    FreePrefix.getInstance().setup();
                    commandSender.sendMessage("§8[§aFreePrefix§8] §7The plugin was §asuccessfully §7reloaded");
                } else {
                    this.help(commandSender);
                }
            } else {
                this.help(commandSender);
            }
        } else {
            this.help(commandSender);
        }
        return true;
    }

    private void help(CommandSender commandSender) {
        commandSender.sendMessage("§8[§aFreePrefix§8] §7This plugin was created by §bSTRG5§7.");
    }
}
