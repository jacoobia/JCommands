package com.jacoobia.jcommands;

import com.jacoobia.jcommands.api.CommandProcessor;
import net.dv8tion.jda.api.JDA;

/**
 * The command center is an application to make annotation based
 * commands similar to annotation based even systems works.
 * It's designed to be plug-and-play and be very quick to go from
 * installing to having working commands.
 *
 *  @author Jacoobia - https://github.com/jacoobia
 */
public class CommandCentre {

    private final CommandParser commandParser = new CommandParser();

    public static String prefix;

    public CommandCentre(JDA jda, String prefix) {
        CommandCentre.prefix = prefix;
        jda.addEventListener(commandParser);
    }

    public void registerCommands(CommandProcessor processor) {
        commandParser.registerCommands(processor);
    }

    public void deregisterCommands(CommandProcessor processor) {
        commandParser.deregisterCommands(processor);
    }

}
