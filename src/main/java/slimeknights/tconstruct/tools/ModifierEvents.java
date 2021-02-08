package slimeknights.tconstruct.tools;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

/**
 * Event subscriber for modifier events
 * Note the way the subscribers are set up, technically works on anything that has the tic_modifiers tag
 */
@SuppressWarnings("unused")
@EventBusSubscriber(modid = TConstruct.modID, bus = Bus.FORGE)
public class ModifierEvents {

  @SubscribeEvent
  static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
    ToolStack tool = ToolStack.from(event.getPlayer().getHeldItemMainhand());
    if (!tool.isBroken()) {
      for (ModifierEntry entry : tool.getModifierList()) {
        entry.getModifier().onBreakSpeed(tool, entry.getLevel(), event);
      }
    }
  }

  @SubscribeEvent
  static void onBlockBreak(BlockEvent.BreakEvent event) {
    ToolStack tool = ToolStack.from(event.getPlayer().getHeldItemMainhand());
    if (!tool.isBroken()) {
      for (ModifierEntry entry : tool.getModifierList()) {
        entry.getModifier().beforeBlockBreak(tool, entry.getLevel(), event);
      }
    }
  }
}