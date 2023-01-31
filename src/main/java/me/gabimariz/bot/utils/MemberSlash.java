package me.gabimariz.bot.utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

public class MemberSlash {

  public static boolean memberRole(SlashCommandInteractionEvent event, String roleId) {
    Role role = Objects.requireNonNull(event.getGuild()).getRoleById(roleId);

    return Objects.requireNonNull(event.getMember()).getRoles().contains(role);
  }

  public static boolean memberPermission(SlashCommandInteractionEvent event, Permission permission) {
    return (Objects.requireNonNull(event.getMember()).hasPermission(permission));
  }
}
