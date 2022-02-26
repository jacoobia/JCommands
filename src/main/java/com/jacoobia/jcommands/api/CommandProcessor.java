package com.jacoobia.jcommands.api;

import com.jacoobia.jcommands.api.annotations.Command;
import com.jacoobia.jcommands.api.annotations.BaseCommand;
import com.jacoobia.jcommands.api.annotations.SubCommand;
import com.jacoobia.jcommands.data.CommandData;
import com.jacoobia.jcommands.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A command processor is a class containing one or more commands,
 * once the processor is registered the commands will be available
 * for use immediately.
 * A command class should follow a few simple rules, the first
 * being it must extend this class, it must then have methods with
 * either {@link BaseCommand} or {@link SubCommand}.
 *
 * @author Jacoobia - https://github.com/jacoobia
 */
public class CommandProcessor {

    private final Map<String, Method> commands = new HashMap<>();

    /**
     * Loops through each of the command methods in this class
     * and stores the qualifier and method to a map to be
     * invoked quickly at a later date
     */
    public CommandProcessor() {
        Class<? extends CommandProcessor> clazz = getClass();
        Method[] methods = clazz.getMethods();

        if(clazz.isAnnotationPresent(Command.class)) {
            loadAnnotatedClass(methods);
        } else {
            loadNonAnnotatedClass(methods);
        }


    }

    /**
     * Fist checks if there's any arguments added to the command,
     * if no then assume that the command passed in is looking for
     * a non-annotated command processor class
     *
     * @param command the command to process
     */
    public void process(CommandData command) {
        try {
            String name = command.getName();
            if(commands.containsKey(name)) {
                commands.get(name).invoke(this, command);
            }
            if(command.getArgs().length > 0) {
                String arg = command.getArgs()[0];
                if (commands.containsKey(arg)) {
                    commands.get(arg).invoke(this, command);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the command that was executed is a relevant commands to this processor.
     * It does this first by checking the class level annotations, then if there isn't
     * one present then checks if the command map contains the command name as a key
     *
     * @param command the command to check
     * @return bool is the command a member of this class
     */
    public boolean relevantCommand(CommandData command) {
        if(command == null) return false;
        String name = command.getName();
        Class<? extends CommandProcessor> clazz = getClass();
        if(clazz.isAnnotationPresent(Command.class) && !StringUtils.isEmpty(name)) {
            Command annotation = clazz.getAnnotation(Command.class);
            String[] qualifiers = annotation.value();
            return StringUtils.stringArrayContains(qualifiers, name);
        }
        return commands.containsKey(command.getName());
    }

    /**
     * If a class is annotated with the {@link Command} annotation then the methods
     * within it require either the {@link SubCommand} or if you're writing anonymous
     * classes to hold single commands (not recommended) then the {@link BaseCommand}
     * annotation
     *
     * @param methods the methods in the class
     */
    private void loadAnnotatedClass(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(SubCommand.class)) {
                String[] qualifiers = method.getAnnotation(SubCommand.class).value();
                Arrays.stream(qualifiers).forEach(qualifier -> commands.put(qualifier, method));
            } else if (method.isAnnotationPresent(BaseCommand.class)) {
                String[] qualifiers = getClass().getAnnotation(Command.class).value();
                Arrays.stream(qualifiers).forEach(qualifier -> commands.put(qualifier, methods[0]));
            }
        }
    }

    /**
     * If a class isn't annotated with the {@link Command} annotation we instead
     * check for methods within the class body that have the annotation.
     * These then get added to the map to be later called
     *
     * @param methods the methods in the class
     */
    private void loadNonAnnotatedClass(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                String[] qualifiers = method.getAnnotation(Command.class).value();
                Arrays.stream(qualifiers).forEach(qualifier -> commands.put(qualifier, methods[0]));
            }
        }
    }

}
