package me.gabimariz.bot.utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class MemberMessage {
  public static boolean role(MessageReceivedEvent event, String roleId) {
    Role role = event.getGuild().getRoleById(roleId);

    return !Objects.requireNonNull(event.getMember()).getRoles().contains(role);
  }

  public static boolean permission(MessageReceivedEvent event, Permission permission) {
    return !Objects.requireNonNull(event.getMember()).hasPermission(permission);
  }
}
