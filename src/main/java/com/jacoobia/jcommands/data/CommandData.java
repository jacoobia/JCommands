package com.jacoobia.jcommands.data;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

/**
 * The command data class holding all the information about a
 * command that has been executed. Gives you all the things you
 * need to process/response etc to a command.
 *
 *  @author Jacoobia - https://github.com/jacoobia
 */
@Getter
@Setter
public class CommandData {

    private Guild guild;
    private MessageChannel channel;
    private Member member;
    private String name;
    private String[] args;

}
