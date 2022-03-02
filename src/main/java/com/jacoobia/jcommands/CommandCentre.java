package com.jacoobia.jcommands;

import com.jacoobia.jcommands.api.CommandProcessor;
import com.jacoobia.jcommands.data.CommandData;
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

    /**
     * The command parser in charge of parsing user input within discord
     * into usable {@link CommandData} to be used in the command class.
     */
    private final CommandParser commandParser = new CommandParser();

    /**
     * The command prefix as defined by the library user
     */
    public static String prefix;

    /**
     * The entry point to the JCommands library
     * @param jda a {@link JDA} object reference
     * @param prefix a desired command prefix
     */
    public CommandCentre(JDA jda, String prefix) {
        CommandCentre.prefix = prefix;
        jda.addEventListener(commandParser);
    }

    /**
     * Registers a command class with the command centre
     * @param processor the processor to add
     */
    public void registerCommands(CommandProcessor processor) {
        commandParser.registerCommands(processor);
    }

    /**
     * Deregisters a command class from the command centre
     * @param processor The processor to remove
     */
    public void deregisterCommands(CommandProcessor processor) {
        commandParser.deregisterCommands(processor);
    }

}
