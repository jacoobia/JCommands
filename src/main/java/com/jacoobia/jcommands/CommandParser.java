package com.jacoobia.jcommands;

import com.jacoobia.jcommands.api.CommandProcessor;
import com.jacoobia.jcommands.data.CommandData;
import com.jacoobia.jcommands.util.StringUtils;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A JDA event hook to listen for guild messages and try to parse them into
 * a command and send that command across to the relevant command class.
 *
 *  @author Jacoobia - https://github.com/jacoobia
 */
public class CommandParser extends ListenerAdapter {

    private static final List<CommandProcessor> commandProcessors = new ArrayList<>();
    private static final int MAX_ARG_COUNT = 20;

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
            if(event.getMessage().getContentRaw().startsWith(CommandCentre.prefix)) {
            CommandData command = parseCommand(event);
            for (CommandProcessor processor : commandProcessors) {
                if (processor.relevantCommand(command)) {
                    processor.process(command);
                }
            }
        }
    }

    /**
     * Registers a command processor
     * @param processor the processor to register
     */
    public void registerCommands(CommandProcessor processor) {
        if(!commandProcessors.contains(processor))
            commandProcessors.add(processor);
    }

    /**
     * Deregisters a command processor
     * @param processor the processor to deregister
     */
    public void deregisterCommands(CommandProcessor processor) {
        if(!commandProcessors.contains(processor))
            commandProcessors.remove(processor);
    }

    /**
     * Extracts data from a message sent in a discord text channel and then
     * creates a {@link CommandData} object for that message
     *
     * @param event the message event
     * @return a newly constructed command object
     */
    public CommandData parseCommand(GuildMessageReceivedEvent event) {
        Message message = event.getMessage();
        CommandData command = new CommandData();
        String commandName = splitMessage(message)[0].replace("!", StringUtils.BLANK);
        command.setName(commandName);
        command.setGuild(event.getGuild());
        command.setArgs(getArgs(message));
        command.setChannel(event.getChannel());
        command.setMember(event.getMember());
        return command;
    }

    /**
     * Gets the arguments from a !command message sent
     *
     * @param message the message to extract the arguments from
     * @return a string array of the arguments
     */
    private String[] getArgs(Message message) {
        String[] split = splitMessage(message);
        String[] args = new String[Math.min(split.length - 1, MAX_ARG_COUNT)];
        for(int i = 1; i < split.length; i++) {
            int argsIndex = i - 1;
            if(argsIndex < MAX_ARG_COUNT)
                args[argsIndex] = split[i];
        }
        return args;
    }

    /**
     * Splits a message into individual strings
     *
     * @param message the message to split
     * @return a string array of the parts
     */
    public String[] splitMessage(Message message) {
        return message.getContentRaw().split(" ");
    }

}
