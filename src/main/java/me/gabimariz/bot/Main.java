package me.gabimariz.bot;

import io.github.cdimascio.dotenv.Dotenv;
import me.gabimariz.bot.commands.Live;
import me.gabimariz.bot.commands.Social;
import me.gabimariz.bot.commands.music.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class Main {
  public static final Dotenv ENV = Dotenv.load();

  public static JDA api;

  public static void main(String[] args) {

    api = JDABuilder.createDefault(ENV.get("TOKEN"), EnumSet.allOf(GatewayIntent.class))
      .addEventListeners(
        new Social(),
        new Live(),
        new Play(),
        new Pause(),
        new Skip(),
        new Stop(),
        new NowPlaying()
      )
      .build();

    api.upsertCommand("social", "My social networks").queue();
    api.upsertCommand("live", "live on").queue();
  }
}
