package me.gabimariz.bot.utils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class Verify {

  public static <T> T nonNull(T value) {
    return Objects.requireNonNull(value);
  }

  public static String[] command(MessageReceivedEvent event) {
    return event.getMessage().getContentRaw().split(" ");
  }

  public static boolean isBot(MessageReceivedEvent event) {
    return event.getAuthor().isBot();
  }
}
