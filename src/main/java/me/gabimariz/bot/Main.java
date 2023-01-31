package me.gabimariz.bot;

import me.gabimariz.bot.commands.Live;
import me.gabimariz.bot.commands.Social;
import me.gabimariz.bot.commands.music.Pause;
import me.gabimariz.bot.commands.music.Play;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class Main {

    public static JDA api;
    private static final String TOKEN = "";
    public static void main(String[] args) {

         api = JDABuilder.createDefault(TOKEN, EnumSet.allOf(GatewayIntent.class))
                 .addEventListeners(new Social(), new Live(), new Play(), new Pause())
                 .build();

         api.upsertCommand("social", "My social networks").queue();
         api.upsertCommand("live", "live on").queue();
    }
}
