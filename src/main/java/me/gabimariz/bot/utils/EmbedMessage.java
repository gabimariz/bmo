package me.gabimariz.bot.utils;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

@AllArgsConstructor
public class EmbedMessage {
  private String title;
  private Color color;
  private String description;
  private String image;
  private String thumbnail;

  public EmbedMessage(String title, Color color, String description) {
    this.title = title;
    this.color = color;
    this.description = description;
  }

  public EmbedBuilder message() {
    EmbedBuilder embed = new EmbedBuilder();

    embed.setTitle(this.title);
    embed.setColor(this.color);
    embed.setDescription(this.description);

    return embed;
  }

  public EmbedBuilder thumbnail() {
    EmbedBuilder embed = new EmbedBuilder();

    embed.setTitle(this.title);
    embed.setColor(this.color);
    embed.setDescription(this.description);
    embed.setThumbnail(this.thumbnail);

    return embed;
  }

  public EmbedBuilder image() {
    EmbedBuilder embed = new EmbedBuilder();

    embed.setTitle(this.title);
    embed.setColor(this.color);
    embed.setDescription(this.description);
    embed.setImage(this.image);

    return embed;
  }

}
