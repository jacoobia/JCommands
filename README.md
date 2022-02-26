<p float="left>
  <a href="https://github.com/DV8FromTheWorld/JDA">
    <img alt="Supported JDA Version" src="https://img.shields.io/badge/JDA%20Version-4.3.0__277-green" style="display: inline-block;"/>
  </a>
</p>                                                                                                                                   


# JCommands
JCommands aims to be an easy to use, quick, out of the box solution to parsing commands in the JDA. This library follows an event subscriber like pattern allowing users to use simple annotations to set up numerous commands in seconds.
This library was created for my own uses for my personal projects but I thought I would release it for others to use. I will update and maintain this but it will be at my own pace.
# Example Usage
                                                                                                                                     
JCommands is easy to set up, all you need to do is create an instance of the CommandCentre and pass in your JDA object and a command prefix.          
Once you have your CommandCentre object you simply need to register and command processor classes you make with it and voilà, you're done! 
```java
  public static main(String[] args) {
    ...
    CommandCentre commandCentre = new CommandCentre(jda, "!");
    commandCentre.registerCommands(new CommandClass());
  }

```
A command with multple sub-commands. i.e: !hello, !hello me
```java
@Command("Hello")
public class CommandClass extends CommandProcessor {

  @BaseCommand
  public void HelloWorld(CommandData data) {
    data.getChannel().sendMessage("Hello World!").queue();
  }
                                                
  @SubCommand({"me", "myself", "i"})
  public void GreetMe(CommandData data) {
    String username = data.getMember().getNickname();
    data.getChannel().sendMessage("Hello " + username + "!").queue();
  }
}

```                                
You can also place multiple commands in a single class without using subcommands!
For this you omit the class level annotaions in favour of method level `@Command` annotations.
```java
public class MultipleCommandClass extends CommandProcessor {
    
    @Command("test")
    public void test(CommandData data) {
        data.getChannel().sendMessage("The test command was called!").queue();
    }

    @Command({"foo", "bar"})
    public void foobar(CommandData data) {
        String command = data.getName();
        data.getChannel().sendMessage("The " + command + " command was called!").queue();
    }
    
}
```
