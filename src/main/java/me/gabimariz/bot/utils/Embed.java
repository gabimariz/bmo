package me.gabimariz.bot.utils;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Embed {
  public static MessageEmbed error() {
    EmbedMessage embed = new EmbedMessage(
      "Acess denied",
      new Color(217, 83, 79),
      "You are not allowed to use the command!"
    );

    return embed.message().build();
  }
}
