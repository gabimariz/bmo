package me.gabimariz.bot.utils;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Embed {
  public static MessageEmbed status(String title, Color color, String description) {
    EmbedMessage embed = new EmbedMessage(
      title,
      color,
      description
    );

    return embed.message().build();
  }
}
